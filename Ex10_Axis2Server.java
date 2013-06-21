
public class Ex10_Axis2Server {

	// вообще говоря, исключения надо обрабатывать корректно
	public static void main(String[] args) throws Exception
      {
     // запускаем сервис
     // http://localhost:6060/axis2/services/Ex10_Axis2Server?wsdl
	new org.apache.axis2.engine.AxisServer().deployService(                 
            MyService.class.getName());
       }
}


