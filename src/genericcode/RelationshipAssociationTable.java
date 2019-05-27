package genericcode;

import ORM.RelationshipMapping;
import owlcode.OWLClass;

public class RelationshipAssociationTable extends OWLClass {
	
	private RelationshipMapping relationshipMapping;
	
	public RelationshipAssociationTable(RelationshipMapping rm) {
		this.classIRI = "ORMF-O::Relationship_Association_Table";
		this.relationshipMapping = rm;
		
		this.setNamedIndividualIRI();

	}

	public RelationshipAssociationTable(String classIri, String individualName) {
		this.classIRI = classIri;
		this.namedIndividualIRI = individualName;
	}

	
	
	public RelationshipMapping getRelationshipMapping() {
		return relationshipMapping;
	}

	public void setRelationshipMapping(RelationshipMapping relationshipMapping) {
		this.relationshipMapping = relationshipMapping;
	}

	@Override
	public void setNamedIndividualIRI() {
		String associationTableName = this.relationshipMapping.getVariable().getAssociationTableName();
		if(associationTableName!=null) {
			this.namedIndividualIRI = "relationship_association_table__" + associationTableName;
			this.codeName = associationTableName;
		}else {
			String c1 = this.relationshipMapping.getVariable().getClassCodeName();
			String c2 = this.relationshipMapping.getVariable().getValueType().getType().getCodeName();
			this.namedIndividualIRI = "relationship_association_table__" + c1 + "_" + c2;
			this.codeName = c1 + "_" + c2;
		}		
	}

}
