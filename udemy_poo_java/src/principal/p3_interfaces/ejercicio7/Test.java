package principal.p3_interfaces.ejercicio7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/*
					Ejercicio operaciones números
					
Implementar la capa de lógica de negocio de una aplicación, que proporcione los 
siguientes métodos:

 procesar. Recibe como parámetro una lista de números y realiza con cada uno de 
ellos el procesamiento que se proporciona como segundo parámetro
 calculoFiltrados. Recibe como parámetro una lista de números y, sobre aquellos 
que cumplan la condición recibida como segundo parámetro, se aplicará la 
operación indicada en el tercer parámetro. El método devolverá una lista con los 
resultados de las operaciones realizadas sobre los números.

Una vez implementada la clase anterior, se creará una capa de presentación que, 
partiendo de una lista de números enteros cualquiera, interaccionará con la capa de 
lógica de negocio anterior y se ejecutarán las siguientes acciones:
 Imprimir por pantalla todos los números de la colección
 Calcular la suma del cuadrado de todos los números pares de la colección
*/

public class Test {

	public static void main(String[] args) {
		arrancar();
	}

	// ---CAPA PRESENTACIÓN---
	private static void arrancar() {
		List<Integer> nums = Arrays.asList(4, 11, 23, 15, 16);
		// proporcionamos las implementaciones de las interfaces funcionales
		// a traves de expresines lambda
		procesar(nums, n -> System.out.println(n));
		List<Integer> resultado = calculoFiltrados(nums, n -> n % 2 == 0, n -> n * n);
		System.out.println("Cuadrados de pares: ");
		for (Integer numero : resultado) {
			System.out.println(numero);
		}
	}

	// ---CAPA LÓGICA---
	private static void procesar(List<Integer> numeros, Consumer<Integer> consumer) {
		// recorre la lista y le aplica a cada n�mero la operaci�n definica en consumer
		for (Integer num : numeros) {
			consumer.accept(num);
		}
	}

	private static List<Integer> calculoFiltrados(List<Integer> numeros, Predicate<Integer> condicion,
			UnaryOperator<Integer> operacion) {
		List<Integer> resultados = new ArrayList<>();
		// si el numero cumpla la condicion del predicado, se le aplica la funcion
		// y el resultado se añade a una lista auxiliar
		for (Integer num : numeros) {
			if (condicion.test(num)) {
				resultados.add(operacion.apply(num));
			}
		}
		return resultados;
	}
}
