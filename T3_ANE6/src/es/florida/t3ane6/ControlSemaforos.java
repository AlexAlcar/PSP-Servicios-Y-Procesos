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
public class ControlSemaforos implements Runnable {
	int s1_estado=1;
	int s2_estado=2;
	
	public void encenderSemaforo1() {
		if (this.s1_estado==1 && s2_estado==2) {
			this.s1_estado=2;
			System.out.println("Semaforo 1 pasa a rojo");
		}
		else {
			this.s1_estado=1;
			System.out.println("Semaforo 1 pasa a verde");
		}
		
		
	}
	public void encenderSemaforo2() {
		if (this.s2_estado==1 && s1_estado==2) {
			this.s2_estado=2;
			System.out.println("Semaforo 2 pasa a rojo");
		}
		else {
			this.s2_estado=1;
			System.out.println("Semaforo 1 pasa a verde");
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				encenderSemaforo1();
				encenderSemaforo2();
				Thread.sleep(5000);
			}
			
		} catch (InterruptedException e) {e.printStackTrace();}
	}
}
