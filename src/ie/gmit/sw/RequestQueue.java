package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestQueue {

	private BlockingQueue<Requestable> bq = new LinkedBlockingQueue<Requestable>();
	
	
	// insert new element at the tail of the queue
	public void addRequest(Requestable res){
		try {
			bq.put(res);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// check if the queue is empty
	public boolean isQueueEmpty(){
		return this.bq.isEmpty();
	}

	// retriev and remove the head of the queue
	public Requestable takeRequest(){
			return this.bq.poll();
	}
	
	// return queue
	public BlockingQueue<Requestable> getQueue(){
		return this.bq;
	}
	
	public int getSizeOfQueue(){
		return this.bq.size();
	}
}
