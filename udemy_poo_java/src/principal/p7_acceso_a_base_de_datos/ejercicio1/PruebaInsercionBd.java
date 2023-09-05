package principal.p7_acceso_a_base_de_datos.ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
 El proceso para realizar operaciones de accion contra una BD es:
 1. Cargar el driver
 2. Establecimiento de la conexion con la BD
 3. Ejecucion de la consulta SQL de accion
 4. Cierre de la conexion
 */
public class PruebaInsercionBd {
	
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3307/prueba";
		String user="root";
		String pwd="root";
		try(Connection cn=DriverManager.getConnection(url,user,pwd)){
			String sql="insert into personas(nombre,edad,email,altura) values ";
			sql+="('prueba5',27,'p5@gmail.com',1.73)";
			Statement st=cn.createStatement();
			st.execute(sql);
			System.out.println("Persona agregada!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
