package pl.codeme.javaseadv.e6.server;

public class RequestStats {
	private String method;
	private String path;
	private int resultCode;
	private double reqTime;
	
	public RequestStats(String method, String path) {
		super();
		this.method = method;
		this.path = path;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public double getReqTime() {
		return reqTime;
	}

	public void setReqTime(double reqTime) {
		this.reqTime = reqTime;
	}

	public String getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}
}
