package es.florida.pspae4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {

	public static void main(String[] args) throws IOException {
		
		String javaHome=System.getProperty("java.home");
		String javaBin=javaHome + File.separator+"bin"+File.separator+"java";
		String classpath=System.getProperty("java.class.path");

		String className="es.florida.pspae4.ServidorPasswordHilos";
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		ProcessBuilder builder = new ProcessBuilder(command);
		System.out.println("LANZADOR >>> lanzado proceso SERVIDOR...");
		builder.inheritIO().start();
		
		className="es.florida.pspae4.ClientePassword";
		command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		builder = new ProcessBuilder(command);
		System.out.println("LANZADOR >>> lanzado proceso CLIENTE...");
		builder.inheritIO().start();

		
	}

}
