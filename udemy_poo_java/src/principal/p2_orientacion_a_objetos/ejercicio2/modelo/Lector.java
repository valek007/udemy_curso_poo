package principal.p2_orientacion_a_objetos.ejercicio2.modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public final class Lector extends BufferedReader{

	public Lector(Reader in) {
		super(in);
	}
	
	public Lector() {
		super(new InputStreamReader(System.in));
	}

	public int readInt() throws IOException {
		return Integer.parseInt(readLine());
	}
	
	public double readDouble() throws IOException{
		return Double.parseDouble(readLine());
	}
}
