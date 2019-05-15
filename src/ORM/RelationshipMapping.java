package ORM;

import genericcode.GenericClass;
import genericcode.GenericVariable;
import owlcode.Item;

public class RelationshipMapping extends Item{
	
	GenericClass source;
	GenericClass target;
	GenericVariable variable;
	String type;
	
	Property relationshipSourceMappedBy = new Property("relationship_source_mapped_by");
	Property relationshipTargetMappedBy = new Property("relationship_target_mapped_by");
	Property relationshipMappedTo = new Property("relationship_mapped_to");
	
	public RelationshipMapping (GenericClass source, GenericClass target, GenericVariable v) {
		
		
		this.source = source;
		this.target = target;
		this.type = v.getRelationshipType();
		this.variable = v;
		this.setNamedIndividualIRI();
		if(this.type.equals("o2o")) {
			this.classIRI = "ORMF-O::One_To_One_Relationship_Mapping";
//			relationshipMappedTo = new Property("one_to_one_mapped_to");
		}else if(this.type.equals("o2m")) {
			this.classIRI = "ORMF-O::One_To_Many_Relationship_Mapping";
//			relationshipMappedTo = new Property("one_to_many_mapped_to");
		}else if(this.type.equals("m2o")) {
			this.classIRI = "ORMF-O::Many_To_One_Relationship_Mapping";
//			relationshipMappedTo = new Property("many_to_one_mapped_to");
		}else {
			this.classIRI = "ORMF-O::Many_To_Many_Relationship_Mapping";
//			relationshipMappedTo = new Property("many_to_many_mapped_to");
		}		
//		System.out.println("-----Relationship Mapping------");
//		System.out.println(source.getCodeName() + " to " + range.getCodeName() + " -> " + this.type);
		
	}

	public RelationshipMapping(String classIri, String individualName) {
		this.classIRI = classIri;
		this.namedIndividualIRI = individualName;
	}
	
	public String getPropertiesAssertion() {
		String ret = "";
		ret += this.relationshipSourceMappedBy.getAssertion(this.source,this);
		ret += this.relationshipTargetMappedBy.getAssertion(this.target,this);
		ret += this.relationshipMappedTo.getAssertion(this,this.target.getTable());
		ret += this.relationshipMappedTo.getAssertion(this,this.source.getTable());
		if(this.type.equals("m2m")) {
			//TODO incuir mapeamento com relationship association table
		}
		return ret;
	}
	
	public GenericClass getSource() {
		return source;
	}

	public GenericClass getTarget() {
		return target;
	}

	public GenericVariable getVariable() {
		return variable;
	}

	public String getType() {
		return type;
	}

	@Override
	public void setNamedIndividualIRI() {
		String name = "";
		if(this.type.equals("o2o")) {
			name += "one_to_one___";
		}else if(this.type.equals("o2m")) {
			name += "one_to_many__";
		}else if(this.type.equals("m2o")) {
			name += "many_to_one__";
		}else {
			name += "many_to_many__";
		}
		name += this.variable.getCodeName() + "__" + this.target.getCodeName();
		this.namedIndividualIRI = name;
		
	}

	public void setSource(GenericClass source) {
		this.source = source;
	}

	public void setTarget(GenericClass range) {
		this.target = range;
	}

}
