package pl.codeme.javaseadv.e6.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Request {
	private InputStream in;
	private BufferedReader br;
	
	private String method;
	private String path;
	private HashMap<String, String> headers;
	private String body;
	
	public Request(InputStream in) {
		this.in = in;
		br = new BufferedReader(new InputStreamReader(this.in));
		headers = new HashMap<String, String>();
		
		processFirstLine();
		processHeaders();
		processBody();
		
	}

	private void processFirstLine() {
		try {
			String firstLine = br.readLine();
		
			String[] tmpFirstLine = firstLine.split(" ");
			method = tmpFirstLine[0];
			path = tmpFirstLine[1];
		} catch (IOException e) {
			e.printStackTrace();
		}
;	}

	public void processHeaders() {
		String tmpLine = "";
		for(;;) {
			try {
				tmpLine = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
			if (tmpLine.equals("")) return;
			
			String name = tmpLine.substring(0, tmpLine.indexOf(':'));
			String value = tmpLine.substring(name.length()+1);
			
			headers.put(name, value);
		} 
	}
	
	public void processBody() {
		body = "";
		try {
			while (br.ready()) {
				body += br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public String getBody() {
		return body;
	}
	
}
