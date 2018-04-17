package pl.codeme.javaseadv.e6.server;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pl.codeme.javaseadv.e6.annotations.Inject;
import pl.codeme.javaseadv.e6.server.http.Request;
import pl.codeme.javaseadv.e6.server.http.Response;

public class ServiceEndpoint {
	private Object service;
	private Method endpoint;

	public static String implementationPackage = "pl.codeme.javaseadv.e6.diimplementation";
	
	public ServiceEndpoint(Object service, Method endpoint) {
		this.service = service;
		this.endpoint = endpoint;
	}
	
	public ServiceEndpoint doInjection() {
		Field[] field = service.getClass().getDeclaredFields();
		
		for(int i=0; i<field.length; i++) {
			if (!field[i].isAnnotationPresent(Inject.class)) continue;
			Class type = field[i].getType();
			String name = type.getName(); // pełna nazwa interfejsu razem z pakietem
			String implementationName = name.substring(name.lastIndexOf(".")+1, name.length()) + "Impl";
			
			try {
				Object obj = Class.forName(implementationPackage+'.'+implementationName)
						.newInstance();
				field[i].set(service, obj);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return this;
		
	}
	
	public void fire(Request req, Response res) {
		try {
			endpoint.invoke(service, req, res);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
