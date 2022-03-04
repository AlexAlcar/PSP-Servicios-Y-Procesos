package es.florida.ae3;

public class Minero {
	int bolsa;
	int tiempoExtraccion=2;
	public Minero() {
		this.bolsa=0;
	}
	
	public void extraerRecurso(Mina mina) {
		/**
		 * Nombre: extraerRecurso
		 * Parámetros de entrada: Un objeto de tipo mina
		 * Descripción: mientras el stock de la mina es mayor que 0, extrae 1 recurso y lo almacena en la variable Integer "bolsa".
		 * Este proceso está Sincronizado, para evitar desfase en los accesos al mismo desde diferentes hilos.
		 */
		synchronized (mina) {
			if(mina.stock>0) {
				try {
					Thread.sleep(tiempoExtraccion);
					mina.stock--;
					bolsa++;
					System.out.println("Stock: "+mina.stock+" Bolsa: "+bolsa);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}
}