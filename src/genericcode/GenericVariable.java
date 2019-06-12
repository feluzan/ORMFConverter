package genericcode;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import ORM.RelationshipType;
import ORM.VariableMapping;
import OWL.Individual;
import OWL.PropertyIRI;

public class GenericVariable extends Individual{
	
	private GenericClass _class;
	private String codeName;
	private boolean mapped;
	private boolean pk;
	private boolean fk;
	private VariableMapping variableMapping;
	private ValueType valueType;
	private RelationshipType relationshipType;
	

	public GenericVariable(OWLOntology o, String iri) {
		super(o, iri);

	}
	
	public GenericVariable(OWLOntology o,OWLNamedIndividual i) {
		super(o, i);
		this.setMapped(false);
		this.setPk(false);
		this.setFk(false);
		// TODO Auto-generated constructor stub
	}
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}

	public boolean isFk() {
		return fk;
	}
	public void setFk(boolean fk) {
		this.fk = fk;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeName() {
		return codeName;
	}

	public GenericClass get_class() {
		return _class;
	}
	public void set_class(GenericClass _class) {
		this._class = _class;
	}

	public boolean isMapped() {
		return mapped;
	}
	public void setMapped(boolean mapped) {
		this.mapped = mapped;
	}
	
	public VariableMapping getVariableMapping() {
		return variableMapping;
	}
	public void setVariableMapping(VariableMapping variableMapping) {
		this.variableMapping = variableMapping;
		if(this.isPk()) {
			this.setProperty(PropertyIRI.PK_MAPPED_BY, variableMapping);
		}else if(this.isFk()) {
			this.setProperty(PropertyIRI.FK_MAPPED_BY, variableMapping);
		}else {
			this.setProperty(PropertyIRI.VARIABLE_MAPPED_BY, variableMapping);
		}
		
	}

	public ValueType getValueType() {
		return valueType;
	}
	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
		this.setProperty(PropertyIRI.IS_TYPE_OF, valueType);
	}

	public RelationshipType getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(RelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}
	
	

}
