package pl.codeme.javaseadv.e6.services;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

import pl.codeme.javaseadv.e6.server.annotations.Endpoint;
import pl.codeme.javaseadv.e6.server.annotations.Service;
import pl.codeme.javaseadv.e6.server.http.Request;
import pl.codeme.javaseadv.e6.server.http.Response;

@Service(path="/files")
public class FileApi {
	private static final String NL = "\r\n";

	@Endpoint(path="/")
	public void list(Request req, Response res) {

		System.out.println(req.getBody());
		
//		JSONObject jsonReq = new JSONObject(req.getBody());
//		System.out.println("ACTION: " + jsonReq.getString("action"));
		
		JSONObject json = new JSONObject();
		json.put("przyklad", "wartosc");
		
		JSONArray fileList = new JSONArray();
		File f = new File(".");
		for (String file : f.list()) {
			fileList.put(file);
		}
		json.put("files", fileList);
		
		res.setContentType("application/json");
		res.setBody(json.toString() + NL);
		res.setResponseCode(200);
		System.out.println("WYSYLAM: " + json.toString());
		res.send();
	}
}
