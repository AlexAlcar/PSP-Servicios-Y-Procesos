package es.florida.examen_psp;

import java.util.ArrayList;
import java.util.List;

public class Subproceso {

	public static void main(String[] args) {
		String[] colores = {"Azúl", "Verde", "Amarillo", "Rojo", "Naranja"};
		List<String> coloresFav = new ArrayList<>();
		Integer personas=100;
		//Para ejercicio2
		/*if(args.length==0) personas=10;
		else personas=args[0];*/
		
		
		for(int x=0;x<=personas;x++) {
			String colRnd=colores[(int)Math.floor(Math.random()*(4+1))];
			Persona p =new Persona(coloresFav);
			Thread t=new Thread(p);
			t.setName(colRnd);
			t.start();
	}
		
	for(String s : coloresFav)System.out.println(s);
}
	}
