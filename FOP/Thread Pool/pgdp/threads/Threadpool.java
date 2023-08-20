package pgdp.threads;


import java.util.concurrent.LinkedBlockingQueue;

public class Threadpool {


	private Thread[] threads;
	private LinkedBlockingQueue<Task> queue;
	private boolean running=true;


	public Threadpool(int numWorkers) {
		this.queue=new LinkedBlockingQueue<>();
		threads=new Thread[numWorkers];
		Runnable worker=()->{
			try{
				while(true){
					Task t=queue.take();
					t.getRunnable().run();
					t.getFuture().finish();
				}
			}
			catch (InterruptedException e){
				e.printStackTrace();
				return;
			}

		};
		for (int i = 0; i < threads.length; i++) {
			threads[i]=new Thread(worker);
			threads[i].start();
		}

	}

	public Future submit(Runnable job) throws Exception {
		if (running){
			Future result=new Future();
			queue.put(new Task(job, result));
			return result;
		}
		else  throw new Exception();

	}

	public void shutdownNow() {
		running =false;
		for (Thread thread:threads) {
			thread.interrupt();
		}
	}
}
