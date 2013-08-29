package br.com.caelum.agiletickets.models;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.mapping.Array;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Weeks;

@Entity
public class Espetaculo {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	private String descricao;

	@Enumerated(EnumType.STRING)
	private TipoDeEspetaculo tipo;

	@OneToMany(mappedBy="espetaculo")
	private List<Sessao> sessoes = newArrayList();

	@ManyToOne
	private Estabelecimento estabelecimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoDeEspetaculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeEspetaculo tipo) {
		this.tipo = tipo;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	/**
      * Esse metodo eh responsavel por criar sessoes para
      * o respectivo espetaculo, dado o intervalo de inicio e fim,
      * mais a periodicidade.
      * 
      * O algoritmo funciona da seguinte forma:
      * - Caso a data de inicio seja 01/01/2010, a data de fim seja 03/01/2010,
      * e a periodicidade seja DIARIA, o algoritmo cria 3 sessoes, uma 
      * para cada dia: 01/01, 02/01 e 03/01.
      * 
      */
	public List<Sessao> criaSessoes(LocalDate inicio, LocalDate fim, LocalTime horario, Periodicidade periodicidade) {
		// ALUNO: Não apague esse metodo. Esse sim será usado no futuro! ;)
		
					
		return periodicidade.criaSessoes(this, inicio, fim, horario)	;
		
	}
	
	public boolean Vagas(int qtd, int min)
    {
        // ALUNO: Não apague esse metodo. Esse sim será usado no futuro! ;)
        int totDisp = 0;

        for (Sessao s : sessoes)
        {
            if (s.getIngressosDisponiveis() < min) return false;
            totDisp += s.getIngressosDisponiveis();
        }

        if (totDisp >= qtd) return true;
        else return false;
    }

    public boolean Vagas(int qtd)
    {
        // ALUNO: Não apague esse metodo. Esse sim será usado no futuro! ;)
        int totDisp = 0;

        for (Sessao s : sessoes)
        {
            totDisp += s.getIngressosDisponiveis();
        }

        if (totDisp >= qtd) return true;
        else return false;
    }

}
