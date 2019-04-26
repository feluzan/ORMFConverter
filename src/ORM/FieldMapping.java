package ORM;

import genericcode.Column;
import genericcode.GenericField;
import owlcode.Item;

public class FieldMapping extends Item{

		GenericField field;
		Column column;
		
		Property variableMappedBy;//				new Property("variable_mapped_by");
		Property variableMappingTo;// = new Property("variable_mapping_to");
		
		
		
		
		public FieldMapping (GenericField f, Column col) {
			
			if(f.isPk()) {
				this.iri = "ORMF-O::Primary_Key_Mapping";
				this.variableMappedBy = new Property("pk_mapped_by");
				this.variableMappingTo = new Property("pk_mapping_to");
			}else if(f.isFk()) {
				this.iri = "ORMF-O::Foreign_Key_Mapping";
				this.variableMappedBy = new Property("fk_mapped_by");
				this.variableMappingTo = new Property("fk_mapping_to");
			}else {
				this.iri = "ORMF-O::Variable_Mapping";
				this.variableMappedBy = new Property("variable_mapped_by");
				this.variableMappingTo = new Property("variable_mapping_to");
			}
			
			this.field = f;
			this.column = col;
			this.setIndividualName();
			
//			this.column = f.getColumnIndividualName();
//			System.out.println(this.table);
			
		}
		
		public String getPropertiesAssertion() {
			return variableMappedBy.getAssertion(this.field, this) + variableMappingTo.getAssertion(this, this.column);
		}

		@Override
		public void setIndividualName() {
			String prefix = "variable_mapping__";
			if(this.field.isPk()) {
				prefix = "primary_key_mapping__";
			}
			if(this.field.isFk()) {
				prefix = "foreign_key_mapping__";
			}
			this.individualName =  prefix + this.field.getIndividualName() + "__to__" + this.column.getIndividualName();
			
			
		}

	
}
