//import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;

import ORM.ClassMapping;
import ORM.VariableMapping;
import ORM.InheritanceMapping;
import ORM.RelationshipMapping;
import genericcode.Column;
import genericcode.GenericClass;
import genericcode.GenericVariable;
import genericcode.Table;
import javacode.JavaClass;
import javacode.JavaVariable;



public class Converter {
	
	public static ArrayList<String> declaredSuperclasses = new ArrayList<String>();
	
	public static Map<String, GenericClass> classes = new HashMap<String, GenericClass>();
	public static Map<GenericClass, Table> tables = new HashMap<GenericClass, Table>();
	public static Map<GenericClass, ClassMapping> classMappings = new HashMap<GenericClass, ClassMapping>();
	public static Map<GenericClass, InheritanceMapping> inheritanceMappings = new HashMap<GenericClass, InheritanceMapping>();
	
	public static Map<String, GenericVariable> variables = new HashMap<String, GenericVariable>();
	public static Map<GenericVariable, Column> columns = new HashMap<GenericVariable, Column>();
	
	public static Map<GenericVariable, VariableMapping> variableMappings = new HashMap<GenericVariable, VariableMapping>();
	public static Map<GenericClass, RelationshipMapping> relationshipMappings = new HashMap<GenericClass, RelationshipMapping>();
	
	
	
	
	
	static GenericClass processClassNode(Node node) {
		JavaClass c = new JavaClass((ClassOrInterfaceDeclaration) node);
		
		if(c.isEntity()) {
			classes.put(c.getCodeName(), c);	
			return c;
		
		}
		return null;
		
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
				
			}
			
			
		}
	}
	
	static void processTables() {
		for(GenericClass c : classes.values()) {
			if(!c.isSubclass()) {
				tables.put(c,new Table(c, true));
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
						Table t = new Table(c,true);
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
	
	static void printClasses() {
		for(GenericClass c : classes.values()) {
			System.out.println(c.getDeclaration());
			System.out.println(c.getAssertion());
		}
	}
	
	static void printTables() {
		for(Table t : tables.values()) {
			System.out.println(t.getDeclaration());
			System.out.println(t.getAssertion());
		}
	}
	
	static void printInheritanceMappings() {
		for(InheritanceMapping im : inheritanceMappings.values()) {
			System.out.println(im.getDeclaration());
			System.out.println(im.getAssertion());
			
			System.out.println(im.getPropertiesAssertion());
		}
	}
	
	static void printClassMappings() {
		for(ClassMapping cm : classMappings.values()) {
			System.out.println(cm.getDeclaration());
			System.out.println(cm.getAssertion());
			
			System.out.println(cm.getPropertiesAssertion());
		}

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
	
//	public static void processRelationship(GenericVariable v) {
//		System.out.println(v.getCodeName());
//		
//	}
	
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
					GenericClass range = classes.get(typeFilter(v.getType()));
					RelationshipMapping rm = new RelationshipMapping(c,range, v);
					relationshipMappings.put(c,rm);
				}
			}
		}
	}
	
	public static void printRelationshipMappings() {
		for(RelationshipMapping rm : relationshipMappings.values()) {
			System.out.println(rm.getDeclaration());
			System.out.println(rm.getAssertion());
			
			System.out.println(rm.getPropertiesAssertion());
		}
	}
	
public static void main(String[] args){
		
		String folderPath = "C:\\Users\\Felix Zanetti\\eclipse-workspace\\JavaTest\\src\\";
		File folder = new File(folderPath);
		
		CompilationUnit compilationUnit = null;
		for (File f : folder.listFiles()) {
			try {
				compilationUnit = JavaParser.parse(f);
			} catch (FileNotFoundException e) {
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
		
		processInheritance();
		processTables();
		processClassMappings();
		//---------------------
		processColumns();
		processVariableMappings();
		//---------------------
		processRelationships();
		
		printClasses();
		printTables();
		printClassMappings();
		printInheritanceMappings();
		printRelationshipMappings();

		
		
		
		
		
		
	}

}
