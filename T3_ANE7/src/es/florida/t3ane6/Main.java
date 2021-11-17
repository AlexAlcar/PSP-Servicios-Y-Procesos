package es.florida.t3ane6;
/**
 * Desarrolla un programa que tenga una clase �ControlSemaforos� con dos m�todos�encenderSemaforo1� y �encenderSemaforo2�,
 *  que hace alternar los sem�foros entre verde y rojo cada 3 segundos, no pudiendo estar los dos en el mismo estado a la vez) 
 *  y un m�todo �main� que crea un objeto �ControlSemaforos� y dos hilos, uno por cada sem�foro.
 *  
	Puedes utilizar una variable de tipo int para controlar qu� sem�foro est� en verde, de forma que cuando 1 est� en verde, el sem�foro 2 espere en rojo. 
	Cuando pasen 5 segundos, el	sem�foro 1 que est� en verde cambiar� a rojo y lo notificar� al sem�foro 2, que dejar� de esperar y pasar� a verde durante otros 5 segundos. 
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
