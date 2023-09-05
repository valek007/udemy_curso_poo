package principal.p2_orientacion_a_objetos.ejercicio3.modelo;

public class Cuenta {
	private double saldo;

	public Cuenta(double saldo) {
		this.saldo = saldo;
	}

	public void ingresar(double cantidad) {
		saldo += cantidad;
	}

	public void retiro(double cantidad) {
		saldo -= cantidad;
	}

	public double obtenerSaldo() {
		return saldo;
	}
}
