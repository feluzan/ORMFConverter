package owlcode;



public abstract class Item {
	
	//class or object iri
	protected String classIRI;
	
	//namedIndividual iri
	protected String namedIndividualIRI;
	protected String codeName;
	

	public abstract void setIndividualName();
	
	public String getIri() {
		return classIRI;
	}

	public String getIndividualName() {
		return namedIndividualIRI;
	}
	
	public String getCodeName() {
		return codeName;
	}

	public String getDeclaration() {
		String ret ="\t<Declaration>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + this.namedIndividualIRI + "\"/>\n"
				+	"\t</Declaration>\n";
		return ret;
	}
	
	public String getAssertion() {
		String ret = "\t<ClassAssertion>\n"
				+	"\t\t<Class IRI=\"#" + this.classIRI + "\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + this.namedIndividualIRI + "\"/>\n"
				+ "\t</ClassAssertion>\n";
		return ret;
		
	}
}
