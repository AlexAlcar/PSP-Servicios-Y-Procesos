package es.florida.ane2;

import java.io.*;
import java.util.*;

public class ANE7 {
	
	public static void lecturaFichero() {
		//M�todo: lecturaFichero
		//Descripci�n: lee el fichero "resultado.txt" y muestra el contenido por pantalla
		//Sin par�metros de entrada/salida	
		File fichero=new File("resultado.txt");
		if(fichero.exists() && fichero.canRead()) {
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
		}else System.out.println("El fichero especificado no existe o no es accesible");
	}
	
	public static void llamadaPrograma(Integer n1, Integer n2) {
		//M�todo: llamadaPrograma
		//Descripci�n: Crea una llamada a una aplicaci�n .java y la invoca usando ProcessBuilder. 
		//En este caso vuelca la salida tanto en la consola de la actual aplicaci�n como en el fichero "salida.txt"
		//Par�metros de entrada: Los dos n�meros enteros que va a sumar la aplicaci�n "ANE3".
		//Sin par�metros de salida.
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
		
		ProcessBuilder pb=new ProcessBuilder(command);
		try {
			File fichSalida= new File("salida.txt");
			Process process= pb.inheritIO().start();
			pb.redirectOutput(fichSalida);
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
