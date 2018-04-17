package pl.codeme.javaseadv.e6.examples;

import java.lang.reflect.Field;

public class MyPrivateExample {

	public static void main(String[] args) {
//		System.setSecurityManager(new SecurityManager());
		
		MyPrivate priv = new MyPrivate();

		System.out.println("PRIV: " + priv.getSecret());

		Class<?> clazz = MyPrivate.class;
		try {
			Field field = clazz.getDeclaredField("secret");

			Field[] fields = clazz.getDeclaredFields();
			for(int i=0; i<fields.length; i++) {
				System.out.println("pole: " + fields[i].getName());
			}
			
			field.setAccessible(true);
			System.out.println("READED: " + field.get(priv));
			field.set(priv, "HACKED!");
			System.out.println("PRIV TERAZ: " + priv.getSecret());
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
