package ie.gmit.sw.comparator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Compare extends UnicastRemoteObject implements ComparatorRemote{

	public Factory factory;
	
	public Compare() throws RemoteException{
		this.factory = new AlgorithmFactory();
	}
	
	public int doCompare(String st1, String st2, String alg){
		
		CompareAlgorithm algorithm;
		algorithm = factory.useAlgorithm(alg);
		
		return algorithm.distance(st1, st2);
	}
}
