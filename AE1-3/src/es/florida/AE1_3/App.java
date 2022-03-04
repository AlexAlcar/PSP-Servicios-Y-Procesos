package es.florida.AE1_3;

import java.util.Scanner;

public class App {
	
	public static int sumaPares (int num) {
		int suma=0;
		for(int x=num;x>0;x--) {
			//bucle regresivo desde el numero pasado como parametro. Si el n�mero es par lo suma. 
			//Contamos que el n�mero pasado, si es par, tambi�n lo suma al no especificar lo contrario
			if(x%2==0) suma=suma+x;
		}
		return suma;
	}

	public static void main(String[] args) {
		Scanner entrada=new Scanner(System.in);	
		int resultado;
		System.out.print("Introduce un n�mero ");
		resultado=sumaPares(entrada.nextInt());
		System.out.println("El resultado de la suma de los pares es: "+resultado);
	}

}
