package pl.codeme.javaseadv.e6.examples;

public class ThreadExample {

	private final static int NUM_THREADS = 10000;
	
	public static void main(String[] args) {
//		MyThread mt = new MyThread(1000);
//		Thread t = new Thread(mt);
//		t.start();
//		t.join();

		ThreadData td = new ThreadData();
	
		Thread[] ths = new Thread[NUM_THREADS];
		for(int i=0; i<NUM_THREADS; i++) {
			ths[i] = new Thread(new MyThread(1, td));
			
//			(new Thread(new MyThread(100, td))).start();
//			(new MyThread(100)).run();
		}

		for(int i=0; i<NUM_THREADS; i++) {
			ths[i].start();
		}
		
		for(int i=0; i<NUM_THREADS; i++) {
			try {
				ths[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println(td.threadNumber);
	}

}
