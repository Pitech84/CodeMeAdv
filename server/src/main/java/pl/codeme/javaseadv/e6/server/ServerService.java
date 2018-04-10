package pl.codeme.javaseadv.e6.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

import pl.codeme.javaseadv.e6.server.http.Request;
import pl.codeme.javaseadv.e6.server.http.Response;
import pl.codeme.javaseadv.e6.services.Blog;

public class ServerService implements Callable<RequestStats> {
	private static final String NL = "\r\n";
	private Socket socket;
	
	public ServerService(Socket socket) {
		this.socket = socket;
	}

	@Override
	public RequestStats call() throws Exception {
		System.out.println("RequestStats");
		
		Request request = new Request(socket.getInputStream());
		Response response = new Response(socket.getOutputStream());

//		Blog blog = new Blog();
//		Class clazz = blog.getClass();
		
//		Class clazz = Class.forName("pl.codeme.javaseadv.e6.services.Blog");
//		Blog blog = clazz.newInstance();

		switch (request.getMethod()) {
			case "GET": doGet(request, response); break;
			case "POST": doPost(request, response); break;
		}
		socket.close();

		return new RequestStats("GET", "/");
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
