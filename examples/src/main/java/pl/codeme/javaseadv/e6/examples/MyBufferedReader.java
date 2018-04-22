package pl.codeme.javaadv.server.http;

import java.io.IOException;
import java.io.InputStream;

public class MyBufferedReader {
	private final int MAX_LINE_LENGTH = 1024;
	
	private InputStream is;

	private byte[] buffer;
	private int r; // read pointer
	private int w; // write pointer
	
	public MyBufferedReader(InputStream is) {
		super();
		this.is = is;
		buffer = new byte[1024*1024*10]; // 10Mb
		r = 0;
		w = 0;
	}
	
	private void updateBufferFromStream() {
		try {
			while(is.available()==0) {
				Thread.sleep(500);
			}
			
			while(is.available()>0) {
				buffer[w++] = (byte)is.read();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] read(int amount) {
		byte[] res = new byte[amount];
		
		// Read data from stream as long as we need it
		while(r+amount>w) {
			updateBufferFromStream();
		}
		
		System.arraycopy(buffer, r, res, 0, amount);
		
		r+=amount;
		
		return res;
	}
	
	public String readLine() {
		byte[] res;
		int wsk = 0;
		boolean found = false;
		
		String dbg ="";
		
		do {
			if (r+wsk>=w) 
				updateBufferFromStream();
			
			if (buffer[r+wsk] == '\n' || buffer[r+wsk] == '\r' || wsk>=MAX_LINE_LENGTH) {
				found=true;
			} else {
				wsk++;
			}

		} while (!found);
		
		res = new byte[wsk];
		System.arraycopy(buffer, r, res, 0, wsk);

		if (buffer[r+wsk] == '\r' && buffer[r+wsk+1] == '\n') {
			wsk++;
		}
		
		wsk++;

		r+=wsk;
		
		dbg = new String(res);
		
		return new String(res);
	}
}
