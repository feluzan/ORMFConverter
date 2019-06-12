package django;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLProperty;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

import ORM.ClassMapping;
import ORM.InheritanceMapping;
import ORM.InheritanceStrategy;
import OWL.ClassIRI;
import OWL.PropertyIRI;
import database.Table;
import database.TableType;
import genericcode.GenericClass;

public class OWL2Django {
	OWLOntology o;
	OWLOntologyManager manager;
	OWLDataFactory factory;
	IRI iri;
	OWLReasonerFactory reasonerFactory;
	OWLReasoner reasoner;
	
	private Map<OWLIndividual, Table> tables = new HashMap<OWLIndividual, Table>();
	private Map<OWLIndividual, GenericClass> classes = new HashMap<OWLIndividual, GenericClass>();
	private Map<OWLIndividual, InheritanceMapping> inheritanceMappings = new HashMap<OWLIndividual,InheritanceMapping>();
	
	public OWL2Django(String OWLPath) {
		
		
		this.manager = OWLManager.createOWLOntologyManager();
		File ORMFOFile = new File(OWLPath);
		this.o=null;
		this.factory = manager.getOWLDataFactory();
		try {
			this.o = manager.loadOntologyFromOntologyDocument(ORMFOFile);
			this.iri = this.o.getOntologyID().getOntologyIRI().get();
		} catch (OWLOntologyCreationException e) {
			System.out.println("[ERROR] Houve algum problema ao carregar a ontologia.");
			System.out.println("\tO programa será encerrado.");
			e.printStackTrace();
			System.exit(1);
		}
		
		this.reasonerFactory = new StructuralReasonerFactory();
		this.reasoner = reasonerFactory.createReasoner(this.o);

		this.retrieveTables();
		this.retrieveClasses();
		this.retrieveSuperclasses();
	}
	
	private void retrieveTables() {
		
		OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
		OWLReasoner reasoner = reasonerFactory.createReasoner(this.o);

		OWLClass c = ClassIRI.ENTITY_TABLE.getOWLClass(o);
		NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(c,false);
		Stream<OWLNamedIndividual> individuals = individualsNodeSet.entities();

		Iterator<OWLNamedIndividual> individualsAsIterator = individuals.iterator();

		while(individualsAsIterator.hasNext()) {
			OWLNamedIndividual i = individualsAsIterator.next();
			Table t = new Table(this.o, i);
			tables.put(i, t);
			
			Stream<OWLClass> allClassesStream = reasoner.getTypes(i).entities();
			Set<OWLClass> allClasses = allClassesStream.collect(Collectors.toSet());
			
			if(allClasses.contains(ClassIRI.MULTIPLE_ENTITY_TABLE.getOWLClass(o))) {
				t.setTableType(TableType.MULTIPLE_ENTITIES_TABLE);
			}
			if(allClasses.contains(ClassIRI.SINGLE_ENTITY_TABLE.getOWLClass(o))) {
				t.setTableType(TableType.SINGLE_ENTITY_TABLE);
			}

		}
		
	}
	
	private void retrieveClassMappings(GenericClass gc) {
		Stream<OWLNamedIndividual> rangesStream;
		Set<OWLNamedIndividual> rangesSet;
		rangesStream = reasoner.getObjectPropertyValues(gc.getIndividual(), PropertyIRI.ENTITY_CLASS_MAPPED_BY.getOWLProperty(this.o)).entities();
		rangesSet = rangesStream.collect(Collectors.toSet());
		
		if(rangesSet.size()>0) {
			ClassMapping cm = new ClassMapping(this.o, rangesSet.iterator().next());
			gc.setClassMapping(cm);
			cm.set_class(gc);
			rangesStream = reasoner.getObjectPropertyValues(cm.getIndividual(), PropertyIRI.ENTITY_CLASS_MAPPED_TO.getOWLProperty(this.o)).entities();
			rangesSet = rangesStream.collect(Collectors.toSet());
			Iterator<OWLNamedIndividual> tableIterator = rangesSet.iterator();
			while(tableIterator.hasNext()) {
				Table t = tables.get(tableIterator.next());
				System.out.println(t.getTableName());
			}
		}
		
	}
	
