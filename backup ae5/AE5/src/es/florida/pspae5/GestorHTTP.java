package es.florida.pspae5;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class GestorHTTP implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException{
		String requestParamValue=null;
		if ("GET".equals(httpExchange.getRequestMethod())) {
			requestParamValue= handleGetRequest(httpExchange);
			handleGETResponse(httpExchange,requestParamValue);
		} else if("POST".equals(httpExchange.getRequestMethod())) {
			requestParamValue= handlePostRequest(httpExchange);
			handlePOSTResponse(httpExchange,requestParamValue);
		}
	}
	
	private String handleGetRequest(HttpExchange httpExchange) {
		return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
		}
	
	private void handleGETResponse(HttpExchange httpExchange, String requestParamValue) {
			OutputStream outputStream= httpExchange.getResponseBody();
			String htmlResponse= "<html><body>Hola"+requestParamValue+</body></html>";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
	}

}
