//import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Scanner;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

import ORM.ClassMapping;
import ORM.Property;
import javacode.Class;

public class Converter {
	static Property entityClassMappedBy = new Property("entity_class_mapped_by");
	
	static void processClassNode(Node node) {
		Class c = new Class((ClassOrInterfaceDeclaration) node);
		
		if(c.isEntity()) {
//			System.out.println(c.getDeclaration());
//			System.out.println(c.getAssertion());
			ClassMapping cm = new ClassMapping(c);
			System.out.println(cm.getDeclaration());
			System.out.println(cm.getAssertion());
			System.out.println(entityClassMappedBy.getAssertion(c, cm));
//					c.entityMapping();
		
		}
		
	}
	
	static void processFieldNode(Node node) {
		
	}
	
	static void processNode(Node node) {
		if(node instanceof FieldDeclaration) {
		}else if(node instanceof ClassOrInterfaceDeclaration) {
		}else {
			return;
		}

	}
	
	
	static void walkOnNode(Node node) {
		if(node instanceof FieldDeclaration) {
			processFieldNode(node);
		}else if(node instanceof ClassOrInterfaceDeclaration) {
			processClassNode(node);
		}else {
			return;
		}
		
		List<Node> nodeList = node.getChildNodes();
		for(Node n : nodeList) {
			walkOnNode(n);
		}
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
			walkOnNode(n);
		}
		
	}

}
