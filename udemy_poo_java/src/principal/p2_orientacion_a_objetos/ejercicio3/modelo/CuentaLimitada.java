package principal.p2_orientacion_a_objetos.ejercicio3.modelo;

public class CuentaLimitada extends Cuenta {

	private double limite;

	public CuentaLimitada(double saldo, double limite) {
		super(saldo);
		this.limite = limite;
	}

	public void ajustarLimite(double limite) {
		this.limite = limite;
	}

	public void ajustarLimite() {
		this.limite = obtenerSaldo() / 2;
	}
	
	public double obtenerLimite() {
		return limite;
	}

	@Override
	public void retiro(double cantidad) {
		if (cantidad <= limite)
			super.retiro(cantidad);
		else
			super.retiro(limite);
	}

}
