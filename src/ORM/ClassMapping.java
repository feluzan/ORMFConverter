package ORM;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLOntology;

import OWL.Individual;
import database.Table;
import genericcode.GenericClass;

public class ClassMapping extends Individual{
	
	private GenericClass _class;
	private ArrayList<Table> tables = new ArrayList<Table>();
//	private OWLObjectProperty 
	
	public ClassMapping(OWLOntology o, GenericClass gc) {
		super(o, "class_mapping__"+ gc.getCodeName());
		
		this._class = gc;
		gc.setClassMapping(this);
		this.classAssertion("#ORMF-O::Class_Mapping");
	}
	
	public void addTable(Table t) {
		this.tables.add(t);
		this.setProperty("#entity_class_mapped_to", t);
	}

}
