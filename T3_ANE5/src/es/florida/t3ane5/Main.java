package es.florida.t3ane5;
/**
 * El delegado de clase ha comprado un bote de alitas de pollo en el KFC (100
	alitas) y los va a repartir entre los 30 compa�eros de clase. Para ello crea una clase que
	implementa Runnable y que tiene un m�todo �consumirAlita� que mientras queden alitas
	permite consumir el n�mero que pida cada compa�ero (entre 1 y 10 alitas, de forma
	aleatoria), un m�todo �run� (obligado al implementar Runnable) que llama a
	�consumirAlita� 
	y un �main� que crea un hilo por cada compa�ero. El �main� deber� mostrar
	al final de la ejecuci�n cu�ntas alitas se han comido entre todos. Realiza dos ejecuciones: sin
	y con sincronizaci�n del m�todo �consumirAlita� (problema similar al de las entradas).
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
