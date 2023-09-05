package principal.p6_acceso_a_ficheros.ejercicio4.presentacion;

import java.util.Scanner;

import principal.p6_acceso_a_ficheros.ejercicio4.logica.GestorEmpleados;
import principal.p6_acceso_a_ficheros.ejercicio4.modelo.Empleado;

/*
 El programa deberá permitir realizar las siguientes operaciones:
 Alta de un empleado a partir de sus datos
 Obtener empleado a partir de su email, si no existe devolverá null
 Obtener empleados ordenados por edad
 Dado un departamento, obtener todos los empleados del mismo
 Calcular la media de salario de un departamento
 Nombre del empleado con mayor salario
 */
public class VistaEmpleados {
	
	public final String RUTA_ARCHIVO = "src/principal/p6_acceso_a_ficheros/ejercicio4/recursos/empleados";
	public final GestorEmpleados ge = new GestorEmpleados();
	
	public void arrancarVista() {
		
		try (Scanner sc = new Scanner(System.in)) {
			int opc = 0;
			do {
				vistaMenu();
				opc = Integer.parseInt(sc.nextLine());
				switch (opc) {
				case 1 -> darAlta(sc);
				case 2 -> buscarXEmail(sc);
				case 3 -> ordenarXEdad(sc);
				case 4 -> buscarXDepartamento(sc);
				case 5 -> mostrarMediaSalario(sc);
				case 6 ->{
					System.out.println("Buscando el empleado mejor pagado.....\n");
					try {
						Thread.sleep(1000);
						System.out.println(ge.obtenerMejorAsalariado(RUTA_ARCHIVO).toString()+"\n");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				case 7 -> System.out.println("Programa ha finalizado");
				}			
			}while(opc!=7);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	private void darAlta(Scanner sc) {
		System.out.println("Introduce los siguientes datos del empleado: ");
		String nombre, email, salario, edad, departamento;
		System.out.println("Nombre:\n");
		nombre = sc.nextLine();
		System.out.println("Email:\n");
		email = sc.nextLine();
		System.out.println("Salario:\n");
		salario = sc.nextLine();
		System.out.println("Edad:\n");
		edad = sc.nextLine();
		System.out.println("Departamento:\n");
		departamento = sc.nextLine();
		System.out.println("Introduciendo al empleado");
		try {
			Thread.sleep(1000);
			if(ge.altaEmpleado(RUTA_ARCHIVO, new Empleado(nombre, email, Double.parseDouble(salario), Integer.parseInt(edad), departamento)))
				System.out.println("El empleado se ha dado de alta correctamente.\n");
			else
				System.out.println("El empleado no se ha podido dar de alta.\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void buscarXEmail(Scanner sc) {
		System.out.println("Introduce el email del empleado:\n");
		String email = sc.nextLine();
		Empleado empleado = ge.existeEmpleado(RUTA_ARCHIVO, email);
		System.out.println("Buscando al empleado.....");
		try {
			Thread.sleep(1000);
			if(empleado!=null)
				System.out.println(empleado.toString()+"\n");
			else
				System.out.println("Empleado no existe");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void ordenarXEdad(Scanner sc) {
		System.out.println("Ordenando por edad.....\n");
		try {
			Thread.sleep(1000);
			for(Empleado emp: ge.obtenerEmpleadosXEdad(RUTA_ARCHIVO)) {
				System.out.println(emp.toString());
				Thread.sleep(500);
			}
			System.out.println();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void buscarXDepartamento(Scanner sc){
		System.out.println("Introduce el departamento");
		String depart = sc.nextLine();
		System.out.println("Buscando por Departamento.....\n");
		try {
			Thread.sleep(1000);
			for(Empleado emp: ge.obtenerEmpleadosXDep(RUTA_ARCHIVO, depart)) {
				System.out.println(emp.toString());
				Thread.sleep(500);
			}
			System.out.println();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void mostrarMediaSalario(Scanner sc) {
		System.out.println("Introduce el departamento");
		String depart = sc.nextLine();
		System.out.println("Calculando media del salario.....\n");
		try {
			Thread.sleep(1000);
			System.out.println("La media es: "+ge.obtenerMediaSalarioXDep(RUTA_ARCHIVO, depart)+"€\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void vistaMenu() {
		
		System.out.println("-----Menú------");
		System.out.println("1.Alta Empleado");
		System.out.println("2.Buscar Empleado");
		System.out.println("3.Ordenar por Edad");
		System.out.println("4.Buscar Por Departamento");
		System.out.println("5.Media del salario");
		System.out.println("6.Empleado mejor pagado");
		System.out.println("7.Salir\n");
		
	}

}
