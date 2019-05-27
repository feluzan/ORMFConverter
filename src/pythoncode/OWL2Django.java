package pythoncode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
//import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ORM.ClassMapping;
import ORM.InheritanceMapping;
import ORM.RelationshipMapping;
import genericcode.GenericClass;
import genericcode.GenericVariable;
import genericcode.Inheritance;
import genericcode.PrimitiveType;
import genericcode.RelationshipAssociationTable;
import genericcode.Table;
import genericcode.Type;
import genericcode.ValueType;

public class OWL2Django {
	
	private Map<String,GenericClass> classes = new HashMap<String,GenericClass>();
	
	private Map<String, Table> tables = new HashMap<String, Table>();
	private Map<String, RelationshipAssociationTable> relationshipAssociationTables = new HashMap<String, RelationshipAssociationTable>();
	
	
	private Map<String, ClassMapping> classMappings = new HashMap<String,ClassMapping>();
	private Map<String, InheritanceMapping> inheritanceMappings = new HashMap<String,InheritanceMapping>();
	private Map<String, RelationshipMapping> relationshipMappings = new HashMap<String,RelationshipMapping>();
	private Map<String, ValueType> valueTypes = new HashMap<String, ValueType>();
	private Map<String, GenericVariable> variables = new HashMap<String,GenericVariable>();
	
	private Map<String, PrimitiveType> primitiveTypes = new HashMap<String,PrimitiveType>();
	private Map<String, Inheritance> inheritances = new HashMap<String, Inheritance>();
	
	private Map<GenericClass, InheritanceMapping> subclassesInheritanceMapping = new HashMap<GenericClass, InheritanceMapping>();
	
	public OWL2Django(File file) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();
			
			//Get the OWL nodes (elements)
			NodeList nodeList = doc.getElementsByTagName("ClassAssertion");
			
			processClassNodes(nodeList);
			
			NodeList propertiesList = doc.getElementsByTagName("ObjectPropertyAssertion");
			processPropertiesNodes(propertiesList);
			
			processInheritance();
			processTables();
			
		 
		
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	public void printFile(File djangoFile) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(djangoFile);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			String importString = "from django.db import models\n\n";
			printWriter.print(importString);
			
			
			//imprimir todas as classes que não são subclasses
			for(GenericClass c : classes.values()) {
				if(c.isSubclass()) continue;
				printWriter.print(c.toString());
				
			}
			
			for(GenericClass c : classes.values()) {
				if(!c.isSubclass()) continue;
				printWriter.print(c.toString());
				
			}
			
			printWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public Type getType(String iri) {
		if(classes.containsKey(iri)) return classes.get(iri);
		if(primitiveTypes.containsKey(iri)) return primitiveTypes.get(iri);
		System.out.println("[ERROR] Type não encontrado: " + iri);
		return null;
	}
	
	public void processInheritance() {
		for(Inheritance inh : inheritances.values()) {
			GenericClass superclass = inh.getSuperclass();
			GenericClass subclass = inh.getSubclass();
			
			subclass.setSuperclass(superclass);
			superclass.setIsSuperclass(true);
			
		}
	}
	
	public void processTables() {
		for(ClassMapping cm : classMappings.values()) {
			GenericClass c = cm.getClazz();
			Table t = cm.getTable();
			t.addClass(c);
			c.setTable(t);
		}
	}
	
