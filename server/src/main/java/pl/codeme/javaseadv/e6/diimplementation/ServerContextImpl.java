package pl.codeme.javaseadv.e6.diimplementation;

import pl.codeme.javaseadv.e6.server.di.ServerContext;

public class ServerContextImpl implements ServerContext {

	@Override
	public String getInfo() {
		return "moja implementacja";
	}

}
