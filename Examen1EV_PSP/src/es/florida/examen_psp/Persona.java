package es.florida.examen_psp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Persona implements Runnable {	
	int personas;
	List<String> coloresFav;
	
	public Persona (List<String> coloresFav) {
		this.personas=0;
		this.coloresFav=coloresFav;
	}
	
		@Override
		public void run() {
		String color=Thread.currentThread().getName();
		coloresFav.add(color);
		
		}


}
