package genericcode;

import java.util.ArrayList;

import owlcode.Item;

public abstract class GenericClass extends Item{
	
	private GenericClass superclass = null;
	
	private String inheritanceStrategy = null;
	protected boolean isAbstract;
	
	public Table table=null;
	
	
	public GenericClass() {
			this.iri = "ORMF-O::Entity_Class";
	}
	
	public void setSuperclass(GenericClass superclass) {
		this.superclass = superclass;
	}
	
	public GenericClass getSuperclass() {
		return this.superclass;
	}
	public boolean isAbstract() {
		return this.isAbstract;
	}
	
	public boolean isMapped() {
//		System.out.println(this.isAbstract());
//		System.out.println(this.inheritanceStrategy);
		if(this.isAbstract() & this.inheritanceStrategy.equals("table_per_class")) return true;
//		if()
		return true;
	}
	public void setTable(Table t) {
		this.table=t;
	}
	
	public Table getTable() {
		return this.table;
	}
	
	public void setIndividualName() {
		this.individualName = "entity_class__" + this.codeName.toLowerCase();
	}

	public abstract String getTableName();
	public abstract String getSuperclassName();
	public abstract String getCodeInheritanceStrategy();
	public abstract void setInheritanceStrategy(GenericClass supremeMother);
	
	public void setInheritanceStragegy(String strategy) {
		this.inheritanceStrategy = strategy;
	}
	
	public String getInheritanceStrategy() {
		return this.inheritanceStrategy;
	}
	
	public String getAssertion() {
		String ret = super.getAssertion();
		if(this.isSubclass()){
			ret += this.getSubclassAssertion();
		}
		return ret;
		
	}
	
	public boolean isSubclass() {
		if(this.getSuperclassName()==null) {
			return false;
		}
		return true;
	}
	
	public String getSubclassAssertion() {
		String ret = "\t<ClassAssertion>\n"
				+	"\t\t<Class IRI=\"#ORMF-O::Entity_Subclass\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + this.individualName + "\"/>\n"
				+ "\t</ClassAssertion>\n";
		return ret;
	}
	
	public String getSuperclassAssertion() {
//		if(this.superclassGettered) {
//			this.superclassGettered = true;
//			return "";
//		}
		String ret = "\t<ClassAssertion>\n"
				+	"\t\t<Class IRI=\"#ORMF-O::Entity_Superclass\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#entity_class__" + this.individualName + "\"/>\n"
				+ "\t</ClassAssertion>\n";
		return ret;
	}
	

}