	private void retrieveInheritanceMapping(GenericClass subclass) {
		Stream<OWLNamedIndividual> rangesStream;
		Set<OWLNamedIndividual> rangesSet;
		
		rangesStream = reasoner.getObjectPropertyValues(subclass.getIndividual(), PropertyIRI.SUBCLASS_MAPPED_BY.getOWLProperty(this.o)).entities();
		rangesSet = rangesStream.collect(Collectors.toSet());

		InheritanceMapping im = new InheritanceMapping(this.o, rangesSet.iterator().next());
		inheritanceMappings.put(im.getIndividual(),im);
		im.setSubclass(subclass);
		
		Stream<OWLClass> allClassesStream = reasoner.getTypes(im.getIndividual()).entities();
		Set<OWLClass> allClasses = allClassesStream.collect(Collectors.toSet());
		
		if(allClasses.contains(ClassIRI.SINGLE_TABLE_INHERITANCE_MAPPING.getOWLClass(o))) {
			im.setInheritanceStrategy(InheritanceStrategy.SINGLE_TABLE);
		}
		if(allClasses.contains(ClassIRI.TABLE_PER_CLASS_INHERITANCE_MAPPING.getOWLClass(o))) {
			im.setInheritanceStrategy(InheritanceStrategy.TABLE_PER_CLASS);
		}
		if(allClasses.contains(ClassIRI.TABLE_PER_CONCRETE_CLASS_INHERITANCE_MAPPING.getOWLClass(o))) {
			im.setInheritanceStrategy(InheritanceStrategy.TABLE_PER_CONCRETE_CLASS);
		}
	}
	
	private void retrieveSuperclasses() {
		OWLClass c = ClassIRI.ENTITY_SUPERCLASS.getOWLClass(o);

		NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(c,false);
		Stream<OWLNamedIndividual> individuals = individualsNodeSet.entities();

		Stream<OWLNamedIndividual> rangesStream;
		Set<OWLNamedIndividual> rangesSet;
		
		
		Iterator<OWLNamedIndividual> individualsAsIterator = individuals.iterator();

		while(individualsAsIterator.hasNext()) {
			OWLNamedIndividual i = individualsAsIterator.next();
			GenericClass superclass = classes.get(i);
			rangesStream = reasoner.getObjectPropertyValues(superclass.getIndividual(), PropertyIRI.SUPERCLASS_MAPPED_BY.getOWLProperty(this.o)).entities();
			rangesSet = rangesStream.collect(Collectors.toSet());
			
			Iterator<OWLNamedIndividual> rangesSetIterator = rangesSet.iterator();
			while(rangesSetIterator.hasNext()) {
				InheritanceMapping im = inheritanceMappings.get(rangesSetIterator.next());
				im.setSuperclass(superclass);
				superclass.addSubclass(im.getSubclass());
				im.getSubclass().setSuperclass(superclass);
			}
			
		}
		
		
		
	}
	
	private void retrieveClasses() {

		OWLClass c = ClassIRI.CLASS.getOWLClass(o);

		NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(c,false);
		Stream<OWLNamedIndividual> individuals = individualsNodeSet.entities();

		Iterator<OWLNamedIndividual> individualsAsIterator = individuals.iterator();

		while(individualsAsIterator.hasNext()) {
			OWLNamedIndividual i = individualsAsIterator.next();
			DjangoClass dc = new DjangoClass(this.o,i);

			classes.put(i, dc);

			Stream<OWLClass> allClassesStream = reasoner.getTypes(i).entities();
			Set<OWLClass> allClasses = allClassesStream.collect(Collectors.toSet());
			
			if(allClasses.contains(ClassIRI.ABSTRACT_CLASS.getOWLClass(o))) {
				dc.set_abstract(true);
			}
			
			
			if(allClasses.contains(ClassIRI.ENTITY_CLASS.getOWLClass(o))) {
				this.retrieveClassMappings(dc);
			}
			
			if(allClasses.contains(ClassIRI.ENTITY_SUBCLASS.getOWLClass(o))) {
				this.retrieveInheritanceMapping(dc);
			}
			
			
		}
	}
	
	
	
	
	
}
