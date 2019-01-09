package thread;

public class MultithreadEx02 {

	public static void main(String[] args) {
		Thread t1 = new DigitThread();
		Thread t2 = new AlphabeticThread();
		Thread t3 = new DigitThread();
		
		t1.start();
		t2.start();
		t3.start();

	}

}
