package ORM;

import org.semanticweb.owlapi.model.OWLOntology;

import OWL.Individual;
import database.Table;
import genericcode.GenericVariable;
import genericcode.GenericClass;

public class RelationshipMapping extends Individual {
	
	private GenericVariable variable;
	private RelationshipType relationshipType;
//	private Table relationshipAssociationTable;
	private RelationshipMapping reverse;

	public RelationshipMapping(OWLOntology o, GenericVariable gv) {
		super(o, "relationship_mapping__" + gv.getCodeName() + "__" + ((GenericClass)gv.getValueType().getType()).getCodeName());
		
		this.relationshipType = gv.getRelationshipType();
		switch(gv.getRelationshipType()) {
		case MANY_TO_MANY:
			this.classAssertion("#ORMF-O::Many_to_Many_Reltionship_Mapping");
			break;
			
		case MANY_TO_ONE:
			this.classAssertion("#ORMF-O::Many_to_One_Reltionship_Mapping");
			break;
			
		case ONE_TO_MANY:
			this.classAssertion("#ORMF-O::One_to_Many_Reltionship_Mapping");
			break;
			
		case ONE_TO_ONE:
			this.classAssertion("#ORMF-O::One_to_One_Reltionship_Mapping");
			break;
			
		default:
			break;
		
		}
		
		this.variable = gv;
		gv.setProperty("#represents_relationship", this);
	}

	public RelationshipMapping getReverse() {
		return reverse;
	}
	public void setReverse(RelationshipMapping reverse) {
		this.reverse = reverse;
		this.setProperty("#relationship_reverse_of", reverse);
	}

	public GenericVariable getVariable() {
		return variable;
	}

	public RelationshipType getRelationshipType() {
		return relationshipType;
	}

}
