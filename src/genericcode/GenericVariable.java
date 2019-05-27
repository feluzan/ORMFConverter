package genericcode;

import ORM.RelationshipMapping;
import owlcode.OWLClass;
import owlcode.OWLProperty;

public abstract class GenericVariable extends OWLClass{
	
	private ValueType valueType;
	private String codeValueType;
	
	
	private Column column;
	private GenericClass clazz;
	
	private boolean isMapped;
	private boolean isPK = false;
	private boolean isFK = false;
	
	private RelationshipMapping relationshipMapping = null;
	private OWLProperty memberBelongsTo = new OWLProperty("member_belongs_to");
	private OWLProperty isTypeOf = new OWLProperty("is_type_of");
	

	public void setCodeValueType(String codeValueType) {
		this.codeValueType = codeValueType;
	}

	public void setNamedIndividualIRI() {
		if(this.isMapped()) {
			if(this.isPk()) {
				this.namedIndividualIRI = "mapped_pk__" + this.codeName;
				return;
			}
			if(this.isFk()) {
				this.namedIndividualIRI = "mapped_fk__" + this.codeName;
				return;
			}
			this.namedIndividualIRI = "mapped_variable__" + this.codeName;
		}else {
			this.namedIndividualIRI = "instance_variable__" + this.codeName;
		}
	}

	public abstract String getColumnCodeName();
	public abstract String getRelationshipType();
	public abstract String getAssociationTableName();
	
	@Override
	public String getAssertion() {
		String ret = "";
		ret += super.getAssertion();
		ret += memberBelongsTo.getAssertion(this, this.clazz);
		ret+= isTypeOf.getAssertion(this,this.getValueType());
		return ret;
		
	}
	
	public void setIsMapped(boolean bool) {
		this.isMapped = bool;
	}
	
	public boolean isMapped() {
		return this.isMapped;
	}
	
	public void setIsPK(boolean ispk) {
		this.isPK=ispk;
	}
	
	public void setIsFK(boolean isfk) {
		this.isFK=isfk;
	}
	
	public ValueType getValueType() {
		return this.valueType;
	}
	
	public void setValueType(Type type) {
		this.valueType = new ValueType(this,type);
	}
	
	public void setValueType(ValueType type) {
		this.valueType = type;
	}
	
	public String getCodeValueType() {
		return codeValueType;
	}
	
	public Column getColumn(){
		return column;
	}
	
	public GenericClass getClazz() {
		return clazz;
	}
	
	public String getClassIndividualName() {
		return this.clazz.getNamedIndividualIRI();
	}
	
	public String getClassCodeName() {
		return this.clazz.getCodeName();
	}
	
	public void setColumn(Column col) {
		this.column = col;
	}

	public void setClazz(GenericClass clazz) {
		this.clazz = clazz;
	}

	public boolean isPk() {
		return this.isPK;
	}
	
	public boolean isFk() {
		return this.isFK;
	}
	
	public void setIsFk(boolean bool) {
		this.isFK = bool;
	}
	
	public void setIsPk(boolean bool) {
		this.isPK = bool;
	}

	public RelationshipMapping getRelationshipMapping() {
		return relationshipMapping;
	}

	public void setRelationshipMapping(RelationshipMapping relationshipMapping) {
		this.relationshipMapping = relationshipMapping;
	}

	
}
