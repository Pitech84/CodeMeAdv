package pl.codeme.javaseadv.e6.server.http;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

public class Response {
	private static final String NL = "\r\n";
	
	private OutputStream out;
	
	private int responseCode;
	private String responseMsg;
	private HashMap<String, String> headers;
	private String body;

	private String contentType = "text/html";

	public Response(OutputStream out) {
		this.out = out;
		headers = new HashMap<String, String>();
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
		// TODO: Zrobić ładne mapowanie
		this.responseMsg = "OK";
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public void addHeader(String name, String value) {
		headers.put(name, value);
	}
	
	public void removeHeader(String name) {
		headers.remove(name);
	}

	public void send() {
		PrintWriter pw = new PrintWriter(out);

//		pw = new PrintWriter(System.out);
//		String msg = "Witaj <b>swiecie</b>";
//		
//		PrintWriter pw = new PrintWriter(socket.getOutputStream());
//		pw.print("HTTP/1.1 200 OK" + NL);
//		pw.print("Content-type: text/html" + NL);
//		pw.print("Content-length: " + msg.length() + NL);
//		pw.print(NL);
//		pw.print(msg);
//		
//		pw.close();
		pw.print("HTTP/1.1 " + responseCode + " " + responseMsg + NL);
		for (String header : headers.keySet()) {
			pw.print(header + ": " + headers.get(header) + NL);
		}
	
		pw.print("Content-type: " + contentType + NL);
		pw.print("Content-length: " + body.getBytes().length + NL);

		pw.print(NL);
		pw.print(body);

		pw.close();
	}
}
















