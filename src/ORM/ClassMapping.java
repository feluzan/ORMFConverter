package ORM;


import genericcode.GenericClass;
import javacode.JavaClass;
import owlcode.Item;

public class ClassMapping extends Item{
	GenericClass c;
	String table;
//	String name;
	
	public ClassMapping (JavaClass c) {
		this.iri = "ORMF-O::Class_Mapping";
		this.c = c;
		this.individualName = nameGenerator();
		this.table = c.getTableName();
//		System.out.println(this.table);
		
	}
	
	

	public String getTable() {
		return this.table;
	}


	private String nameGenerator() {
		return "class_mapping_" + c.getIndividualName();
	}
	
}
