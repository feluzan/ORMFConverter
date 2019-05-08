package javacode;

import java.util.List;

//import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.type.Type;

import genericcode.GenericClass;
import genericcode.GenericField;

public class JavaField extends GenericField{
	

	private List<AnnotationExpr> annotations = null;
	Type valueType;
//	private NodeList<Modifier> modifiers = null;
	
	public JavaField(VariableDeclarator variable, NodeList<AnnotationExpr> annotations, GenericClass clazz) {
		this.clazz = clazz;
		this.codeName = variable.getNameAsString();
		this.valueType = variable.getType();
		this.annotations = annotations;
		this.iri = "ORMF-O::Mapped_Variable";
		if(this.isPk()) this.iri = "ORMF-O::Mapped_Primary_Key";
		if(this.isFk()) this.iri = "ORMF-O::Mapped_Variable";

		this.setIndividualName();
		
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
	
	
	public boolean isTransient() {
		return this.hasAnnotation("Transient");
	}
	
	public boolean isPk() {
		return this.hasAnnotation("Id");
	}
	
	public boolean isFk() {
		return this.hasAnnotation("ForeignKey");
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

}
