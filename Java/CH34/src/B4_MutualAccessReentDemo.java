import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Counter4
{
	int count = 0; // 공유되는 변수
	ReentrantLock criticObj = new ReentrantLock();

	public void increment()
	{
		criticObj.lock();
		try
		{
		count++; // 첫 번째 쓰레드에 의해 실행
		}
		finally
		{
			criticObj.unlock();
		}
	}
	
	public void decrement()
	{
		criticObj.lock();
		try
		{
		count--; // 또 다른 쓰레드에 의해 실행
		}
		finally
		{
			criticObj.unlock();
		}
	}
	
	public int getCount()
	{
		return count;
	}
}
public class B4_MutualAccessReentDemo {
	public static Counter4 cnt = new Counter4();
	
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
		
		ExecutorService exr = Executors.newFixedThreadPool(2);
		exr.submit(task1);
		exr.submit(task2);
		exr.shutdown();
		exr.awaitTermination(100, TimeUnit.SECONDS);
	
		System.out.println(cnt.getCount());
				

	}

}

