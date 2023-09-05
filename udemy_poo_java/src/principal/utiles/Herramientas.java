package principal.utiles;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import principal.p1_clases_standar.modelo.Contacto;

public class Herramientas {

	// ---MANEJO NÚMEROS---
	public static int generarNumeroAleatorio(int rango) {
		return (int) (Math.random() * rango + 1);
	}
	
	/**
	 Devuelve un número doble redondeado, quitando las decimales que sobran como un String.
	 */
	public static String redondearNumero(double numero) {
		DecimalFormat df = new DecimalFormat("#.##"); // dos decimales
		return df.format(numero); // redondeado vale "2.85"
	}

	// ---MANEJO ARCHIVOS---
	public static List<String> leerArchivoIO(String ruta) {
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
	
	public static List<String[]> recuperarArrayTexto(String ruta) {

		List<String[]> partes = new ArrayList<String[]>();
		for (String cadena : leerArchivoIO(ruta)) {
			String[] campos = cadena.split("[|]");
			partes.add(campos);
		}
		return partes;
	}
	
	public static Stream<String> leerArchivoNIO(String ruta) {
		
		Path pt = Paths.get(ruta);
		Stream<String> cadenas = null;
		try {
			return cadenas = Files.lines(pt);
		} catch (IOException e) {
			return cadenas;
		}
	}
	
	
	public static HashMap<String, Contacto> recuperarAgendaNIO(String ruta) {

		HashMap<String, Contacto> agenda = new HashMap<>();
		
		Contacto[] result = leerArchivoNIO(ruta)//leemos el archivo
				.map(s->s.split("[|]"))//separamos los datos
				.map(d->new Contacto(d[0],d[2],Integer.parseInt(d[1])))//creamos el contacto
				.toArray(t->new Contacto[t]);//damos el tamaño al array
		for (Contacto c : result) {
			agenda.put(c.getEmail(), c);
		}
		return agenda;
	}
	
	public static boolean escribirArchivo(String ruta, String texto) {

		try (PrintWriter archivo = new PrintWriter(new FileOutputStream(ruta, true))) {
			archivo.println(texto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// ---MANEJO FECHAS---
	public static Date formatearFechaRecuperada(String cadena) {

		// USO DE CALENDAR AÑO/MES/DÍA
		Calendar calendar = Calendar.getInstance();
		String[] data = cadena.split("/");
		int[] data2 = { Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]) };
		calendar.set(data2[2], data2[1] - 1, data2[0]);

		Date fecha = calendar.getTime();

		return fecha;
	}

	/**
	 * Este método convierte una fecha tipo Date en un String con formato fecha española
	 */
	public static String convertirFecha(Date date) {

		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("ES", "es"));
		String fecha = df.format(date);
		return fecha;
	}
	
	/**
	 * Este método comprueba dos fechas y te devuelve un resultado según 
	 * lo que necesites atributos tipo puede ser 'MES','ANNO','DIA','HORA','MINUTO'
	 * @return el resultado se devuelve utilizando el método ChronoUnit.
	 */
	public static long comprobarFechas(LocalDate fecha1, LocalDate fecha2, String tipo) {
		
		long resultado = 0;
		
		switch (tipo) {
		case "MES" -> resultado = ChronoUnit.MONTHS.between(fecha1, fecha2);
		case "ANNO" -> resultado = ChronoUnit.YEARS.between(fecha1, fecha2);
		case "DIA" -> resultado = ChronoUnit.DAYS.between(fecha1, fecha2);
		case "HORA" -> resultado = ChronoUnit.HOURS.between(fecha1, fecha2);
		case "MINUTO" -> resultado = ChronoUnit.MINUTES.between(fecha1, fecha2);
		}
		
		return resultado;
	}
	
	public static void comprobarFechasChronoUnit() {
		// Crear dos fechas LocalDate
		LocalDate fecha1 = LocalDate.of(2020, 1, 1);
		LocalDate fecha2 = LocalDate.of(2023, 8, 15);

		// Obtener el número de meses entre las dos fechas
		long meses = ChronoUnit.MONTHS.between(fecha1, fecha2);

		// Mostrar el resultado
		System.out.println("La diferencia entre " + fecha1 + " y " + fecha2 + " es de " + meses + " meses.");
	}
	
	public static void comparacionPeriodYChronoUnit() {
		// Crear dos objetos LocalDate
	    LocalDate date1 = LocalDate.of(2023, 8, 15);
	    LocalDate date2 = LocalDate.of(2023, 9, 15);

	    // Crear un objeto Period entre las dos fechas
	    Period period = Period.between(date1, date2);
	    System.out.println("Periodo entre " + date1 + " y " + date2 + ": " + period);

	    // Crear dos objetos LocalTime
	    LocalTime time1 = LocalTime.of(15, 17, 13);
	    LocalTime time2 = LocalTime.of(16, 25, 03);

	    // Crear un objeto ChronoUnit entre las dos horas
	    long hours = ChronoUnit.HOURS.between(time1, time2);
	    System.out.println("Horas entre " + time1 + " y " + time2 + ": " + hours);
	}

}
