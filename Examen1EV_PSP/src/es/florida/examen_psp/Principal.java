package es.florida.examen_psp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce un numero de personas: ");
		String personas=entrada.nextLine();
		
		String clase="es.florida.examen_psp";
		String javaHome=System.getProperty("java.home");
		String javaBin=javaHome+File.separator+"bin"+File.separator+"java";
		String classpath=System.getProperty("java.class.path");
		String className=clase;
		
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.add(personas);
		
		ProcessBuilder pb=new ProcessBuilder(command);
		try {
			Process process= pb.inheritIO().start();
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
