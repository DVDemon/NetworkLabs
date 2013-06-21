import org.apache.ws.axis2.MyServiceStub;

public class Ex10_Axis2Client {
    	public static void main(String[] argv) throws Exception
	{
	  MyServiceStub stub = new MyServiceStub();
	  MyServiceStub.DoHelloWorld request = new MyServiceStub.DoHelloWorld();
	  request.setSrc(new int[] {1,2,3,4,5,10,11,12});
	  MyServiceStub.DoHelloWorldResponse result = stub.doHelloWorld(request);
	  if(result!=null)
	  {
	   if(result.get_return()!=null)
	     for(int i=0;i<result.get_return().length;i++)
             System.out.println("["+result.get_return()[i]+"]");
	  }
	 }

}
