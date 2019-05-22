package genericcode;

import java.util.ArrayList;

import ORM.InheritanceMapping;

public abstract class GenericClass extends Type{
	
	private GenericClass superclass = null;
	private String inheritanceStrategy = null;
	private InheritanceMapping inheritanceMapping = null;
	public InheritanceMapping getInheritanceMapping() {
		return inheritanceMapping;
	}

	public void setInheritanceMapping(InheritanceMapping inheritanceMapping) {
		this.inheritanceMapping = inheritanceMapping;
	}

	protected boolean isAbstract;
	boolean isSuperclas = false;
	boolean isSubclass = false;
	public Table table=null;
	ArrayList<GenericVariable> variables = new ArrayList<GenericVariable>();
	
	
	public GenericClass() {
			this.classIRI = "ORMF-O::Entity_Class";
	}
	
	public abstract String getTableName();
	public abstract String getSuperclassName();
	public abstract String getCodeInheritanceStrategy();
	public abstract void setInheritanceStrategy(GenericClass supremeMother);

	public void addVariable(GenericVariable f) {
		this.variables.add(f);
	}
	
	public ArrayList<GenericVariable> getVariables(){
		return this.variables;
	}
	
	public GenericVariable getVariable(int i) {
		return this.variables.get(i);
	}
	
	public void setSuperclass(GenericClass superclass) {
		this.superclass = superclass;
		this.isSubclass = true;
		superclass.setIsSuperclass(true);
	}
	
	public void setIsSuperclass(boolean b) {
		this.isSuperclas=b;
	}
	
	public void setIsSubclass(boolean b) {
		this.isSubclass = b;
	}
	
	public GenericClass getSuperclass() {
		return this.superclass;
	}
	
	public boolean isAbstract() {
		return this.isAbstract;
	}
	
	public boolean isMapped() {
		
		if(!this.isAbstract()) return true;
//		System.out.println(this.isAbstract());
//		System.out.println(this.getCodeName());
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
	
	public void setNamedIndividualIRI() {
		this.namedIndividualIRI = "entity_class__" + this.codeName;
	}

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
		if(isSuperclass()) {
			ret+=this.getSuperclassAssertion();
		}
		return ret;
		
	}
	
	public boolean isSubclass() {
		return this.isSubclass;
	}
	
	public boolean isSuperclass() {
		return this.isSuperclas;
	}

	public String getSubclassAssertion() {
		String ret = "\t<ClassAssertion>\n"
				+	"\t\t<Class IRI=\"#ORMF-O::Entity_Subclass\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#" + this.namedIndividualIRI + "\"/>\n"
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
				+		"\t\t<NamedIndividual IRI=\"#entity_class__" + this.namedIndividualIRI + "\"/>\n"
				+ "\t</ClassAssertion>\n";
		return ret;
	}
	

}
