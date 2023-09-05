package principal.p3_interfaces.ejercicio4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*

*/

public class Test {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		//implementación que transforma un String en un Integer
		Function<String,Integer> fr = (String a)->Integer.getInteger(a);
		Integer num = fr.apply("45");
		
		//Muestra el dato recibido
		Consumer<String> cr = (String a)->System.out.println(a);
		cr.accept("Hola mundo");
		
		//Simula la obtención de una conexión con una base de datos
		Supplier<Connection> sp=()->{
			String cad="cadena conexión BD";
			String user="usauario";
			String pass="password";
			Connection cn = null;
			try {
				cn=DriverManager.getConnection(cad, user, pass);
				return cn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return cn;
		};
		Connection cn = sp.get();
		
	}
}
