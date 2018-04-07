package pl.codeme.javaseadv.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 7000);
			Thread.sleep(500);
			while (s.getInputStream().available() > 0) {
				System.out.print((char)s.getInputStream().read());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
