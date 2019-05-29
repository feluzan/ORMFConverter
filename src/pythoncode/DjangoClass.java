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
		if(this.isSubclass()) {
			superclass = this.getSuperclass().getCodeName();
			
			switch(this.getInheritanceMapping().getInheritanceStrategy()) {
				
			case "single_table":
				System.out.println("[WARN] Estratégia padrão do Django utilizada: Table per Class.");
				break;
				
			case "table_per_concrete_class":
				System.out.println("[WARN] Estratégia padrão do Django utilizada: Table per Class.");
				break;
				
			case "table_per_class":
//				System.out.println("single_table");
				break;
				
			default:
				System.out.println("Estrategia nao considerada: " + this.getInheritanceMapping().getInheritanceStrategy());
				System.out.println("[WARN] Estratégia padrão do Django utilizada: Table per Class.");
			}
		}
		ret += "class " + this.codeName + "(" + superclass + "):\n";
		for(GenericVariable v : this.getVariables()) {
			if(v.isMapped()) ret += "\t" + ((DjangoVariable)v).toString();
			
		}
		
		
		String tableName = this.getTable().getCodeName();
		String metaClass = "";
		metaClass+= "\n\tclass Meta:\n";
//		metaClass+= "\t\tdb_table = '" + tableName + "'\n";
		if(this.isAbstract()) {
//			System.out.println("---------------------------" + this.isAbstract());
			metaClass += "\t\tabstract = True\n";
		}
		metaClass+="\t\tpass\n";
		
		
		ret += metaClass;
		ret+="\n\n";
		
		return ret;
		
	}

}
