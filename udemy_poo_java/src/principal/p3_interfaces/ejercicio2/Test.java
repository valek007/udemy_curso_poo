package principal.p3_interfaces.ejercicio2;

import java.util.ArrayList;

/*

*/

public class Test {
	
	public static void main(String[] args) {
		
		//Ejemplo expresión lambda
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(23);
		nums.add(56);
		nums.add(39);
		nums.add(64);
		nums.add(12);
		
		//El método forEach implementa una Interfaz Funcional
		System.out.println("Mostrar el array por pantalla");
		nums.forEach(n->System.out.print(n+" "));
		System.out.println("\nMostrar el array con los números multiplicados por 2");
		nums.forEach(n->{ n *= 2; System.out.print(n+" ");});
		
	}
}
