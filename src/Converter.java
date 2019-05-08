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
import ORM.FieldMapping;
import ORM.InheritanceMapping;
import genericcode.Column;
import genericcode.GenericClass;
import genericcode.Table;
import javacode.JavaClass;
import javacode.JavaField;



public class Converter {
	
	public static ArrayList<String> declaredSuperclasses = new ArrayList<String>();
	
	public static Map<String, GenericClass> classes = new HashMap<String, GenericClass>();
	public static Map<GenericClass, Table> tables = new HashMap<GenericClass, Table>();
	public static Map<GenericClass, ClassMapping> classMappings = new HashMap<GenericClass, ClassMapping>();
	
	public static Map<GenericClass, InheritanceMapping> inheritanceMappings = new HashMap<GenericClass, InheritanceMapping>();
	
	
	
	
	static GenericClass processClassNode(Node node) {
		JavaClass c = new JavaClass((ClassOrInterfaceDeclaration) node);
//		System.out.println(c.getCodeName());
		
		
		if(c.isEntity()) {
			classes.put(c.getCodeName(), c);
			
			//Create EntityTable item
			//Table t = new Table(c, true);
			//tables.put(c,t);
			
			//Create ClassMapping item
			//ClassMapping cm = new ClassMapping(c, t);
			//classMappings.put(c, cm);
			
//			System.out.println(c.getDeclaration());
//			System.out.println(c.getAssertion());
//			
//			System.out.println(t.getDeclaration());
//			System.out.println(t.getAssertion());
//
//			System.out.println(cm.getDeclaration());
//			System.out.println(cm.getAssertion());
//			
//			System.out.println(cm.getPropertiesAssertion());	

			return c;
		
		}
		return null;
		
	}
	
	static GenericClass getHierarchyMother(GenericClass c) {
		GenericClass mother = classes.get(c.getSuperclass());
		if(mother.isSubclass()) return getHierarchyMother(mother);
		return mother;
	}
	
	static void processInheritance() {
		for(GenericClass c : classes.values()) {
			
			if(c.isSubclass()) {
				GenericClass superclass = classes.get(c.getSuperclass());
				GenericClass subclass = classes.get(c.getCodeName());
				if(subclass.getInheritanceStrategy()==null) {
					GenericClass supremeMother = getHierarchyMother(subclass);
					c.setInheritanceStrategy(supremeMother);
				}				
				
				InheritanceMapping im = new InheritanceMapping(superclass, subclass);
				inheritanceMappings.put(subclass,im);
				
			}
			
			
		}
	}
	
	static void processTables() {
		for(GenericClass c : classes.values()) {
			if(!c.isSubclass()) {
				Table t = new Table(c, true);
				tables.put(c,t);
			}
		}
		for(GenericClass c : classes.values()) {
			if(c.isSubclass()) {
				String inheritanceStrategy = c.getInheritanceStrategy();
				if(inheritanceStrategy.equals("table_per_class")){
					
				}if(inheritanceStrategy.equals("table_per_concrete_class")){
					if(!c.isAbstract()) {
						Table t = new Table(c,true);
						tables.put(c, t);
					}
				}else {
					Table t = getHierarchyMother(c).getTable();
					tables.put(c,  t);
				}
			}
		}
	}
	
	static void processFieldNode(Node node, GenericClass clazz) {
		
		NodeList<AnnotationExpr> annotations = ((BodyDeclaration<ClassOrInterfaceDeclaration>) node).getAnnotations();	
		for(VariableDeclarator v : ((FieldDeclaration) node).getVariables()) {
			JavaField f = new JavaField(v, annotations, clazz);
			
			
			if(!f.isTransient()) {
				Column col = new Column(f);
				FieldMapping fm = new FieldMapping(f, col);
				
				System.out.println(f.getDeclaration());
				System.out.println(f.getAssertion());
			
				System.out.println(col.getDeclaration());
				System.out.println(col.getAssertion());
			
				System.out.println(fm.getDeclaration());
				System.out.println(fm.getAssertion());
				System.out.println(fm.getPropertiesAssertion());
			}
		}
		
	}
	
	static void processClassMapping() {
		for(GenericClass c : classes.values()) {
			ClassMapping cm = new ClassMapping(c,c.getTable());
			classMappings.put(c, cm);
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
		}
	}
	
	static void printClassMappings() {
		for(ClassMapping cm : classMappings.values()) {
			System.out.println(cm.getDeclaration());
			System.out.println(cm.getAssertion());
			
			System.out.println(cm.getPropertiesAssertion());
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
//				e.printStackTrace();
				//TODO colocar log de arquivo ou diretorio nao encontrado...
				continue;
			}
			List<Node> nodeList = compilationUnit.getChildNodes();

			for (Node n : nodeList) {
				if(n instanceof ClassOrInterfaceDeclaration) {
					GenericClass c = processClassNode(n);
//					processFieldNode(n,c);
				}
			}
		}
		
		processInheritance();
		processTables();
		
		
		printClasses();
		printTables();
		printClassMappings();
		printInheritanceMappings();

		
		
		
		
		
		
	}

}
