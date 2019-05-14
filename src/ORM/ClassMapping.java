package ORM;


import genericcode.GenericClass;
import genericcode.Table;
import owlcode.Item;

public class ClassMapping extends Item{
	GenericClass clazz;
	Table table;
	Property entityClassMappedBy = new Property("entity_class_mapped_by");
	Property classMappingTo = new Property("entity_class_mapped_to");
//	String name;
	
	public ClassMapping (GenericClass c, Table t) {
		this.classIRI = "ORMF-O::Class_Mapping";
		this.clazz = c;
//		System.out.println("---");
		this.table = t;
		this.setIndividualName();
		
	}
	
	public ClassMapping(String classIri, String individualName) {
		this.classIRI = classIri;
		this.namedIndividualIRI = individualName;
	}
	
	
	public String getPropertiesAssertion() {
		String ret = "";
		ret+= entityClassMappedBy.getAssertion(this.clazz, this);
		ret+=classMappingTo.getAssertion(this, this.table);
		return ret;
	}
	

//	public Table getTable() {
//		return this.table;
//	}

	@Override
	public void setIndividualName() {
		this.namedIndividualIRI = "class_mapping__" + this.clazz.getIndividualName() + "__to__" + this.table.getIndividualName();
		
	}
	
}
