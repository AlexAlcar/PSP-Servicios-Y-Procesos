package es.florida.pspae4;

import java.io.BufferedReader;
import java.io.Serializable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;




public class ServidorPassword implements Runnable {
	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;

	public ServidorPassword(Socket socket) {
		this.socket=socket;
	}
	
	public Password encriptar(Password pass) {
		/**
		 * M�todo: encriptar
		 * Par�metros de entrada: Objeto de tipo Password. 
		 * Par�metros de salida: Un objeto de tipo Password
		 * Descripci�n: Obtiene, a partir de una contrase�a de texto plano incluida 
		 * en un atributo del objeto Password, una "encriptada" basada en la siguiente
		 * posici�n ASCII de cada car�cter.
		 */
		char[] charArray=pass.getTextoPlano().toCharArray();
		String crypt="";
		for(char c : charArray) {
			int ascii=(int)c;
			ascii++;
			//solo asciis mayores de 32 son imprimibles
			if (ascii>32) crypt+=(char)ascii;
			else crypt+="*";
		}
		pass.setEncriptada(crypt);
		return pass;
	}

	public void run() {
		try {
			
			System.err.println("SERVIDOR >>> Escuchando...\n");
			InputStream is= socket.getInputStream();
			InputStreamReader isr= new InputStreamReader(is);
			bfr= new BufferedReader(isr);
			
			//genera un objeto de tipo contrase�a para envi�rselo al cliente.
			ObjectOutputStream outObjeto= new ObjectOutputStream(socket.getOutputStream());
			Password pass = new Password();
			outObjeto.writeObject(pass);
			System.err.println("SERVIDOR >>> Env�o al cliente el objeto password \n" );
			
			ObjectInputStream inObjeto= new ObjectInputStream(socket.getInputStream());
			//cast del objeto
			Password pMod=(Password)inObjeto.readObject();
			System.err.println("SERVIDOR >>> Recibido del cliente el password en texto plano: "+pMod.getTextoPlano());
			Password pEncriptada=encriptar(pMod);
			System.err.println("SERVIDOR >>> Env�o al cliente el password encriptado: "+pMod.getEncriptada());
			outObjeto.writeObject(pEncriptada);
			
			outObjeto.close();
			inObjeto.close();
			socket.close();			
			
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("SERVER ERROR");
		}
	}
}
