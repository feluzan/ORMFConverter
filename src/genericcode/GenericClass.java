package genericcode;

import owlcode.Item;

public abstract class GenericClass extends Item{
	
	private boolean superclassGettered = false;
	
	
	
	public GenericClass() {
			
			this.iri = "ORMF-O::Entity_Class";
	
	}
	
	public void setIndividualName() {
		this.individualName = "entity_class__" + this.codeName.toLowerCase();
	}

	public abstract String getTableName();
	public abstract String getSuperclass();
	public abstract String getInheritanceStrategy();
	
	public String getAssertion() {
		String ret = super.getAssertion();
		if(this.isSubclass()){
			ret += this.getSubclassAssertion();
		}
		return ret;
		
	}
	
	public boolean isSubclass() {
		if(this.getSuperclass()==null) {
			return false;
		}
		return true;
	}
	
	public String getSubclassAssertion() {
		String ret = "\t<ClassAssertion>\n"
				+	"\t\t<Class IRI=\"#OOC-O::Entity_Subclass\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + this.individualName + "\"/>\n"
				+ "\t</ClassAssertion>\n";
		return ret;
	}
	
	public String getSuperclassAssertion() {
		if(this.superclassGettered) {
			this.superclassGettered = true;
			return "";
		}
		String ret = "\t<ClassAssertion>\n"
				+	"\t\t<Class IRI=\"#OOC-O::Entity_Superclass\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#entity_class__" + this.individualName + "\"/>\n"
				+ "\t</ClassAssertion>\n";
		return ret;
	}
	

}
