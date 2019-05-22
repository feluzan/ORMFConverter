package genericcode;

import ORM.RelationshipMapping;
import owlcode.Item;

public class RelationshipAssociationTable extends Item {
	
	private RelationshipMapping relationshipMapping;
	
	public RelationshipAssociationTable(RelationshipMapping rm) {
		this.classIRI = "ORMF-O::Relationship_Association_Table";
		this.relationshipMapping = rm;
		
		this.setNamedIndividualIRI();

	}

	@Override
	public void setNamedIndividualIRI() {
		String associationTableName = this.relationshipMapping.getVariable().getAssociationTableName();
		if(associationTableName!=null) {
			this.namedIndividualIRI = "table__" + associationTableName;
			this.codeName = associationTableName;
		}else {
			String c1 = this.relationshipMapping.getSource().getCodeName();
			String c2 = this.relationshipMapping.getTarget().getCodeName();
			this.namedIndividualIRI = "table__" + c1 + "_" + c2;
			this.codeName = c1 + "_" + c2;
		}		
	}

}
