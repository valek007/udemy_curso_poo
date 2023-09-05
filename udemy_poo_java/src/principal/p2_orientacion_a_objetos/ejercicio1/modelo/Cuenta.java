package principal.p2_orientacion_a_objetos.ejercicio1.modelo;

public class Cuenta {
	private double saldo;

	public Cuenta(double saldo) {
		this.saldo = saldo;
	}

	public void ingresar(double ingreso) {
		saldo += ingreso;
	}

	public void retiro(double retiro) {
		saldo -= retiro;
	}

	public double obtenerSaldo() {
		return saldo;
	}
}
