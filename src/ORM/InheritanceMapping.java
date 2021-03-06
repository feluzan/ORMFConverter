package ORM;

import genericcode.GenericClass;
import owlcode.OWLClass;
import owlcode.OWLProperty;

public class InheritanceMapping extends OWLClass{
	
	GenericClass superclass;
	GenericClass subclass;
	
	OWLProperty superclassMappedBy = new OWLProperty("superclass_mapped_by");
	OWLProperty subclassMappedBy = new OWLProperty("subclass_mapped_by");
	OWLProperty inheritanceMappedTo;
	
	String inheritanceStrategy;
	
	public InheritanceMapping(GenericClass superclass, GenericClass subclass) {
		this.superclass = superclass;
		this.subclass = subclass;
		
		this.inheritanceStrategy = this.superclass.getInheritanceStrategy();
		
		if(inheritanceStrategy.contentEquals("single_table")){
			this.classIRI = "ORMF-O::Single_Table_Inheritance_Mapping";
			this.inheritanceMappedTo = new OWLProperty("single_table_inheritance_mapped_to");
		}else if (inheritanceStrategy.contentEquals("joined")){
			this.classIRI = "ORMF-O::Table_per_Class_Inheritance_Mapping";
			this.inheritanceMappedTo = new OWLProperty("table_per_class_inheritance_mapped_to");
		}else {
			this.classIRI = "ORMF-O::Table_per_Concrete_Class_Inheritance_Mapping";
			this.inheritanceMappedTo = new OWLProperty("table_per_concrete_class_inheritance_mapped_to");
		}
		this.setNamedIndividualIRI();
		

	}
	
	public InheritanceMapping(String classIri, String individualName) {
		this.classIRI = classIri;
		this.namedIndividualIRI = individualName;
		
//		System.out.println("---- " + this.namedIndividualIRI);
		this.inheritanceStrategy = this.namedIndividualIRI.split("__")[3];
	}
	
	public String getInheritanceStrategy() {
		return inheritanceStrategy;
	}

	public void setInheritanceStrategy(String inheritanceStrategy) {
		this.inheritanceStrategy = inheritanceStrategy;
	}

	public String getSuperclassPropertyAssertion() {
		String ret = "";
		ret += this.superclassMappedBy.getAssertion(this.superclass, this);
		return ret;
	}
	
	public String getSubclassPropertyAssertion(GenericClass subclass) {
		String ret = "";
		ret += this.subclassMappedBy.getAssertion(subclass, this);

		return ret;
	}
	
	public String getPropertiesAssertion() {
		String ret = "";
		ret += this.superclassMappedBy.getAssertion(this.superclass, this);
		ret += this.subclassMappedBy.getAssertion(this.subclass, this);
		
		ret+=this.inheritanceMappedTo.getAssertion(this, this.subclass.getTable());
		
		if(this.inheritanceStrategy.equals("table_per_class")) {
			GenericClass parentClass = this.superclass;
			ret+=this.inheritanceMappedTo.getAssertion(this, parentClass.getTable());
			while(parentClass.isSubclass()) {
				parentClass = parentClass.getSuperclass();
				ret+=this.inheritanceMappedTo.getAssertion(this, parentClass.getTable());
			}
			
		}
		
		return ret;
	}
	
	public String getSubclassAssertion() {
		return "";
	}
	
//	public String getAssertion() {
//		String ret = "";
//		ret += super.getAssertion();
//		return ret;	
//	}

	public GenericClass getSuperclass() {
		return superclass;
	}

	public void setSuperclass(GenericClass superclass) {
		this.superclass = superclass;
	}

	public GenericClass getSubclass() {
		return subclass;
	}

	public void setSubclass(GenericClass subclass) {
		this.subclass = subclass;
	}

	@Override
	public void setNamedIndividualIRI() {
		this.namedIndividualIRI = "inheritance_mapping__" + this.superclass.getNamedIndividualIRI() + "__" + this.inheritanceStrategy;
		// TODO Auto-generated method stub
		
	}
	

}
