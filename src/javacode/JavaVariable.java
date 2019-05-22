package javacode;

import java.util.List;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;


import genericcode.GenericClass;
import genericcode.GenericVariable;

public class JavaVariable extends GenericVariable{
	
	private List<AnnotationExpr> annotations = null;
	private List<Modifier> modifiers = null;
	
	
	public JavaVariable(Node node, GenericClass clazz) {
		this.setClazz(clazz);
		this.annotations = ((BodyDeclaration<FieldDeclaration>) node).getAnnotations();

		VariableDeclarator variable = ((FieldDeclaration) node).getVariables().get(0);
		this.codeName = clazz.getCodeName() + "." + variable.getNameAsString();
		this.setCodeValueType(variable.getTypeAsString());
		
		this.setIsPK();
		this.setIsFK();
		this.setIsMapped();
		
		this.classIRI = "ORMF-O::Mapped_Variable";
		if(this.isPk()) this.classIRI = "ORMF-O::Mapped_Primary_Key";
		if(this.isFk()) this.classIRI = "ORMF-O::Mapped_Foreign_Key";

		this.setNamedIndividualIRI();
		clazz.addVariable(this);
	}
	
	@Override
	public void setCodeValueType(String codeValueType) {
		
		int i = codeValueType.indexOf("<");
		if(i<0) super.setCodeValueType(codeValueType);
		else {
			String substring = codeValueType.substring(codeValueType.indexOf("<")+1);
			substring = substring.substring(0,substring.indexOf(">"));
			super.setCodeValueType(substring);
		}
		
		
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
	
	public void setIsPK() {
		if(this.hasAnnotation("Id")) this.setIsPk(true);
	}
	
	public void setIsFK() {
		this.setIsFk(this.hasAnnotation("OneToOne") | this.hasAnnotation("OneToMany") | this.hasAnnotation("ManyToOne") | this.hasAnnotation("ManyToMany"));
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

	public String getMappedBy(String type) {
		
		AnnotationExpr ann = null;
		
		switch(type){
		case "o2o":
			ann = this.getAnnotation("OneToOne");
			
			break;
			
		case "o2m":
			ann = this.getAnnotation("OneToMany");
			break;
			
		case "m2o":
			ann = this.getAnnotation("ManyToOne");
			break;
			
		case "m2m":
			ann = this.getAnnotation("ManyToMany");
			break;
			
		default:
			return null;
		}
		
		List<MemberValuePair> members = ann.findAll(MemberValuePair.class);
		for(MemberValuePair m : members) {
			if(m.getName().toString().equals("mappedBy")) {
				return m.getValue().toString().replace("\"", "");
			}
		}
		return null;
	}

	public void setIsMapped() {
		if(this.hasAnnotation("Transient")) {
			this.setIsMapped(false);
			return;
		}
		if(this.hasAnnotation("OneToMany")){
			if(this.getMappedBy("o2m")!=null) {
				this.setIsMapped(false);
				return;
			}
		}
		this.setIsMapped(true);
	}

	@Override
	public String getAssociationTableName() {
		if(!this.hasAnnotation("JoinTable")) return null;
		AnnotationExpr ann = this.getAnnotation("JoinTable");
		List<MemberValuePair> members = ann.findAll(MemberValuePair.class);
		for(MemberValuePair m : members) {
			if(m.getName().toString().equals("name")) {
				return m.getValue().toString().replace("\"", "");
			}
		}
		return null;
		
	}

}
