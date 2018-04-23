package pl.codeme.javaseadv.e6.client.http;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class Request {
	private static final String NL = "\r\n";

	private String method; // TODO: przerobić na ENUM!
	
	private String path;
	
	private HashMap<String, String> headers;
	
	private String body;
	
	private String server;
	
	private String contentType;

	private OutputStream os;
	
	public Request(OutputStream os, String server, String path) {
		this.os = os;
		this.server = server;
		this.path = path;
		headers = new HashMap<String, String>();
	}
	
	public void setBody(String body, String contentType) {
		this.body = body;
		this.contentType = contentType;
	}
	
	public void addHeader(String name, String value) {
		headers.put(name, value);
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	private void println(String line) {
		line += NL;
		try {
			System.out.print(line);
			os.write(line.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String method) {
		// GET /path/to/resource HTTP/1.1
		println(method + ' ' + path + " HTTP/1.1");
		
		addHeader("Host", server);
		if (contentType != null) {
			addHeader("Content-Type", contentType);
		}
		if (body != null) {
			addHeader("Content-Length", ""+body.getBytes().length);
		} else {
			addHeader("Content-Length", "0");
		}
		
		for(String key : headers.keySet()) {
			println(key + ": " + headers.get(key));
		}
	
		println("");
		
		if (body != null) {
			System.out.println(body);
			try {
				os.write(body.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}










