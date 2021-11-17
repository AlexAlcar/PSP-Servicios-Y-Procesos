package es.florida.t3ane6;
/**
 *  Desarrolla un programa que tenga una clase �ControlSemaforos� con dos m�todos�encenderSemaforo1� y �encenderSemaforo2�,
 *  que hace alternar los sem�foros entre verde y rojo cada 3 segundos, no pudiendo estar los dos en el mismo estado a la vez) 
 *  
 *  Puedes utilizar una variable de tipo int para controlar qu� sem�foro est� en verde, de forma que cuando 1 est� en verde, el sem�foro 2 espere en rojo. 
	Cuando pasen 5 segundos, el	sem�foro 1 que est� en verde cambiar� a rojo y lo notificar� al sem�foro 2, que dejar� de esperar y pasar� a verde durante otros 5 segundos. 
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
					System.err.print("Sem�foro 2 rojo");
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
					System.err.print("Sem�foro 1 rojo");
					System.out.println("Semaforo 2 verde durante "+tiempoFuncionamiento/1000+ " segundos");
					Thread.sleep(tiempoFuncionamiento);
					semaforo=2;
					notify();
				}catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}
	
}
