package pythoncode;

import genericcode.GenericClass;

public class PythonClass extends GenericClass {
	
	public PythonClass(String codeName) {
		this.codeName = codeName;
	}

	public String getLikeCode() {
	 String ret = "";
	 ret+="class " + this.codeName + "(models.Model):\n";
	 
	 return ret;
	}
	
	@Override
	public void setNamedIndividualIRI() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSuperclassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCodeInheritanceStrategy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInheritanceStrategy(GenericClass supremeMother) {
		// TODO Auto-generated method stub
		
	}

}
