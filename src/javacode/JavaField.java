package javacode;

import java.util.List;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;

import genericcode.GenericClass;
import genericcode.GenericField;

public class JavaField extends GenericField{
	

	private List<AnnotationExpr> annotations = null;
//	private NodeList<Modifier> modifiers = null;
	
	public JavaField(VariableDeclarator variable, NodeList<AnnotationExpr> annotations, GenericClass clazz) {
		this.clazz = clazz;
		this.codeName = variable.getNameAsString();	
		this.valueType = variable.getType();
		this.annotations = annotations;
		if(this.isId()) {
			this.iri = "ORMF-O::Mapped_Variable";
		}
//		System.out.println(this.isId());
		this.setIndividualName();
//		this.annotations = ((BodyDeclaration<VariableDeclarator>) variable).getAnnotations();	
//		this.modifiers = ((TypeDeclaration<FieldDeclaration>) node).getModifiers();
		
	}
	
	public boolean isId() {
		for(AnnotationExpr ann : this.annotations) {
			if(ann.getNameAsString().contentEquals("Id")) return true;
//			System.out.println(ann.getNameAsString());
		}
		return false;
	}

}
