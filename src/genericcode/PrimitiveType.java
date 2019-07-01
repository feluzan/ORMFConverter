package genericcode;

import org.semanticweb.owlapi.model.OWLOntology;

import OWL.ClassIRI;

public class PrimitiveType extends Type {

	public PrimitiveType(OWLOntology o, String iri) {
		super(o, iri);
		
		this.classAssertion(ClassIRI.PRIMITIVE_TYPE);
		// TODO Auto-generated constructor stub
	}
	
//	public String java2Django() {
//		
//	}

}
