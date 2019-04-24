package ORM;

import com.github.javaparser.ast.Node;
import writer.Writer;

public class Property extends Writer{
//	private String name;
//	private String iri;
	
//	Node domain;
//	Node range;
	
	public Property (String iri) {
		this.iri = iri;
//		this.domain = domain;
//		this.range = range;
	}
	
	public String getAssertion(Writer domain, Writer range) {
	String ret =	"\t<ObjectPropertyAssertion>\n"
				+		"\t\t<ObjectProperty IRI=\"#" + this.iri + "\" />\n"
				+		"\t\t<NamedIndividual IRI=\"#" + domain.getName() + "\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + range.getName() + "\"/>\n"
				+ 	"\t</ObjectPropertyAssertion>";
		return ret;
	}
}
