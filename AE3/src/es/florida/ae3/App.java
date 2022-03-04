package es.florida.ae3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) {
		int total=0;
		Mina mina = new Mina(1000);	
		Minero[] arrayMineros = {new Minero(), new Minero(), new Minero(), 
				new Minero(), new Minero(),new Minero(),new Minero(),
				new Minero(),new Minero(),new Minero()};
		ExecutorService executor = Executors.newFixedThreadPool(15);
		while(mina.stock>0) {
			for (Minero minero:arrayMineros) {
				executor.execute(()->minero.extraerRecurso(mina));	
			}
		}
		executor.shutdown();
		
		try {
			executor.awaitTermination(50000, TimeUnit.SECONDS);
			for (Minero minero:arrayMineros) {
				System.out.println("Extraido: "+minero.bolsa);
				total+=minero.bolsa;
			}
			System.err.println("Mina agotada, se han extraido "+total+" recursos");
		} catch (InterruptedException e) {e.printStackTrace();}
	}
}