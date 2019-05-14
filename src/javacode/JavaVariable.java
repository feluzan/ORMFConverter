package javacode;

import java.util.List;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
//import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;


import genericcode.GenericClass;
import genericcode.GenericVariable;

public class JavaVariable extends GenericVariable{
	

	private List<AnnotationExpr> annotations = null;
	private List<Modifier> modifiers = null;
	Type valueType;
	
	public JavaVariable(Node node, GenericClass clazz) {
		this.clazz = clazz;
		this.annotations = ((BodyDeclaration<FieldDeclaration>) node).getAnnotations();

		VariableDeclarator variable = ((FieldDeclaration) node).getVariables().get(0);
		this.codeName = clazz.getCodeName() + "." + variable.getNameAsString();
		this.type = variable.getTypeAsString();
		
		this.classIRI = "ORMF-O::Mapped_Variable";
		if(this.isPk()) this.classIRI = "ORMF-O::Mapped_Primary_Key";
		if(this.isFk()) this.classIRI = "ORMF-O::Mapped_Variable";

		this.setIndividualName();
		clazz.addVariable(this);
	}
	
	
	public boolean hasAnnotation(String annotation) {
		for(AnnotationExpr ann : this.annotations) {
			if(ann.getNameAsString().equals(annotation)) return true;
		}
		return false;
	}
	
	public AnnotationExpr getAnnotation(String annotation) {
		for(AnnotationExpr ann : this.annotations) {
			if(ann.getNameAsString().equals(annotation)) return ann;
		}
		return null;
	}
		
	public boolean isMapped() {
		return this.hasAnnotation("Transient");
	}
	
	public boolean isPk() {
		return this.hasAnnotation("Id");
	}
	
	public boolean isFk() {
//		return this.hasAnnotation("ForeignKey");
		return this.hasAnnotation("OneToOne") | this.hasAnnotation("OneToMany") | this.hasAnnotation("ManyToOne") | this.hasAnnotation("ManyToMany");
	}

	@Override
	public String getColumnCodeName() {
		AnnotationExpr ann = this.getAnnotation("Column");
		if(ann != null) {
			
			List<MemberValuePair> members = ann.findAll(MemberValuePair.class);
			for(MemberValuePair m : members) {
				if(m.getName().toString().equals("name")) {
					
					return m.getValue().toString().replace("\"", "");
				}
			}
			
		}
		return this.codeName;
	}

	@Override
	public String getRelationshipType() {
		if(this.hasAnnotation("OneToOne")) return "o2o";
		if(this.hasAnnotation("OneToMany")) return "o2m";
		if(this.hasAnnotation("ManyToOne")) return "m2o";
		if(this.hasAnnotation("ManyToMany")) return "m2m";
		return null;
	}

}
