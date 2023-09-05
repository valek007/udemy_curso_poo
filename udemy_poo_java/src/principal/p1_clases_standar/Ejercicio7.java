package principal.p1_clases_standar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import principal.p1_clases_standar.modelo.Producto;

/*
 Ejercicio Gestion de Pedidos
Realizar un programa que permita realizar la gestión de pedidos de un almacen. Un pedido se
caracteriza por los siguientes datos:
 producto
 precioUnitario
 unidades
 fecha
Los pedidos se almacenarán en un archivo de texto, donde cada pedido se guardará con el
formato:
producto|precioUnitario|unidades|fecha
La fecha se almacenará con el formato año-mes-dia
Al iniciarse el programa, aparecerá un menú con las siguientes opciones:
1.- Nuevo pedido.
2.- Pedido mayor.
3.- Total facturado.
4.- Pedidos entre fechas
5.- Salir
La opción 1 solicitará la introducción de los cuatro datos del pedido y lo grabará en el fichero
Al elegir la opción 2, se mostrará los datos del pedido que tenga más unidades. Si hubiera dos
pedidos con el mismo número de unidades, se mostrará cualquiera de ellos
Con la opción 3 se mostrará el total facturado, es decir, la suma de todos los pedidos
Al seleccionar la opción 4, se solicitará la introducción de dos fechas y nos mostrará los datos
de todos los pedidos realizados entre esas dos fechas.
 */

public final class Ejercicio7 {

	private final static String RUTA_ARCHIVO = "src/principal/parte1/recursos/almacen";

	public static void main(String[] args) {

		arrancarPrograma();
	}

	// ---CAPA PRESENTACIÓN---
	private static void mostrarMenu() {
		System.out.println("1.- Nuevo pedido");
		System.out.println("2.- Pedido mayor");
		System.out.println("3.- Total facturado");
		System.out.println("4.- Pedidos entre fechas");
		System.out.println("5.- Salir");
	}

	private static void arrancarPrograma() {

		HashMap<String, Producto> almacen = recuperarAlmacen(RUTA_ARCHIVO);

		try (Scanner sc = new Scanner(System.in)) {
			int opc = 0;
			do {
				mostrarMenu();
				opc = Integer.parseInt(sc.nextLine());

				switch (opc) {
				case 1 -> {
					System.out.println("Introduce el nombre del producto");
					String nombre = sc.nextLine();
					System.out.println("Introduce el precio del producto");
					Double precio = Double.parseDouble(sc.nextLine());
					System.out.println("Introduce las unidades del producto");
					int unidades = Integer.parseInt(sc.nextLine());
					System.out.println("Introduce la fecha de expedición con formato 'dd/mm/yyyy'");
					String fecha = sc.nextLine();
					if (agregarProducto(nombre, precio, unidades, fecha, almacen)) {
						System.out.println("El producto se ha agregado correctamente.\n");
					} else {
						System.out.println("El producto no se ha podido agregar.\n");
					}
				}
				case 2 -> {
					Producto producto = buscarProductoMayor(almacen);
					if (!(producto == null)) {
						System.out.println("Datos del producto:\n" + producto + "\n");

					} else {
						System.out.println("El producto no se encuentra en el almacen.\n");
					}
				}
				case 3 -> {
					System.out.println("Facturación total: "+facturacion(almacen)+"€\n");
				}
				case 4 -> {
					System.out.println("Introduce la primera fecha con formato 'dd/mm/yyyy'");
					String fecha1 = sc.nextLine();
					System.out.println("Introduce la segunda fecha con formato 'dd/mm/yyyy'");
					String fecha2 = sc.nextLine();
					mostrarPedidosEntreFechas(fecha1, fecha2, almacen);
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
	private static boolean agregarProducto(String nombre, Double precio, int unidades, String fecha,
			HashMap<String, Producto> almacen) {

		if (!almacen.containsKey(nombre)) {
			almacen.put(nombre, new Producto(nombre, precio, unidades, formatearFechaRecuperada(fecha)));
			escribirArchivo(RUTA_ARCHIVO, nombre + "|" + precio + "|" + unidades + "|" + fecha);
			return true;
		}
		return false;
	}

	private static Producto buscarProductoMayor(HashMap<String, Producto> almacen) {

		Producto resultado = null;
		for (Producto x : almacen.values()) {
			if (x.getUnidades() >= 0) {
				resultado = x;
			}
		}
		return resultado;
	}
	
	private static String facturacion(HashMap<String, Producto> almacen) {
		
		double facturacion = 0;
		for (Producto x : almacen.values()) {
			facturacion+=x.getPrecio()*x.getUnidades();
		}
		//Dar formato al double
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String resultado = df.format(facturacion);
		
		return resultado;
	}

	private static void mostrarPedidosEntreFechas(String fecha1, String fecha2, HashMap<String, Producto> almacen) {
		
		Date fecha1R = formatearFechaRecuperada(fecha1);
		Date fecha2R = formatearFechaRecuperada(fecha2);
		
		List<Producto> resultado = new ArrayList<Producto>();
		for (Producto x : almacen.values()) {
			Date fechaX = x.getFecha();
			if (fechaX.after(fecha1R) && (fechaX.equals(fecha2R)) || fechaX.before(fecha2R)) {
				resultado.add(x);
			  }
		}
		
		for (Producto p : resultado) {
			System.out.println(p.getNombre() + ": precio(" + p.getPrecio() + "€) unidades(" + p.getUnidades()
					+ ") fecha(" + convertirFecha(p.getFecha()) + ")");
		}
	}

	// ---MANEJO ARCHIVOS---
	private static List<String> leerArchivo(String ruta) {
		String cadena = null;
		List<String> campos = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)))) {
			while ((cadena = br.readLine()) != null) {
				campos.add(cadena);
			}
			return campos;
		} catch (Exception e) {
			return campos;
		}

	}

	private static boolean escribirArchivo(String ruta, String texto) {

		try (PrintWriter archivo = new PrintWriter(new FileOutputStream(ruta, true))) {
			archivo.println(texto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private static HashMap<String, Producto> recuperarAlmacen(String ruta) {

		HashMap<String, Producto> almacen = new HashMap<>();

		for (String cadena : leerArchivo(ruta)) {
			String[] producto = cadena.split("[|]");
			if (!almacen.containsKey(producto[0])) {
				almacen.put(producto[0], new Producto(producto[0], Double.parseDouble(producto[1]),
						Integer.parseInt(producto[2]), formatearFechaRecuperada(producto[3])));
			}

		}
		return almacen;
	}
	
	//---MANEJO FECHAS---
	private static Date formatearFechaRecuperada(String cadena) {

		// USO DE CALENDAR AÑO/MES/DÍA
		Calendar calendar = Calendar.getInstance();
		String[] data = cadena.split("/");
		int[] data2 = { Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]) };
		calendar.set(data2[2], data2[1] - 1, data2[0]);

		Date fecha = calendar.getTime();

		return fecha;
	}

	private static String convertirFecha(Date date) {

		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("ES", "es"));
		String fecha = df.format(date);
		return fecha;
	}
}
