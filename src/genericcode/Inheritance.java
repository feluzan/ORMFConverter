package genericcode;

import ORM.Property;
import owlcode.Item;

public class Inheritance extends Item {
	
	GenericClass superclass;
	GenericClass subclass;
	
	Property inheritedFrom = new Property("inherited_from");
	Property inheritsFrom = new Property("inherits_from");

	public Inheritance(GenericClass superclass, GenericClass subclass) {
		this.classIRI = "OOC-O::Inheritance";
		this.superclass = superclass;
		this.subclass = subclass;
		this.setNamedIndividualIRI();
	}
	
	public Inheritance(String classIri, String individualName) {
		this.classIRI = classIri;
		this.namedIndividualIRI = individualName;
	}
	
	public String getPropertiesAssertion() {
		String ret = "";
		ret += this.inheritedFrom.getAssertion(this.superclass, this);
		ret += this.inheritsFrom.getAssertion(this.subclass, this);
		return ret;
	}
	
	public GenericClass getSuperclass() {
		return superclass;
	}

	public void setSuperclass(GenericClass superclass) {
		this.superclass = superclass;
	}

	public GenericClass getSubclass() {
		return subclass;
	}

	public void setSubclass(GenericClass subclass) {
		this.subclass = subclass;
	}

	@Override
	public void setNamedIndividualIRI() {
		// TODO Auto-generated method stub
		String iri = "";
		iri += "inheritance__";
		iri += this.superclass.getCodeName();
		iri += "__";
		iri += this.subclass.getCodeName();
		this.namedIndividualIRI = iri;
		
		

	}

}
