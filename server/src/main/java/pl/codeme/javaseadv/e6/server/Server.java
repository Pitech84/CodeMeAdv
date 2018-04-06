package pl.codeme.javaseadv.e6.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {

	private static final String NL = "\r\n";

	private static boolean finish = false;
	
	public static void main(String[] args) {
		try {
			ExecutorService executor = Executors.newFixedThreadPool(10);
			ServerSocket ss = new ServerSocket(7000);
			System.out.println("Serwer uruchomiony");

			do {
				Future<RequestStats> stats = executor.submit(new ServerService(ss.accept()));
			} while (!finish);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
