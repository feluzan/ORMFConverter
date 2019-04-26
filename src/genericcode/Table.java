package genericcode;

import owlcode.Item;

public class Table extends Item{
	
	GenericClass clazz;
	
	
	public Table(GenericClass c, boolean isEntityTable) {
		if(isEntityTable) {
			 this.iri = "ORMF-O::Entity_Table";
		}else {
			this.iri = "ORMF-O::Relationship_Association_Table";
		}
		this.clazz = c;
		this.setIndividualName();
		
	}


	@Override
	public void setIndividualName() {
		this.individualName = "table__" + this.clazz.getTableName();
	}

}
