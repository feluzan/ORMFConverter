package pythoncode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	public String getColumnCodeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRelationshipType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String typeDeclaration(Type type, ArrayList<String> parameters) {
		String ret = "";
		
		if(this.isPk()) {
			parameters.add("primary_key=True");
			
		}
				
		switch (type.getCodeName()) {  
	       case "int":
	    	   ret = "models.IntegerField"; 
	    	   break;
	       case "long":
	    	   ret = "models.IntegerField"; 
	    	   break;
	       case "Long":
	    	   ret = "models.IntegerField"; 
	    	   break;
	       case "char":
	    	   ret = "models.CharField";
	    	   break;
	       case "date":
	    	   ret = "models.DateField";
	    	   break;
	       case "datetime":
	    	   ret = "models.DateTimeField";
	    	   break;
	       case "float":
	    	   ret = "models.FloatField";
	    	   break;
	       case "String":
	    	   ret = "models.CharField";
	    	   parameters.add("max_length=255");
	    	   break;
	       
	       default:
	    	   
	    	   if(type instanceof GenericClass) {
	    		   String relationshipType = this.getRelationshipMapping().getType();
	    		   
	    		   switch(relationshipType){
	    		   		case "o2o":
	    		   			ret = "models.OneToOneField";
	    		   			parameters.add("'" + type.getCodeName() + "'");
	    		   			parameters.add("on_delete=models.CASCADE");
	    		   			break;
	    		   			
	    		   		case "o2m":
	    		   			ret = "models.IntegerField"; 
	    		   			System.out.println("[WARN] Tipo " + type.getCodeName() + " mapeado para Integer."); 
	    		   			break;
	    		   			
	    		   		case "m2o":
	    		   			ret = "models.ForeignKey";
	    		   			parameters.add("'" + type.getCodeName() + "'");
	    		   			parameters.add("on_delete=models.CASCADE");
	    		   			break;
	    		   			
	    		   		case "m2m":
	    		   			ret = "models.ManyToManyField";
	    		   			parameters.add("'" + type.getCodeName() + "'");
	    		   			break;
	    		   			
	    		   		default:
	    		   			System.out.println("[ERROR] Relationship Mapping incompatível!");
	    		   }
		   		}else {
		    	   ret = "models.IntegerField"; 
		    	   System.out.println("[WARN] Tipo " + type.getCodeName() + " mapeado para Integer."); 
		   		}
		}
		
		return ret;
	}
	
	public String toString() {
		String ret = "";
		Type type = this.getValueType().getType();
		ArrayList<String> parameters = new ArrayList<String>();
		String variableCodeName = this.getCodeName();
		
		ret += variableCodeName + " = ";
		ret += this.typeDeclaration(type, parameters);
		
		String columnName = this.getVariableMapping().getColumn().getCodeName();
		columnName = columnName.split("\\.")[1];
		if(!variableCodeName.equals(columnName)){
			parameters.add("db_column='" + columnName  +"'");
		}
		
		ret += "(" + String.join(", ", parameters) + ")";
		ret += "\n";
		
		
		return ret;
	}

	@Override
	public String getAssociationTableName() {
		// TODO Auto-generated method stub
		return null;
	}
}
