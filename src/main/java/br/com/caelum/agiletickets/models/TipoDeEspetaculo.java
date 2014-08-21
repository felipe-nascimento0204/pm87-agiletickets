package br.com.caelum.agiletickets.models;

public enum TipoDeEspetaculo {
	
	CINEMA(0.05, 0.10, false),
	SHOW(0.05, 0.10, false),
	TEATRO(1.00, 1.00, false),
	BALLET(0.50, 0.20, true),
	ORQUESTRA(0.50, 0.20, true);
	
	private final double percentualDisponibilidade;
	private final double percentualAumento;
	private final boolean aumentoPorHora;
	
	private TipoDeEspetaculo(double percentualDisponibilidade, double percentualAumento, boolean aumentoPorHora){
		this.percentualDisponibilidade	= percentualDisponibilidade;
		this.percentualAumento = percentualAumento;
		this.aumentoPorHora = aumentoPorHora;
	}

	public double getPercentualDisponibilidade() {
		return percentualDisponibilidade;
	}

	public double getPercentualAumento() {
		return percentualAumento;
	}

	public boolean isAumentoPorHora() {
		return aumentoPorHora;
	}
	
}
