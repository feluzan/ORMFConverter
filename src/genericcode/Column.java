package genericcode;

import owlcode.Item;

public class Column extends Item {
	
	GenericVariable variable;
	
	public Column (GenericVariable v) {
		if(v.isPk()) {
			this.classIRI = "RDBS-O::Primary_Key_Column";
		}else if(v.isFk()) {
			this.classIRI = "RDBS-O::Foreign_Key_Column";
		}else {
			this.classIRI = "RDBS-O::Column";
		}
		
		this.codeName = this.variable.getClazz().getTable().getCodeName() + "." + v.getColumnCodeName();
		this.variable = v;
		this.setNamedIndividualIRI();
		v.setColumn(this);
//		this.setIndividualName(f);
	}
	
//	private void setIndividualName(GenericField f) {
//		this.individualName = this.codeName + "__column__" + f.getIndividualName();
//		
//	}

	@Override
	public void setNamedIndividualIRI() {
		this.namedIndividualIRI = "column__" + this.codeName;
		// TODO Auto-generated method stub
		
	}
	
//	private String genIndividualName() {
//		
//	}
	
	
}
