package pythoncode;

import genericcode.GenericClass;
import genericcode.GenericVariable;

public class DjangoClass extends GenericClass {
	
	public DjangoClass(String classIRI, String namedIndividualIRI) {
		this.classIRI = classIRI;
		this.namedIndividualIRI = namedIndividualIRI;
		this.codeName = namedIndividualIRI.replace("entity_class__", "");
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

	
	public String toString() {
		String ret = "";
		
		String superclass = "models.Model";
		if(this.isSubclass()) superclass = this.getSuperclass().getCodeName();
		ret += "class " + this.codeName + "(" + superclass + "):\n";
		for(GenericVariable v : this.getVariables()) {
			
			ret += "\t" + ((DjangoVariable)v).toString();
			
		}
		
		ret+="\n";
		
		return ret;
		
	}

}
