package genericcode;

import java.util.ArrayList;

import owlcode.Item;

public class Table extends Item{
	
	ArrayList<GenericClass> classes = new ArrayList<GenericClass>();
	
	
	public Table(GenericClass c, boolean isEntityTable) {
		if(isEntityTable) {
			 this.iri = "ORMF-O::Entity_Table";
		}else {
			this.iri = "ORMF-O::Relationship_Association_Table";
		}
		this.classes.add(c);
		this.setIndividualName();
		this.codeName = c.getTableName();
		c.setTable(this);
		
	}


	@Override
	public void setIndividualName() {
		this.individualName = "table__" + this.classes.get(0).getTableName();
	}
	
//	public void set

}
