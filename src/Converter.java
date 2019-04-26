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
//	public static ArrayList<GenericClass> allClasses = new ArrayList<GenericClass>();
	public static Map<String, GenericClass> classes = new HashMap<String, GenericClass>();
	
	
	
	
	static GenericClass processClassNode(Node node) {
		JavaClass c = new JavaClass((ClassOrInterfaceDeclaration) node);
		
		
		if(c.isEntity()) {
			classes.put(c.getCodeName(), c);
			//Create EntityTable item
			Table t = new Table(c, true);
			
			//Create ClassMapping item
			ClassMapping cm = new ClassMapping(c, t);
			
//			System.out.println(c.getDeclaration());
//			System.out.println(c.getAssertion());
			
//			if(c.isSubclass()) {
//				InheritanceMapping im = new InheritanceMapping()
//				if (!declaredSuperclasses.contains(c.getSuperclass())) {
//				
//					System.out.println(GenericClass.getSuperclassAssertion(c.getSuperclass()));
//					declaredSuperclasses.add(c.getSuperclass());
//				
//				}
//			}
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
	
	static void processFieldNode(Node node, GenericClass clazz) {
		
		NodeList<AnnotationExpr> annotations = ((BodyDeclaration<ClassOrInterfaceDeclaration>) node).getAnnotations();	
		for(VariableDeclarator v : ((FieldDeclaration) node).getVariables()) {
			JavaField f = new JavaField(v, annotations, clazz);
			
			
			if(!f.isTransient()) {
				Column col = new Column(f);
				FieldMapping fm = new FieldMapping(f, col);
				
//				System.out.println(f.getDeclaration());
//				System.out.println(f.getAssertion());
////				
//				System.out.println(col.getDeclaration());
//				System.out.println(col.getAssertion());
////				
//				System.out.println(fm.getPropertiesAssertion());
			}
		}
		
	}
	
	static void processNode(Node node) {
		if(node instanceof FieldDeclaration) {
		}else if(node instanceof ClassOrInterfaceDeclaration) {
		}else {
			return;
		}

	}
	
	
	static void walkOnNode(Node node, GenericClass clazz) {
		if(node instanceof ClassOrInterfaceDeclaration) {
			GenericClass c = processClassNode(node);
			List<Node> nodeList = node.getChildNodes();
			for(Node n : nodeList) {
				walkOnNode(n, c);
			}
		}else if(node instanceof FieldDeclaration) {

			processFieldNode(node, clazz);
		}else {
			return;
		}
	}

	static void processInheritance() {
		for(GenericClass c : classes.values()) {
			
			if(c.isSubclass()) {
				InheritanceMapping im = new InheritanceMapping (classes.get(c.getCodeName()), classes.get(c.getSuperclass()));
			}
			
			
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
				walkOnNode(n, null);
			}
		}
		
		processInheritance();

		
		
		
		
		
		
	}

}
