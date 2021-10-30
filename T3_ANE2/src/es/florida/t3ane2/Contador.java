package es.florida.t3ane2;
/**
 * Implementa una clase denominada “Contador” que implemente Runnable con tres
atributos: un atributo de tipo String denominado “nombreHilo”, otro atributo de tipo entero
denominado “inicioContador” y otro de tipo entero denominado “limiteContador”, que
indicará el fin de la cuenta. El programa principal deberá crear 5 hilos contadores con límites
distintos y que se muestre por pantalla la cuenta de cada uno.
 * @author Alex
 *
 */
public class Contador implements Runnable{
	String nombreHilo;
	int inicioContador, limiteContador;
	
	public Contador(String nombreHilo, int inicioContador, int limiteContador) {
		this.nombreHilo=nombreHilo;
		this.inicioContador=inicioContador;
		this.limiteContador=limiteContador;
	}
	@Override
	public void run() {
		for(int i=this.inicioContador;i<=this.limiteContador;i++) System.out.println("Hilo "+this.nombreHilo+" contador: "+ i);
	}
	
}
