//import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

import javacode.Java2OWL;
import pythoncode.OWL2Django;



public class Converter {
	
	
public static void main(String[] args){
	
//	String folderPath = "C:\\Users\\Felix Zanetti\\eclipse-workspace\\JavaTest\\src\\";
//	File folder = new File(folderPath);
	
	
	Scanner scanner = new Scanner(System.in);
	System.out.println("Digite o path da pasta que contem os arquivos das classes Java:");
	String folderPath = scanner.nextLine();
	
	System.out.println("Digite o path da arquivo de saída que conterá a OWL:");
	String owlPath = scanner.nextLine();
	
	System.out.println("Digite o path da arquivo de saída que conterá o código em Ptrhon com Django:");
	String djangoPath = scanner.nextLine();
	
	
//	String folderPath = "C:\\Users\\Felix Zanetti\\eclipse-workspace\\JavaTest\\src\\";
//	String owlPath = "output.owl";
//	String djangoPath = "outputDjango.py";
		

	System.out.println("[INFO] Carregando pasta...");
	File folder = null;
	try{
		folder = new File(folderPath);
	}catch (Exception e) {
		System.out.println("[ERROR] Erro no carregamento da pasta " + folderPath);
		System.exit(1);
	}
	
	Java2OWL java2owl = new Java2OWL(folder);
	java2owl.printFile(owlPath);
	scanner.close();
	System.out.println("[INFO] Escrita no arquivo " + owlPath + " concluída.");
	
	System.out.println("\n\n--- Iniciando Etapa OWL -> Python");
	
	File owlfile = new File(owlPath);
	OWL2Django owl2Django = new OWL2Django(owlfile);
	
	File djangoFile = new File(djangoPath);
	
	owl2Django.printFile(djangoFile);
	
	
	

		
		
		
		
		
		
	}

}
