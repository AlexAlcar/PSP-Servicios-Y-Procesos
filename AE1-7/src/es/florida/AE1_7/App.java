package es.florida.AE1_7;

import java.util.Scanner;

public class App {
	public static void calculaSalario() {
		String nombre;
		int exp;
		String respuesta="";
		
		Scanner entrada=new Scanner(System.in);	
		System.out.print("Introduce el nombre del desarrollador: ");
		nombre=entrada.next();
		System.out.print("Introduce los años de experiencia ");
		exp=entrada.nextInt();
		
		if(exp<1)respuesta="Desarrollador Junior L1 – 15000-18000";
		if(exp>=1 && exp<=2) respuesta="Desarrollador Junior L2 – 18000-22000";
		if(exp>=3 && exp<=5) respuesta="Desarrollador Senior L1 – 22000-28000";
		if(exp>5 && exp<=8) respuesta="Desarrollador Senior L2 – 28000-36000";
		if (exp>8) respuesta="Analista / Arquitecto. Salario a convenir en base a rol";
		
		System.out.println("El empleado "+nombre+" con "+exp+" años de experiencia tiene un nivel y un salario medio de: "+respuesta);
	}

	public static void main(String[] args) {
	/*AE1-7 Escribe un método que pida por teclado el nombre y los años de experiencia como desarrollador de software y
	muestre el nivel y el salario en base al siguiente criterio:
	a. Si lleva menos de 1 año --> “Desarrollador Junior L1 – 15000-18000”
	b. Si lleva entre 1 y 2 años --> “Desarrollador Junior L2 – 18000-22000”
	c. Si lleva entre 3 y 5 años --> ”Desarrollador Senior L1 – 22000-28000”
	d. Si lleva entre 5 y 8 años --> “Desarrollador Senior L2 – 28000-36000”
	e. Si lleva más de 8 años --> “Analista / Arquitecto. Salario a convenir en base a rol”*/

		calculaSalario();	
		
	}

}
