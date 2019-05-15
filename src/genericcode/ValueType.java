package genericcode;

import ORM.Property;
import owlcode.Item;

public class ValueType extends Item {
	
	GenericVariable variable;
	Type type;
	
	Property refersTo = new Property("refers_to");
	
	public ValueType(GenericVariable v, Type type){
		this.variable = v;
		this.classIRI = "OOC-O::Value_Type";
		this.type = type;
		this.setNamedIndividualIRI();
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return this.type;
	}
	
	@Override
	public String getAssertion() {
		String ret = "";
		ret += super.getAssertion();
		ret += refersTo.getAssertion(this,this.type);
		
		return ret;
	}
	
	@Override
	public void setNamedIndividualIRI() {
		this.namedIndividualIRI = "value_type__" + this.type.getCodeName() + "__" + this.variable.getCodeName();

	}

}
