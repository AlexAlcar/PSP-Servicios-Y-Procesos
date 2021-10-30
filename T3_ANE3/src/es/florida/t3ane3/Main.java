package es.florida.t3ane3;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Caracol c1 = new Caracol("Pepe",2);
		Caracol c2 = new Caracol("Martina",3);
		Caracol c3 = new Caracol("Josua",8);
		Caracol c4 = new Caracol("Facundo",6);
		Caracol c5 = new Caracol("Antonio",9);
		
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
