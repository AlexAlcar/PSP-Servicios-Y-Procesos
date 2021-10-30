package es.florida.ejemplost3;

class EjecutorTarea implements Runnable{
	private String nombre;
	int numEjecucion;
	
	public EjecutorTarea (String nombre) {
		this.nombre=nombre;
	}
	@Override
	public void run() {
		String cad;
		while(numEjecucion < 100) {
			for (double i=0;i<499.9;i=i+0.02) {Math.sqrt(i);}
			
		}
	}


}
