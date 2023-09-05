package principal.p2_orientacion_a_objetos.ejercicio1.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class CuentaLimitada extends Cuenta {

	private double limite;

	public CuentaLimitada(double saldo, double limite) {
		super(saldo);
		this.limite = limite;
	}

	public void ajustarLimite(double limite) {
		this.limite = limite;
	}

	public void ajustarLimite() {
		this.limite = obtenerSaldo()/2;
	}
}
