package com.brayanbedritchuk.tcc;

import java.util.ArrayList;
import java.util.List;

import com.brayanbedritchuk.tcc.exceptions.ValidacaoException;
import com.brayanbedritchuk.tcc.model.Atributo;
import com.brayanbedritchuk.tcc.model.ComponenteAndroid;
import com.brayanbedritchuk.tcc.model.TelaAndroid;
import com.brayanbedritchuk.tcc.util.AtributoUtils;

public class ValidacaoComponentes {

	private List<String> listaErros;

	public ValidacaoComponentes() {
		this.listaErros = new ArrayList<>();
	}

	public void efetuarValidacoes(TelaAndroid tela) throws ValidacaoException {
		ComponenteAndroid componente = tela.getComponentePrincipal();

		validarTela(tela);
		validarComponentePrincipal(componente);
		validarComponentesAdicionados(componente.getFilhos());

		if (!listaErros.isEmpty()) {
			String msgErro = concatenarDescricaoErros();
			throw new ValidacaoException(msgErro);
		}
	}

	private void validarTela(TelaAndroid tela) throws ValidacaoException {
		if (!isCampoPreenchido(tela.getNome())) {
			listaErros.add("É necessário preencher o nome da tela");
		}
	}

	private void validarComponentesAdicionados(List<ComponenteAndroid> filhos)
			throws ValidacaoException {

		if (filhos.isEmpty()) {
			listaErros.add("É necessário adicionar pelo menos um componente");
			return;
		}

		for (ComponenteAndroid filho : filhos) {

			for (Atributo a : filho.getAtributos()) {
				verificarCampoWidth(listaErros, filho, a);
				verificarCampoHeight(listaErros, filho, a);
			}
		}
	}

	private void verificarCampoWidth(List<String> erros, ComponenteAndroid filho, Atributo a) {
		if (isCampoWidth(a.getNome()) && !isCampoPreenchido(a.getValor())) {
			erros.add("É necessário preencher o campo width do componente " + filho.getTipo());
		}
	}

	private void verificarCampoHeight(List<String> erros, ComponenteAndroid filho, Atributo a) {
		if (isCampoHeight(a.getNome()) && !isCampoPreenchido(a.getValor())) {
			erros.add("É necessário preencher o campo height do componente " + filho.getTipo());
		}
	}

	private void validarComponentePrincipal(ComponenteAndroid componentePrincipal) {
		for (Atributo a : componentePrincipal.getAtributos()) {
			if (isCampoWidth(a.getNome()) && !isCampoPreenchido(a.getValor())) {
				listaErros.add("É necessário preencher o campo width do componente principal");
			}

			if (isCampoHeight(a.getNome()) && !isCampoPreenchido(a.getValor())) {
				listaErros.add("É necessário preencher o campo height do componente principal");
			}
		}
	}

	private String concatenarDescricaoErros() throws ValidacaoException {
		StringBuilder sb = new StringBuilder();

		for (String e : listaErros) {
			sb.append(e + "<br/>");
		}

		return sb.toString();
	}

	private boolean isCampoHeight(String nome) {
		return nome.equalsIgnoreCase(AtributoUtils.HEIGHT);
	}

	private boolean isCampoWidth(String nome) {
		return nome.equalsIgnoreCase(AtributoUtils.WIDTH);
	}

	private boolean isCampoPreenchido(String campo) {
		return (campo != null) && !(campo.trim().equals(""));
	}
}
