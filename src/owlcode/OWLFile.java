package owlcode;

public class OWLFile {
	
	String owlHead;
	
	public OWLFile() {
		owlHead = "";
		
		
		owlHead += "<?xml version=\"1.0\"?>\n";
		owlHead += "<Ontology xmlns=\"http://www.w3.org/2002/07/owl#\"\n";
		owlHead += "\t xml:base=\"http://www.semanticweb.org/felixzanetti/ontologies/2019/3/untitled-ontology-4\"\n";
		owlHead += "\t xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n";
		owlHead += "\t xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"\n";
		owlHead += "\t xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"\n";
		owlHead += "\t xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"\n";
		owlHead += "\t ontologyIRI=\"http://www.semanticweb.org/felixzanetti/ontologies/2019/3/untitled-ontology-4\">\n";
		owlHead += "\t<Prefix name=\"\" IRI=\"http://www.semanticweb.org/felixzanetti/ontologies/2019/3/untitled-ontology-4\"/>\n";
		owlHead += "\t<Prefix name=\"owl\" IRI=\"http://www.w3.org/2002/07/owl#\"/>\n";
		owlHead += "\t<Prefix name=\"rdf\" IRI=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"/>\n";
		owlHead += "\t<Prefix name=\"xml\" IRI=\"http://www.w3.org/XML/1998/namespace\"/>\n";
		owlHead += "\t<Prefix name=\"xsd\" IRI=\"http://www.w3.org/2001/XMLSchema#\"/>\n";
		owlHead += "\t<Prefix name=\"rdfs\" IRI=\"http://www.w3.org/2000/01/rdf-schema#\"/>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Class\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Extendable_Class\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Inheritance\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Instance_Variable\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Member\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Member_Variable\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Primitive_Type\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Subclass\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Superclass\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Type\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Value_Type\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Variable\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Class_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Class\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Subclass\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Superclass\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Table\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Foreign_Key_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Inheritance_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Many_To_Many_Relationship_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Many_To_One_Relationship_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Mapped_Foreign_Key\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Mapped_Primary_Key\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Mapped_Variable\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Multiple_Entities_Table\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Nullability\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::One_To_Many_Relationship_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::One_To_One_Relationship_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Primary_Key_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Association_Table\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Single_Entity_Table\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Single_Table_Inheritance_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Table_per_Class_Inheritance_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Table_per_Concrete_Class_Inheritance_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Variable_Mapping\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Column\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Column_Constraint\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Column_Type_Constraint\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Constraint\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Foreign_Key_Column\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Foreign_Key_Constraint\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Primary_Key_Column\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Primary_Key_Constraint\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Table\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#entity_class_mapped_by\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#entity_class_mapped_to\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#inherited_from\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#inherits_from\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#is_type_of\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#many_to_many_association_mapped_to\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#member_belongs_to\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#one_to_many_association_mapped_to\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#pk_mapped_by\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#pk_mapped_to\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#refers_to\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#relationship_reverse_of\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#represents_relationship\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#single_table_inheritance_mapped_to\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#subclass_mapped_by\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#superclass_mapped_by\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#table_per_class_inheritance_mapped_to\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#table_per_concrete_class_inheritance_mapped_to\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#variable_mapped_by\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<Declaration>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#variable_mapped_to\"/>\n";
		owlHead += "\t</Declaration>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Class\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Type\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Extendable_Class\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Class\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Instance_Variable\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Member_Variable\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Member_Variable\"/>\n";
		owlHead += "\t\t<ObjectIntersectionOf>\n";
		owlHead += "\t\t\t<Class IRI=\"#OOC-O::Member\"/>\n";
		owlHead += "\t\t\t<Class IRI=\"#OOC-O::Variable\"/>\n";
		owlHead += "\t\t</ObjectIntersectionOf>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Primitive_Type\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Type\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Subclass\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Class\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Superclass\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Extendable_Class\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Class\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Class\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Subclass\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Subclass\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Superclass\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Superclass\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Table\"/>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Table\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Foreign_Key_Mapping\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Variable_Mapping\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Many_To_Many_Relationship_Mapping\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Mapping\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Many_To_One_Relationship_Mapping\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Mapping\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Mapped_Foreign_Key\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Mapped_Variable\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Mapped_Primary_Key\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Mapped_Variable\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Mapped_Variable\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Instance_Variable\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Multiple_Entities_Table\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Table\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::One_To_Many_Relationship_Mapping\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Mapping\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::One_To_One_Relationship_Mapping\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Mapping\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Primary_Key_Mapping\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Variable_Mapping\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Association_Table\"/>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Table\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Single_Entity_Table\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Table\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Single_Table_Inheritance_Mapping\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Inheritance_Mapping\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Table_per_Class_Inheritance_Mapping\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Inheritance_Mapping\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Table_per_Concrete_Class_Inheritance_Mapping\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Inheritance_Mapping\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Column_Constraint\"/>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Constraint\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Column_Type_Constraint\"/>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Column_Constraint\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Foreign_Key_Column\"/>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Column\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Foreign_Key_Constraint\"/>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Column_Constraint\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Primary_Key_Column\"/>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Column\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<SubClassOf>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Primary_Key_Constraint\"/>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Column_Constraint\"/>\n";
		owlHead += "\t</SubClassOf>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#entity_class_mapped_by\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Class\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#entity_class_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Class_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#inherited_from\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Superclass\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#inherits_from\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Subclass\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#is_type_of\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Variable\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#many_to_many_association_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Many_To_Many_Relationship_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#member_belongs_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Member\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#one_to_many_association_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::One_To_Many_Relationship_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#pk_mapped_by\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Mapped_Primary_Key\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#pk_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Primary_Key_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#refers_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Value_Type\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#relationship_reverse_of\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#represents_relationship\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Instance_Variable\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#single_table_inheritance_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Single_Table_Inheritance_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#subclass_mapped_by\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Subclass\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#superclass_mapped_by\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Superclass\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#table_per_class_inheritance_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Table_per_Class_Inheritance_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#table_per_concrete_class_inheritance_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Table_per_Concrete_Class_Inheritance_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#variable_mapped_by\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Mapped_Variable\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyDomain>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#variable_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Variable_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyDomain>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#entity_class_mapped_by\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Class_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#entity_class_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Entity_Table\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#inherited_from\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Inheritance\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#inherits_from\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Inheritance\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#is_type_of\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Value_Type\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#many_to_many_association_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Association_Table\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#member_belongs_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Class\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#one_to_many_association_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Association_Table\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#pk_mapped_by\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Primary_Key_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#pk_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Primary_Key_Column\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#refers_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#OOC-O::Type\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#relationship_reverse_of\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#represents_relationship\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Relationship_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#single_table_inheritance_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Multiple_Entities_Table\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#subclass_mapped_by\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Inheritance_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#superclass_mapped_by\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Inheritance_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#table_per_class_inheritance_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Single_Entity_Table\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#table_per_concrete_class_inheritance_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Single_Entity_Table\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#variable_mapped_by\"/>\n";
		owlHead += "\t\t<Class IRI=\"#ORMF-O::Variable_Mapping\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";
		owlHead += "\t<ObjectPropertyRange>\n";
		owlHead += "\t\t<ObjectProperty IRI=\"#variable_mapped_to\"/>\n";
		owlHead += "\t\t<Class IRI=\"#RDBS-O::Column\"/>\n";
		owlHead += "\t</ObjectPropertyRange>\n";




	}
	
	
	public String getOwlHead() {
		return owlHead;
	}
	
	public String getOwlClosure() {
		String ret = "";
		ret += "</Ontology>\n";
		ret += "<!-- Generated by the ORMFConverter https://github.com/feluzan/ORMFConverter -->\n";
		return ret;
	}


	

}
