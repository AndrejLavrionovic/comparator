package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;

public class Results {

	private Map<String, Requestable> results = new HashMap<String, Requestable>();
	
	public void addResult(String key, Requestable value){
		results.put(key, value);
	}
	
	public Requestable takeResult(String key){
		return results.get(key);
	}
	
	public boolean isResultsEmpty(){
		return this.results.isEmpty();
	}
	
	public Map<String, Requestable> getResults(){
		return this.results;
	}
	
	public boolean isResultReady(String key){
		return this.results.containsKey(key);
	}
}
