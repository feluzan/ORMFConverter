package genericcode;

import owlcode.OWLClass;
import owlcode.OWLProperty;

public class ValueType extends OWLClass {
	
	GenericVariable variable;
	Type type;
	
	OWLProperty refersTo = new OWLProperty("refers_to");
	
	public ValueType(GenericVariable v, Type type){
		this.variable = v;
		this.classIRI = "OOC-O::Value_Type";
		this.type = type;
		this.setNamedIndividualIRI();
	}
	
	public ValueType(String classIri, String individualName) {
		this.classIRI = classIri;
		this.namedIndividualIRI = individualName;
	}
	
	
	public void setVariable(GenericVariable variable) {
		this.variable = variable;
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
