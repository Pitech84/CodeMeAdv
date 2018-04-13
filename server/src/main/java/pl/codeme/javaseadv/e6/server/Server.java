package pl.codeme.javaseadv.e6.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import pl.codeme.javaseadv.e6.server.ServiceEndpoint;
import pl.codeme.javaseadv.e6.server.annotations.Endpoint;
import pl.codeme.javaseadv.e6.server.annotations.Service;
import pl.codeme.javaseadv.e6.services.Blog;

public class Server {

	private static final String NL = "\r\n";

	private static boolean finish = false;
	
	private static HashSet<Future<RequestStats>> stats;
	
	private static HashMap<String, ServiceEndpoint> services;

	private static void registerService(Class<?> clazz) {
		try {
			if (!clazz.isAnnotationPresent(Service.class)) 
				return;
			
			System.out.println("Registering service: " + clazz.getName());
			Object serviceInstance = clazz.newInstance();

			Service[] service = (Service[]) clazz.getAnnotationsByType(Service.class);
			String prefix = service[0].path();
			
			System.out.println("Endpoints:");
			Method[] methods = clazz.getMethods();
			for(int i=0; i<methods.length; i++) {
				if (!methods[i].isAnnotationPresent(Endpoint.class)) continue;
				Endpoint[] ann = methods[i].getAnnotationsByType(Endpoint.class);
				for(int j=0; j<ann.length; j++) {
					String path = ann[j].path();
					System.out.println("\t" + prefix+path);
					services.put(
						prefix+path, 
						new ServiceEndpoint(serviceInstance, methods[i])
					);
				}
			}

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		stats = new HashSet<Future<RequestStats>>();
		services = new HashMap<String, ServiceEndpoint>();
		
		//		Class clazz = Class.forName("pl.codeme.javaseadv.e6.services.Blog");
		registerService(Blog.class);

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
			System.out.println("\nSerwer uruchomiony");

//			ReentrantLock lock = new ReentrantLock();
			do {
				Future<RequestStats> reqStats = executor.submit(
						new ServerService(ss.accept(), services)
				);
//				lock.lock();
				stats.add(reqStats);
//				lock.unlock();
			} while (!finish);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	private static void reflectionExample() {
		Blog blog = new Blog();
		Class blogClazz = blog.getClass();

		Service[] service = (Service[]) blogClazz.getAnnotationsByType(Service.class);
		System.out.println("SERVICE: " + service[0].path());
		
		Method[]  methods = blogClazz.getMethods();
		for(int i=0; i<methods.length; i++) {
			System.out.println(methods[i].getName() +":");
			if (methods[i].isAnnotationPresent(Endpoint.class)) { 
				System.out.println("JEST ADNOTACJA ENDPOINT");
				Endpoint[] annotations = methods[i].getAnnotationsByType(Endpoint.class);
				for(int j=0; j<annotations.length; j++) {
					System.out.println("PATH: " + annotations[j].path());
				}
			}
		}
				
		System.exit(0);
	}
}
