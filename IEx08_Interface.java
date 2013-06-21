import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEx08_Interface extends Remote 
{
	public int[] doHelloWorld(int[] src) throws RemoteException;
}
