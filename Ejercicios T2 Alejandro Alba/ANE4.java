package es.florida.ane2;

import java.io.*;
import java.util.*;

public class ANE4 {
	
	public static void lecturaFichero() {
		//Método: lecturaFichero
		//Descripción: lee el fichero "resultado.txt" y muestra el contenido por pantalla
		//Sin parámetros de entrada/salida	
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
	

	public static void main(String[] args) {
		Integer n1=4, n2=7;
		String clase="es.florida.ane2.ANE1";
		String javaHome=System.getProperty("java.home");
		String javaBin=javaHome+File.separator+"bin"+File.separator+"java";
		String classpath=System.getProperty("java.class.path");
		System.out.println(classpath);
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
			Process process= pb.inheritIO().start();
			process.waitFor();
			//System.out.println(process.exitValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lecturaFichero();
	}

}
