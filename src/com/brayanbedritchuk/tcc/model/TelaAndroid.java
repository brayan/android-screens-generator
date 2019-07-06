package com.brayanbedritchuk.tcc.model;

public class TelaAndroid extends Entidade {

	private static final long serialVersionUID = -2439647577927962732L;

	public static final String PARAMETRO_NOME = "componentePrincipalNome";
	public static final String PARAMETRO_PACOTE = "componentePrincipalPacote";
	public static final String PARAMETRO_TIPO = "componentePrincipalTipo";
	public static final String PARAMETRO_LARGURA = "componentePrincipalLargura";
	public static final String PARAMETRO_ALTURA = "componentePrincipalAltura";
	public static final String PARAMETRO_ORIENTACAO = "componentePrincipalOrientacao";

	private String nome;
	private String pacote;
	private String path;

	private ComponenteAndroid componentePrincipal;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPacote() {
		return pacote;
	}

	public void setPacote(String pacote) {
		this.pacote = pacote;
	}

	public ComponenteAndroid getComponentePrincipal() {
		return componentePrincipal;
	}

	public void setComponentePrincipal(ComponenteAndroid componentePrincipal) {
		this.componentePrincipal = componentePrincipal;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
