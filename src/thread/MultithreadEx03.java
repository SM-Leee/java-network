package thread;

public class MultithreadEx03 {

	public static void main(String[] args) {
		
		Thread thread1 = new AlphabeticThread();
		Thread thread2 = new DigitThread();
		Runnable runnable= new UppercaseAlphabeticRunnableImpl();
		
		thread1.start();
		thread2.start();
		new Thread(runnable).start();
		
//		Thread thread3 = new Thread(new UppercaseAlphabeticRunnableImpl());
//		thread3.start();
		

	}

}
