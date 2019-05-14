package genericcode;

import ORM.Property;
import owlcode.Item;

public abstract class GenericVariable extends Item{
	
	protected String type;
	protected Column column;
	protected GenericClass clazz;
	
	Property memberBelongsTo = new Property("member_belongs_to");
	

	public void setNamedIndividualIRI() {
		this.namedIndividualIRI = "mapped_variable__" + this.codeName;
	}
	
	
	public abstract boolean isMapped();
	public abstract boolean isPk();
	public abstract boolean isFk();
	public abstract String getColumnCodeName();
	public abstract String getRelationshipType();
	
	
	@Override
	public String getAssertion() {
		return super.getAssertion() + memberBelongsTo.getAssertion(this, this.clazz);
		
	}
	
	public String getType() {
		return this.type;
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
}
