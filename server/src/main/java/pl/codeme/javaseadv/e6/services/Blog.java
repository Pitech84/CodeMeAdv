package pl.codeme.javaseadv.e6.services;

import pl.codeme.javaseadv.e6.annotations.Inject;
import pl.codeme.javaseadv.e6.server.annotations.Endpoint;
import pl.codeme.javaseadv.e6.server.annotations.Service;
import pl.codeme.javaseadv.e6.server.http.Request;
import pl.codeme.javaseadv.e6.server.http.Response;
import pl.codeme.javaseadv.e6.server.di.ServerContext;

 @Service(path="/blog")
public class Blog {

	@Inject
	public ServerContext context;
	
	@Endpoint(path="/")
	public void list(Request req, Response res) {
		res.setBody("Lista artykulow<br>"
				+ "Kontekst: " + context.getInfo());
		res.send();
	}
	
	@Endpoint(path="/.*\\.html")
//	@Endpoint(path="/articles/.*")
	public void showArticle(Request req, Response res) {
		res.setBody("Artykul z adresu " + req.getPath());
		res.send();
	}
}
