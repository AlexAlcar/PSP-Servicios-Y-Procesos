package es.florida.ae1_5;

import java.util.Arrays;

public class App {

	public static void muestraArray(int[] arr ) {
		int aux=0;
		for(int x:arr)if (x>aux) aux=x;
		System.out.println("El n�mero mayor es el "+aux);	
	}
	public static void main(String[] args) {
		// AE1-5 Escribe un m�todo que acepte un array o una lista de elementos y devuelva el mayor de ellos.
		int[] array= {7,2,4,1,9,8,9,29};
		muestraArray(array);
	}

}
