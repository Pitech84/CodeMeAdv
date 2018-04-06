package pl.codeme.javaseadv.e6.examples;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsExample {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		System.out.println("Start");
		
		Callable<String> f = () -> { return "sialala"; };
		
		Future<String> result = service.submit(new Callable<String>()  {

			@Override
			public String call() throws Exception {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {}
				
				return "Finito";
			} 
			
		});
		
		
		try {
			while (!result.isDone()) {
				Thread.sleep(500);
				System.out.print('.'); 
			}
			
			System.out.println(result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
