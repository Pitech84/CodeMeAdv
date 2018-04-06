package pl.codeme.javaseadv.e6.examples;

public class MyThread implements Runnable {

	private int sleepTime;
	private ThreadData td;
	
	public MyThread(int sleepTime, ThreadData td) {
		this.sleepTime = sleepTime;
		this.td = td;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(sleepTime);
			
			synchronized (td) {
				td.threadNumber++;
			}
			
//			System.out.println("Finished " + sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
