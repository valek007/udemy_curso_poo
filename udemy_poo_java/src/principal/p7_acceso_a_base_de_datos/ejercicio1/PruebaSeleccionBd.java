package principal.p7_acceso_a_base_de_datos.ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
El proceso para realizar operaciones de accion contra una BD es:
1. Cargar el driver
2. Establecimiento de la conexion con la BD
3. Ejecucion de la consulta SQL de accion
4. Manipulacion de resultados
5. Cierre de la conexion
*/

public class PruebaSeleccionBd {
	
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3307/prueba";
		String user="root";
		String pwd="root";
		try(Connection cn=DriverManager.getConnection(url, user, pwd)){
			String sql="select * from personas";
			Statement st=cn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString("nombre")+"-"+rs.getInt("edad"));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}
