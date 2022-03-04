package es.florida.ae1_4;

public class App {

	public static void main(String[] args) {
		// AE1-4 Escribe el código necesario para calcular el factorial del número 15, en menos de 5 instrucciones
		double fact=1;
		for (int i=2;i<=15;i++) fact=fact*i;
		System.out.println("El factorial de 15 es: "+fact);
	}

}
