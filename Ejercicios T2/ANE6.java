package es.florida.ane2;

import java.io.*;
import java.util.*;

public class ANE6 {
	
	public static void lecturaFichero() {
		try {
			FileReader fr= new FileReader("resultado.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea=br.readLine();
			System.out.println("Contenido del fichero: ");
			while(linea!=null) {
				System.out.println(linea);
				linea=br.readLine();
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void llamadaPrograma(Integer n1, Integer n2) {
		//System.out.println("Lanzando llamadaPrograma...");
		String clase="es.florida.ane2.ANE3";
		String javaHome=System.getProperty("java.home");
		String javaBin=javaHome+File.separator+"bin"+File.separator+"java";
		String classpath=System.getProperty("java.class.path");
		String className=clase;
		
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.add(n1.toString());
		command.add(n2.toString());
		System.out.println(command);
		
		ProcessBuilder pb=new ProcessBuilder(command);
		try {
			//FileWriter fw =new FileWriter("salidaEjecucion.txt");
			//BufferedWriter bw=new BufferedWriter(fw);
			Process process= pb.inheritIO().start();
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		
		llamadaPrograma(4,7);
		lecturaFichero();
		llamadaPrograma(5,9);
		lecturaFichero();
	}

}
