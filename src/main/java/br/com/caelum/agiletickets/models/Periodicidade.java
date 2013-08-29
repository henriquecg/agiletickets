package br.com.caelum.agiletickets.models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Weeks;


public enum Periodicidade {
	DIARIA {
		@Override
		public List<Sessao> criaSessoes(Espetaculo espetaculo,
				LocalDate inicio, LocalDate fim, LocalTime horario) {
		
			Integer qtdDias;
			
			qtdDias=Days.daysBetween(inicio, fim).getDays();	
			  
			return criaSessao(espetaculo, inicio, horario,qtdDias,1);
		}
	}, SEMANAL {
		@Override
		public List<Sessao> criaSessoes(Espetaculo espetaculo,
				LocalDate inicio, LocalDate fim, LocalTime horario) {
			
			int qtdSemanas;
			
			qtdSemanas=Weeks.weeksBetween(inicio, fim).getWeeks();	
			
			return criaSessao(espetaculo, inicio, horario,qtdSemanas,7);
		}
	};
	
	private static List<Sessao> criaSessao(Espetaculo espetaculo,LocalDate inicio,LocalTime horario,int qtdSessoes, int incremento){
		
		List<Sessao> listaSessoes=new ArrayList<Sessao>();
		
		for (int i = 0; i <= qtdSessoes; i++) {
			Sessao novaSessao = new Sessao();
			novaSessao.setInicio(inicio.toDateTime(horario));
			novaSessao.setEspetaculo(espetaculo);
			
			listaSessoes.add(novaSessao);
			
			inicio = inicio.plusDays(incremento);
		}
		
		
		return listaSessoes;
	}
	
	
	public abstract List<Sessao> criaSessoes(Espetaculo espetaculo,LocalDate inicio, LocalDate fim, LocalTime horario);
	
	
}
