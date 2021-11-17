package es.florida.t3ane6;
/**
 * Desarrolla un programa que tenga una clase “ControlSemaforos” con dos métodos“encenderSemaforo1” y “encenderSemaforo2”,
 *  que hace alternar los semáforos entre verde y rojo cada 3 segundos, no pudiendo estar los dos en el mismo estado a la vez) 
 *  y un método “main” que crea un objeto “ControlSemaforos” y dos hilos, uno por cada semáforo.
 *  
	Puedes utilizar una variable de tipo int para controlar qué semáforo está en verde, de forma que cuando 1 esté en verde, el semáforo 2 espere en rojo. 
	Cuando pasen 5 segundos, el	semáforo 1 que está en verde cambiará a rojo y lo notificará al semáforo 2, que dejará de esperar y pasará a verde durante otros 5 segundos. 
 * @author AALBACAR
 *
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControlSemaforos control = new ControlSemaforos();
		
		Thread h1 = new Thread (new Runnable() {
			@Override
			public void run() {control.encenderSemaforo1(); }
		});
		
		Thread h2 = new Thread (new Runnable() {
			@Override
			public void run() {control.encenderSemaforo2(); }
		});
		h1.start();
		h2.start();
		
	}

}
