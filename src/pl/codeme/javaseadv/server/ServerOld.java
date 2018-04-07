//package pl.codeme.javaseadv.server;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class ServerOld {
//
//	private static final String NL = "\r\n";
//	
//	public static void main(String[] args) {
//		
//		
//		
//		try {
//			ServerSocket ss = new ServerSocket(7000);
//			System.out.println("Czekam na połączenie");
//	
//			Socket s = ss.accept();
//			System.out.println("Połączenie zaakceptowane");
//			
//			String msg = "Witaj <b>swiecie</b>";
//			PrintWriter pw = new PrintWriter(s.getOutputStream());
//			pw.print("HTTP/1.1 200 OK" + NL);
//			pw.print("Content-type: text/html" + NL);
//			pw.print("Content-length: " + msg.length() + NL);
//			pw.print(NL);
//			pw.println(msg);
//			
//			pw.close();
//			s.close();
//			ss.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}
