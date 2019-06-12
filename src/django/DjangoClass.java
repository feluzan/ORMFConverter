package django;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import ORM.InheritanceStrategy;
import genericcode.GenericClass;

public class DjangoClass extends GenericClass {

	public DjangoClass(OWLOntology o, OWLNamedIndividual i) {
		super(o, i);
//		i.
	}

	@Override
	public String getSuperclassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InheritanceStrategy getCodeInheritanceStrategy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCodeTableName() {
		// TODO Auto-generated method stub
		return null;
	}

}
