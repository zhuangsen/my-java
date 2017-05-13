
public class TestThread
{
	private int j;
	public synchronized void inc()
	{
		j++;
		System.out.println(Thread.currentThread().getName() + "-inc:" + j);
	}
	public synchronized void dec()
	{
		j--;
		System.out.println(Thread.currentThread().getName() + "-dec:" + j);
	}
	public static void main(String[] args)
	{
		TestThread t=new TestThread();
		for (int i = 0; i < 2; i++)
		{
			Thread inc= new Thread(new Inc(t), "int");
			Thread dec=new Thread(new Dec(t),"dec");
			inc.start();
			dec.start();
		}
	}
}
	
	class Inc implements Runnable
	{
		private TestThread obj;		
		
		public Inc(TestThread obj)
		{
			this.obj=obj;
		}
		public void run()
		{
//			for (int i = 0; i < 10; i++)
//			{
				this.obj.inc();
//			}
		}
	}
	class Dec implements Runnable
	{
		private TestThread obj;
		public Dec(TestThread obj)
		{
			this.obj=obj;
		}
		public void run()
		{
//			for (int i = 0; i < 10; i++)
//			{
				this.obj.dec();
//			}
		}
	}