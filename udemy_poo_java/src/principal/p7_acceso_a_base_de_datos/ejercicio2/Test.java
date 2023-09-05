package principal.p7_acceso_a_base_de_datos.ejercicio2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import principal.p7_acceso_a_base_de_datos.ejercicio2.beans.Contacto;


/*
Realizar una nueva versión del ejercicio 6, en el que los datos
de la agenda se guardaban en una base de datos, implementando las 
operaciones de busqueda y recuperacion de contactos a traves 
de java.nio.
 */
public class Test {

	public static void main(String[] args) {

		arrancarPrograma(); 
	}

	// ---CAPA PRESENTACIÓN---
	private static void mostrarMenu() {
		System.out.println("1.- Añadir Contacto");
		System.out.println("2.- Buscar Contacto");
		System.out.println("3.- Eliminar Contacto");
		System.out.println("4.- Mostrar Contactos");
		System.out.println("5.- Salir");
	}

	private static void arrancarPrograma() {

		try (Scanner sc = new Scanner(System.in)) {
			int opc = 0;
			do {
				mostrarMenu();
				opc = Integer.parseInt(sc.nextLine());

				switch (opc) {
				case 1 -> {
					System.out.println("Introduce el nombre del contacto");
					String nombre = sc.nextLine();
					System.out.println("Introduce el email del contacto");
					String email = sc.nextLine();
					System.out.println("Introduce la edad del contacto");
					int edad = Integer.parseInt(sc.nextLine());
					if (agregarContacto(nombre, email, edad)) {
						System.out.println("El contacto se ha agregado correctamente.\n");
					} else {
						System.out.println("El contacto no se ha podido agregar.\n");
					}
				}
				case 2 -> {
					System.out.println("Introduce el email del contacto");
					String email = sc.nextLine();
					Contacto contacto = buscarContacto(email);
					if (!(contacto == null)) {
						System.out.println("Datos del contacto:.\n" + contacto + "\n");

					} else {
						System.out.println("El contacto no se encuentra en la agenda.\n");
					}
				}
				case 3 -> {
					System.out.println("Introduce el email del contacto");
					String email = sc.nextLine();
					if (eliminarContacto(email))
						System.out.println("El contacto se ha eliminado correctamente\n");
					else
						System.out.println("El contacto no se ha podido eliminar\n");
				}
				case 4 -> {
					System.out.println("Todos los contactos:");
					mostrarContactos();
					System.out.println();
				}
				}

			} while (opc != 5);
			System.out.println("Programa ha finalizado");
		} catch (Exception e) {
			System.out.println("Opción no valida");
			e.printStackTrace();
		}
	}

	// ---CAPA LÓGICA---
	private static boolean agregarContacto(String nombre, String email, int edad) {
		
		try(Connection cn = crearConexion("agenda")){
			String sql="insert into contactos(nombre,email,edad) values (?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setString(2, email);
			ps.setInt(3, edad);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static Contacto buscarContacto(String email) {
		Contacto contacto = null;
		try(Connection cn = crearConexion("agenda")){
			String sql="select * from contactos where email=?";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			contacto = new Contacto(rs.getInt("idcontacto"),rs.getString("nombre"), rs.getString("email"), rs.getInt("edad"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacto;
	}
	
	private static boolean eliminarContacto(String email) {
		
		try(Connection cn = crearConexion("agenda")){
			String sql="delete from contactos where email=?";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, email);
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void mostrarContactos() {

		try(Connection cn=crearConexion("agenda")){
			String sql="select * from contactos";
			Statement st=cn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				System.out.println("Contacto "+rs.getInt("idcontacto")+": ("+rs.getString("nombre")+", "+ rs.getString("email")+", "+ rs.getInt("edad")+")");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	//----MANEJO BASE DE DATOS------
	
	private static Connection crearConexion(String schema) throws SQLException {
		
		String url="jdbc:mysql://localhost:3307/"+schema;
		String user="root";
		String pwd="root";
		return DriverManager.getConnection(url,user,pwd);
	}
}
