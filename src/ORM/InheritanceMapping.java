package ORM;

import genericcode.GenericClass;
import owlcode.Item;

public class InheritanceMapping extends Item{
	
	GenericClass superclass;
	GenericClass subclass;
	
	Property superclassMappedBy = new Property("superclass_mapped_by");
	Property subclassMappedBy = new Property("subclass_mapped_by");
	Property inheritanceMapedTo;
	
	String inheritanceStrategy;
//	Property classMappingTo = new Property("entity_class_mapped_to");
	
	public InheritanceMapping(GenericClass superclass, GenericClass subclass) {
		this.superclass = superclass;
		this.subclass = subclass;
		
		this.inheritanceStrategy = this.superclass.getInheritanceStrategy();
		
		if(inheritanceStrategy.contentEquals("single_table")){
			this.iri = "ORMF-O::Single_Table_Inheritance_Mapping";
			inheritanceMapedTo = new Property("single_table_inheritance_mapped_to");
		}else if (inheritanceStrategy.contentEquals("joined")){
			this.iri = "ORMF-O::Table_per_Class_Inheritance_Mapping";
			inheritanceMapedTo = new Property("table_per_class_inheritance_mapped_to");
		}else {
			this.iri = "ORMF-O::Table_per_Concrete_Class_Inheritance_Mapping";
			inheritanceMapedTo = new Property("table_per_concrete_class_inheritance_mapped_to");
		}
		this.setIndividualName();
		

	}
	
	public String getSuperclassPropertyAssertion() {
		String ret = "";
		ret += this.superclassMappedBy.getAssertion(this.superclass, this);
		return ret;
	}
	
	public String getSubclassPropertyAssertion(GenericClass subclass) {
		String ret = "";
		ret += this.subclassMappedBy.getAssertion(subclass, this);
//		ret += this.inheritanceMapedTo.getAssertion(this,)
		return ret;
	}
	
	public String getPropertyAssertion() {
		String ret = "";
		ret += this.superclassMappedBy.getAssertion(this.superclass, this);
//		ret += this.subclassMappedBy.getAssertion(this.subclass, this);
		return ret;
	}
	
	public String getSubclassAssertion() {
		return "";
	}
	
	public String getAssertion() {
		String ret = "";
//		ret += this.subclass.getSubclassAssertion();
//		ret += this.superclass.getSuperclassAssertion();
		ret += super.getAssertion();
		
		
		
		return ret;	
	}


	@Override
	public void setIndividualName() {
		this.individualName = "inheritance_mapping__" + this.superclass.getIndividualName() + "__" + this.inheritanceStrategy;
		// TODO Auto-generated method stub
		
	}
	

}
