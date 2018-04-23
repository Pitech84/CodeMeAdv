package pl.codeme.javaseadv.e6.client.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Response {

	private int responseCode;
	
	private HashMap<String, String> headers;
	
	private String body;

	private BufferedReader br;
	
	private String readLine() {
		String result = null;
		
		try {
			while (!br.ready()) {
				Thread.sleep(500);
			}
			result = br.readLine();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		};
		
		return result;
	}
	
	private void processFirstLine() throws IOException {
		String line = readLine();
		responseCode =  Integer.parseInt(line.split(" ")[1]);
	}
	
	private void processHeaders() throws IOException {
		String line = readLine();
		while (line.length() > 0) {
			String name = line.substring(0, line.indexOf(':'));
			String value = line.substring(name.length()+2);
			
			line = readLine();
		}
	}
	
	private void processBody() throws IOException {
		body = "";
		while(br.ready()) {
			body += br.readLine();
		}
	}
	
	public Response(InputStream is) {
		br = new BufferedReader(new InputStreamReader(is));
		headers = new HashMap<String, String>();
		
		try {
			processFirstLine();
			processHeaders();
			processBody();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getResponseCode() {
		return responseCode;
	}

	public String getHeader(String name) {
		return headers.get(name);
	}

	public String getBody() {
		return body;
	}
	
	
}
