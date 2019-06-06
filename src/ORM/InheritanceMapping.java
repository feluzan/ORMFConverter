package ORM;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLOntology;

import OWL.ClassIRI;
import OWL.Individual;
import OWL.PropertyIRI;
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
			this.classAssertion(ClassIRI.SINGLE_TABLE_INHERITANCE_MAPPING);
			break;
			
		case TABLE_PER_CLASS:
			this.classAssertion(ClassIRI.TABLE_PER_CLASS_INHERITANCE_MAPPING);
			break;
			
		case TABLE_PER_CONCRETE_CLASS:
			this.classAssertion(ClassIRI.TABLE_PER_CONCRETE_CLASS_INHERITANCE_MAPPING);
			break;
			
		default:
			System.out.println("[ERROR] InheritanceMapping");
		}
		
		subclass.setProperty(PropertyIRI.SUBCLASS_MAPPED_BY, this);
		subclass.getSuperclass().setProperty(PropertyIRI.SUPERCLASS_MAPPED_BY, this);
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
		PropertyIRI pIRI = null;
		String iri = "#";
		switch(this.inheritanceStrategy) {
		case SINGLE_TABLE:
			pIRI = PropertyIRI.SINGLE_TABLE_INHERITANCE_MAPPED_TO;
			break;
		case TABLE_PER_CLASS:
			pIRI = PropertyIRI.TABLE_PER_CLASS_INHERITANCE_MAPPED_TO;
			break;
		case TABLE_PER_CONCRETE_CLASS:
			pIRI = PropertyIRI.TABLE_PER_CONCRETE_CLASS_INHERITANCE_MAPPED_TO;
			break;
		default:
			break;
		
		}
		this.setProperty(pIRI, t);
	}

	public ArrayList<Table> getTables() {
		return this.tables;
	}
}
