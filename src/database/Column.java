package database;

import org.semanticweb.owlapi.model.OWLOntology;

import ORM.VariableMapping;
import OWL.Individual;

public class Column extends Individual {
	
	private String codeName;
	private VariableMapping variableMapping;

	public Column(OWLOntology o, VariableMapping vm) {
		super(o, "column__" + vm.getVariable().getCodeName());
		
		this.variableMapping = vm;
		vm.setColumn(this);
		
		this.classAssertion("#RDBS-O::Column");
		if(vm.getVariable().isPk()) this.classAssertion("#RDBS-O::Primary_Key_Column");
		if(vm.getVariable().isFk()) this.classAssertion("#RDBS-O::Foreign_Key_Column");
	}

	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public VariableMapping getVariableMapping() {
		return variableMapping;
	}
	public void setVariableMapping(VariableMapping variableMapping) {
		this.variableMapping = variableMapping;
	}

	
}
