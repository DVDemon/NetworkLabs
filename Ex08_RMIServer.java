import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;

public class Ex08_RMIServer extends UnicastRemoteObject implements IEx08_Interface {
	public Ex08_RMIServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) 
	{
		if (System.getSecurityManager() == null) {
		    //System.setSecurityManager(new RMISecurityManager());
		}
	
		try {
                    
                    //java.rmi.registry.LocateRegistry.createRegistry(1099);
		    Naming.rebind("MyServer", new Ex08_RMIServer());
		    System.out.println("Server bound in registry");
		} catch (Exception e) {
		    
		    e.printStackTrace();
		}

	}

	public int[] doHelloWorld(int[] src) throws RemoteException {
		int[] result = new int[src.length];
		
		for(int i=0;i<src.length;i++)
		{
			result[i]=src[src.length-i-1];
		}
		return result;
	}

}
