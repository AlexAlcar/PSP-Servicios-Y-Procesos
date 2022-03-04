package es.florida.pspae5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GestorHTTP implements HttpHandler {
	int tempActual=15;
	int tempTermostato=15;
	
	@Override
	public void handle(HttpExchange httpExchange) throws IOException{
		String requestParamValue=null;
		if ("GET".equals(httpExchange.getRequestMethod())) {
			requestParamValue= handleGetRequest(httpExchange);
			try {
				handleGETResponse(httpExchange, requestParamValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if("POST".equals(httpExchange.getRequestMethod())) {
			requestParamValue= handlePostRequest(httpExchange);
			try {handlePOSTResponse(httpExchange,requestParamValue);}
			catch (Exception e) {	e.printStackTrace();} 
		}
	}
	
	public static void envioMail(String mensaje, String asunto, String email_remitente, String email_remitente_pass, String host_email, String port_email, String[] email_destino, String[] anexo) throws UnsupportedEncodingException, MessagingException{
		//Nombre:envioMail
		//Parámetros de entrada: String mensaje, String asunto, String email_remitente, String email_remitente_pass, String host_email,
		//String port_email, String[] email_destino, String[] anexo
		//Método de envío de correo adaptado para incluir los dos destinatarios y los dos adjuntos que se pide en el ejercicio
		
		Properties props= System.getProperties();
		props.put("mail.smtp.host", host_email);
		props.put("mail.smtp.user", email_remitente);
		props.put("mail.smtp.clave", email_remitente_pass);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true"); //tls: puerto 587
		props.put("mail.smtp.port", port_email);
		
		Session session= Session.getDefaultInstance(props);
		
		MimeMessage message= new MimeMessage(session);
		message.setFrom(new InternetAddress(email_remitente));
		message.addRecipients(Message.RecipientType.TO, email_destino[0]);
		message.addRecipients(Message.RecipientType.TO, email_destino[1]);
		message.setSubject(asunto);
		BodyPart messageBodyPart1= new MimeBodyPart();
		messageBodyPart1.setText(mensaje);
		
		BodyPart messageBodyPart2= new MimeBodyPart();
		DataSource src= new FileDataSource(anexo[0]);
		messageBodyPart2.setDataHandler(new DataHandler(src));
		messageBodyPart2.setFileName(anexo[0]);
		
		//modificado para anexo2
		BodyPart messageBodyPart3= new MimeBodyPart();
		DataSource src2= new FileDataSource(anexo[1]);
		messageBodyPart3.setDataHandler(new DataHandler(src2));
		messageBodyPart3.setFileName(anexo[1]);
		
		Multipart multipart= new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		multipart.addBodyPart(messageBodyPart2);
		multipart.addBodyPart(messageBodyPart3);
		message.setContent(multipart);
		Transport transport= session.getTransport("smtp");
		transport.connect(host_email, email_remitente, email_remitente_pass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	
	
	private String handleGetRequest(HttpExchange httpExchange) {
		/**
		 * Nombre: handleGetRequest
		 * Parámetro de entrada: un objeto de tipo HttpExchange.
		 * Parámetro de salida: String
		 * Descripción: Separa, por el carácter "/" el string recibido en la URL y lo devuelve en el return.
		 */
		return httpExchange.getRequestURI().toString().split("\\?")[1];
		}
	
	private String handlePostRequest(HttpExchange httpExchange) {
		/**
		 * Nombre: handlePostRequest
		 * Parámetro de entrada: un objeto de tipo HttpExchange.
		 * Parámetro de salida: String
		 *  Descripción: Lee el cuerpo de la petición con BufferedReader, y lo devuelve en formato String.
		 */
		System.out.println("Recibida URI tipo POST: ");
		InputStream is= httpExchange.getRequestBody();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br=new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while((line=br.readLine()) !=null) {
				sb.append(line);
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		//Procesar lo que hay en inputStream, por ejemplo linea a linea y guardarlo todo en un string, que sera el que devuelve el metodo
		return sb.toString();
	}
	
	private void handleGETResponse(HttpExchange httpExchange, String requestParamValue) throws IOException, MessagingException {
		/**
		 * Nombre: handleGETResponse
		 * Parámetros de entrada: un objeto de tipo HttpExchange y un string con los parámetros recibidos en el GET.
		 * Descripción: maneja la respuesta del GET, en función del texto recibido como parámetro.
		 */
		//		
		if(requestParamValue.equals("temperaturaActual")) {
			OutputStream outputStream= httpExchange.getResponseBody();
			String htmlResponse= "<html><body><h1>Temperatura actual: "+ tempActual + "</h1><br/><h1>Temperatura termostato: "+ tempTermostato + "</h1></body></html>";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
			System.out.println("Devuelve respuesta HTML: " + htmlResponse);
		}else {
			OutputStream outputStream= httpExchange.getResponseBody();
			String htmlResponse= "<html><body><h1>Parámetro inválido, si usas GET utiliza 'temperaturaActual'</h1></body></html>";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
			System.out.println("Devuelve respuesta HTML: " + htmlResponse);
		}
	}
	
	private void handlePOSTResponse(HttpExchange httpExchange, String requestParamValue) throws IOException, InterruptedException, MessagingException {
		/**
		 * Nombre: handlePOSTResponse
		 * Parámetros de entrada: un objeto de tipo HttpExchange y un string con los parámetros recibidos en el POST.
		 * Descripción: maneja la respuesta del POST, en función del texto recibido como parámetro hacemos una cosa u otra.
		 */
		//chequeamos que se reciba un numero
		String[] param= requestParamValue.split("=");
		
		if(param[0].equals("setTemperatura")) {
			System.out.println("parametro correcto");
			tempTermostato=Integer.parseInt(param[1]);
			//lanzamos el método que ajusta la temperatura del termostato
			regularTemperatura();
			OutputStream outputStream= httpExchange.getResponseBody();
			System.out.println("Respuesta a la petición POST.");
			String htmlResponse= "Actualizada temperatura objetivo a: " +param[1]+" grados.";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
			System.out.println("Devuelve respuesta HTML: "+htmlResponse);
			
		}else if(param[0].equals("notificarAveria:email_remitente")) {
			//caso enviar email, recupero el remitente y la contraseña del cuerpo del POST
			String remitente=requestParamValue.toString().split(";")[0].split("=")[1];
			String pass=requestParamValue.toString().split(";")[1].split("=")[1];
	
			String mensaje="Se ha detectado una avería en el sistema, se informa a los responsables. Winter is coming";
			String asunto="AVERIA";
			String host="smtp.gmail.com";
			String port="587";
			String[] email_destino={"mantenimientoinvernalia@gmail.com", "megustaelfresquito@gmail.com"};
			//String[] email_destino={"alalca01@floridauniversitaria.es"};
			String[] anexo= {"certificado.pdf", "imagen.jpg"};
			OutputStream outputStream= httpExchange.getResponseBody();
			String htmlResponse= "Correo enviado";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
			
			envioMail(mensaje, asunto, remitente, pass, host, port, email_destino, anexo );			
		}
		else {
			System.out.println("parametro INCORRECTO");
			OutputStream outputStream= httpExchange.getResponseBody();
			System.out.println("Respuesta a la petición POST.");
			String htmlResponse= "parametro invalido";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
			System.out.println("Devuelve respuesta HTML: "+htmlResponse);
		}
	}
	private void regularTemperatura() throws InterruptedException {
		//Nombre: regularTemperatura
		//Sin parámetros de entrada/salida
		//Descripción: aumento o disminuyo la temperatura en función del valor recibido
		
		if(tempActual<tempTermostato) {
			System.out.println("Temperatura objetivo: "+tempTermostato+". Aumentando temperatura de la estufa...");
			for(int i=tempActual;i<tempTermostato;i++) {
				System.out.println("Temperatura actual: "+tempActual);
				tempActual++;
				Thread.sleep(5000);
			}
		}else {
			System.out.println("Temperatura objetivo: "+tempTermostato+". Disminuyendo temperatura de la estufa...");
			for(int i=tempActual;i>tempTermostato;i--) {
				System.out.println("Temperatura actual: "+tempActual);
				tempActual--;
				Thread.sleep(5000);
			}
		}
	}
}
