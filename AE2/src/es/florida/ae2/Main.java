package es.florida.ae2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void llamadaPrograma(String nombreFichero, int cores) {
		//Nombre: llamadaPrograma
		//Descripción: compone un comando con el nombre del fichero y el nº de línea a 
		//procesar y llama al programa APP tantas veces como cores tiene el procesador mientras queden lineas por leer del fichero de texto.
		//Parámetros de entrada: el nombre del fichero con los NEOs y el nº de cores de la CPU
		System.out.println("Nº cores: "+cores+". Iniciando la llamada al programa...");
		String clase="es.florida.ae2.APP";
		String javaHome=System.getProperty("java.home");
		String javaBin=javaHome+File.separator+"bin"+File.separator+"java";
		String classpath=System.getProperty("java.class.path");
		String className=clase;
		String linea;
		Integer conteoLineas=0;
	
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.add(nombreFichero);

		try {
			FileReader fr=new FileReader(nombreFichero);
			BufferedReader br = new BufferedReader(fr);
			//conteoLineas contiene el numero total de lineas que tiene el archivo
			while((linea=br.readLine())!=null) conteoLineas++;
			fr.close();
			br.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Integer lineaAleer =1;
		while (conteoLineas>0) {
			System.out.println("conteoLineas dentro del bucle: "+conteoLineas);
			if(conteoLineas>=cores) {//si quedan mas lineas que cores...
				//añado como parámetro la linea a leer y lo incremento en cada iteración del while
				for (int i=1;i<=cores;i++) {
					command.add(Integer.toString(lineaAleer));
					ProcessBuilder pb=new ProcessBuilder(command);
					try {
						Process process= pb.inheritIO().start();
						process.waitFor();
					} catch (Exception e) {
						e.printStackTrace();
					}
					lineaAleer++;
					//como el segundo argumento (linea a leer) cambia en cada iteración, lo elimino y añado cada vez.
					command.remove(command.size()-1);
				}
			}else {//si quedan menos lineas a leer que cores, modifico el FOR para que solo lea las lineas pendientes
				for (int i=1;i<=conteoLineas;i++) {
					command.add(Integer.toString(lineaAleer));
					ProcessBuilder pb=new ProcessBuilder(command);
					try {
						Process process= pb.inheritIO().start();
						process.waitFor();
					} catch (Exception e) {
						e.printStackTrace();
					}
					lineaAleer++;
					command.remove(command.size()-1);
				}
			}
			conteoLineas-=cores;
		}
	}
	
	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		Runtime runtime = Runtime.getRuntime();
		int cores=runtime.availableProcessors();
		
		llamadaPrograma("NEOs.txt", cores);
		long endTime=System.currentTimeMillis() - startTime;
		System.out.println("Tiempo de ejecución total: "+endTime/1e6+" ms");
		System.out.println("Tiempo medio de ejecución para cada cálculo: "+(endTime/cores)/1e6+" ms");
	}
}
