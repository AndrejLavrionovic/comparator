package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import ie.gmit.sw.comparator.ComparatorRemote;
import ie.gmit.sw.comparator.Compare;

public class ComparatorServiceImpl extends UnicastRemoteObject implements ComparatorService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComparatorServiceImpl() throws RemoteException {}

	@Override
	public int getResult(String s, String t, String alg) throws RemoteException {
		ComparatorRemote cr = new Compare();
		return cr.doCompare(s, t, alg);
	}
	
	public static void main(String[] args){
		try{
			ComparatorService service = new ComparatorServiceImpl();
			
			/*
			 * To work with remote server we need to set property of
			 * java.rmi.server.hostname as a server ip address
			 * solution was found in http://stackoverflow.com/questions/15685686/java-rmi-connectexception-connection-refused-to-host-127-0-1-1
			 * 
			 * System.setProperty("java.rmi.server.hostname","192.168.0.19");
			 * 
			 */
			
			//Start the RMI regstry on port 1099
			LocateRegistry.createRegistry(1099);
			Naming.rebind("compare", service);
			System.out.println("Server ready.");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
