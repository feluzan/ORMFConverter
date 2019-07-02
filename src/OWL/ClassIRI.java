package OWL;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;

public enum ClassIRI {
	
	ABSTRACT_CLASS{
		public String toString() {
			return "#OOC-O::Abstract_Class";
		}
	},
	
	INSTANCE_VARIABLE{
		public String toString() {
			return "#OOC-O::Instance_Variable";
		}
	},
	
	CLASS{
		public String toString() {
			return "#OOC-O::Class";
		}
		
	},
	
	ENTITY_CLASS{
		public String toString() {
			return "#ORM-O::Entity_Class";
		}
	},
	
	SUBCLASS{
		public String toString() {
			return "#OOC-O::Subclass";
		}
	},
	
	ENTITY_SUBCLASS{
		public String toString() {
			return "#ORM-O::Entity_Subclass";
		}
	},
	
	SUPERCLASS{
		public String toString() {
			return "#OOC-O::Superclass";
		}
	},
	
	ENTITY_SUPERCLASS{
		public String toString() {
			return "#ORM-O::Entity_Superclass";
		}
	},
	
	CLASS_MAPPING{
		public String toString() {
			return "#ORM-O::Class_Mapping";
		}
	},
	
	INHERITANCE_MAPPING{
		public String toString() {
			return "#ORM-O::Inheritance_Mapping";
		}
	},
	
	SINGLE_TABLE_INHERITANCE_MAPPING{
		public String toString() {
			return "#ORM-O::Single_Table_Inheritance_Mapping";
		}
	},
	
	TABLE_PER_CLASS_INHERITANCE_MAPPING{
		public String toString() {
			return "#ORM-O::Table_per_Class_Inheritance_Mapping";
		}
	},
	
	TABLE_PER_CONCRETE_CLASS_INHERITANCE_MAPPING{
		public String toString() {
			return "#ORM-O::Table_per_Concrete_Class_Inheritance_Mapping";
		}
	},
	
	MAPPED_VARIABLE{
		public String toString() {
			return "#ORM-O::Mapped_Variable";
		}
	},
	
	MAPPED_PRIMARY_KEY{
		public String toString() {
			return "#ORM-O::Mapped_Primary_Key";
		}
	},
	
	MAPPED_FOREIGN_KEY{
		public String toString() {
			return "#ORM-O::Mapped_Foreign_Key";
		}
	},
	
	MANY_TO_MANY_RELATIONSHIP_MAPPING{
		public String toString() {
			return "#ORM-O::Many_to_Many_Reltionship_Mapping";
		}
	},
	
	ONE_TO_MANY_RELATIONSHIP_MAPPING{
		public String toString() {
			return "#ORM-O::One_to_Many_Reltionship_Mapping";
		}
	},
	
	MANY_TO_ONE_RELATIONSHIP_MAPPING{
		public String toString() {
			return "#ORM-O::Many_to_One_Reltionship_Mapping";
		}
	},
	
	ONE_TO_ONE_RELATIONSHIP_MAPPING{
		public String toString() {
			return "#ORM-O::One_to_One_Reltionship_Mapping";
		}
	},
	
	PRIMITIVE_TYPE{
		public String toString() {
			return "#OOC-O::Primitive_Type";
		}
	},
	
	COLUMN{
		public String toString() {
			return "#RDBS-O::Column";
		}
	},
	
	PRIMARY_KEY_COLUMN{
		public String toString() {
			return "#RDBS-O::Primary_Key_Column";
		}
	},
	
	FOREIGN_KEY_COLUMN{
		public String toString() {
			return "#RDBS-O::Foreign_Key_Column";
		}
	},
	
	VARIABLE_MAPPING{
		public String toString() {
			return "#ORM-O::Variable_Mapping";
		}
	},
	
	PRIMARY_KEY_MAPPING{
		public String toString() {
			return "#ORM-O::Primary_Key_Mapping";
		}
	},
	
	FOREIGN_KEY_MAPPING{
		public String toString() {
			return "#ORM-O::Foreign_Key_Mapping";
		}
	},
	
	SINGLE_ENTITY_TABLE{
		public String toString() {
			return "#ORM-O::Single_Entity_Table";
		}
	},
	
	MULTIPLE_ENTITY_TABLE{
		public String toString() {
			return "#ORM-O::Multiple_Entities_Table";
		}
	},
	
	ENTITY_TABLE{
		public String toString() {
			return "#ORM-O::Entity_Table";
		}
	},
	
	RELATIONSHIP_ASSOCIATION_ENTITY_TABLE{
		public String toString() {
			return "#ORM-O::Relationship_Association_Table";
		}
	},
	
	VALUE_TYPE{
		public String toString() {
			return "#OOC-O::Value_Type";
		}
	};
	
	public OWLClass getOWLClass(OWLOntology o) {
		IRI oIRI = o.getOntologyID().getOntologyIRI().get();
		return o.getOWLOntologyManager().getOWLDataFactory().getOWLClass(oIRI + this.toString());
	}
	
}
