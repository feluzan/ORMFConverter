//import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import django.OWL2Django;
import jpa.Java2OWL;



public class Converter {

	public static void main(String[] args){
		
	//	String folderPath = "C:\\Users\\Felix Zanetti\\eclipse-workspace\\JavaTest\\src\\";
	//	File folder = new File(folderPath);
		
		String ORMFOPath = "ormfo.owl";
		System.out.println("[INFO] Iniciando leitura da ORMF-O...");
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		File ORMFOFile = new File(ORMFOPath);
		OWLOntology ormfo=null;
		OWLDataFactory factory = manager.getOWLDataFactory();
		try {
			ormfo = manager.loadOntologyFromOntologyDocument(ORMFOFile);
		} catch (OWLOntologyCreationException e) {
			System.out.println("[ERROR] Houve algum problema ao carregar a ontologia.");
			System.out.println("\tO programa ser� encerrado.");
			e.printStackTrace();
			System.exit(1);
		}
		
		
		
		Scanner scanner = new Scanner(System.in);
	//	System.out.println("Digite o path da pasta que contem os arquivos das classes Java:");
	//	String folderPath = scanner.nextLine();
	//	
	//	System.out.println("Digite o path da arquivo de sa�da que conter� a OWL:");
	//	String owlPath = scanner.nextLine();
	//	
	//	System.out.println("Digite o path da arquivo de sa�da que conter� o c�digo em Ptrhon com Django:");
	//	String djangoPath = scanner.nextLine();
		
		
		String folderPath = "D:\\Projetos\\DomainModelExample\\src\\model";
		String owlPath = "output.owl";
		String djangoPath = "outputDjango.py";
		
			
	
		System.out.println("[INFO] Carregando pasta...");
		File folder = null;
		try{
			folder = new File(folderPath);
		}catch (Exception e) {
			System.out.println("[ERROR] Erro no carregamento da pasta " + folderPath);
			System.out.println("\tO programa ser� encerrado.");
			System.exit(1);
		}
		
		Java2OWL java2owl = new Java2OWL(folder, ormfo);
		
		
		java2owl.printFile(owlPath);
		scanner.close();
		System.out.println("[INFO] Escrita no arquivo " + owlPath + " conclu�da.");
		
		System.out.println("\n[INFO] Iniciando Etapa OWL -> Python");
	//	
	//	File owlfile = new File(owlPath);
		OWL2Django owl2Django = new OWL2Django(owlPath);
	//	
	//	File djangoFile = new File(djangoPath);
	//	
	//	owl2Django.printFile(djangoFile);
	//	System.out.println("[INFO] Escrita no arquivo " + djangoFile + " conclu�da.");
		
		
		
	
			
			
			
			
			
			
	}

}
