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
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
//import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;

import javacode.Class;

public class Converter {

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
			
			if(n instanceof ClassOrInterfaceDeclaration) {
				
				Class c = new Class((ClassOrInterfaceDeclaration) n);
				
				c.print();
			
				System.out.println("------------------");
				
				System.out.println(c.annotations2array());
			
			}
		
		}
		
	}

}
