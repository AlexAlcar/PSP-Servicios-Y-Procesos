package es.florida.Examen2EvPSP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;


public class ServidorHilo {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		System.err.println("SERVER --> Starting server...");
		ServerSocket socketEscucha= null;
		try{
			socketEscucha= new ServerSocket(1234);
		} 
		catch(IOException e) {
			System.err.println("SERVER ERROR");
			return;
		}
		while(true) {
			Socket conexion= socketEscucha.accept();
			System.err.println("SERVIDOR --> CONNECTION WITH CLIENT STABLISHED, LAUNCHING THREAD...\n");
			Servidor s=new Servidor(conexion);
			Thread hilo=new Thread(s);
			hilo.start();
		}
	}
}
