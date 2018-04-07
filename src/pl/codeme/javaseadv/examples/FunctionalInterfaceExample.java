package pl.codeme.javaseadv.examples;

public class FunctionalInterfaceExample {
	
	public static void main(String[] args) {
		Function f = new Function() {
			
			@Override
			public void print(String msg) {
				System.out.println(msg);
				
			}
		};
//		f.print("Hello world");
		Function f2 = (msg) -> System.out.println(msg);
		f2.print("Hello world 2");
//		printSomewhere(
//				(msg) -> {System.out.println(msg); },
//				"Hello world 4");
		printSomewhere(System.out::println, "Hello world 5");
		
		Function f3 = System.out::println; // delegatura metod 
		printSomewhere(f3, "Sialalala");
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		Thread t2 = new Thread(() ->{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {}
			System.out.println("Finito");
			});
//		t2.start();
		StringToIntInterface fs1 = Integer::valueOf;
		fs1 = (msg) -> {return msg.length(); };
		System.out.println(fs1.convert("10"));
		
	}
		
				
	
	


	
	private static void printSomewhere(Function f, String msg) {
		f.print(msg);
	}
}

