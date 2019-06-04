package database;

import org.semanticweb.owlapi.model.OWLOntology;

import OWL.Individual;
import genericcode.GenericClass;
public class Table extends Individual{
	
	private String tableName;
//	private ArrayList<GenericClass> classes;
	private TableType tableType;

	public Table(OWLOntology o, GenericClass gc, TableType tableType) {
		super(o, "table__" + gc.getCodeTableName());
		if(!gc.is_abstract()) gc.getClassMapping().addTable(this);
		this.setTableType(tableType);
//		this.setTableName(gc.getCodeTableName());
		
//		this.classes = new ArrayList<GenericClass>();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

//	public ArrayList<GenericClass> getClasses() {
//		return classes;
//	}
//
//	public void setClasses(ArrayList<GenericClass> classes) {
//		this.classes = classes;
//	}
//
//	public void addClass(GenericClass gc) {
//		this.classes.add(gc);
//	}
//	
	public TableType getTableType() {
		return this.tableType;
	}
	
	public void setTableType(TableType tableType) {
		this.tableType=tableType;
		switch (tableType){
		case SINGLE_ENTITY_TABLE:
			this.classAssertion("#ORMF-O::Single_Entity_Table");
			break;
		case MULTIPLE_ENTITIES_TABLE:
			this.classAssertion("#ORMF-O::Multiple_Entities_Table");
			break;
		case ENTITY_TABLE:
			this.classAssertion("#ORMF-O::Entity_Table");
			break;
		default:
			System.out.println("[ERROR]");
		}
	}
}
