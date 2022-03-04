package es.florida.pspae5;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServidorHTTP {

	public static void main(String[] args) throws Exception {
		String host = "localhost"; //127.0.0.1
		int puerto = 7777;
		InetSocketAddress direccionTCPIP= new InetSocketAddress(host,puerto);
		int backlog= 0; //Numero de conexiones pendientes que el servidor puede mantener en la cola
		HttpServer servidor = HttpServer.create(direccionTCPIP, backlog);
		
		GestorHTTP gestorHTTP= new GestorHTTP();//Clase que gestiona los GETs, POSTs, etc
		String rutaRespuesta= "/test"; //Ruta, a partir de loscalhost, en la que el servidor dará respuesta
		servidor.createContext(rutaRespuesta, gestorHTTP); //Crea un contexto, asocia la ruta al gestor HTTP
		
		//Opcion2 de ejecucion: multihilo con ThreadPoolExecutor
		ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
		servidor.setExecutor(threadPoolExecutor);
		servidor.start();
		System.out.println("Servidor HTTP arranca en el puerto "+ puerto);

	}

}
