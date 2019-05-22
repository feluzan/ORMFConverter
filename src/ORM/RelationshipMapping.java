package ORM;

import genericcode.GenericClass;
import genericcode.GenericVariable;
import genericcode.RelationshipAssociationTable;
import genericcode.Table;
import owlcode.Item;

public class RelationshipMapping extends Item{
	
	private GenericClass source;
	private GenericClass target;
	private GenericVariable variable;
	private String type;
	
	private RelationshipMapping reverse = null;
	private RelationshipAssociationTable relationshipAssociationTable=null;
	
	private Property relationshipSourceMappedBy = new Property("relationship_source_mapped_by");
	private Property relationshipTargetMappedBy = new Property("relationship_target_mapped_by");
	private Property manyToManyAssociationMappedTo = new Property("many_to_many_association_mapped_to");
	private Property oneToManyAssociationMappedTo = new Property("one_to_many_association_mapped_to");
	
	public RelationshipMapping (GenericClass source, GenericClass target, GenericVariable v) {
		
		
		this.source = source;
		this.target = target;
		this.type = v.getRelationshipType();
		this.variable = v;
		this.setNamedIndividualIRI();
		if(this.type.equals("o2o")) {
			this.classIRI = "ORMF-O::One_To_One_Relationship_Mapping";
		}else if(this.type.equals("o2m")) {
			this.classIRI = "ORMF-O::One_To_Many_Relationship_Mapping";
		}else if(this.type.equals("m2o")) {
			this.classIRI = "ORMF-O::Many_To_One_Relationship_Mapping";
		}else {
			this.classIRI = "ORMF-O::Many_To_Many_Relationship_Mapping";
		}		
		
	}

	public RelationshipMapping(String classIri, String individualName) {
		this.classIRI = classIri;
		this.namedIndividualIRI = individualName;
	}
	
	public String getPropertiesAssertion() {
		String ret = "";
		ret += this.relationshipSourceMappedBy.getAssertion(this.source,this);
		ret += this.relationshipTargetMappedBy.getAssertion(this.target,this);
		
		if(this.type.equals("m2m")) {
			ret+=manyToManyAssociationMappedTo.getAssertion(this,this.relationshipAssociationTable);
		}
		if(this.type.equals("o2m") & this.reverse==null) {
			ret += oneToManyAssociationMappedTo.getAssertion(this,this.relationshipAssociationTable);
		}
		return ret;
	}
	
	public String getDeclaration() {
		String ret="";
		ret+=super.getDeclaration();
		if(this.relationshipAssociationTable!=null) {
			ret+=this.relationshipAssociationTable.getDeclaration();
		}
		return ret;
		
	}
	
	public String getAssertion() {
		String ret = "";
		ret+=super.getAssertion();
		if(this.relationshipAssociationTable!=null) {
			ret+=this.relationshipAssociationTable.getAssertion();
		}
		return ret;
	}
	
	public void setReverse(RelationshipMapping reverse) {
		this.reverse = reverse;
	}
	
	public RelationshipMapping getReverse() {
		return reverse;
	}
	public void setRelationshipAssociationTable(RelationshipAssociationTable t) {
		this.relationshipAssociationTable = t;
	}
	
	public RelationshipAssociationTable getRelationshipAssociationTable() {
		return this.relationshipAssociationTable;
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
