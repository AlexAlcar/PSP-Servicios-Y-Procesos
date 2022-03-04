package es.florida.Examen2EvPSP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		String host= "localhost";
		int puerto= 1234;
		System.out.println("CLIENT --> Iniciando cliente...");
		Socket conexion= new Socket(host,puerto);
		System.out.println("CLIENT --> Conexi�n con el servidor establecida..");
		Credenciales cre = new Credenciales();
		Scanner entrada=new Scanner(System.in);	
		System.out.print("Introduce el nombre de usuario: ");
		String user=entrada.next();
		System.out.print("Introduce la contrase�a: ");
		String pass=entrada.next();
		
		cre.setUser(user);
		cre.setPass(pass);
		
		ObjectOutputStream credFull= new ObjectOutputStream(conexion.getOutputStream());
		credFull.writeObject(cre);
		System.out.println("CLIENTE >> Envio los credenciales al servidor \n");

		ObjectInputStream inObjeto= new ObjectInputStream(conexion.getInputStream());
		String respuesta= (String) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo respuesta: "+respuesta);
		if(respuesta.equals("ERROR")) {
			System.out.println("Respuesta: "+respuesta+ ". Finalizando conexi�n");
			credFull.close();
			conexion.close();
		}
		else {
			//preguntamos cuantas lineas y lo enviamos
			System.out.print("Cuantas l�neas quieres enviar? ");
			Integer lineas=entrada.nextInt();
			
			//env�o n� de lineas
			ObjectOutputStream lineasOut= new ObjectOutputStream(conexion.getOutputStream());
			lineasOut.writeObject(lineas);
			
			//Recibo el resultado de la creaci�n del fichero:
			ObjectInputStream inCreacionFichero= new ObjectInputStream(conexion.getInputStream());
			String respFichero= (String) inCreacionFichero.readObject();
			System.out.println("Respuesta de la creaci�n del fichero: "+respFichero);
			
			if(!respFichero.equals("PREPARADO")) {
				System.out.print("No se ha podido crear el fichero, cerrando conexi�n..: ");
				credFull.close();
				conexion.close();
				inCreacionFichero.close();
			}else {
				//Si el fichero se ha creado correctamente, solicita una a una las lineas que quiere enviar
				for(int x=0;x<lineas;x++) {
					System.out.print("Introduce la l�nea que quieres enviar al servidor: ");
					String textoLinea=entrada.next();
					//env�o la linea introducida
					ObjectOutputStream lineaEnviar= new ObjectOutputStream(conexion.getOutputStream());
					lineaEnviar.writeObject(textoLinea);
				}
			}
			//Env�o respuesta FIN CLIENTE
			ObjectOutputStream respFin= new ObjectOutputStream(conexion.getOutputStream());
			respFin.writeObject("FIN CLIENTE");
			
			//Recibo respuesta FIN SERVIDOR
			ObjectInputStream inRespFinServer= new ObjectInputStream(conexion.getInputStream());
			System.out.println("Respuesta de la creaci�n del fichero: "+(String) inRespFinServer.readObject());
			
			credFull.close();
			conexion.close();
			inCreacionFichero.close();
			entrada.close();
		}
	}
}
