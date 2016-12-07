package ie.gmit.sw.comparator;


public class Compare implements ComparatorRemote{

	public Factory factory;
	
	public Compare(){
		this.factory = new AlgorithmFactory();
	}
	
	public int doCompare(String st1, String st2, String alg){
		
		CompareAlgorithm algorithm;
		algorithm = factory.useAlgorithm(alg);
		
		return algorithm.distance(st1, st2);
	}
}
