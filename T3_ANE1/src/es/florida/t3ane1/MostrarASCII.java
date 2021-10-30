package es.florida.t3ane1;

/**
 * Crea una clase llamada “MostrarASCII” que implemente Runnable y que tenga como
	atributo un número entero denominado “tipo”. Si el tipo es 1, la clase mostrará los
	caracteres ASCII impares y si es 2, los pares. La aplicación (método main) deberá crear dos
	hilos, uno para mostrar los impares y otro para los pares.
 */
public class MostrarASCII implements Runnable{
	
	int tipo;
	
	public MostrarASCII (int tipo) {
		this.tipo=tipo;
	}
	
	@Override
	public void run() {
		
		if(this.tipo==1) {
			//Muestra carácteres ASCII impares
			for (int i=1; i<=255;i=i+2) {
				char letra=0;
				System.out.println("ASCII de la letra IMPAR "+letra+ " es "+i);
				letra++;
			}
		}
		else if (this.tipo==2) {
			//meustra ASCII pares
			for (int i=0; i<=255;i=i+2) {
				char letra=0;
				System.out.println("ASCII de la letra PAR "+letra+ " es "+i);
				letra++;
			}
		}
	}

}
