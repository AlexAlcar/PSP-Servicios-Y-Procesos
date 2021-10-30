package es.florida.t3ane1;

public class Main {

	public static void main(String[] args) {
		/**
		 * Crea una clase llamada “MostrarASCII” que implemente Runnable y que tenga como
			atributo un número entero denominado “tipo”. Si el tipo es 1, la clase mostrará los
			caracteres ASCII impares y si es 2, los pares. La aplicación (método main) deberá crear dos
			hilos, uno para mostrar los impares y otro para los pares.
		 */
		MostrarASCII impares= new MostrarASCII(1);
		MostrarASCII pares= new MostrarASCII(2);
		
		Thread hilo1=new Thread(impares);
		Thread hilo2=new Thread(pares);
		
		hilo1.start();
		hilo2.start();
	}

}
