package es.florida.ae1_6;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	public static int sumaArray(ArrayList<Integer> array) {
		int suma=0;
		for(int x : array) suma+=x;
		return suma;
	}

	public static void main(String[] args) {
		//AE1-6 Escribe un m�todo que pida 5 n�meros enteros (sin validaci�n de momento), 
		//los muestre por consola en orden inverso y devuelva la suma de todos los n�meros proporcionados.
		ArrayList<Integer> lista=new ArrayList<Integer>();
		Scanner entrada=new Scanner(System.in);	
		System.out.println("Introduce 5 n�meros enteros.");
		for(int x=0;x<5;x++) lista.add(entrada.nextInt());
		entrada.close();
		
		System.out.println("La suma de todos los n�meros es: "+sumaArray(lista));
		
	}

}
