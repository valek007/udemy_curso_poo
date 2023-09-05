package principal.p4_streams.ejercicio2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 Operaciones finales con streams
 
 -Se realizan a traves de metodos de la interfaz que realizan
 una operacion final, pudiendo o no devolver un resultado.
 
 -Tras la operacion final el Stream es consumido y no puede
 volver a utilizarse.
 
 -Gran parte de estos metodos requieren objetos de interfaces
 funcionales java.util.function, por lo que se utilizaran
 expresiones lambda.
 */
public class Test {

	public static void main(String[] args) {

		// ---------------Metodos finales(I)---------------------

		/* long count(). Devuelve el número de elementos de un Stream. */

		Stream<Integer> stA1 = Stream.of(2, 5, 7, 3, 6, 2, 3);
		// indica el total de numeros pares
		System.out.println(stA1.filter(s -> s % 2 == 0).count());

		/*
		 * void forEach(Consumer<?superT> action). Realiza una accion para cada elemento
		 * del foEach().
		 */

		Stream<Integer> stA2 = Stream.of(2, 5, 8, 3, 6, 2, 10);
		// muestra todos los elementos
		stA2.forEach(n -> System.out.println(n));
		// equivale a
		stA2.peek(n -> System.out.println(n)).count();

		// ---------------Metodos finales(II)---------------------

		/*
		 * boolean anyMatch(Predicate<?superT> predicate). Indica si algun elemento del
		 * Stream cumple con la condicion del predicado.
		 */

		Stream<Integer> stB1 = Stream.of(2, 5, 7, 3, 6, 2, 3);
		// indica si alguno es mayor de 5
		System.out.println("alguno mayor de 5?" + stB1.anyMatch(n -> n > 5));

		/*
		 * boolean allMatch(Predicate<?superT> predicate). Devuelve true si todos los
		 * elementos del Stream cumplen con la condicion del predicado.
		 */

		Stream<Integer> stB2 = Stream.of(2, 5, 7, 3, 6, 2, 3);
		// indica si alguno es mayor de 5
		System.out.println("alguno mayor de 5?" + stB2.allMatch(n -> n > 5));

		/*
		 * boolean noneMatch(Predicate<?superT> predicate). Devuelve true si ninguno de
		 * los elementos del Stream cumplen con la condicion del predicado.
		 */

		Stream<Integer> stB3 = Stream.of(2, 5, 7, 3, 6, 2, 3);
		// indica si alguno es mayor de 5
		System.out.println("alguno mayor de 5?" + stB3.noneMatch(n -> n > 5));

		// ---------------Metodos finales(III)---------------------

		/*
		 * Optional<T> max(Comparator<?superT> comparator). Devuelve el mayor de los
		 * elementos, segun el criterio de comparacion del objeto Comparator:
		 */

		Stream<Integer> stC1 = Stream.of(20, 5, 8, 3, 9);
		// muestra el mayor de los numeros del Stream
		Optional<Integer> opC = stC1.max((a, b) -> a - b);
		System.out.println("mayor: " + opC.get());

		/*
		 * Optional<T> mix(Comparator<?superT> comparator). Operacion contraria a max
		 */

		// ---------------Metodos finales(IV)---------------------

		/*
		 * Optional<T> findFirst(Comparator<?superT> comparator). Devuelve el primer
		 * elemento del Stream, o un Otional vacio si no hay nada
		 */

		Stream<Integer> stD1 = Stream.of(11, 5, 8, 3, 9);
		// devuelve el primer par
		Optional<Integer> opD = stD1.filter(s -> s % 2 == 2).findFirst();
		if (opD.isPresent()) {
			System.out.println("El primer par es " + opD.get());
		}

		/*
		 * R collect(Collector<?superT,A,R> collector). Devuelve un List, Map o Set con
		 * los datos del Stream.
		 */

		Stream<Integer> stD2 = Stream.of(20, 5, 8, 5, 3, 3, 9);
		// genera una lista con los elementos del Stream sin duplicados
		List<Integer> lista = stD2.distinct().collect(Collectors.toList());
		lista.forEach(n -> System.out.println(n));

		// ---------------Metodos finales(V)---------------------

		/*
		 * T[] toArray(IntFunction<T[]gen>). Convierte el Stream en un array. Se le debe
		 * pasar un objeto IntFunction que genere un array del tipo de objeto a generar.
		 */

		Stream<Integer> stE1 = Stream.of(20, 5, 8, 3, 9);
		// genera un array de Integer. El metodo de IntFunction
		// recibe un entero que es el tamaño del array, por lo que
		// genera un array de ese tamaño.
		Integer[] res = stE1.toArray(t -> new Integer[t]);
		for (Integer x : res) {
			System.out.println(x);
		}

		// ---------------Clase Optional-------------------------

		/*
		 * - Encapsula resultados de una operacion final
		 * 
		 * - Podemos utilizar los siguientes metodos para manipularlo: . T get().
		 * Devuelve el valor encapsulado. Si no hay ningun valor, lanza una
		 * NoSuchElementException. . T orElse(T other). Devuelve el valor encapsulado.
		 * Si no hay ninguno, entonces devuelve el valor pasado como paramentro. .
		 * boolean isPresent(). Permite comprobar si contiene o no algun valor.
		 */
	}
}
