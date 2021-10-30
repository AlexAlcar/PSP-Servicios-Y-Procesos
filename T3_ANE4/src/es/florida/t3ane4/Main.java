package es.florida.t3ane4;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Caracol c1 = new Caracol("Pepe",5);
		Caracol c2 = new Caracol("Martina",5);
		Caracol c3 = new Caracol("Josua",5);
		Caracol c4 = new Caracol("Facundo",5);
		Caracol c5 = new Caracol("Antonio",5);
		
		Thread h1=new Thread(c1);
		Thread h2=new Thread(c2);
		Thread h3=new Thread(c3);
		Thread h4=new Thread(c4);
		Thread h5=new Thread(c5);
		
		h1.setPriority(1);
		h2.setPriority(2);
		h3.setPriority(3);
		h4.setPriority(4);
		h5.setPriority(9);
		
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		h5.start();
	}

}
