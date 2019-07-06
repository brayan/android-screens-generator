package com.brayanbedritchuk.tcc.conversores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.brayanbedritchuk.tcc.exceptions.ComponenteInvalidoException;
import com.brayanbedritchuk.tcc.model.Atributo;
import com.brayanbedritchuk.tcc.model.ComponenteAndroid;
import com.brayanbedritchuk.tcc.model.TelaAndroid;
import com.brayanbedritchuk.tcc.model.componentes.CardViewConfiguracao;
import com.brayanbedritchuk.tcc.model.componentes.CheckBoxConfiguracao;
import com.brayanbedritchuk.tcc.model.componentes.ComponenteConfiguracao;
import com.brayanbedritchuk.tcc.model.componentes.EditTextConfiguracao;
import com.brayanbedritchuk.tcc.model.componentes.FloatingActionButtonConfiguracao;
import com.brayanbedritchuk.tcc.model.componentes.RectangularButtonConfiguracao;
import com.brayanbedritchuk.tcc.model.componentes.RecyclerViewConfiguracao;
import com.brayanbedritchuk.tcc.model.componentes.TextViewConfiguracao;
import com.brayanbedritchuk.tcc.model.componentes.ToolbarConfiguracao;
import com.brayanbedritchuk.tcc.util.AtributoUtils;

public class ConversorTelaAndroid {

	private HttpServletRequest request;
	private TelaAndroid tela;

	public TelaAndroid converter(HttpServletRequest request) throws ComponenteInvalidoException {
		this.request = request;
		this.tela = new TelaAndroid();

		converterComponentePrincipal();
		converterComponentesFilhos();
		gerarIds();

		return tela;
	}

	private void converterComponentePrincipal() {
		tela.setNome(request.getParameter(TelaAndroid.PARAMETRO_NOME).trim());
		tela.setPacote(request.getParameter(TelaAndroid.PARAMETRO_PACOTE).trim());

		ComponenteAndroid componente = new ComponenteAndroid();
		componente.setTipo(request.getParameter(TelaAndroid.PARAMETRO_TIPO).trim());
		componente.adicionarAtributo(
				new Atributo("xmlns:android", "http://schemas.android.com/apk/res/android"));
		componente.adicionarAtributo(new Atributo(AtributoUtils.WIDTH,
				request.getParameter(TelaAndroid.PARAMETRO_LARGURA).trim()));
		componente.adicionarAtributo(new Atributo(AtributoUtils.HEIGHT,
				request.getParameter(TelaAndroid.PARAMETRO_ALTURA).trim()));
		componente.adicionarAtributo(new Atributo(AtributoUtils.ORIENTATION,
				request.getParameter(TelaAndroid.PARAMETRO_ORIENTACAO).trim()));

		tela.setComponentePrincipal(componente);
	}

	private void converterComponentesFilhos() throws ComponenteInvalidoException {

		ComponenteAndroid filho = null;

		for (Map.Entry<String, String[]> parametro : request.getParameterMap().entrySet()) {

			if (isParametroComponentePrincipal(parametro)) {
				continue;
			}

			ComponenteConfiguracao componenteAtual = getComponenteAtual(parametro.getKey());

			if (isParametroInicial(parametro)) {
				filho = inicializarFilho(componenteAtual);
			}

			adicionarAtributo(filho, parametro);

			if (isParametroFinal(parametro)) {
				tela.getComponentePrincipal().adicionarFilho(filho);
				filho = null;
			}
		}
	}

	private ComponenteAndroid inicializarFilho(ComponenteConfiguracao componenteAtual) {

		ComponenteAndroid filho = new ComponenteAndroid();

		filho.setTipo(componenteAtual.getTipo());
		filho.setCaminhoImport(componenteAtual.getImport());
		filho.setSupportLib(componenteAtual.isSupportLib());

		return filho;
	}

	private boolean isParametroFinal(Map.Entry<String, String[]> parametro) {
		return parametro.getKey().toLowerCase().contains("altura");
	}

	private boolean isParametroInicial(Map.Entry<String, String[]> parametro) {
		return parametro.getKey().toLowerCase().contains("largura");
	}

	private boolean isParametroComponentePrincipal(Map.Entry<String, String[]> parametro) {
		return parametro.getKey().toLowerCase().contains("principal");
	}

	private void adicionarAtributo(ComponenteAndroid novoComponente,
			Entry<String, String[]> entry) {

		String atributo = entry.getKey().toLowerCase();
		String valor = entry.getValue()[0].trim();

		if (atributo.contains("largura")) {
			novoComponente.adicionarAtributo(new Atributo(AtributoUtils.WIDTH, valor));
		} else if (atributo.contains("altura")) {
			novoComponente.adicionarAtributo(new Atributo(AtributoUtils.HEIGHT, valor));
		} else if (atributo.contains("texto")) {
			novoComponente.adicionarAtributo(new Atributo(AtributoUtils.TEXT, valor));
		} else if (atributo.contains("orientation")) {
			novoComponente.adicionarAtributo(new Atributo(AtributoUtils.ORIENTATION, valor));
		} else if (atributo.contains("hint")) {
			novoComponente.adicionarAtributo(new Atributo(AtributoUtils.HINT, valor));
		}

	}

	private ComponenteConfiguracao getComponenteAtual(String key)
			throws ComponenteInvalidoException {
		String componente = key.toLowerCase();

		if (componente.contains("toolbar")) {
			return new ToolbarConfiguracao();
		} else if (componente.contains("edittext")) {
			return new EditTextConfiguracao();
		} else if (componente.contains("textview")) {
			return new TextViewConfiguracao();
		} else if (componente.contains("cardview")) {
			return new CardViewConfiguracao();
		} else if (componente.contains("rectangularbutton")) {
			return new RectangularButtonConfiguracao();
		} else if (componente.contains("floatingactionbutton")) {
			return new FloatingActionButtonConfiguracao();
		} else if (componente.contains("recyclerview")) {
			return new RecyclerViewConfiguracao();
		} else if (componente.contains("checkbox")) {
			return new CheckBoxConfiguracao();
		}

		throw new ComponenteInvalidoException(
				"Nenhum componente foi encontrado para a chave recebida: " + key.toLowerCase());
	}

	public void gerarIds() {
		HashMap<String, List<ComponenteAndroid>> componentes = new HashMap<>();

		List<ComponenteAndroid> filhos = tela.getComponentePrincipal().getFilhos();

		for (ComponenteAndroid filho : filhos) {

			List<ComponenteAndroid> componentesAdicionados = componentes.get(filho.getTipo());

			if (componentesAdicionados == null) {
				componentesAdicionados = new ArrayList<>();
				componentesAdicionados.add(filho);

				filho.setNome(filho.getTipo().toLowerCase() + componentesAdicionados.size());

				filho.adicionarAtributo(new Atributo("android:id", "@+id/" + filho.getNome()));

				componentes.put(filho.getTipo(), componentesAdicionados);
			} else {
				componentesAdicionados.add(filho);
				filho.setNome(filho.getTipo().toLowerCase() + componentesAdicionados.size());
				filho.adicionarAtributo(new Atributo("android:id", "@+id/" + filho.getNome()));
			}

		}
	}

}
