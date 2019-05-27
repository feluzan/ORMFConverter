package ORM;

import genericcode.GenericVariable;
import genericcode.RelationshipAssociationTable;
import owlcode.OWLClass;
import owlcode.OWLProperty;

public class RelationshipMapping extends OWLClass{

	private GenericVariable variable;
	private String type;
	
	private RelationshipMapping reverse = null;
	private RelationshipAssociationTable relationshipAssociationTable=null;

	private OWLProperty representsRelationship = new OWLProperty("represents_relationship");
	private OWLProperty manyToManyAssociationMappedTo = new OWLProperty("many_to_many_association_mapped_to");
	private OWLProperty oneToManyAssociationMappedTo = new OWLProperty("one_to_many_association_mapped_to");
	
	public RelationshipMapping (GenericVariable v) {
		this.variable = v;
		this.type = v.getRelationshipType();
		
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
		ret += this.representsRelationship.getAssertion(this.variable, this);
		
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
	
	public GenericVariable getVariable() {
		return variable;
	}

	
	
	public void setVariable(GenericVariable variable) {
		this.variable = variable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type=type;
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
		name += this.variable.getCodeName() + "__" + this.variable.getCodeValueType();
		this.namedIndividualIRI = name;
		
	}

	
}
