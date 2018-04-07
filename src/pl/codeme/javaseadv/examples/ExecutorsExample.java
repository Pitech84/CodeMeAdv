package pl.codeme.javaseadv.examples;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsExample {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		System.out.println("Start");

		Callable<String> f = () -> {return "sialala"; }; // moglibysmy probowac zapisac to jako lambde
		// ale musielibysmy podac jaki typ parametru wejciowego i typ zwracany
		Future<String> result = service.submit(new Callable<String>() {

			@Override
			public String call()   {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {}
				return "Finito";
			}
		});
		
		try {
			while (!result.isDone()) {
				Thread.sleep(500);
				System.out.print(".");
			}
			System.out.println(result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
/*	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		service.submit(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			System.out.println("Finito");
		});

	}*/

}
