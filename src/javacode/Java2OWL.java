package javacode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

import ORM.ClassMapping;
import ORM.InheritanceMapping;
import ORM.RelationshipMapping;
import ORM.VariableMapping;
import genericcode.Column;
import genericcode.GenericClass;
import genericcode.GenericVariable;
import genericcode.Inheritance;
import genericcode.PrimitiveType;
import genericcode.Table;
import owlcode.OWLFile;

public class Java2OWL {
	public static ArrayList<String> declaredSuperclasses = new ArrayList<String>();

	public static Map<String, PrimitiveType> primitiveTypes = new HashMap<String, PrimitiveType>();
	public static Map<String, GenericClass> classes = new HashMap<String, GenericClass>();
	
	
	public static Map<GenericClass, Table> tables = new HashMap<GenericClass, Table>();
	public static Map<GenericClass, ClassMapping> classMappings = new HashMap<GenericClass, ClassMapping>();
	public static Map<GenericClass, InheritanceMapping> inheritanceMappings = new HashMap<GenericClass, InheritanceMapping>();
	public static Map<GenericClass, Inheritance> inheritances = new HashMap<GenericClass, Inheritance>();
	
	public static Map<String, GenericVariable> variables = new HashMap<String, GenericVariable>();
	public static Map<GenericVariable, Column> columns = new HashMap<GenericVariable, Column>();
	
	public static Map<GenericVariable, VariableMapping> variableMappings = new HashMap<GenericVariable, VariableMapping>();
	public static Map<GenericClass, RelationshipMapping> relationshipMappings = new HashMap<GenericClass, RelationshipMapping>();
	
