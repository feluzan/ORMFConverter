package genericcode;

public class PrimitiveType extends Type {
	
	
	public PrimitiveType(String typeName) {
		this.classIRI = "OOC-O::Primitive_Type";
		this.codeName = typeName;
		this.setNamedIndividualIRI();
	}

	@Override
	public void setNamedIndividualIRI() {
		this.namedIndividualIRI = "primitive_type__" + this.codeName;
		// TODO Auto-generated method stub

	}

}
