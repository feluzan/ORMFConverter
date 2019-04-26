package genericcode;

import owlcode.Item;

public class Column extends Item {
	
	GenericField field;
	
	public Column (GenericField f) {
		if(f.isPk()) {
			this.iri = "RDBS-O::Primary_Key_Column";
		}else if(f.isFk()) {
			this.iri = "RDBS-O::Foreign_Key_Column";
		}else {
			this.iri = "RDBS-O::Column";
		}
		
		this.codeName = f.getColumnCodeName();
		this.field = f;
		this.setIndividualName();
//		this.setIndividualName(f);
	}
	
//	private void setIndividualName(GenericField f) {
//		this.individualName = this.codeName + "__column__" + f.getIndividualName();
//		
//	}

	@Override
	public void setIndividualName() {
		this.individualName = "column__" + this.codeName + "__" + this.field.getIndividualName();
		// TODO Auto-generated method stub
		
	}
	
//	private String genIndividualName() {
//		
//	}
	
	
}
