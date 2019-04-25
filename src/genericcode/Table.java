package genericcode;

import owlcode.Item;

public class Table extends Item{
	
	
	public Table(String name, boolean isEntityTable) {
		if(isEntityTable) {
			 this.iri = "ORMF-O::Entity_Table";
		}else {
			this.iri = "ORMF-O::Relationship_Association_Table";
		}
		this.individualName = name;
		
	}

}
