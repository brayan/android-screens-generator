package com.brayanbedritchuk.tcc.model;

public class Atributo extends Entidade {

	private static final long serialVersionUID = 5126864898971285474L;

	private String nome;
	private String valor;

	public Atributo() {
		super();
	}

	public Atributo(String nome, String valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
