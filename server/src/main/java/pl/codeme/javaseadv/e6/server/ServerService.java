package pl.codeme.javaseadv.e6.server;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Callable;

import pl.codeme.javaseadv.e6.server.annotations.Endpoint;
import pl.codeme.javaseadv.e6.server.annotations.Service;
import pl.codeme.javaseadv.e6.server.http.Request;
import pl.codeme.javaseadv.e6.server.http.Response;
import pl.codeme.javaseadv.e6.services.Blog;

public class ServerService implements Callable<RequestStats> {
	private static final String NL = "\r\n";
	private Socket socket;
	private HashMap<String, ServiceEndpoint> services;
	
	public ServerService(Socket socket, HashMap<String, ServiceEndpoint> services) {
		this.socket = socket;
		this.services = services;
	}

	@Override
	public RequestStats call() throws Exception {
		Request request = new Request(socket.getInputStream());
		Response response = new Response(socket.getOutputStream());

		for (String url : this.services.keySet()) {
			if (request.getPath().matches(url)) {
				services.get(url).doInjection().fire(request, response);
				socket.close();
				return new RequestStats(request.getMethod(), request.getPath());
			}
		}

// Brzydko, po staremu...
//		switch (request.getMethod()) {
//			case "GET": doGet(request, response); break;
//			case "POST": doPost(request, response); break;
//		}
		socket.close();
		return new RequestStats(request.getPath(), "404");
	}

	public void doGet(Request request, Response response) {
		response.setResponseCode(200);
		response.addHeader("Server", "make money server");
		response.setBody("Witaj <b>świecie</b>!");
		response.send();
	}
	
	public void doPost(Request request, Response response) {
	}
}
