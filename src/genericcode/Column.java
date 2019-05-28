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
	
	public Column(String classIri, String individualName) {
		this.classIRI = classIri;
		this.namedIndividualIRI = individualName;
		this.setCodeName();
	
		
	}

	public void setCodeName() {
		String temp = this.namedIndividualIRI.split("__")[1];
		this.codeName = temp;
	}
	
	@Override
	public void setNamedIndividualIRI() {
		if(this.variable.isPk()) {
			this.namedIndividualIRI = "primary_key_column__" + this.codeName;
		}else if(this.variable.isFk()) {
			this.namedIndividualIRI = "foreign_key_column__" + this.codeName;
		}else {
			this.namedIndividualIRI = "column__" + this.codeName;
		}
		
	}

	
	
}
