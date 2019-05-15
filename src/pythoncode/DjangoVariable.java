package pythoncode;

import genericcode.GenericVariable;

public class DjangoVariable extends GenericVariable {

	public DjangoVariable(String classIri, String individualName) {
		this.classIRI = classIri;
		this.namedIndividualIRI = individualName;
	}

	@Override
	public boolean isMapped() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPk() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFk() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getColumnCodeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRelationshipType() {
		// TODO Auto-generated method stub
		return null;
	}

}
