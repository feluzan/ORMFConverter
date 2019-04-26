package ORM;

import genericcode.GenericClass;
import owlcode.Item;

public class InheritanceMapping extends Item{
	
	GenericClass superclass;
	GenericClass subclass;
	
	Property superclassMappedBy = new Property("superclass_mapped_by");
	Property subclassMappedBy = new Property("subclass_mapped_by");
//	Property classMappingTo = new Property("entity_class_mapped_to");
	
	public InheritanceMapping(GenericClass superclass, GenericClass subclass) {
		this.superclass = superclass;
		this.subclass = subclass;
		
		String inheritanceStrategy = this.superclass.getInheritanceStrategy();
		
		if(inheritanceStrategy.contentEquals("single_table")){
			this.iri = "ORMF-O::Single_Table_Inheritance_Mapping";
		}else if (inheritanceStrategy.contentEquals("joined")){
			this.iri = "Table_per_Subclass_Inheritance_Mapping";
		}else {
			this.iri = "ORMF-O::Table_per_Class_Inheritance_Mapping";
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public String getPropertyAssertion() {
		String ret = "";
		ret += this.superclassMappedBy.getAssertion(this.superclass, this);
		ret += this.subclassMappedBy.getAssertion(this.subclass, this);
		return ret;
	}
	public String getAssertion() {
		String ret = this.subclass.getSubclassAssertion();
		ret += this.superclass.getSuperclassAssertion();
		ret += super.getAssertion();
		
		
		
		return ret;	
	}


	@Override
	public void setIndividualName() {
		this.individualName = "inheritance_mapping__" + this.superclass.getIndividualName() + "__" + this.subclass.getIndividualName();
		// TODO Auto-generated method stub
		
	}
	

}
