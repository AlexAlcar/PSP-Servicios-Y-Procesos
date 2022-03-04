package es.florida.ae2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class APP {
	
	public static void guardarProbabilidad(String neo, String prob) {
		//Nombre: guardarProbabilidad
		//Descripci�n: guarda en un fichero el nombre del NEO y la probabilidad de colisi�n
		//Par�metros de entrada: el nombre del NEO y la probabilidad
		try {
			FileWriter fw=new FileWriter(neo+".txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Probabilidad de desastre: "+prob+" %");
			bw.newLine();
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void calcularColision (String neo, double posicionNeo,double velocidadNeo) {
		//Nombre: calcularColision
		//Descripci�n: calcula la posibilidad de que el NEO colisione con la Tierra y llama
		//al m�todo guardarProbabilidad para escribir esos datos en un fichero de texto.
		//Par�metros de entrada: nombre, posici�n relativa a la tierra y velocidad del NEO 
		double posicionTierra = 1;
		double velocidadTierra = 100;
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
		posicionNeo = posicionNeo + velocidadNeo * i;
		posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random() *
		Math.pow( ((posicionNeo-posicionTierra)/(posicionNeo+posicionTierra)), 2);
		String resultadoForm = String.format("%.2f",resultado);
		if (resultado>10)System.err.println("NEO "+neo+". ALERTA MUNDIAL, la probabilidad de desastre es: "+resultadoForm+ "%");
		else System.out.println("NEO "+neo+ ". Probabilidad de desastre baja: "+resultadoForm +" %");
		guardarProbabilidad(neo,resultadoForm);
	}

	public static void main(String[] args) {
		//Nombre: APP (main)
		//Descripci�n: Lee el fichero y, para el n� de linea pasada como segundo argumento
		//llama al m�todo calcularColision
		//Par�metros entrada: nombre del fichero a leer y n� de l�nea a leer
		File fichero = new File(args[0]);
		int cores=Integer.parseInt(args[1]);
		String linea;
		int contador=0;
		
		if(fichero.exists() && fichero.canRead()) {
			try {
				FileReader fr=new FileReader(args[0]);
				BufferedReader br=new BufferedReader(fr);

				while((linea=br.readLine())!=null) {
					contador++;
					if(contador==cores) {
						String[] datos=linea.split(",");
						calcularColision(datos[0],Double.parseDouble(datos[1]),Double.parseDouble(datos[2]));
					}
				}
				fr.close();
				br.close();;
			} catch (Exception e) {
				e.printStackTrace();
				e.getMessage();
			};
		}else System.err.println("ERROR, el archivo no existe o no es accesible");
	}
}
