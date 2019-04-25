package genericcode;


import java.util.List;

import owlcode.Item;

public class GenericClass extends Item{
	
	
	
	public GenericClass() {
			
			this.iri = "ORMF-O::Entity_Class";
	
	}
	
	protected void setIndividualName() {
		this.individualName = this.codeName.toLowerCase();
	}
	
	
}
