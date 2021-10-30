package es.florida.t3ane2;

public class Main {

	public static void main(String[] args) {
		Contador c1 = new Contador("Contador1", 1, 200);
		Contador c2 = new Contador("Contador2", 699, 1100);
		Contador c3 = new Contador("Contador3", 9, 20);
		Contador c4 = new Contador("Contador4", 1999, 2500);
		Contador c5 = new Contador("Contador5", 99600, 99900);
		
		Thread h1=new Thread(c1);
		Thread h2=new Thread(c2);
		Thread h3=new Thread(c3);
		Thread h4=new Thread(c4);
		Thread h5=new Thread(c5);
		
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		h5.start();
	}

}
