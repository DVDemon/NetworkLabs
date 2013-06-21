
import java.rmi.Naming;
import java.rmi.RemoteException;

public class EX08_RMIClient {

	public static void main(String[] args) {
		
		try{
                    
                
			IEx08_Interface server = (IEx08_Interface) Naming.lookup("rmi://localhost/MyServer");
			
			int src[]=new int[] {1,2,3,4,5};
			int ret[] = server.doHelloWorld(src);
		    System.out.println("Result:");
		    for(int i=0;i<ret.length;i++) System.out.println("["+i+"]="+ret[i]);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}

}
