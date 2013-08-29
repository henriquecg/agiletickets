package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.testng.Assert;

public class EspetaculoTest {

	@Test
	public void deveCriar1SessaoParaEspetaculoComDatasIguaisEPeriodicidadeDiaria(){
		LocalDate inicio= new LocalDate();
		LocalDate fim= new LocalDate();
		LocalTime hora= new LocalTime();
		Periodicidade diaria= Periodicidade.DIARIA;
		
		Espetaculo show = new Espetaculo();
		List<Sessao> sessoes= show.criaSessoes(inicio, fim, hora, diaria);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(sessoes.get(0).getEspetaculo(),show);
		Assert.assertEquals(sessoes.get(0).getInicio(),inicio.toDateTime(hora));
		
	}
	
	@Test
	public void naoDeveCriarSessaoParaEspetaculoComDataInicioMaiorQueFimDiario(){
		LocalDate inicio= new LocalDate();
		LocalDate fim= new LocalDate().minusDays(5);
		LocalTime hora= new LocalTime();
		Periodicidade diaria= Periodicidade.DIARIA;
		
		Espetaculo show = new Espetaculo();
		List<Sessao> sessoes= show.criaSessoes(inicio, fim, hora, diaria);
		
		Assert.assertEquals(0, sessoes.size());

		
	}
	
	@Test
	public void deveCriar1SessaoParaEspetaculoComDatasIguaisEPeriodicidadeSemanal(){
		LocalDate inicio= new LocalDate();
		LocalDate fim= new LocalDate();
		LocalTime hora= new LocalTime();
		Periodicidade semanal= Periodicidade.SEMANAL;
		
		Espetaculo show = new Espetaculo();
		List<Sessao> sessoes= show.criaSessoes(inicio, fim, hora, semanal);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(sessoes.get(0).getEspetaculo(),show);
		Assert.assertEquals(sessoes.get(0).getInicio(),inicio.toDateTime(hora));

	}
	
	@Test
	public void naoDeveCriarSessaoParaEspetaculoComDataInicioMaiorQueFimSemanal(){
		LocalDate inicio= new LocalDate();
		LocalDate fim= new LocalDate().minusDays(20);
		LocalTime hora= new LocalTime();
		Periodicidade semanal= Periodicidade.SEMANAL;
		
		Espetaculo show = new Espetaculo();
		List<Sessao> sessoes= show.criaSessoes(inicio, fim, hora, semanal);
		
		Assert.assertEquals(0, sessoes.size());
	
	}
	
	@Test
	public void deveCriarSessaoParaEspetaculoComDatasDiferentessEPeriodicidadeDiaria(){
		LocalDate inicio= new LocalDate();
		LocalDate fim= inicio.plusDays(10);
		LocalTime hora= new LocalTime();
		Periodicidade diaria= Periodicidade.DIARIA;
		
		
		Espetaculo show = new Espetaculo();
		List<Sessao> sessoes= show.criaSessoes(inicio, fim, hora, diaria);
		
		Assert.assertEquals(11, sessoes.size());
		Assert.assertEquals(sessoes.get(0).getEspetaculo(),show);
		
		
		for (int i = 0; i < sessoes.size(); i++) {
			Assert.assertEquals(sessoes.get(i).getInicio(),inicio.plusDays(i).toDateTime(hora));	
		}
		

	}
	
	@Test
	public void deveCriarSessaoParaEspetaculoComDatasDiferentessEPeriodicidadeSemanal(){
		LocalDate inicio= new LocalDate();
		LocalDate fim= inicio.plusDays(10);
		LocalTime hora= new LocalTime();
		Periodicidade semanal= Periodicidade.SEMANAL;
		
		
		Espetaculo show = new Espetaculo();
		List<Sessao> sessoes= show.criaSessoes(inicio, fim, hora, semanal);
		
		Assert.assertEquals(2, sessoes.size());
		Assert.assertEquals(sessoes.get(0).getEspetaculo(),show);
		
		
		for (int i = 0; i < sessoes.size(); i++) {
			Assert.assertEquals(sessoes.get(i).getInicio(),inicio.plusWeeks(i).toDateTime(hora));	
		}
		

	}
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
}
