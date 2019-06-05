package javacode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;

import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;

import ORM.ClassMapping;
import ORM.InheritanceMapping;
import ORM.RelationshipMapping;
import ORM.VariableMapping;
import database.Column;
import database.Table;
import database.TableType;
import genericcode.GenericClass;
import genericcode.GenericVariable;
import genericcode.PrimitiveType;
import genericcode.Type;
import genericcode.ValueType;

public class Java2OWL {
	OWLOntology ormfo;
	OWLOntologyManager manager;
	OWLDataFactory factory;

	public static Map<String, GenericClass> classes = new HashMap<String, GenericClass>();
	public static Map<String, PrimitiveType> primitiveTypes = new HashMap<String, PrimitiveType>();
	public static Map<GenericClass, InheritanceMapping> inheritanceMappings = new HashMap<GenericClass, InheritanceMapping>();

	public static Map<GenericClass, ClassMapping> classMappings = new HashMap<GenericClass, ClassMapping>();
	
	public static Map<GenericClass, Table> tables = new HashMap<GenericClass, Table>();
	
	public static Map<String, GenericVariable> variables = new HashMap<String, GenericVariable>();
	public static Map<GenericVariable, VariableMapping> variableMappings = new HashMap<GenericVariable, VariableMapping>();
	public static Map<GenericVariable, RelationshipMapping> relationshipMappings = new HashMap<GenericVariable, RelationshipMapping>();
	
	public Java2OWL(File folder, OWLOntology ormfo) {
		this.ormfo = ormfo;
		this.manager = ormfo.getOWLOntologyManager();
		this.factory = this.manager.getOWLDataFactory();
		this.processPrimitiveTypes();
		
		CompilationUnit compilationUnit = null;
		for (File f : folder.listFiles()) {
			if(!f.isFile()) continue;
			try {
				System.out.println("[INFO] Iniciando leitura do arquivo " + f.getName());
				compilationUnit = JavaParser.parse(f);
			} catch (FileNotFoundException e) {
				System.out.println("[ERROR] Arquivo " + f.getName() + " não encontrado.");
				continue;
			}
			List<Node> nodeList = compilationUnit.getChildNodes();

			for (Node n : nodeList) {
				if(n instanceof ClassOrInterfaceDeclaration) {
					processClassNode(n);
				}
			}
		}
		
//		processTypes();
//		
		processInheritance();
		processClassMappings();
		processTables();
		
		processFields();
		processVariableMappings();
		processColumns();
		
		processRelationships();

//		//---------------------
//		processColumns();
//		processVariableMappings();
//		//---------------------
//		processRelationships();
//		//---------------------
//		checkRelationshipReverses();
//		//---------------------
//		processRelationshipAssociationTables();
		
	}
	
	private void processClassNode(Node node) {
		String className = ((NodeWithSimpleName<ClassOrInterfaceDeclaration>) node).getNameAsString();
		JavaClass jc = new JavaClass(this.ormfo, (ClassOrInterfaceDeclaration) node);
		classes.put(className, jc);
		
	}
	
