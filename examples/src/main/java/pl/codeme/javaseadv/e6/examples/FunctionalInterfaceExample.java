package pl.codeme.javaseadv.e6.examples;

import pl.codeme.javaseadv.e6.examples.interfaces.Function;
import pl.codeme.javaseadv.e6.examples.interfaces.StringToIntInterface;

public class FunctionalInterfaceExample {

	public static void main(String[] args) {
		
		// Klasa anonimowa 
		// Klasa nie posiada nazwy, jedynie implementuje interfejs i od razu tworzy obiekt
		Function f1 = new Function() {
			@Override
			public void print(String msg) {
				System.out.println(msg);
			}
			
		};
		f1.print("Przykład 1 - klasa anonimowa");
		
		// Funkcja lamba - implementuje interfejs funkcyjny
		Function f2 = (msg) -> System.out.println(msg);
		f2.print("Przykład 2 - funkcja lambda");
		
		// Funkcja lambda jako parametr metody oczekującej interfejsu funkcyjnego w parametrze
		printSomewhere(
			(msg) -> { System.out.println(msg); },
			"Przykład 3 - funkcja lambda jako parametr metody"
		);

		// Referencja do metody
		printSomewhere(System.out::println, "Przykład 4 - referencja do metody");

		Function f3 = System.out::println;
		printSomewhere(f3, "Przykład 5 - referencja do metody poprzez zmienną");

		// Tradycyjnie - obiekt implementujący interfejs Runnable 
		// jako obiekt klasy anonimowej przekazany do konstruktora klasy Thread
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Przykład 6 - utworzenie wątku klasą anonimową");
			}
			
		});
		t1.start();

		// Bardziej zwięzła forma - za pomocą funkcji lambda 
		Thread t2 = new Thread(() -> { 
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {}
			System.out.print("Przykład 7 - utworzenie wątku z pomocą funkcji lambda");
		});
		t2.start();

		// Przykład innego interfejsu funkcyjnego
		StringToIntInterface fs1;

		// Zmienna fs1 to funkcja valueOf pozyskana dzięki referencji metody 
		fs1 = Integer::valueOf;
		System.out.println("Przykład 8: fs1.convert to Integer::valueOf - " + fs1.convert("10"));

		// Teraz fs1 przyjmuje jako wartość funkcję lambda
		fs1 = (msg) -> { return msg.length(); };
		System.out.println("Przykład 9: fs1.convert to podana funckja lambda - " + fs1.convert("10"));
	}

	private static void printSomewhere(Function f, String msg) {
		f.print(msg);
	}
}
