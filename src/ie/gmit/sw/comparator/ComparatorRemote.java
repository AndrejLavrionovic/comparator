package ie.gmit.sw.comparator;

import java.rmi.Remote;

import org.omg.CORBA.portable.RemarshalException;

public interface ComparatorRemote extends Remote {
	public int doCompare(String st1, String st2, String alg) throws RemarshalException;
}
