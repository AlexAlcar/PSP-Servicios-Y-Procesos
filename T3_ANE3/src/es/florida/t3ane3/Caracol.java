package es.florida.t3ane3;

public class Caracol implements Runnable {
	String nombre;
	double velocidad;
	
	
	public Caracol (String nombre, double velocidad) {
		this.nombre=nombre;
		this.velocidad=velocidad;
	}
	
	@Override
	public void run() {
		double porcentaje=0;
		for (double i=0;i<=100;i=i+this.velocidad) {
			porcentaje=(i*100)/100;
			System.out.println("Caracol: "+this.nombre+" ha completado el "+porcentaje+"%");
		}
	}
}
