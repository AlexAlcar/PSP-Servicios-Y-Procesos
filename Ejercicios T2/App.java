package es.florida.ane2;

import java.io.File;
import java.util.*;

public class App {

	public static void main(String[] args) {
		Integer n1=4, n2=7;
		String clase="es.florida.ane2.Sumador";
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
			System.out.println(process.exitValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
