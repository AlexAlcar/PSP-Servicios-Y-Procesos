package es.florida.t3ane5;
/**
 * El delegado de clase ha comprado un bote de alitas de pollo en el KFC (100
	alitas) y los va a repartir entre los 30 compañeros de clase. Para ello crea una clase que
	implementa Runnable y que tiene un método “consumirAlita” que mientras queden alitas
	permite consumir el número que pida cada compañero (entre 1 y 10 alitas, de forma
	aleatoria), un método “run” (obligado al implementar Runnable) que llama a
	“consumirAlita” 
	y un “main” que crea un hilo por cada compañero. El “main” deberá mostrar
	al final de la ejecución cuántas alitas se han comido entre todos. Realiza dos ejecuciones: sin
	y con sincronización del método “consumirAlita” (problema similar al de las entradas).
 * @author Alex
 *
 */

public class Main {

	public static void main(String[] args) {
		KFC obj = new KFC();
		Thread t;
		for(int i=0;i<30;i++) {
			t=new Thread(obj);
			t.setName("Alumno "+i+1);
			t.start();
		}
		try {
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Entre toda la clase se han comido "+obj.alitasComidas+" alitas");
	}

}
