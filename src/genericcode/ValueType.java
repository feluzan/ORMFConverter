package genericcode;

import org.semanticweb.owlapi.model.OWLOntology;

import OWL.Individual;
import OWL.ObjectPropertyIRI;

public class ValueType extends Individual {
	
	Type type;
	GenericVariable variable;

	public ValueType(OWLOntology o, GenericVariable gv, Type t) {
		super(o, "value_type__" + gv.getCodeName());

		gv.setValueType(this);
		this.variable = gv;
		
		this.setType(t);
	}

	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
		this.setObjectProperty(ObjectPropertyIRI.REFERS_TO, type);
	}

	public GenericVariable getVariable() {
		return variable;
	}
	public void setVariable(GenericVariable variable) {
		this.variable = variable;
	}

	
}
