package ORM;

import com.github.javaparser.ast.Node;
import owlcode.Item;

public class Property extends Item{
//	private String name;
//	private String iri;
	
//	Node domain;
//	Node range;
	
	public Property (String iri) {
		this.iri = iri;
//		this.domain = domain;
//		this.range = range;
	}
	
	public String getAssertion(Item domain, Item range) {
	String ret =	"\t<ObjectPropertyAssertion>\n"
				+		"\t\t<ObjectProperty IRI=\"#" + this.iri + "\" />\n"
				+		"\t\t<NamedIndividual IRI=\"#" + domain.getName() + "\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + range.getName() + "\"/>\n"
				+ 	"\t</ObjectPropertyAssertion>";
		return ret;
	}
}
