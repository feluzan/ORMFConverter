package owlcode;



public abstract class Item {
	
	protected String iri;
	protected String individualName;
	protected String codeName;
	

	public abstract void setIndividualName();
	
	public String getIri() {
		return iri;
	}

	public String getIndividualName() {
		return individualName;
	}
	
	public String getCodeName() {
		return codeName;
	}

	public String getDeclaration() {
		String ret ="\t<Declaration>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + this.individualName + "\"/>\n"
				+	"\t</Declaration>\n";
		return ret;
	}
	
	public String getAssertion() {
		String ret = "\t<ClassAssertion>\n"
				+	"\t\t<Class IRI=\"#" + this.iri + "\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + this.individualName + "\"/>\n"
				+ "\t</ClassAssertion>\n";
		return ret;
		
	}
}
