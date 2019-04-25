//import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

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
import ORM.Property;
import genericcode.GenericClass;
import genericcode.Table;
import javacode.JavaClass;
import javacode.JavaField;

public class Converter {
	static Property entityClassMappedBy = new Property("entity_class_mapped_by");
	static Property classMappingTo = new Property("entity_class_mapped_to");
	static Property memberBelongsTo = new Property("member_belongs_to");
	
	static GenericClass processClassNode(Node node) {
		JavaClass c = new JavaClass((ClassOrInterfaceDeclaration) node);
		
		if(c.isEntity()) {
			//Create ClassMapping item
			ClassMapping cm = new ClassMapping(c);
			
			//Create EntityTable item
			Table t = new Table(cm.getTable(), true);
			
			
			System.out.println(c.getDeclaration());
			System.out.println(c.getAssertion());
			
			System.out.println(cm.getDeclaration());
			System.out.println(cm.getAssertion());
			
			System.out.println(t.getDeclaration());
			System.out.println(t.getAssertion());
			
			System.out.println(entityClassMappedBy.getAssertion(c, cm));
			System.out.println(classMappingTo.getAssertion(cm, t));
			return c;
		
		}
		return null;
		
	}
	
	static void processFieldNode(Node node, GenericClass clazz) {
		
		NodeList<AnnotationExpr> annotations = ((BodyDeclaration<ClassOrInterfaceDeclaration>) node).getAnnotations();	
		for(VariableDeclarator v : ((FieldDeclaration) node).getVariables()) {
//			System.out.println("processando variable...");
			JavaField f = new JavaField(v, annotations, clazz);
			
			System.out.println(f.getDeclaration());
			System.out.println(f.getAssertion());
			
			System.out.println(memberBelongsTo.getAssertion(f,clazz));
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
		
//		List<Node> nodeList = node.getChildNodes();
//		for(Node n : nodeList) {
//			walkOnNode(n);
//		}
	}

	public static void main(String[] args){
		
		String filePath = "C:\\Users\\Felix Zanetti\\eclipse-workspace\\JavaTest\\src\\Pessoa.java";

		CompilationUnit compilationUnit = null;
		try {
			compilationUnit = JavaParser.parse(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		List<Node> nodeList = compilationUnit.getChildNodes();

		for (Node n : nodeList) {
			walkOnNode(n, null);
		}
		
	}

}
