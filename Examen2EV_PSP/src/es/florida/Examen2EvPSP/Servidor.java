package es.florida.Examen2EvPSP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Servidor implements Runnable {
	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;

	public Servidor(Socket socket) {
		this.socket=socket;
	}
	
	public boolean checkCredentials (Credenciales cre) {
		boolean pass=false;
		try {
			FileReader fr1=new FileReader("Usuarios_autorizados.txt");
			FileReader fr2=new FileReader("Contrasenyas_autorizadas.txt");
			BufferedReader br1=new BufferedReader(fr1);
			BufferedReader br2=new BufferedReader(fr2);
			String lineaUser=br1.readLine();
			String lineaPass=br2.readLine();
			
			while(lineaUser!=null) {
				if(lineaUser.equals(cre.getUser()) && lineaPass.equals(cre.getPass())) pass=true;
				lineaUser=br1.readLine();
				lineaPass=br2.readLine();
			}
			fr1.close();
			br1.close();
			fr2.close();
			br2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pass;
	}
	
	public String createFileToSend() {
		//crea un fichero de texto para escritura llamado "contenido.txt"
		File nuevoFichero = new File("contenido.txt");
		if (!nuevoFichero.exists()) {
			try {
				nuevoFichero.createNewFile();
				System.err.println("FILE CREATED SUCCESFULLY");	
				return "PREPARADO";
			} catch (IOException e) {
				System.err.println("ERROR CREATING FILE");
				e.printStackTrace();
				return "ERROR";
			}	
		} else
			System.err.println("THE FILE ALREADY EXISTS");
		return "PREPARADO";
	}
	
	public void run() {
		try {
			String respuesta;
			System.err.println("SERVER ==> Listening...\n");
			InputStream is= socket.getInputStream();
			InputStreamReader isr= new InputStreamReader(is);
			bfr= new BufferedReader(isr);
			
			ObjectInputStream inObjeto= new ObjectInputStream(socket.getInputStream());
			Credenciales credFull=(Credenciales)inObjeto.readObject();
			System.err.println("SERVER --> Recieved credentials: "+credFull.getUser()+" and "+credFull.getPass());
			
			if(!checkCredentials(credFull)) {
				respuesta="ERROR";
				ObjectOutputStream outObjeto= new ObjectOutputStream(socket.getOutputStream());
				outObjeto.writeObject(respuesta);
				System.err.println("SERVER --> THE CREDENTIALS ARE NOT VALID, FORBIDDEN ACCESS.");
				outObjeto.close();
				socket.close();
			}
			else {
				ObjectOutputStream outObjeto= new ObjectOutputStream(socket.getOutputStream());
				outObjeto.writeObject("200 OK");
				System.err.println("SERVER --> THE CREDENTIALS ARE VALID, ACCESS GRANTED.");
			
				//Recibo las líneas que hay que leer:
				ObjectInputStream inLineas= new ObjectInputStream(socket.getInputStream());
				Integer lineas=(Integer)inLineas.readObject();
				System.err.println("SERVER --> Recieved lines: "+lineas);
				
				//creo el fichero y envío el resultado de la creación del mismo:
				ObjectOutputStream outFichero= new ObjectOutputStream(socket.getOutputStream());
				outFichero.writeObject(createFileToSend());
				
				//Recibo cada una de las líneas que me manda el cliente, las muestro y las escribo en el fichero:
				FileWriter fw =new FileWriter("contenido.txt");
				BufferedWriter bw=new BufferedWriter(fw);
				for(int x=0;x<lineas;x++) {
					ObjectInputStream inTextoLinea= new ObjectInputStream(socket.getInputStream());
					String textoLinea=(String)inTextoLinea.readObject();
					System.err.println("SERVER --> LINE RECIEVED: "+textoLinea);
					bw.write(textoLinea+"\n");
				}
				bw.close();
				fw.close();
				System.err.println("SERVER --> FILE FILLED SUCCESFULLY.");
				
				//recibo respuesta "FIN CLIENTE"
				ObjectInputStream inRespFinal= new ObjectInputStream(socket.getInputStream());
				String respFinal=(String)inRespFinal.readObject();
				System.err.println("SERVER --> RESPONSE FROM CLIENT: "+respFinal);
				
				//Envío respuesta "FIN SERVIDOR"
				ObjectOutputStream outRespFinal= new ObjectOutputStream(socket.getOutputStream());
				outRespFinal.writeObject("SERVER --> FIN SERVIDOR");
				
				inObjeto.close();
				socket.close();		
			}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println("SERVER ERROR");
			}
	}
}
