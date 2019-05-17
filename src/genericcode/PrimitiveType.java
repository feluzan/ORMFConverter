package genericcode;

public class PrimitiveType extends Type {
	
	
	public PrimitiveType(String typeName) {
		this.classIRI = "OOC-O::Primitive_Type";
		this.codeName = typeName;
		this.setNamedIndividualIRI();
	}
	
	public PrimitiveType(String classIri, String individualName) {
		this.classIRI = classIri;
		this.namedIndividualIRI = individualName;
		this.setCodeName();
	}
	
	public void setCodeName() {
		this.codeName = this.namedIndividualIRI.split("__")[1];
	}

	@Override
	public void setNamedIndividualIRI() {
		this.namedIndividualIRI = "primitive_type__" + this.codeName;
		// TODO Auto-generated method stub

	}

}
