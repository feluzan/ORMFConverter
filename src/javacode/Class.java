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

public class Class {
	
	private String name;
	private boolean isEntity;
	private List<AnnotationExpr> annotations = null;
	private NodeList<Modifier> modifiers = null;
	NodeList<ClassOrInterfaceType> extendeds = null;
//	private ArrayList<String> annotations = new ArrayList<String>();
//	private ArrayList<String> modifiers = new ArrayList<String>();
	
	private List<FieldDeclaration> fields;
	
	public Class(ClassOrInterfaceDeclaration node) {
		this.name = ((NodeWithSimpleName<ClassOrInterfaceDeclaration>) node).getNameAsString();
		
		this.annotations = ((BodyDeclaration<ClassOrInterfaceDeclaration>) node).getAnnotations();	
		this.modifiers = ((TypeDeclaration<ClassOrInterfaceDeclaration>) node).getModifiers();
		this.fields = node.findAll(FieldDeclaration.class);
		this.extendeds = node.getExtendedTypes();
		
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEntity() {
		return isEntity;
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
	
	public String entityDeclaration() {
		String ret ="\t<Declaration>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + this.name + "\"/>\n"
				+	"\t</Declaration>\n";
		return ret;
	}
	public String entityAssertion() {
		String ret = "\t<ClassAssertion>\n"
				+	"\t\t<Class IRI=\"#ORMF-O::Entity_Class\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + this.name + "\"/>\\n"
				+ "\t</ClassAssertion>";
		return ret;
		
		
	}
	
	

}
