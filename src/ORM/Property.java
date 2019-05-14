package ORM;

import owlcode.Item;

public class Property extends Item{
//	private String name;
//	private String iri;
	
//	Node domain;
//	Node range;
	
	public Property (String iri) {
		this.classIRI = iri;
//		this.domain = domain;
//		this.range = range;
	}
	
	public String getAssertion(Item domain, Item range) {
	String ret =	"\t<ObjectPropertyAssertion>\n"
				+		"\t\t<ObjectProperty IRI=\"#" + this.classIRI + "\" />\n"
				+		"\t\t<NamedIndividual IRI=\"#" + domain.getIndividualName() + "\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + range.getIndividualName() + "\"/>\n"
				+ 	"\t</ObjectPropertyAssertion>\n";
		return ret;
	}

	@Override
	public void setIndividualName() {
		// TODO Auto-generated method stub
		
	}
}
