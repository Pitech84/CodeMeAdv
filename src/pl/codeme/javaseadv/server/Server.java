package pl.codeme.javaseadv.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	private static boolean finish = false;

	public static void main(String[] args) {
		
		
		
		try {
			ExecutorService executor = Executors.newFixedThreadPool(10);
			ServerSocket ss = new ServerSocket(7000);
			System.out.println("Czekam na połączenie");
	
			do {
			Socket s = ss.accept();
			System.out.println("Połączenie zaakceptowane");
			executor.submit(new ServerService(s));
			} while (!finish);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
