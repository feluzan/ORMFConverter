package ORM;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLOntology;

import OWL.Individual;
import genericcode.GenericClass;
import database.Table;


public class InheritanceMapping extends Individual{
	
	private GenericClass superclass;
	private GenericClass subclass;
	private InheritanceStrategy inheritanceStrategy;
	private ArrayList<Table> tables;// = new ArrayList<Table();
	
	public InheritanceMapping(OWLOntology o, GenericClass subclass) {
		super(o, "inheritance_mapping__" + subclass.getSuperclass().getCodeName() + "_" + subclass.getCodeName());
		this.tables = new ArrayList<Table>();
		GenericClass rootClass = subclass.getSuperclass();
		while(rootClass.isSubclass()) {
			rootClass = rootClass.getSuperclass();
		}
		
		this.inheritanceStrategy = rootClass.getCodeInheritanceStrategy();
		switch(this.inheritanceStrategy) {
		case SINGLE_TABLE:
			this.classAssertion("#ORMF-O::Single_Table_Inheritance_Mapping");
			break;
			
		case TABLE_PER_CLASS:
			this.classAssertion("#ORMF-O::Table_per_Class_Inheritance_Mapping");
			break;
			
		case TABLE_PER_CONCRETE_CLASS:
			this.classAssertion("#ORMF-O::Table_per_Concrete_Class_Inheritance_Mapping");
			break;
			
		default:
			System.out.println("[ERROR] InheritanceMapping");
		}
		
		subclass.setProperty("#subclass_mapped_by", this);
		subclass.getSuperclass().setProperty("#superclass_mapped_by", this);
	}
	

	public InheritanceStrategy getInheritanceStrategy() {
		return inheritanceStrategy;
	}
	public void setInheritanceStrategy(InheritanceStrategy inheritanceStrategy) {
		this.inheritanceStrategy = inheritanceStrategy;
	}
	public GenericClass getSuperclass() {
		return superclass;
	}
	public void setSuperclass(GenericClass superclass) {
		this.superclass = superclass;
	}

	public GenericClass getSubclass() {
		return subclass;
	}
	public void setSubclass(GenericClass subclass) {
		this.subclass = subclass;
	}

	
	public void addTable(Table t) {
		this.tables.add(t);
		String iri = "#";
		switch(this.inheritanceStrategy) {
		case SINGLE_TABLE:
			iri += "single_table_inheritance_mapped_to";
			break;
		case TABLE_PER_CLASS:
			iri += "table_per_class_inheritance_mapped_to";
			break;
		case TABLE_PER_CONCRETE_CLASS:
			iri += "table_per_concrete_class_inheritance_mapped_to";
			break;
		default:
			break;
		
		}
		this.setProperty(iri, t);
	}

	public ArrayList<Table> getTables() {
		return this.tables;
	}
}