	public void printFile(String filePath) {
		File outfile = new File("testoutput.owl");
		try {
			this.manager.saveOntology(this.ormfo, new OWLXMLDocumentFormat(),new FileOutputStream(outfile));
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void processPrimitiveTypes() {
		primitiveTypes.put("boolean",new PrimitiveType(this.ormfo,"primitive_type__boolean"));
		primitiveTypes.put("byte",new PrimitiveType(this.ormfo,"primitive_type__byte"));
		primitiveTypes.put("char",new PrimitiveType(this.ormfo,"primitive_type__char"));
		primitiveTypes.put("double",new PrimitiveType(this.ormfo,"primitive_type__double"));
		primitiveTypes.put("float",new PrimitiveType(this.ormfo,"primitive_type__float"));
		primitiveTypes.put("int",new PrimitiveType(this.ormfo,"primitive_type__int"));
		primitiveTypes.put("long",new PrimitiveType(this.ormfo,"primitive_type__long"));
		primitiveTypes.put("Long",new PrimitiveType(this.ormfo,"primitive_type__Long"));
		primitiveTypes.put("short",new PrimitiveType(this.ormfo,"primitive_type__short"));
		primitiveTypes.put("String",new PrimitiveType(this.ormfo,"primitive_type__String"));
	}

	private void processInheritance() {
		for(GenericClass jc : classes.values()) {
			String superclassName = ((JavaClass)jc).getSuperclassName();
			if(superclassName!=null) {
				GenericClass superclass = classes.get(superclassName);
				jc.setSuperclass(superclass);
				superclass.addSubclass(jc);
				
				InheritanceMapping im = new InheritanceMapping(this.ormfo, jc);
				inheritanceMappings.put(jc, im);
			}
		}
	}
	
	private void processClassMappings() {
		
		for(GenericClass gc : classes.values()) {
			if(gc.isEntity()) {
				if(!gc.is_abstract()) {
					ClassMapping cm = new ClassMapping(this.ormfo,gc);
					classMappings.put(gc, cm);
				}
				
			}
		}
	}
	
	private void processSubclassTable(GenericClass gc) {
		InheritanceMapping im = inheritanceMappings.get(gc);
		Table t;
		GenericClass superclass;
		switch(im.getInheritanceStrategy()) {
		case SINGLE_TABLE:
			if(gc.is_abstract() || !gc.isEntity()) break;
			GenericClass rootclass = gc.getSuperclass();
			while(rootclass.isSubclass()) {
				rootclass = rootclass.getSuperclass();
			}
			t = tables.get(rootclass);
			im.addTable(t);
			gc.getClassMapping().addTable(t);
			break;
			
		case TABLE_PER_CLASS:
			t = new Table(this.ormfo, gc, TableType.SINGLE_ENTITY_TABLE);
			tables.put(gc, t);
			if(gc.is_abstract()) break;
			gc.getClassMapping().addTable(t);
			superclass = gc.getSuperclass();
			gc.getClassMapping().addTable(tables.get(superclass));
			im.addTable(tables.get(superclass));
			while(superclass.isSubclass()) {
				superclass = superclass.getSuperclass();
				gc.getClassMapping().addTable(tables.get(superclass));
				im.addTable(tables.get(superclass));
			}
			break;
			
		case TABLE_PER_CONCRETE_CLASS:
			if(gc.is_abstract()) break;
			t = new Table(this.ormfo, gc, TableType.SINGLE_ENTITY_TABLE);
			tables.put(gc, t);
			gc.getClassMapping().addTable(t);
			im.addTable(t);
			superclass = gc.getSuperclass();
			if(!superclass.is_abstract()) {
				gc.getClassMapping().addTable(tables.get(superclass));
				im.addTable(tables.get(superclass));
			}
			while(superclass.isSubclass()) {
				superclass = superclass.getSuperclass();
				if(!superclass.is_abstract()) {
					gc.getClassMapping().addTable(tables.get(superclass));
					im.addTable(tables.get(superclass));
				}
			}
			
			break;
			
		default:
			break;
		
		}
		for(GenericClass subclass : gc.getSubclasses()) {
			processSubclassTable(subclass);
		}
	}

	public void processTables() {
		
		//PASSO 1: Criando tabelas para classes que nao sao subclasses
		for(GenericClass gc : classes.values()) {
			if(!gc.isEntity()) continue;
			if(!gc.isSubclass()) {
				
				if(gc.isSuperclass()) {
					Table t;
					switch(gc.getCodeInheritanceStrategy()) {
					case SINGLE_TABLE:
						t = new Table(this.ormfo, gc, TableType.MULTIPLE_ENTITIES_TABLE);
						tables.put(gc, t);
						if(gc.is_abstract()) break;
						gc.getClassMapping().addTable(t);
						break;
						
					case TABLE_PER_CLASS:
						t = new Table(this.ormfo, gc, TableType.SINGLE_ENTITY_TABLE);
						tables.put(gc, t);
						if(gc.is_abstract()) break;
						gc.getClassMapping().addTable(t);
						break;
						
					case TABLE_PER_CONCRETE_CLASS:
						if(gc.is_abstract()) break;
						t = new Table(this.ormfo, gc, TableType.SINGLE_ENTITY_TABLE);
						tables.put(gc, t);
						gc.getClassMapping().addTable(t);
						break;
						
					default:
						System.out.println("[ERROR] Process Tables...");
						break;
					
					}
				}else {
					Table t = new Table(this.ormfo, gc, TableType.ENTITY_TABLE);
					gc.getClassMapping().addTable(t);
					tables.put(gc, t);
				}
				
				//Passo 2: percorrendo recursivamente a lista de subclasses desta classe
				for(GenericClass subclass : gc.getSubclasses()) {
					processSubclassTable(subclass);
				}
				
			}
			
		}
	}
		
	private void processFields() {
		
		for(GenericClass gc : classes.values()) {
			for(FieldDeclaration field : ((JavaClass)gc).getFields()) {
				JavaVariable jv = new JavaVariable(this.ormfo,gc,field);
				variables.put(jv.getCodeName(), jv);
				
				
				//VALUE TYPE
				String codeType = jv.getCodeType();
				int i = codeType.indexOf("<");
				if(i>0){
					codeType = codeType.substring(codeType.indexOf("<")+1);
					codeType = codeType.substring(0,codeType.indexOf(">"));
				}
				Type type = primitiveTypes.get(codeType);
				if(type==null) {
					type = classes.get(codeType);
				}
				if(type==null) {
					System.out.println("[ERROR] Type não encontado. O programa será encerrado.");
					System.exit(1);
				}
				ValueType vt = new ValueType(this.ormfo,jv,type);
			}
		}
	}

	private void processVariableMappings() {
		
		for(GenericVariable gv : variables.values()) {
			if(gv.isMapped()) {
				VariableMapping vm = new VariableMapping(this.ormfo, gv);
				variableMappings.put(gv, vm);
				
				}
		}
	}
	
	private void processColumns() {
		for(VariableMapping vm : variableMappings.values()) {
			Column c = new Column(this.ormfo,vm);
		}
	}

	private void processRelationships() {
		
		for(GenericVariable gv : variables.values()) {
			if(gv.isFk()) {
				RelationshipMapping rm = new RelationshipMapping(this.ormfo, gv);
				relationshipMappings.put(gv, rm);
			}
		}
		
		for(RelationshipMapping rm : relationshipMappings.values()) {
			GenericVariable gv = rm.getVariable();
			String mappedBy = ((JavaVariable)gv).getMappedBy();
			if(mappedBy==null) continue;

			String targetClass = ((GenericClass)gv.getValueType().getType()).getCodeName();
			GenericVariable rv = variables.get(targetClass + "." + mappedBy);
			System.out.println(rv.getCodeName()  + " +++++++++++++");
			RelationshipMapping reverse = relationshipMappings.get(rv);
			rm.setReverse(reverse);
//			GenericVariabel var = variables.get()
//			System.out.println(variables.get( ((GenericClass)gv.getValueType().getType()).getCodeName()));
//			System.out.println(mappedBy);
		}
	}
	
	
	
	
	//	public void printFile(String filePath) {
//		try {
//			FileWriter fileWriter = new FileWriter(filePath);
//		    PrintWriter printWriter = new PrintWriter(fileWriter);
//		    OWLFile owl = new OWLFile();
//		    printWriter.print(owl.getOwlHead());
//		    
//		    printWriter.print(this.getPrimitiveTypes());
//		    
//		    printWriter.print(this.getClasses());
//		    printWriter.print(this.getTables());
//		    printWriter.print(this.getClassMappings());
//		    printWriter.print(this.getInheritance());
//		    printWriter.print(this.getInheritanceMappings());
//		    printWriter.print(this.getRelationshipMappings());
//		    
//		    printWriter.print(this.getVariables());
//		    printWriter.print(this.getColumns());
//		    printWriter.print(this.getVariableMappings());
////		    System.out.println(this.getVariables());
//		    
//		    printWriter.print(owl.getOwlClosure());
//		        
//		    printWriter.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
//

//	
//	static void processTypes() {
//		for(GenericVariable v : variables.values()) {
//			
//			if(classes.containsKey(v.getCodeValueType())) {
//				v.setValueType(classes.get(v.getCodeValueType()));
//				continue;	
//			}
//			
//			if(primitiveTypes.containsKey(v.getCodeValueType())) {
//				v.setValueType(primitiveTypes.get(v.getCodeValueType()));
//				continue;	
//			}
//			
//			System.out.println("[ERROR] Type nao encontrado: " + v.getCodeValueType());
//			
//			
//		}
//	}
//	
//	static GenericClass getHierarchyMother(GenericClass c) {
//		GenericClass mother = classes.get(c.getSuperclassName());
//		if(mother.isSubclass()) return getHierarchyMother(mother);
//		return mother;
//	}
//	
//	static void processInheritance() {
//		for(GenericClass c : classes.values()) {
//			
//			if(c.isSubclass()) {
//				GenericClass superclass = classes.get(c.getSuperclassName());
//				c.setSuperclass(superclass);
//				if(c.getInheritanceStrategy()==null) {
//					GenericClass supremeMother = getHierarchyMother(c);
//					c.setInheritanceStrategy(supremeMother);
//				}						
//				InheritanceMapping im = new InheritanceMapping(superclass, c);
//				inheritanceMappings.put(c,im);
//				
//				Inheritance i = new Inheritance(superclass,c);
//				inheritances.put(c, i);
//				
//			}
//			
//		}
//	}
//	

//	
//	static void processClassMappings() {
//		for(GenericClass c : classes.values()) {
//			if(c.isMapped()) {
//				ClassMapping cm = new ClassMapping(c,tables.get(c));
//				classMappings.put(c, cm);
//			}
//			
//		}
//	}
//	
//	static void checkRelationshipReverses() {
//		
//		for(RelationshipMapping rm : relationshipMappings.values()) {	
//			GenericVariable v = rm.getVariable();
//			if(v.isFk()) {
//				String mappedBy = ((JavaVariable)v).getMappedBy(rm.getType());
//				if(mappedBy==null) continue;
////				System.out.println("-----------" +rm.getNamedIndividualIRI());
//				GenericVariable vreverse = variables.get(rm.getVariable().getValueType().getType().getCodeName()+"."+mappedBy);
//				RelationshipMapping reverse = relationshipMappings.get(vreverse);
//				rm.setReverse(reverse);
////				reverse.setReverse(rm);
//			}
//			
//		}
//	}
//	
//	static void processRelationshipAssociationTables() {
//		
//		for(RelationshipMapping rm : relationshipMappings.values() ) {
//			if(rm.getType().equals("m2m")) {
//				if(((JavaVariable) rm.getVariable()).getMappedBy("m2m")!=null) continue;
//				RelationshipAssociationTable rat = new RelationshipAssociationTable(rm);
//				relationshipAssociationTables.put(rm,rat);
//				rm.setRelationshipAssociationTable(rat);
//			}
//			
//			if(rm.getType().equals("o2m")) {
//				if(rm.getReverse()!=null) continue;
//				RelationshipAssociationTable rat = new RelationshipAssociationTable(rm);
//				relationshipAssociationTables.put(rm,rat);
//				rm.setRelationshipAssociationTable(rat);
//			}
//		}
//		
//		for(RelationshipMapping rm : relationshipMappings.values() ) {
//			if(rm.getReverse()!=null & rm.getRelationshipAssociationTable()==null) {
//				rm.setRelationshipAssociationTable(rm.getReverse().getRelationshipAssociationTable());
//			}
//		}
//	}
//	
//	static void processClassFields(Node node, GenericClass clazz) {
//		for(Node childNode : node.findAll(FieldDeclaration.class)) {
//			JavaVariable v = new JavaVariable(childNode,clazz);
//			variables.put(v.getCodeName(),v);
//		}
//	}
//
//	public static void processColumns() {
//		for(GenericVariable v : variables.values()) {
//			if(!v.isMapped()) continue;
////			System.out.println("Coluna da variável: " + v.getCodeName());
//			Column c = new Column(v);
//			columns.put(v,c);
//		}
//	}
//	
//	public static void processVariableMappings() {
//		for(GenericVariable v : variables.values()) {
//			if(!v.isMapped()) continue;
//			Column c = columns.get(v);
//			VariableMapping vm = new VariableMapping(v,c);
//			variableMappings.put(v,vm);
//		}
//	}
//	
//	public static String typeFilter(String type) {
//		if(!type.contains("<")) return type;
//		String aux = type.substring(type.indexOf("<") + 1);
//		System.out.println(aux + " ---- ");
//		aux = aux.substring(0, aux.indexOf(">"));
//		return aux;
//	}
//	
//	public static void processRelationships() {
//		for(GenericClass c : classes.values()) {
//			for(GenericVariable v : c.getVariables()) {
//				if(v.isFk()) {
//					GenericClass range = classes.get(typeFilter(v.getCodeValueType()));
//					RelationshipMapping rm = new RelationshipMapping(v);
//					v.setRelationshipMapping(rm);
//					relationshipMappings.put(v,rm);
//				}
//			}
//		}
//	}
//	
//	public String getRelationshipMappings() {
//		String ret = "";
//		for(RelationshipMapping rm : relationshipMappings.values()) {
//			ret+=rm.getDeclaration();
//			ret+=rm.getAssertion();
//			
//			ret+=rm.getPropertiesAssertion();
//		}
//		return ret;
//	}
	
}