	public void processClassNodes(NodeList nodeList) {
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				
				Element e = (Element) n;
				Element clazz = (Element) e.getElementsByTagName("Class").item(0);
				String class_iri = clazz.getAttribute("IRI").replace("#", "");
				
				Element namedIndividual = (Element) e.getElementsByTagName("NamedIndividual").item(0);
				String iri = namedIndividual.getAttribute("IRI").replace("#", "");
				
				if(class_iri.equals("OOC-O::Primitive_Type")) {
					PrimitiveType pType = new PrimitiveType(class_iri, iri);
					primitiveTypes.put(iri,pType);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Entity_Class")) {
					DjangoClass c = new DjangoClass(class_iri, iri);
					classes.put(iri,c);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Entity_Superclass")) {
					GenericClass c = classes.get(iri);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Entity_Subclass")) {
					continue;
				}
						
				
				if(class_iri.equals("ORMF-O::Entity_Table")) {
					Table t = new Table(class_iri, iri);
					t.setType("entity_table");
					tables.put(iri,t);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Single_Entity_Table")) {
					Table t = new Table(class_iri, iri);
					t.setType("single_entity_table");
					tables.put(iri,t);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Multiple_Entities_Table")) {
					Table t = new Table(class_iri, iri);
					t.setType("multiple_entities_table");
					tables.put(iri,t);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Relationship_Association_Table")) {
					RelationshipAssociationTable rat = new RelationshipAssociationTable(class_iri, iri);
//					t.setType("relationship_association_table");
					relationshipAssociationTables.put(iri,rat);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Class_Mapping")) {
					ClassMapping cm = new ClassMapping(class_iri,iri);
					classMappings.put(iri, cm);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Table_per_Concrete_Class_Inheritance_Mapping") |
						class_iri.equals("ORMF-O::Table_per_Class_Inheritance_Mapping") |
						class_iri.equals("ORMF-O::Single_Table_Inheritance_Mapping")) {
					InheritanceMapping im = new InheritanceMapping(class_iri, iri);
					inheritanceMappings.put(iri, im);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::One_To_One_Relationship_Mapping")) {
					RelationshipMapping rm = new RelationshipMapping(class_iri, iri);
					rm.setType("o2o");
					relationshipMappings.put(iri, rm);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Many_To_One_Relationship_Mapping")) {
					RelationshipMapping rm = new RelationshipMapping(class_iri, iri);
					rm.setType("m2o");
					relationshipMappings.put(iri, rm);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Many_To_Many_Relationship_Mapping")) {
					RelationshipMapping rm = new RelationshipMapping(class_iri, iri);
					rm.setType("m2m");
					relationshipMappings.put(iri, rm);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::One_To_Many_Relationship_Mapping")) {
					RelationshipMapping rm = new RelationshipMapping(class_iri, iri);
					relationshipMappings.put(iri, rm);
					rm.setType("o2m");
					continue;
				}
				
				if(class_iri.equals("OOC-O::Value_Type")) {
					ValueType v = new ValueType(class_iri, iri);
					valueTypes.put(iri, v);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Mapped_Variable")) {
					DjangoVariable v = new DjangoVariable(class_iri, iri);
					v.setIsMapped(true);
					variables.put(iri, v);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Mapped_Primary_Key")) {
					DjangoVariable v = new DjangoVariable(class_iri, iri);
					v.setIsPK(true);
					v.setIsMapped(true);
					variables.put(iri, v);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Mapped_Foreign_Key")) {
					DjangoVariable v = new DjangoVariable(class_iri, iri);
					v.setIsFK(true);
					v.setIsMapped(true);
					variables.put(iri, v);
					continue;
				}
				
				if(class_iri.equals("OOC-O::Instance_Variable")) {
					DjangoVariable v = new DjangoVariable(class_iri, iri);
					v.setIsFK(true);
					v.setIsMapped(false);
					variables.put(iri, v);
					continue;
				}
				
				if(class_iri.equals("OOC-O::Inheritance")) {
					Inheritance inh = new Inheritance(class_iri, iri);
					inheritances.put(iri, inh);
					continue;
				}
				
				System.out.println("[!!ALERT!!] Classe OWL não convertida: " + class_iri + "\t(" + iri + ")");
				
			}
		}
		
	}
	
	public void processPropertiesNodes(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				
				Element e = (Element) n;
				Element property = (Element) e.getElementsByTagName("ObjectProperty").item(0);
				String property_iri = property.getAttribute("IRI").replace("#", "");
				Element domain = (Element) e.getElementsByTagName("NamedIndividual").item(0);
				Element range = (Element) e.getElementsByTagName("NamedIndividual").item(1);
				String domain_iri = domain.getAttribute("IRI").replace("#", "");
				String range_iri = range.getAttribute("IRI").replace("#", "");
				
				if(property_iri.equals("entity_class_mapped_by")){
					GenericClass c = classes.get(domain_iri);
					ClassMapping cm = classMappings.get(range_iri);
					cm.setClazz(c);
					continue;
				}
				
				if(property_iri.equals("entity_class_mapped_to")){
					ClassMapping cm = classMappings.get(domain_iri);
					Table t = tables.get(range_iri);
					cm.setTable(t);
					continue;
				}
				
				if(property_iri.equals("superclass_mapped_by")){
					GenericClass c = classes.get(domain_iri);
					InheritanceMapping im = inheritanceMappings.get(range_iri);
					im.setSuperclass(c);
					c.setInheritanceStragegy(im.getInheritanceStrategy());
					continue;
				}
				
				if(property_iri.equals("subclass_mapped_by")){
					GenericClass c = classes.get(domain_iri);
					InheritanceMapping im = inheritanceMappings.get(range_iri);
					im.setSubclass(c);
					c.setInheritanceStragegy(im.getInheritanceStrategy());
					c.setInheritanceMapping(im);
//					subclassesInheritanceMapping.put(c, im);
					continue;
				}
				
//				if(property_iri.equals("relationship_source_mapped_by")){
//					GenericClass c = classes.get(domain_iri);
//					RelationshipMapping rm = relationshipMappings.get(range_iri);
//					rm.setSource(c);
//					continue;
//				}
				
//				if(property_iri.equals("relationship_target_mapped_by")){
//					GenericClass c = classes.get(domain_iri);
//					RelationshipMapping rm = relationshipMappings.get(range_iri);
//					rm.setTarget(c);
//					continue;
//				}
				
				if(property_iri.equals("relationship_reverse_of")){
					RelationshipMapping rm1 = relationshipMappings.get(domain_iri);
					RelationshipMapping rm2 = relationshipMappings.get(range_iri);
					rm1.setReverse(rm2);
					continue;
				}
				
				if(property_iri.equals("is_type_of")){
					GenericVariable v = variables.get(domain_iri);
					ValueType vType = valueTypes.get(range_iri);
					v.setValueType(vType);
					vType.setVariable(v);
					continue;
				}
				
				if(property_iri.equals("refers_to")){
					ValueType v = valueTypes.get(domain_iri);
					Type type = getType(range_iri);
					v.setType(type);
					continue;
				}
				
				
				if(property_iri.equals("member_belongs_to")){
					GenericVariable v = variables.get(domain_iri);
					GenericClass c = classes.get(range_iri);
					c.addVariable(v);
					v.setClazz(c);
					continue;
				}
				
				if(property_iri.equals("inherits_from")){
					GenericClass c = classes.get(domain_iri);
					Inheritance inh = inheritances.get(range_iri);
					
					inh.setSubclass(c);
					continue;
				}
				
				if(property_iri.equals("inherited_from")){
					GenericClass c = classes.get(domain_iri);
					Inheritance inh = inheritances.get(range_iri);
					inh.setSuperclass(c);
					continue;
				}
				
				if(property_iri.equals("many_to_many_association_mapped_to") |
						property_iri.equals("one_to_many_association_mapped_to") ){
					RelationshipMapping rm = relationshipMappings.get(domain_iri);
					RelationshipAssociationTable rat = relationshipAssociationTables.get(range_iri);
					
					rat.setRelationshipMapping(rm);
					rm.setRelationshipAssociationTable(rat);
					continue;
				}
				
				if(property_iri.equals("represents_relationship")){
					GenericVariable v = variables.get(domain_iri);
					RelationshipMapping rm = relationshipMappings.get(range_iri);
					
					rm.setVariable(v);
					v.setRelationshipMapping(rm);
					continue;
				}
				
				
				System.out.println("[!!ALERT!!] Propriedade OWL não convertida: " + property_iri);
			}
		}
	}
}
