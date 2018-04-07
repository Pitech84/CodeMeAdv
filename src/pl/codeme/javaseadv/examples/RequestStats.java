package pl.codeme.javaseadv.examples;

public class RequestStats {
	private String metod;
	private String path;
	private int resultCode;
	private double reqTime;
	
	public RequestStats(String metod, String path) {
		super();
		this.metod = metod;
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

	public String getMetod() {
		return metod;
	}

	public String getPath() {
		return path;
	}
	
	
}
