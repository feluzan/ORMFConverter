package javacode;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import ORM.ClassMapping;
import writer.Writer;

public class Class extends Writer{
	
//	private String name;
//	private final String iri = "ORMF-O::Entity_Class";
	private boolean isEntity;
	private List<AnnotationExpr> annotations = null;
	private NodeList<Modifier> modifiers = null;
	NodeList<ClassOrInterfaceType> extendeds = null;
	private List<FieldDeclaration> fields;
	
	public Class(ClassOrInterfaceDeclaration node) {
		
		this.iri = "ORMF-O::Entity_Class";
		this.name = ((NodeWithSimpleName<ClassOrInterfaceDeclaration>) node).getNameAsString();	
		this.annotations = ((BodyDeclaration<ClassOrInterfaceDeclaration>) node).getAnnotations();	
		this.modifiers = ((TypeDeclaration<ClassOrInterfaceDeclaration>) node).getModifiers();
		this.fields = node.findAll(FieldDeclaration.class);
		this.extendeds = node.getExtendedTypes();
		
	}

	public boolean isEntity() {
		for (AnnotationExpr ann : this.annotations) {
			if (ann.getNameAsString().equals("Entity")) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> annotations2array(){
		ArrayList<String> ret = new ArrayList<String>();
		for (AnnotationExpr ann : this.annotations) {
			ret.add(ann.toString());
		}
		return ret;
		
	}
	
	public AnnotationExpr getSomeAnnotation(int index) {
		return this.annotations.get(index);
	}
	
	public ArrayList<String> modifiers2array(){
		ArrayList<String> ret = new ArrayList<String>();
		for (Modifier mod : this.modifiers) {
			ret.add(mod.toString());
		}
		return ret;
		
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	public NodeList<Modifier> getModifiers() {
		return modifiers;
	}

	public void setEntity(boolean isEntity) {
		this.isEntity = isEntity;
	}
	
	public void print() {
		System.out.println("Nome da classe: " + this.name);
		System.out.println("Anotações: " + this.annotations2array());
		System.out.println("Modifiers: " + this.modifiers2array());
		System.out.println("Fields: " + this.fields.toString());
		System.out.println("Estende: " + this.extendeds.toString());
	}

}
