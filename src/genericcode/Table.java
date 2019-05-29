package genericcode;

import java.util.ArrayList;

import owlcode.OWLClass;

public class Table extends OWLClass{
	
	ArrayList<GenericClass> classes = new ArrayList<GenericClass>();
	String type;
	
	
	public Table(GenericClass c, String type) {
		
		this.type = type;
		// Tipos de Table:
		// Entity Table
		// Entity Table > Multiple Entities Table
		// Entity Table > Single Entity Table
		// Relationship Association Table
		if (type.equals("entity_table")){
			this.classIRI = "ORMF-O::Entity_Table";
		}else if(type.equals("single_entity_table")) {
			 this.classIRI = "ORMF-O::Single_Entity_Table";
		}else if (type.equals("multiple_entities_table")){
			this.classIRI = "ORMF-O::Multiple_Entities_Table";
		}else if (type.equals("relationship_association_table")){
			this.classIRI = "ORMF-O::Relationship_Association_Table";
		}else {
			System.out.println("[ERROR] Tipo de tabela incorreto!");
		}
		this.classes.add(c);
		this.setNamedIndividualIRI();
		this.codeName = c.getTableName();
		c.setTable(this);
		
	}
	
	public Table(String classIRI, String namedIndividualIRI) {
		this.classIRI = classIRI;
		this.namedIndividualIRI = namedIndividualIRI;
		
		if (classIRI.equals("ORMF-O::Entity_Table")){
			this.type = "entity_table";
			this.codeName = namedIndividualIRI.replace("entity_table__","");
		}else if(classIRI.equals("ORMF-O::Single_Entity_Table")) {
			 this.type = "single_entity_table";
			 this.codeName = namedIndividualIRI.replace("single_entity_table__","");
		}else if (classIRI.equals("ORMF-O::Multiple_Entities_Table")){
			this.type = "multiple_entities_table";
			this.codeName = namedIndividualIRI.replace("multiple_entities_table__","");
		}else if (classIRI.equals("ORMF-O::Relationship_Association_Table")){
			this.type = "relationship_association_table";
			this.codeName = namedIndividualIRI.replace("relationship_association_table__","");
		}else {
			System.out.println("[ERROR] Tipo de tabela incorreto!");
		}
		
		
//		this.codeName = namedIndividualIRI.replace("table__","");
	}

	public void setType(String type) {
			this.type = type;
		}

	@Override
	public void setNamedIndividualIRI() {
		this.namedIndividualIRI = this.type + "__" + this.classes.get(0).getTableName();
	}
	
	public void addClass(GenericClass c) {
		classes.add(c);
	}

}
