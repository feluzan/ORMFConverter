package genericcode;

import owlcode.OWLClass;

public class Column extends OWLClass {
	
	GenericVariable variable;
	
	public Column (GenericVariable v) {
		this.variable = v;
		if(v.isPk()) {
			this.classIRI = "RDBS-O::Primary_Key_Column";
		}else if(v.isFk()) {
			this.classIRI = "RDBS-O::Foreign_Key_Column";
		}else {
			this.classIRI = "RDBS-O::Column";
		}
		
//		System.out.println(this.variable.getClazz().getTable().getCodeName());
//		System.out.println(v.getColumnCodeName());
		
		this.codeName = this.variable.getClazz().getTable().getCodeName() + "." + v.getColumnCodeName();
		
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
