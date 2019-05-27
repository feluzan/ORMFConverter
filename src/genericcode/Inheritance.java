package genericcode;

import owlcode.OWLClass;
import owlcode.OWLProperty;

public class Inheritance extends OWLClass {
	
	GenericClass superclass;
	GenericClass subclass;
	
	OWLProperty inheritedFrom = new OWLProperty("inherited_from");
	OWLProperty inheritsFrom = new OWLProperty("inherits_from");

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
