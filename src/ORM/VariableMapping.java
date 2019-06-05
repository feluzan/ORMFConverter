package ORM;

import org.semanticweb.owlapi.model.OWLOntology;

import OWL.Individual;
import database.Column;
import genericcode.GenericVariable;

public class VariableMapping extends Individual {
	
	private GenericVariable variable;
	private Column column;
	
	public VariableMapping(OWLOntology o, GenericVariable gv) {
		super(o, "variable_mapping__" + gv.getCodeName());
		
		this.variable = gv;
		gv.setVariableMapping(this);
		this.classAssertion("#ORMF-O::Variable_Mapping");
		if(gv.isPk()) this.classAssertion("#ORMF-O::Primary_Key_Mapping");
		if(gv.isFk()) this.classAssertion("#ORMF-O::Foreign_Key_Mapping");
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
		if(this.variable.isPk()) {
			this.setProperty("#pk_mapped_to", column);
		}else if(this.variable.isFk()) {
			this.setProperty("#fk_mapped_to", column);
		}else {
			this.setProperty("#variable_mapped_to", column);
		}
	}
	
	

}
