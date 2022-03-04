package es.florida.pspae4;
import java.io.IOException;
import java.net.*;


public class ServidorPasswordHilos {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		System.err.println("SERVIDOR >>> Inizializando servidor...");
		ServerSocket socketEscucha= null;
		try{socketEscucha= new ServerSocket(1234);} 
		catch(IOException e) {System.err.println("ERROR EN EL SERVIDOR");return;}
		
		while(true) {
			Socket conexion= socketEscucha.accept();
			System.err.println("SERVIDOR => CONEXION RECIBIDA, LANZANDO HILO\n");
			ServidorPassword p=new ServidorPassword(conexion);
			Thread hilo=new Thread(p);
			hilo.start();
		}
	}
}
