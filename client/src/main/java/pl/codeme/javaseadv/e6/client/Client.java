package pl.codeme.javaseadv.e6.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.HostnameVerifier;

import pl.codeme.javaseadv.e6.client.http.Request;
import pl.codeme.javaseadv.e6.client.http.Response;

public class Client {
	private String host;
	private Socket socket;
	
	public Client(String hostName, int port) throws UnknownHostException, IOException {
		this.host = hostName;
		socket = new Socket(hostName, port);
	}
	
	private void waitForResponse() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sendRequest() throws IOException {
		Request req = new Request(socket.getOutputStream(), host, "/files/");
		req.setBody("{\"action\":\"list\",\"params\":{\"dir\":\".\"}}", "application/json");
		req.send("POST");

		waitForResponse();
		
		Response res = new Response(socket.getInputStream());
		System.out.println(res.getBody());
		System.out.println("KOD ODPOWIEDZI: " + res.getResponseCode());
	}

	public static void main(String[] args) {
			try {
				Client client = new Client("localhost", 7000);
				client.sendRequest();
			
			} catch (Exception e) {
				e.printStackTrace();
			}

	}

}
