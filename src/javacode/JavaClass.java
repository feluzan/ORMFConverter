package javacode;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import genericcode.GenericClass;

public class JavaClass extends GenericClass{
	
	private List<AnnotationExpr> annotations = null;
	private NodeList<Modifier> modifiers = null;
	NodeList<ClassOrInterfaceType> extendeds = null;
	private List<FieldDeclaration> fields;
	
	public JavaClass(ClassOrInterfaceDeclaration node) {

		this.codeName = ((NodeWithSimpleName<ClassOrInterfaceDeclaration>) node).getNameAsString();
		this.annotations = ((BodyDeclaration<ClassOrInterfaceDeclaration>) node).getAnnotations();	
		this.modifiers = ((TypeDeclaration<ClassOrInterfaceDeclaration>) node).getModifiers();
		this.fields = node.findAll(FieldDeclaration.class);
		this.extendeds = node.getExtendedTypes();
		this.setIndividualName();
//		this.isSubclass();
		
	}
	
	public String getSuperclass() {
		for(Node n : this.extendeds) {
			return ((NodeWithSimpleName<ClassOrInterfaceDeclaration>) n).getNameAsString();
		}
		return null;
	}
	
	public boolean isEntity() {
		for (AnnotationExpr ann : this.annotations) {
			if (ann.getNameAsString().equals("Entity")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasAnnotation(String annotation) {
		for(AnnotationExpr ann : this.annotations) {
			if(ann.getNameAsString().equals(annotation)) return true;
		}
		return false;
	}
	
	@Override
	public String getTableName() {
			
			for (AnnotationExpr ann : this.getAnnotations()) {
				if (ann.getNameAsString().equals("Table")) {
					List<MemberValuePair> members = ann.findAll(MemberValuePair.class);
					for(MemberValuePair m : members) {
						if(m.getName().toString().equals("name")) {
							return m.getValue().toString().replace("\"", "");
						}
					}
				}
			}
			
			return this.codeName;
		}
	
	public ArrayList<String> annotations2array(){
		ArrayList<String> ret = new ArrayList<String>();
		for (AnnotationExpr ann : this.annotations) {
			ret.add(ann.toString());
		}
		return ret;
		
	}
	
//	public AnnotationExpr getSomeAnnotation(int index) {
//		return this.annotations.get(index);
//	}
	
	public AnnotationExpr getAnnotation(String annotation) {
		for(AnnotationExpr ann : this.annotations) {
			if(ann.getNameAsString().equals(annotation)) return ann;
		}
		return null;
	}
	
	public ArrayList<String> modifiers2array(){
		ArrayList<String> ret = new ArrayList<String>();
		for (Modifier mod : this.modifiers) {
			ret.add(mod.toString());
		}
		return ret;
		
	}

	public void setName(String name) {
		this.individualName = name;
	}

	public List<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	public NodeList<Modifier> getModifiers() {
		return modifiers;
	}
	
	public void print() {
		System.out.println("Nome da classe: " + this.individualName);
		System.out.println("Anotações: " + this.annotations2array());
		System.out.println("Modifiers: " + this.modifiers2array());
		System.out.println("Fields: " + this.fields.toString());
		System.out.println("Estende: " + this.extendeds.toString());
	}

	@Override
	public String getInheritanceStrategy() {
		AnnotationExpr ann = this.getAnnotation("Inheritance");
		List<MemberValuePair> members = ann.findAll(MemberValuePair.class);
		for(MemberValuePair m : members) {
			if(m.getName().toString().equals("strategy")) {
				return m.getValue().toString().replace("\"", "").toLowerCase();
			}
		}
		return null;
	}

}
