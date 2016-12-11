package ie.gmit.sw;

import java.rmi.Naming;

public class Resultator implements Runnable {
	
	private RequestQueue queue;
	private Results results;
	private Requestable request;
	
	public Resultator(RequestQueue queue, Results results){
		this.queue = queue;
		this.results = results;
	}

	@Override
	public void run() {
		request = queue.takeRequest();
		
		//You can use this method to implement the functionality of an RMI client
		try{
			ComparatorService service = (ComparatorService)Naming.lookup("rmi://192.168.0.17:1099/compare");
			int r = service.getResult(request.getStringOne(), request.getStringTwo(), request.getAlgorithm());
			
			request.setResult(r);
			
			if(request.getResult() != null){
				results.addResult(request.getJobNumber(), request);
			}

		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}

	}

}
