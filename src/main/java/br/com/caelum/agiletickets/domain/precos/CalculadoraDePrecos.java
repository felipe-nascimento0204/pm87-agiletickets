package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		TipoDeEspetaculo tipo = sessao.getEspetaculo().getTipo();
		
		preco = calcularAumentoPreco(sessao, tipo.getPercentualDisponibilidade(), tipo.getPercentualAumento());
		
		if(tipo.isAumentoPorHora() && sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	private static BigDecimal calcularAumentoPreco(Sessao sessao, double percentualDisponibilidade, double percentualAumento){
		BigDecimal preco;
		
		if(sessao.getIngressosDisponiveis() / sessao.getTotalIngressos().doubleValue() <= percentualDisponibilidade) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(percentualAumento)));
		} else {
			preco = sessao.getPreco();
		}
		
		return preco;
		
	}

}