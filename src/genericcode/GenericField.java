package genericcode;

import com.github.javaparser.ast.type.Type;

import owlcode.Item;

public class GenericField extends Item{
	
	protected Type valueType;
	protected String columnName;
	protected GenericClass clazz;
	
//	public GenericField(GenericClass clazz) {
////		
//		this.clazz = clazz;
//	}

	protected void setIndividualName() {
		this.individualName = this.codeName + "__" + this.clazz.getIndividualName();
	}
	
//	public String getNameFromIndividualName() {
//		
//	}
}
