package owlcode;



public abstract class Item {
	
	protected String classIRI;
	
	protected String namedIndividualIRI;
	protected String codeName;
	

	public abstract void setNamedIndividualIRI();
	

	public String getIri() {
		return classIRI;
	}

	public String getNamedIndividualIRI() {
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
