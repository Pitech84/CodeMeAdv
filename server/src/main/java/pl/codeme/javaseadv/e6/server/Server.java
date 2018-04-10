package pl.codeme.javaseadv.e6.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

	private static final String NL = "\r\n";

	private static boolean finish = false;
	
	private static HashSet<Future<RequestStats>> stats;
	
	public static void main(String[] args) {
		stats = new HashSet<Future<RequestStats>>();
		
		try {
			ExecutorService executor = Executors.newFixedThreadPool(10);
			ExecutorService watchdog = Executors.newSingleThreadExecutor();
			watchdog.submit(() -> {
				do {
					for(Future<RequestStats> s : stats) {
						if (s.isDone()) {
							RequestStats rs;
							try {
								rs = s.get();
								System.out.println(rs.toString());
							} catch (InterruptedException | ExecutionException e) {
								e.printStackTrace();
							}
							stats.remove(s);
						}
					}
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} while (!finish);
			});

			ServerSocket ss = new ServerSocket(7000);
			System.out.println("Serwer uruchomiony");

//			ReentrantLock lock = new ReentrantLock();
			do {
				Future<RequestStats> reqStats = executor.submit(new ServerService(ss.accept()));
//				lock.lock();
				stats.add(reqStats);
//				lock.unlock();
			} while (!finish);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
