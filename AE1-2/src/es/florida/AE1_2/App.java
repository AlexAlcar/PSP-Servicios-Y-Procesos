package es.florida.AE1_2;

import java.util.Arrays;
import java.util.List;

public class App {

	public static void main(String[] args) {
	String[] alumnos= {"David","Alex","Dani","Erika","Carla","Manu"};
	//Creamos una lista a partir del array para no tener que hacer los Add
	List<String> lalumnos = Arrays.asList(alumnos);
	
	for (String a:alumnos) System.out.println(a);
	for (String b:lalumnos) System.out.println(b);

	}

}
