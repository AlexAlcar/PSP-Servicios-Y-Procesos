package es.florida.t3ane5;
/**
 * 100 alitas entre 30 compañeros de clase. 
 * Crea una clase que implementa Runnable y que tiene un método “consumirAlita” que mientras queden alitas
	permite consumir el número que pida cada compañero (entre 1 y 10 alitas, de forma
	aleatoria)
	
	Un método “run” (obligado al implementar Runnable) que llama a “consumirAlita” 
	y un “main” que crea un hilo por cada compañero. 
	
	El “main” deberá mostrar al final de la ejecución cuántas alitas se han comido entre todos. 
	Realiza dos ejecuciones: sin y con sincronización del método “consumirAlita” (problema similar al de las entradas).
 * @author Alex
 *
 */
public class KFC implements Runnable {
	int alitasDisponibles=100;
	int alitasComidas=0;
	
	synchronized public void consumirAlita(String nombre, int comidas) {
		if(comidas<=alitasDisponibles) {	
			alitasDisponibles=alitasDisponibles-comidas;	
			alitasComidas=alitasComidas+comidas;
			System.out.println("El "+nombre+" ha consumido "+comidas+" alitas, quedan "+this.alitasDisponibles);
			}
		else System.out.println("Se han acabado las alitas");
	}
	
	@Override
	public void run() {
		String nombre= Thread.currentThread().getName();
		int alitasComidas=(int)(Math.random()*10+1);
		
		consumirAlita(nombre, alitasComidas);
	}
	
}
