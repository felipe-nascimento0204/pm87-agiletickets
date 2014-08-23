package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void permiteCriarUmaSessaoDeUmDia() throws Exception {
		LocalDate inicio = new LocalDate(2014, 8, 10);
		LocalDate fim = new LocalDate(2014, 8, 10);
		LocalTime horario = new LocalTime(20, 0);
		 
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoesCriadas = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(1, sessoesCriadas.size());
	}
	
	@Test
	public void deveCriarDuasSessoesParaUmPeriodoDeDoisDias() throws Exception {
		
		LocalDate inicio = new LocalDate(2014, 8, 10);
		LocalDate fim = new LocalDate(2014, 8, 11);
		LocalTime horario = new LocalTime(20, 0);
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoesCriadas = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(2, sessoesCriadas.size());
		
	}
	
	@Test
	public void deveCriarUmaSessaoParaCadaDia() throws Exception {
		
		LocalDate inicio = new LocalDate(2014, 8, 10);
		LocalDate fim = inicio.plusDays(3);
		LocalTime horario = new LocalTime(20, 0);
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoesCriadas = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(4, sessoesCriadas.size());
		
	}
	
	@Test
	public void deveCriarUmaSessaoParaCadaDiaCorreto() throws Exception {
		
		LocalTime horarioSessao = new LocalTime(20, 0);
		LocalDate primeiraSessao = new LocalDate(2014, 8, 10);
		LocalDate quartaSessao = new LocalDate(2014, 8, 13);
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoesCriadas = espetaculo.criaSessoes(primeiraSessao, quartaSessao, horarioSessao, Periodicidade.DIARIA);
		
		Assert.assertEquals(new LocalDate(2014, 8, 10), sessoesCriadas.get(0).getInicio().toLocalDate());
		Assert.assertEquals(new LocalDate(2014, 8, 11), sessoesCriadas.get(1).getInicio().toLocalDate());
		Assert.assertEquals(new LocalDate(2014, 8, 12), sessoesCriadas.get(2).getInicio().toLocalDate());
		Assert.assertEquals(new LocalDate(2014, 8, 13), sessoesCriadas.get(3).getInicio().toLocalDate());
	}
	
	@Test
	public void deveCriarUmaSessaoPorSemana(){
		
		LocalDate inicio = new LocalDate(2014, 8, 10);
		LocalDate fim = inicio.plusDays(20);
		LocalTime horario = new LocalTime(20, 0);
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoesCriadas = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		Assert.assertEquals(1, sessoesCriadas.size());
		
	}
	
}
