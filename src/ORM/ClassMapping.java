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
		this.setNamedIndividualIRI();
		
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

	public GenericClass getClazz() {
		return clazz;
	}

	public Table getTable() {
		return table;
	}

	public void setClazz(GenericClass clazz) {
		this.clazz = clazz;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	@Override
	public void setNamedIndividualIRI() {
		this.namedIndividualIRI = "class_mapping__" + this.clazz.getNamedIndividualIRI() + "__to__" + this.table.getNamedIndividualIRI();
		
	}
	
}
