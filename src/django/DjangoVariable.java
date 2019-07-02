package django;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import genericcode.GenericClass;
import genericcode.GenericVariable;
import genericcode.PrimitiveType;
import genericcode.Type;
import jpa.JavaPrimitiveType;

public class DjangoVariable extends GenericVariable {

	public DjangoVariable(OWLOntology o, String iri) {
		super(o, iri);
		// TODO Auto-generated constructor stub
	}
	
	public DjangoVariable(OWLOntology o,OWLNamedIndividual i) {
		super(o, i);
		// TODO Auto-generated constructor stub
	}

	public String toCode() {

		System.out.println("Gerando c�digo de vari�vel...");
		String ret = "";
		
		Type type = this.getValueType().getType();
		
		
		if(type instanceof PrimitiveType) {
			
			ret+=this.getCodeName() + " = ";
			
			ret+=(JavaPrimitiveType.getJavaPrimitiveType(((PrimitiveType)type).getTypeName())).toDjango();
			
			ret+="\n";
			
		}else {
			if(type instanceof GenericClass) {
				
				ret+="� uma classe...\n";
				
			}else {
				System.out.println("[ERROR] Problema ao identificar o tipo da vari�vel.");
				System.out.println("\tO programa ser� encerrado.");
				System.exit(1);
			}
		}
		
		
		return ret;
	}
}
