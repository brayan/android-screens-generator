package com.brayanbedritchuk.tcc.model;

import java.util.ArrayList;
import java.util.List;

public class ComponenteAndroid extends Entidade {

	private static final long serialVersionUID = 6885404277712048877L;

	private final List<Atributo> atributos = new ArrayList<Atributo>();
	private final List<ComponenteAndroid> filhos = new ArrayList<ComponenteAndroid>();

	private String nome;
	private String tipo;
	private String caminhoImport;
	private String tagXml;
	private boolean supportLib;

	public ComponenteAndroid() {
		super();
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public void adicionarAtributo(Atributo atributo) {
		this.atributos.add(atributo);
	}

	public void removerAtributo(Atributo atributo) {
		this.atributos.remove(atributo);
	}

	public List<ComponenteAndroid> getFilhos() {
		return filhos;
	}

	public void adicionarFilho(ComponenteAndroid filho) {
		this.filhos.add(filho);
	}

	public void removerFilho(ComponenteAndroid filho) {
		this.filhos.remove(filho);
	}

	public boolean possuiFilhos() {
		return !(this.filhos.isEmpty());
	}

	public boolean possuiAtributos() {
		return !(this.atributos.isEmpty());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTagXml() {
		return tagXml;
	}

	public void setTagXml(String tagXml) {
		this.tagXml = tagXml;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCaminhoImport() {
		return caminhoImport;
	}

	public void setCaminhoImport(String caminhoImport) {
		this.caminhoImport = caminhoImport;
	}

	public boolean isSupportLib() {
		return supportLib;
	}

	public void setSupportLib(boolean supportLib) {
		this.supportLib = supportLib;
	}

}
