package genericcode;

import ORM.Property;
import owlcode.Item;

public abstract class GenericField extends Item{
	
	protected String type;
	protected Column column;
	protected GenericClass clazz;
	
	Property memberBelongsTo = new Property("member_belongs_to");
	

	public void setIndividualName() {
		this.individualName = "mapped_variable__" + this.codeName + "__" + this.clazz.getIndividualName();
	}
	
	public abstract boolean isPk();
	public abstract boolean isFk();
	public abstract String getColumnCodeName();
	
	
	@Override
	public String getAssertion() {
		return super.getAssertion() + memberBelongsTo.getAssertion(this, this.clazz);
		
	}
	
	public Column getColumn(){
		return column;
	}
	
	public GenericClass getClazz() {
		return clazz;
	}
	
	public String getClassIndividualName() {
		return this.clazz.getIndividualName();
	}
	
	public String getClassCodeName() {
		return this.clazz.getCodeName();
	}
	
	public void setColumn(Column col) {
		this.column = col;
	}
}
