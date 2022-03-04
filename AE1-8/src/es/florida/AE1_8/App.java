package es.florida.AE1_8;

import java.util.Scanner;

public class App {
	public static boolean checkPrimo(int num) {
		
		//comprobamos si es primo, llamaremos a este método dentro del método comprobar primos por código mas limpio
		if(num==1 || num==4) return false;
		for (int x=2;x<num/2;x++) if (num%x==0) return false;
		return true;
	}
	
	public static void comprobarPrimos() {
		int a=0;
		int b;
		
		Scanner entrada=new Scanner(System.in);	
		System.out.print("Introduce el primer número: ");
		a=entrada.nextInt();
		int x=a+1;
		System.out.print("Introduce el segundo número: ");
		b=entrada.nextInt();
		
		for(x=a+1;x!=b;x++) {
			//comprobamos si es primo
			if (checkPrimo(x)==true) System.out.println(x+" Es primo");
			else System.out.println(x);
		}
		entrada.close();
	}

	public static void main(String[] args) {
		/*AE1-8 Escribe un método que pida por teclado 2 números como extremos de un intervalo. 
		 * Luego imprime por pantalla todos los números entre ese intervalo, 
		 * indicando junto al número si es un número primo o no. 
		 * Al terminar de mostrar los números por pantalla, debe mostrar un mensaje indicando el tiempo consumido en la
		 *  ejecución del método */
		long startTime = System.currentTimeMillis();
		//long tiempo =System.currentTimeMillis()-startTime;
		comprobarPrimos();
		long tiempoTotal=System.currentTimeMillis()-startTime;
		System.out.println("He tardado: "+tiempoTotal+" ms");
	}

}
