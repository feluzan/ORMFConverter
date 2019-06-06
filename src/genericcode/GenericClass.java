package genericcode;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;

import ORM.ClassMapping;
import ORM.InheritanceMapping;
import ORM.InheritanceStrategy;
import OWL.ClassIRI;
import OWL.PropertyIRI;

//import ORM.InheritanceMapping;

public abstract class GenericClass extends Type{
	


	private String codeName;
	private boolean _abstract;
	private boolean entity;
	private GenericClass superclass;
	private ArrayList<GenericClass> subclasses;
	private ClassMapping classMapping;
//	private OWLObjectProperty classMappedBy;

	public GenericClass(OWLOntology o, String iri) {
		super(o, iri);
		this.subclasses = new ArrayList<GenericClass>();
	}
	
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	
	public boolean is_abstract() {
		return _abstract;
	}
	public void set_abstract(boolean _abstract) {
		this._abstract = _abstract;
	}
	
	
	public boolean isEntity() {
		return entity;
	}
	public void setEntity(boolean entity) {
		this.entity = entity;
	}
	
	
	public GenericClass getSuperclass() {
		return superclass;
	}
	public void setSuperclass(GenericClass superclass) {
		this.superclass = superclass;
		if(this.isEntity()) {
			this.classAssertion(ClassIRI.ENTITY_SUBCLASS);
		}else {
			this.classAssertion(ClassIRI.SUBCLASS);
		}
	}
	public boolean isSubclass() {
		if(this.superclass==null) return false;
		return true;
	}
	
	
	public abstract String getSuperclassName();
	public abstract InheritanceStrategy getCodeInheritanceStrategy();
	public abstract String getCodeTableName();
	
	
	public boolean isSuperclass() {
		if(this.subclasses.size()==0) return false;
		return true;
	}
	public void addSubclass(GenericClass gc) {
		this.subclasses.add(gc);
	}
	public ArrayList<GenericClass> getSubclasses(){
		return this.subclasses;
	}
	
	
	public void setClassMapping(ClassMapping cm) {
		this.classMapping = cm;
		this.setProperty(PropertyIRI.ENTITY_CLASS_MAPPED_BY, cm);
	}
	public ClassMapping getClassMapping() {
		return this.classMapping;
	}
//	public void setSuperclass(boolean isSuperclass) {
//		this.isSuperclass = isSuperclass;
//		if(isSuperclass){
//			
//			if(this.isEntity()) {
//				this.classAssertion("#ORMF-O::Entity_Superclass");
//			}else {
//				this.classAssertion("#OOC-O::Superclass");
//			}
//			
//		}
//	}
	
	
//	public GenericClass(String codeName) {
//		this.codeName = codeName;
//	}
	

	
	
	
	
//	private GenericClass superclass = null;
//	private String inheritanceStrategy = null;
//	private InheritanceMapping inheritanceMapping = null;protected boolean isAbstract;
//	boolean isSuperclas = false;
//	boolean isSubclass = false;
//	public Table table=null;
//	ArrayList<GenericVariable> variables = new ArrayList<GenericVariable>();
	
	
	
//	public GenericClass() {
////		this.classIRI = "ORMF-O::Entity_Class";
//	}
	
//	public void setNamedIndividualIRI() {
//		OWL
//		this.
		
//		this.namedIndividualIRI = "entity_class__" + this.codeName;
//	}
	
	
//	public InheritanceMapping getInheritanceMapping() {
//		return inheritanceMapping;
//	}
//
//	public void setInheritanceMapping(InheritanceMapping inheritanceMapping) {
//		this.inheritanceMapping = inheritanceMapping;
//	}
//
//	
//	
//	
//	public abstract String getTableName();

//	public abstract String getCodeInheritanceStrategy();
//	public abstract void setInheritanceStrategy(GenericClass supremeMother);
//
//	public void addVariable(GenericVariable f) {
//		this.variables.add(f);
//	}
//	
//	public ArrayList<GenericVariable> getVariables(){
//		return this.variables;
//	}
//	
//	public GenericVariable getVariable(int i) {
//		return this.variables.get(i);
//	}
//	
//	public void setSuperclass(GenericClass superclass) {
//		this.superclass = superclass;
//		this.isSubclass = true;
//		superclass.setIsSuperclass(true);
//	}
//	
//	public void setIsSuperclass(boolean b) {
//		this.isSuperclas=b;
//	}
//	
//	public void setIsSubclass(boolean b) {
//		this.isSubclass = b;
//	}
//	
//	public GenericClass getSuperclass() {
//		return this.superclass;
//	}
//	
//	public boolean isAbstract() {
//		return this.isAbstract;
//	}
//	
//	public void setIsAbstract(boolean b) {
//		this.isAbstract = b;
//	}
//	
//	public boolean isMapped() {
//		
//		if(!this.isAbstract()) return true;
////		System.out.println(this.isAbstract());
////		System.out.println(this.getCodeName());
//		if(this.isAbstract() & this.inheritanceStrategy.equals("table_per_class")) return true;
////		if()
//		return true;
//	}
//	
//	public void setTable(Table t) {
//		this.table=t;
//	}
//	
//	public Table getTable() {
//		return this.table;
//	}
//	

//
//	public void setInheritanceStragegy(String strategy) {
//		this.inheritanceStrategy = strategy;
//	}
//	
//	public String getInheritanceStrategy() {
//		return this.inheritanceStrategy;
//	}
//	
//	public String getAssertion() {
//		String ret = super.getAssertion();
//		if(this.isSubclass()){
//			ret += this.getSubclassAssertion();
//		}
//		if(isSuperclass()) {
//			ret+=this.getSuperclassAssertion();
//		}
//		if(this.isAbstract()) {
//			ret+=this.getAbstractAssertion();
//		}
//		return ret;
//		
//	}
//	
//	public boolean isSubclass() {
//		return this.isSubclass;
//	}
//	
//	public boolean isSuperclass() {
//		return this.isSuperclas;
//	}
//
//	public String getSubclassAssertion() {
//		String ret = "\t<ClassAssertion>\n"
//				+	"\t\t<Class IRI=\"#ORMF-O::Entity_Subclass\"/>\n"
//				+		"\t\t<NamedIndividual IRI=\"#" + this.namedIndividualIRI + "\"/>\n"
//				+ "\t</ClassAssertion>\n";
//		return ret;
//	}
//	
//	public String getAbstractAssertion() {
//		String ret = "\t<ClassAssertion>\n"
//				+	"\t\t<Class IRI=\"#OOC-O::Abstract_Class\"/>\n"
//				+		"\t\t<NamedIndividual IRI=\"#" + this.namedIndividualIRI + "\"/>\n"
//				+ "\t</ClassAssertion>\n";
//		return ret;
//	}
//	
//	public String getSuperclassAssertion() {
//		String ret = "\t<ClassAssertion>\n"
//				+	"\t\t<Class IRI=\"#ORMF-O::Entity_Superclass\"/>\n"
//				+		"\t\t<NamedIndividual IRI=\"#entity_class__" + this.namedIndividualIRI + "\"/>\n"
//				+ "\t</ClassAssertion>\n";
//		return ret;
//	}
//	

}
