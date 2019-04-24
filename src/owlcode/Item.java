package owlcode;

import com.github.javaparser.ast.Node;

public abstract class Item {
	
	protected String iri;
	protected String name;
	
	
	public String getIri() {
		return iri;
	}

	public String getName() {
		return name;
	}

	public String getDeclaration() {
		String ret ="\t<Declaration>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + this.name + "\"/>\n"
				+	"\t</Declaration>\n";
		return ret;
	}
	
	public String getAssertion() {
		String ret = "\t<ClassAssertion>\n"
				+	"\t\t<Class IRI=\"#" + this.iri + "\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + this.name + "\"/>\n"
				+ "\t</ClassAssertion>\n";
		return ret;
		
	}
}
