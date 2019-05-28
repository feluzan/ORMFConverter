package ORM;

import genericcode.Column;
import genericcode.GenericVariable;
import owlcode.OWLClass;
import owlcode.OWLProperty;

public class VariableMapping extends OWLClass{

		GenericVariable variable;
		Column column;
		
		OWLProperty variableMappedBy;//				new Property("variable_mapped_by");
		OWLProperty variableMappingTo;// = new Property("variable_mapping_to");
		
		public VariableMapping (GenericVariable v, Column col) {
			
			if(v.isPk()) {
				this.classIRI = "ORMF-O::Primary_Key_Mapping";
				this.variableMappedBy = new OWLProperty("pk_mapped_by");
				this.variableMappingTo = new OWLProperty("pk_mapped_to");
			}else if(v.isFk()) {
				this.classIRI = "ORMF-O::Foreign_Key_Mapping";
				this.variableMappedBy = new OWLProperty("fk_mapped_by");
				this.variableMappingTo = new OWLProperty("fk_mapped_to");
			}else {
				this.classIRI = "ORMF-O::Variable_Mapping";
				this.variableMappedBy = new OWLProperty("variable_mapped_by");
				this.variableMappingTo = new OWLProperty("variable_mapped_to");
			}
			
			this.variable = v;
			this.column = col;
			this.setNamedIndividualIRI();
			
			
		}
		
		public VariableMapping(String classIri, String individualName) {
			this.classIRI = classIri;
			this.namedIndividualIRI = individualName;

			
		}
		
		public String getPropertiesAssertion() {
			return variableMappedBy.getAssertion(this.variable, this) + variableMappingTo.getAssertion(this, this.column);
		}

		@Override
		public void setNamedIndividualIRI() {
			String prefix = "variable_mapping__";
			if(this.variable.isPk()) {
				prefix = "primary_key_mapping__";
			}
			if(this.variable.isFk()) {
				prefix = "foreign_key_mapping__";
			}
			this.namedIndividualIRI =  prefix + this.variable.getNamedIndividualIRI() + "__to__" + this.column.getNamedIndividualIRI();
			
			
		}

		
		public GenericVariable getVariable() {
			return variable;
		}

		public void setVariable(GenericVariable variable) {
			this.variable = variable;
		}

		public Column getColumn() {
			return column;
		}

		public void setColumn(Column column) {
			this.column = column;
		}

	
}