	public Java2OWL(File folder) {
		this.setPrimitiveTypes();
		
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
					GenericClass c = processClassNode(n);
					processClassFields(n,c);
					
				}
			}
		}
		
		processTypes();
		
		processInheritance();
		processTables();
		processClassMappings();
		//---------------------
		processColumns();
		processVariableMappings();
		//---------------------
		processRelationships();
		
		
	}
	
	public void setPrimitiveTypes() {
		primitiveTypes.put("boolean",new PrimitiveType("boolean"));
		primitiveTypes.put("byte",new PrimitiveType("byte"));
		primitiveTypes.put("char",new PrimitiveType("char"));
		primitiveTypes.put("double",new PrimitiveType("double"));
		primitiveTypes.put("float",new PrimitiveType("float"));
		primitiveTypes.put("int",new PrimitiveType("int"));
		primitiveTypes.put("long",new PrimitiveType("long"));
		primitiveTypes.put("short",new PrimitiveType("short"));
		primitiveTypes.put("String",new PrimitiveType("String"));
	}
	
	public void printFile(String filePath) {
//		BufferedWriter writer;
		try {
			FileWriter fileWriter = new FileWriter(filePath);
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    OWLFile owl = new OWLFile();
		    printWriter.print(owl.getOwlHead());
		    
		    printWriter.print(this.getPrimitiveTypes());
		    
		    printWriter.print(this.getClasses());
		    printWriter.print(this.getTables());
		    printWriter.print(this.getClassMappings());
		    printWriter.print(this.getInheritance());
		    printWriter.print(this.getInheritanceMappings());
		    printWriter.print(this.getRelationshipMappings());
		    
		    printWriter.print(this.getVariables());
//		    System.out.println(this.getVariables());
		    
		    printWriter.print(owl.getOwlClosure());
		        
		    printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	static GenericClass processClassNode(Node node) {
		JavaClass c = new JavaClass((ClassOrInterfaceDeclaration) node);
		
		if(c.isEntity()) {
			classes.put(c.getCodeName(), c);	
			return c;
		
		}
		return null;
		
	}
	
	static void processTypes() {
		for(GenericVariable v : variables.values()) {
			
			if(classes.containsKey(v.getCodeValueType())) {
				v.setValueType(classes.get(v.getCodeValueType()));
				continue;	
			}
			
			if(primitiveTypes.containsKey(v.getCodeValueType())) {
				v.setValueType(primitiveTypes.get(v.getCodeValueType()));
				continue;	
			}
			
			System.out.println("[ERROR] Type nao encontrado: " + v.getCodeValueType());
			
			
		}
	}
	
	static GenericClass getHierarchyMother(GenericClass c) {
		GenericClass mother = classes.get(c.getSuperclassName());
		if(mother.isSubclass()) return getHierarchyMother(mother);
		return mother;
	}
	
	static void processInheritance() {
		for(GenericClass c : classes.values()) {
			
			if(c.isSubclass()) {
				GenericClass superclass = classes.get(c.getSuperclassName());
				c.setSuperclass(superclass);
				if(c.getInheritanceStrategy()==null) {
					GenericClass supremeMother = getHierarchyMother(c);
					c.setInheritanceStrategy(supremeMother);
				}						
				InheritanceMapping im = new InheritanceMapping(superclass, c);
				inheritanceMappings.put(c,im);
				
				Inheritance i = new Inheritance(superclass,c);
				inheritances.put(c, i);
				
			}
			
		}
	}
	
	static void processTables() {
		for(GenericClass c : classes.values()) {
			if(!c.isSubclass()) {
				String tableType;
				if(c.isSuperclass()) {
					String inheritanceStrategy = c.getInheritanceStrategy();
					if(inheritanceStrategy.equals("single_table")) {
						tableType = "multiple_entities_table";
					}else {
						tableType = "single_entity_table";
					}
				}else {
					tableType = "entity_class";
				}
				Table t = new Table(c,tableType);
				tables.put(c,t);
				c.setTable(tables.get(c));
			}
		}
		
		for(GenericClass c : classes.values()) {
			if(c.isSubclass()) {
				String inheritanceStrategy = c.getInheritanceStrategy();
				if(inheritanceStrategy.equals("single_table")){
					
					Table t = tables.get(getHierarchyMother(c));
					tables.put(c,  t);
					c.setTable(t);
					
				}else {
					if(c.isMapped()) {
						Table t = new Table(c,"single_entity_table");
						tables.put(c, t);
					}
					
				}
			}
		}
	}
	
	static void processClassMappings() {
		for(GenericClass c : classes.values()) {
			if(c.isMapped()) {
				ClassMapping cm = new ClassMapping(c,tables.get(c));
				classMappings.put(c, cm);
			}
			
		}
	}
	
	public String getPrimitiveTypes() {
		String ret = "";
		for(PrimitiveType pType : primitiveTypes.values()) {
			ret += pType.getDeclaration();
			ret+= pType.getAssertion();
		}
		
		return ret;
	}
	
	public String getClasses() {
		String ret = "";
		for(GenericClass c : classes.values()) {
			ret+=c.getDeclaration();
			ret+=c.getAssertion();
		}
		return ret;
	}
	
	public String getTables() {
		String ret = "";
		for(Table t : tables.values()) {
			ret+=t.getDeclaration();
			ret+=t.getAssertion();
		}
		return ret;
	}
	
	private String getInheritance() {
		String ret = "";
		for(Inheritance i : inheritances.values()) {
			ret+=i.getDeclaration();
			ret+=i.getAssertion();
			ret+=i.getPropertiesAssertion();
		}
		return ret;
	}

	public String getInheritanceMappings() {
		String ret = "";
		for(InheritanceMapping im : inheritanceMappings.values()) {
			ret+=im.getDeclaration();
			ret+=im.getAssertion();
			ret+=im.getPropertiesAssertion();
		}
		return ret;
	}
	
	public String getClassMappings() {
		String ret = "";
		for(ClassMapping cm : classMappings.values()) {
			ret+=cm.getDeclaration();
			ret+=cm.getAssertion();
			
			ret+=cm.getPropertiesAssertion();
		}
		return ret;

	}
	
	public String getVariables() {
		String ret = "";
		
		for(GenericVariable v : variables.values()) {
			ret += v.getDeclaration();
			ret += v.getAssertion();
			ret += v.getValueType().getDeclaration();
			ret += v.getValueType().getAssertion();
		}
		
		return ret;
	}

	static void processClassFields(Node node, GenericClass clazz) {
		for(Node childNode : node.findAll(FieldDeclaration.class)) {
			JavaVariable v = new JavaVariable(childNode,clazz);
			variables.put(v.getCodeName(),v);
		}
	}

	public static void processColumns() {
		for(GenericVariable v : variables.values()) {
			if(!v.isMapped()) continue;
			Column c = new Column(v);
			columns.put(v,c);
		}
	}
	
	public static void processVariableMappings() {
		for(GenericVariable v : variables.values()) {
			if(!v.isMapped()) continue;
			Column c = columns.get(v);
			VariableMapping vm = new VariableMapping(v,c);
			variableMappings.put(v,vm);
		}
	}
	
	public static String typeFilter(String type) {
		if(!type.contains("<")) return type;
		String aux = type.substring(type.indexOf("<") + 1);
		System.out.println(aux + " ---- ");
		aux = aux.substring(0, aux.indexOf(">"));
		return aux;
	}
	
	public static void processRelationships() {
		for(GenericClass c : classes.values()) {
			for(GenericVariable v : c.getVariables()) {
				if(v.isFk()) {
					GenericClass range = classes.get(typeFilter(v.getCodeValueType()));
					RelationshipMapping rm = new RelationshipMapping(c,range, v);
					relationshipMappings.put(c,rm);
				}
			}
		}
	}
	
	public String getRelationshipMappings() {
		String ret = "";
		for(RelationshipMapping rm : relationshipMappings.values()) {
			ret+=rm.getDeclaration();
			ret+=rm.getAssertion();
			
			ret+=rm.getPropertiesAssertion();
		}
		return ret;
	}
	
}
