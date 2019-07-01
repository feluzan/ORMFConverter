package django;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import ORM.InheritanceStrategy;
import genericcode.GenericClass;
import genericcode.GenericVariable;

public class DjangoClass extends GenericClass {

	public DjangoClass(OWLOntology o, OWLNamedIndividual i) {
		super(o, i);
//		i.
	}

	@Override
	public String getSuperclassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InheritanceStrategy getCodeInheritanceStrategy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCodeTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toCode() {
		String ret = "";
		
		String superclass = "models.Model";
		
		if(this.isSubclass()) {
			superclass = this.getSuperclass().getCodeName();
			
//			switch(this.getInheritanceMapping().getInheritanceStrategy()) {
//					
//				case "table_per_concrete_class":
//					String tableName = this.getTable().getCodeName();
//					if(!this.codeName.equals(tableName)) {
//						metaClass += "\t\tdb_table = '" + tableName + "'\n";	
//					}
//					break;
//	
//				default:
//					System.out.println("[WARN]\tEstrategia de herança " + this.getInheritanceMapping().getInheritanceStrategy() + " não suportada.");
//					System.out.println("\tPadrão do Django utilizada: Table per Concrete Class.");
//					metaClass += "\t\tdb_table = '" + this.codeName + "'\n";
//			}
		}else {
//			String tableName = this.getClassMapping(). ().getCodeName();
			
//			metaClass += "\t\tdb_table = '" + tableName + "'\n";	

		}
		
		ret += "class " + this.getCodeName() + "(" + superclass + "):\n";
		
		for(GenericVariable v : this.getVariables()) {
			if(v.isMapped()) ret += "\t" + ((DjangoVariable)v).toString();
			
		}
		
		return ret;
	}
}
