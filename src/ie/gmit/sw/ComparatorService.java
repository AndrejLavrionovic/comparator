package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ComparatorService extends Remote {
	public int getResult(String s, String t, String alg) throws RemoteException;
}
