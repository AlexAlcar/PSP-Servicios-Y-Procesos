package es.florida.pspae4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientePassword {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		String host= "localhost";
		int puerto= 1234;
		System.out.println("CLIENTE >> Inizializando cliente...");
		Socket conexion= new Socket(host,puerto);
		//Recibimos objeto (vacío) y lo casteamos a tipo Password
		System.out.println("CLIENTE >> Recibido objeto Password vacío. \n");
		ObjectInputStream inObjeto= new ObjectInputStream(conexion.getInputStream());
		Password p= (Password) inObjeto.readObject();
		
		Scanner entrada=new Scanner(System.in);	
		System.out.print("Introduce la contraseña sin encriptar: ");
		String pwd=entrada.next();
		p.setTextoPlano(pwd);
		entrada.close();
		
		System.out.println("CLIENTE >> Cambio el valor por: "+p.getTextoPlano()+"\n");
		ObjectOutputStream pMod= new ObjectOutputStream(conexion.getOutputStream());
		pMod.writeObject(p);
		System.out.println("CLIENTE >> Envio el password en texto plano al servidor \n");
		Password pEncriptado= (Password) inObjeto.readObject();
		System.out.println("CLIENTE >> He recibido el password encriptado: "+pEncriptado.getEncriptada());
		
		inObjeto.close();
		pMod.close();
		conexion.close();
	}
}