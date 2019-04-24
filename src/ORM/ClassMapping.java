package ORM;

import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.ThisExpr;

import javacode.Class;
import writer.Writer;

public class ClassMapping extends Writer{
	Class c;
	String table;
//	String name;
	
	public ClassMapping (Class c) {
		this.iri = "ORMF-O::Class_Mapping";
		this.c = c;
		this.name = nameGenerator();
		this.table = getTableName();
//		System.out.println(this.table);
		
	}

	private String nameGenerator() {
		return "class_mapping_" + c.getName();
	}
	
	private String getTableName() {
		
//		List<AnnotationExpr> ann = c.getAnnotations();
		
		for (AnnotationExpr ann : c.getAnnotations()) {
			if (ann.getNameAsString().equals("Entity")) {
				List<MemberValuePair> members = ann.findAll(MemberValuePair.class);
				for(MemberValuePair m : members) {
					if(m.getName().toString().equals("table")) {
						return m.getValue().toString();
					}
					
				}
				
			}
		}
		
		return c.getName();
	}
}
