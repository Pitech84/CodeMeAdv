package pl.codeme.javaseadv.e6.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pl.codeme.javaseadv.e6.server.http.Request;
import pl.codeme.javaseadv.e6.server.http.Response;

public class ServiceEndpoint {
	private Object service;
	private Method endpoint;

	public ServiceEndpoint(Object service, Method endpoint) {
		this.service = service;
		this.endpoint = endpoint;
	}
	
	public void fire(Request req, Response res) {
		try {
			endpoint.invoke(service, req, res);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
