package genericcode;

import org.semanticweb.owlapi.model.OWLOntology;

public class PrimitiveType extends Type {

	public PrimitiveType(OWLOntology o, String iri) {
		super(o, iri);
		
		this.classAssertion("#OOC-O::Primitive_Type");
		// TODO Auto-generated constructor stub
	}

}
