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
		String metaClass = "";
		metaClass+= "\n\tclass Meta:\n";
		
		if(this.isSubclass()) {
			superclass = this.getSuperclass().getCodeName();
			
			switch(this.getInheritanceMapping().getInheritanceStrategy()) {
					
				case "table_per_concrete_class":
					String tableName = this.getTable().getCodeName();
					if(!this.codeName.equals(tableName)) {
						metaClass += "\t\tdb_table = '" + tableName + "'\n";	
					}
					break;
	
				default:
					System.out.println("[WARN]\tEstrategia de herança " + this.getInheritanceMapping().getInheritanceStrategy() + " não suportada.");
					System.out.println("\tPadrão do Django utilizada: Table per Concrete Class.");
					metaClass += "\t\tdb_table = '" + this.codeName + "'\n";
			}
		}else {
			String tableName = this.getTable().getCodeName();
			
			metaClass += "\t\tdb_table = '" + tableName + "'\n";	

		}
		
		ret += "class " + this.codeName + "(" + superclass + "):\n";
		for(GenericVariable v : this.getVariables()) {
			if(v.isMapped()) ret += "\t" + ((DjangoVariable)v).toString();
			
		}


		if(this.isAbstract()) {
			metaClass += "\t\tabstract = True\n";
		}
		metaClass+="\t\tpass\n";
		
		
		ret += metaClass;
		ret+="\n\n";
		
		return ret;
		
	}

}
