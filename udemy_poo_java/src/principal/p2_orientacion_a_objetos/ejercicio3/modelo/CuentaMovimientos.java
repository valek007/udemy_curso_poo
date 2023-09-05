package principal.p2_orientacion_a_objetos.ejercicio3.modelo;

import java.util.HashSet;

public class CuentaMovimientos extends CuentaLimitada {

	private HashSet<Movimiento> movs;

	public CuentaMovimientos(double saldo, double limite) {
		super(saldo, limite);
		movs = new HashSet<Movimiento>();
	}

	public HashSet<Movimiento> obtenerMovimientos() {
		return movs;
	}

	@Override
	public void retiro(double cantidad) {
		if (cantidad <= obtenerLimite()) {
			movs.add(new Movimiento(cantidad, "Retiro"));
			super.retiro(cantidad);
		}
		else {
			movs.add(new Movimiento(obtenerLimite(), "Retiro"));
			super.retiro(obtenerLimite());
		}
	}

	@Override
	public void ingresar(double cantidad) {
		movs.add(new Movimiento(cantidad, "Ingreso"));
		super.ingresar(cantidad);
	}

}
