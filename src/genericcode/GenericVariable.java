package genericcode;

import ORM.Property;
import owlcode.Item;

public abstract class GenericVariable extends Item{
	
	protected ValueType valueType;
	protected String codeValueType;
	
	protected Column column;
	protected GenericClass clazz;
	
	protected boolean isPK = false;
	protected boolean isFK = false;
	Property memberBelongsTo = new Property("member_belongs_to");
	Property isTypeOf = new Property("is_type_of");
	

	public void setNamedIndividualIRI() {
		this.namedIndividualIRI = "mapped_variable__" + this.codeName;
	}
	
	public abstract boolean isMapped();
	public abstract boolean isPk();
	public abstract boolean isFk();
	public abstract String getColumnCodeName();
	public abstract String getRelationshipType();
//	public abstract String getTypeAssertion();
	
	@Override
	public String getAssertion() {
		String ret = "";
		ret += super.getAssertion();
		ret += memberBelongsTo.getAssertion(this, this.clazz);
		ret+= isTypeOf.getAssertion(this,this.getValueType());
		return ret;
		
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
}
