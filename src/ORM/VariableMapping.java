package ORM;

import genericcode.Column;
import genericcode.GenericVariable;
import owlcode.OWLClass;
import owlcode.OWLProperty;

public class VariableMapping extends OWLClass{

		GenericVariable field;
		Column column;
		
		OWLProperty variableMappedBy;//				new Property("variable_mapped_by");
		OWLProperty variableMappingTo;// = new Property("variable_mapping_to");
		
		
		
		
		public VariableMapping (GenericVariable f, Column col) {
			
			if(f.isPk()) {
				this.classIRI = "ORMF-O::Primary_Key_Mapping";
				this.variableMappedBy = new OWLProperty("pk_mapped_by");
				this.variableMappingTo = new OWLProperty("pk_mapped_to");
			}else if(f.isFk()) {
				this.classIRI = "ORMF-O::Foreign_Key_Mapping";
				this.variableMappedBy = new OWLProperty("fk_mapped_by");
				this.variableMappingTo = new OWLProperty("fk_mapped_to");
			}else {
				this.classIRI = "ORMF-O::Variable_Mapping";
				this.variableMappedBy = new OWLProperty("variable_mapped_by");
				this.variableMappingTo = new OWLProperty("variable_mapped_to");
			}
			
			this.field = f;
			this.column = col;
			this.setNamedIndividualIRI();
			
//			this.column = f.getColumnIndividualName();
//			System.out.println(this.table);
			
		}
		
		public String getPropertiesAssertion() {
			return variableMappedBy.getAssertion(this.field, this) + variableMappingTo.getAssertion(this, this.column);
		}

		@Override
		public void setNamedIndividualIRI() {
			String prefix = "variable_mapping__";
			if(this.field.isPk()) {
				prefix = "primary_key_mapping__";
			}
			if(this.field.isFk()) {
				prefix = "foreign_key_mapping__";
			}
			this.namedIndividualIRI =  prefix + this.field.getNamedIndividualIRI() + "__to__" + this.column.getNamedIndividualIRI();
			
			
		}

	
}
