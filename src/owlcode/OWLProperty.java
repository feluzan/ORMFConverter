package owlcode;

public class OWLProperty extends OWLClass{
//	private String name;
//	private String iri;
	
//	Node domain;
//	Node range;
	
	public OWLProperty (String iri) {
		this.classIRI = iri;
//		this.domain = domain;
//		this.range = range;
	}
	
	public String getAssertion(OWLClass domain, OWLClass range) {
	String ret =	"\t<ObjectPropertyAssertion>\n"
				+		"\t\t<ObjectProperty IRI=\"#" + this.classIRI + "\" />\n"
				+		"\t\t<NamedIndividual IRI=\"#" + domain.getNamedIndividualIRI() + "\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + range.getNamedIndividualIRI() + "\"/>\n"
				+ 	"\t</ObjectPropertyAssertion>\n";
		return ret;
	}

	@Override
	public void setNamedIndividualIRI() {
		// TODO Auto-generated method stub
		
	}
}
