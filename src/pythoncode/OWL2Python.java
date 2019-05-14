package pythoncode;

import java.io.File;
import java.io.IOException;
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
import genericcode.Table;

public class OWL2Python {
	
	private Map<String,GenericClass> classes = new HashMap<String,GenericClass>();
	private Map<String, Table> tables = new HashMap<String, Table>();
	private Map<String, ClassMapping> classMappings = new HashMap<String,ClassMapping>();
	private Map<String, InheritanceMapping> inheritanceMappings = new HashMap<String,InheritanceMapping>();
	private Map<String, RelationshipMapping> relationshipMappings = new HashMap<String,RelationshipMapping>();

	public OWL2Python(File file) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();
			
			//Get the OWL nodes (elements)
			NodeList nodeList = doc.getElementsByTagName("ClassAssertion");
			
			processNodes(nodeList);
		 
		
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void processNodes(NodeList nodeList) {
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				
				Element e = (Element) n;
				Element clazz = (Element) e.getElementsByTagName("Class").item(0);
				String class_iri = clazz.getAttribute("IRI").replace("#", "");
				
				if(class_iri.equals("ORMF-O::Entity_Class")) {
					Element namedIndividual = (Element) e.getElementsByTagName("NamedIndividual").item(0);
					String codeName = namedIndividual.getAttribute("IRI").replace("#entity_class__", "");
					
					PythonClass c = new PythonClass(codeName);
					classes.put(codeName,c);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Entity_Table")) {
					Element namedIndividual = (Element) e.getElementsByTagName("NamedIndividual").item(0);
					String codeName = namedIndividual.getAttribute("IRI").replace("#table__", "");
					
					Table t = new Table(codeName);
					tables.put(codeName,t);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Class_Mapping")) {
					Element namedIndividual = (Element) e.getElementsByTagName("NamedIndividual").item(0);
					String iri = namedIndividual.getAttribute("IRI").replace("#", "");
					
					ClassMapping cm = new ClassMapping(class_iri,iri);
					classMappings.put(iri, cm);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::Table_per_Concrete_Class_Inheritance_Mapping") |
						class_iri.equals("ORMF-O::Table_per_Class_Inheritance_Mapping") |
						class_iri.equals("ORMF-O::Single_Table_Inheritance_Mapping")) {
					Element namedIndividual = (Element) e.getElementsByTagName("NamedIndividual").item(0);
					String iri = namedIndividual.getAttribute("IRI").replace("#", "");
//					System.out.println(iri);
					
					InheritanceMapping im = new InheritanceMapping(class_iri, iri);

					inheritanceMappings.put(iri, im);
					continue;
				}
				
				if(class_iri.equals("ORMF-O::One_To_One_Relationship_Mapping") |
						class_iri.equals("ORMF-O::One_To_Many_Relationship_Mapping") |
						class_iri.equals("ORMF-O::Many_To_One_Relationship_Mapping") |
						class_iri.equals("ORMF-O::Many_To_Many_Relationship_Mapping")) {
					Element namedIndividual = (Element) e.getElementsByTagName("NamedIndividual").item(0);
					String iri = namedIndividual.getAttribute("IRI").replace("#", "");

					RelationshipMapping rm = new RelationshipMapping(class_iri, iri);

					relationshipMappings.put(iri, rm);
					continue;
				}
				
				System.out.println("Class: " + class_iri);
				
				
				
				
				
				
			}
		}
		
	}
	
	
}
