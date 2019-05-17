package pythoncode;

import genericcode.GenericClass;
import genericcode.GenericVariable;
import genericcode.PrimitiveType;
import genericcode.Type;

public class DjangoVariable extends GenericVariable {
	

	public DjangoVariable(String classIri, String individualName) {
		this.classIRI = classIri;
		this.namedIndividualIRI = individualName;
		this.setCodeName();
		
//		System.out.println(individualName);
		
	}
	
	public void setCodeName() {
		String name = this.namedIndividualIRI.split("\\.")[1];
		this.codeName = name;
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

	public String typeDeclaration(Type type) {
		String ret = "";
		String pk = "";
		if(this.isPK) pk = "primary_key=True";
		
		if(type instanceof GenericClass) {
			ret = "models.ForeignKey('";
			ret += type.getCodeName();
			ret += "', on_delete=models.CASCADE)";
			return ret;
		}else {
			if(type.getCodeName().equals("int")) ret = "models.IntegerField";
			if(type.getCodeName().equals("char")) ret = "models.CharField";
			if(type.getCodeName().equals("date")) ret = "models.DateField";
			if(type.getCodeName().equals("datetime")) ret = "models.DateTimeField";
			if(type.getCodeName().equals("float")) ret = "models.FloatField";
		}
		
		
		ret += "(" + pk + ")";
		
		return ret;
	}
	public String toString() {
		String ret = "";
		Type type=null;
		ret += this.getCodeName() + " = " + this.typeDeclaration(this.getValueType().getType()) + "\n";
		
		return ret;
	}
}
