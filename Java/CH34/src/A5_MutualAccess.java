class Counter
{
	int count = 0; // 공유되는 변수
	
	public void increment()
	{
		count++; // 첫 번째 쓰레드에 의해 실행
	}
	
	public void decrement()
	{
		count--; // 또 다른 쓰레드에 의해 실행
	}
	
	public int getCount()
	{
		return count;
	}
}
public class A5_MutualAccess {
	public static Counter cnt = new Counter();
	
	public static void main(String[] args) throws InterruptedException {
		Runnable task1 = () -> {
			for(int i = 0; i < 1000; i++)
			{
				cnt.increment(); // 값을 1 증가
			}
		};
		
		Runnable task2 = () -> {
			for(int i = 0; i < 1000; i++)
			{
				cnt.decrement(); // 값을 1 감소
			}
		};
		
		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		t1.start();
		t2.start();
		t1.join();// t1이 참조하는 쓰레드의 종료를 기다린다.
		t2.join();// t2가 참조하는 쓰레드의 종료를 기다린다.
		//쓰레드가 종료되면 출력을 진행함. 위 join의 영향
		System.out.println(cnt.getCount());
				

	}

}
