package principal.p4_streams.ejercicio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import principal.p2_orientacion_a_objetos.ejercicio6.modelo.Empleado;


/*
Operaciones intermedias con streams

- Se realizan a traves de metodos de la interfaz que devuelven
un nuevo Stream resultante de la operacion.

-Sobre este nuevo Stream pueden aplicarse nuevas operaciones
intermedias o finales, el el original queda consumido y no puede
volver a utilizarse.

Gran parte de estos metodos requieren objetos de interfaces
funcionales java.util.function, por lo que se utilizaran
expresiones lambda.
 */
public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// -------------Ejemplo creacion Stream 1------------------------

		// A partir de una coleccion
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(20);
		nums.add(100);
		nums.add(8);
		Stream<Integer> stA1 = nums.stream();

		// A partir de un array
		String[] cads = { "a", "xy", "jk", "mv" };
		Stream<String> stA2 = Arrays.stream(cads);

		// A partir de una serie discreta de datos
		Stream<Double> stA3 = Stream.of(2.4, 7.4, 9.1);

		//--------Ejemplo Streams 1: Métodos intermedios(I)----------------
		
		/*Stream<T> distinct(). Devuelve un Stream eliminando los elementos 
	    duplicados, segun aplicacion de equals().*/
			
			Stream<Integer> stB1 = Stream.of(2,5,3,3,6,2,4);
			//Devuelve un Stream formado por 2,5,3,6 y 4
			Stream<Integer> stB2 = stB1.distinct();
			//lo siguiente porvocaria excepcion, el stream esta ya cerrado.
			stB1.skip(2);
			
		/*Stream<T> limit(long m).Devuelve un nuevo Stream con
		los n primeros elementos del mismo.*/
			
			Stream<Integer> stB3 = Stream.of(2,5,8,3,6,2,10);
			//Devuelve un Stream formado por 2,5 y 8
			Stream<Integer> stB4 = stB3.limit(3);
			
		/*Stream<T> skip(long n).Devuelve un nuevo Stream,
		saltandose los n primeros elementos.*/
			
			Stream<Integer> stB5 = Stream.of(2,5,8,3,6,2,10);
			Stream<Integer> stB6 = stB5.skip(3);
			
		//--------Ejemplo Streams 3: Métodos intermedios(II)----------------
			
		/*Stream<T> filter(Predicate<?superT> predicate). Aplica
		un filtro sobre el Stream, devolviendo un nuevo Stream con
		los elementos que cumplen el predicado:*/
				
				Stream<Integer> stC1 = Stream.of(2,5,7,3,6,2,4);
				//crea un segundo Stream con los mayores de 3
				Stream<Integer> stC2 = stC1.filter(s->s>3);
				
		/*Stream<R> map(Function<?superT,? extends R> mapper).
		Transforma cada elemento del Stream en otro según el criterio
		definido por el objeto Function que se le pasa como parámetro:*/
				
				Stream<String> stC3 = Stream.of("Juan","Maria","Ana");
				//genera un Stream con los nombres en mayusculas
				Stream<String> stC4 = stC3.map(s->s.toUpperCase());
				
		//--------Ejemplo Streams 4: Métodos intermedios(III)----------------
				
		/*Stream<T>peek(Consumer<?superT> action). Aplica una accion
		con cada elemento del Stream, devolviendo una copia del propio
		Stream. La accion se ejecuta en modo lazy.*/
				
				Stream<Integer> nums2 = Stream.of(20,5,8,3,9);
				//no se muestra nada
				nums2.peek(s->System.out.println(s));
				//la operacion es lazy, solo se llevara a cabo de forma real
				//cuando al Stream resultante se le aplique una operacion final.
				
		//--------Ejemplo Streams 5: Métodos intermedios(IV)----------------

		/*IntStream mapToInt(ToIntFunction<?superT> mapper). Aplica una funcion
		a cada elemento del Stream que genera un int de cada elemento. 
		El resultado se devuelve como IntStream:*/
				
				//suponemos que listaEmpleados es un ArrayList de objetos Empleado
				ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
				Stream<Empleado> emps = listaEmpleados.stream();
				IntStream edades = emps.mapToInt(e->e.getEdad());
				//el metodo max() devuelve un OptionalInt, cuto metodo getAsInt(=
				//devuelve el valor encapsulado.
				int max=edades.max().getAsInt();
				System.out.println("Edad maxima:"+max);
	}
}
