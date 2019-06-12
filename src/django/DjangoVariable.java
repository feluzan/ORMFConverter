package django;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import genericcode.GenericVariable;

public class DjangoVariable extends GenericVariable {

	public DjangoVariable(OWLOntology o, String iri) {
		super(o, iri);
		// TODO Auto-generated constructor stub
	}
	
	public DjangoVariable(OWLOntology o,OWLNamedIndividual i) {
		super(o, i);
		// TODO Auto-generated constructor stub
	}

}
