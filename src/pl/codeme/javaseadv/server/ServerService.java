package pl.codeme.javaseadv.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

import pl.codeme.javaseadv.examples.RequestStats;

public class ServerService implements Callable<RequestStats> {
	
	private Socket socket;
	private static final String NL = "\r\n";
	
	public ServerService(Socket socket) {
		this.socket = socket;
	}
	@Override
	public RequestStats call() throws Exception {
		String msg = "Witaj <b>swiecie</b>";
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.print("HTTP/1.1 200 OK" + NL);
		pw.print("Content-type: text/html" + NL);
		pw.print("Content-length: " + msg.length() + NL);
		pw.print(NL);
		pw.println(msg);
		
		pw.close();
		socket.close();

		return new RequestStats("GET", "/");
	}

}
