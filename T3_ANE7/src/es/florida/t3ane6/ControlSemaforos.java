package es.florida.t3ane6;
/**
 *  Desarrolla un programa que tenga una clase “ControlSemaforos” con dos métodos“encenderSemaforo1” y “encenderSemaforo2”,
 *  que hace alternar los semáforos entre verde y rojo cada 3 segundos, no pudiendo estar los dos en el mismo estado a la vez) 
 *  
 *  Puedes utilizar una variable de tipo int para controlar qué semáforo está en verde, de forma que cuando 1 esté en verde, el semáforo 2 espere en rojo. 
	Cuando pasen 5 segundos, el	semáforo 1 que está en verde cambiará a rojo y lo notificará al semáforo 2, que dejará de esperar y pasará a verde durante otros 5 segundos. 
 * @author AALBACAR
 *
 */
public class ControlSemaforos {
	int semaforo=1;
	int tiempoFuncionamiento=5000;
	
	public void encenderSemaforo1() {
		while(true) {
			synchronized (this) {
				try {
					while(semaforo==2) wait();
					System.err.print("Semáforo 2 rojo");
					System.out.println("Semaforo 1 verde durante "+tiempoFuncionamiento/1000+ " segundos");
					Thread.sleep(tiempoFuncionamiento);
					semaforo=2;
					notify();
				}catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}
	
	public void encenderSemaforo2() {
		while(true) {
			synchronized (this) {
				try {
					while(semaforo==1) wait();
					System.err.print("Semáforo 1 rojo");
					System.out.println("Semaforo 2 verde durante "+tiempoFuncionamiento/1000+ " segundos");
					Thread.sleep(tiempoFuncionamiento);
					semaforo=2;
					notify();
				}catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}
	
}
