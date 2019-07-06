package com.brayanbedritchuk.tcc.geradores;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import com.brayanbedritchuk.tcc.ConfiguracaoTemplate;
import com.brayanbedritchuk.tcc.exceptions.ConfiguracaoException;
import com.brayanbedritchuk.tcc.model.ComponenteAndroid;
import com.brayanbedritchuk.tcc.model.TelaAndroid;
import com.brayanbedritchuk.tcc.util.TemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class GeradorTela {

	private Configuration config;
	private TelaAndroid tela;
	private ServletContext ctx;

	public GeradorTela(ServletContext ctx) throws ConfiguracaoException {
		this.ctx = ctx;
		inicializarConfiguracao();
	}

	private void inicializarConfiguracao() throws ConfiguracaoException {
		String pathTemplates = TemplateUtils.getDiretorioTemplates(ctx);
		this.config = new ConfiguracaoTemplate(pathTemplates).getConfiguracao();
	}

	public void gerar(TelaAndroid tela) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {

		this.tela = tela;
		ComponenteAndroid componente = tela.getComponentePrincipal();

		gerarEstruturaDiretorio(componente);
		gerarTagsXml(componente);
		gerarArquivoXml(componente);
		gerarArquivoJava(componente);
	}

	private void gerarTagsXml(ComponenteAndroid componente) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {

		for (ComponenteAndroid filho : componente.getFilhos()) {
			gerarTagsXml(filho);
		}

		Template temp = getTemplateComponenteXml(componente);
		Writer fileWriter = new StringWriter();
		temp.process(getComponentesFreeMakerXml(componente), fileWriter);

		componente.setTagXml(fileWriter.toString());
	}

	private void gerarEstruturaDiretorio(ComponenteAndroid componente) {
		String path = ctx.getRealPath("") + "\\" + tela.getNome();
		tela.setPath(path);
		new File(path).mkdirs();
	}

	private void gerarArquivoXml(ComponenteAndroid c) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {

		String path = tela.getPath() + "/activity" + tela.getNome().toLowerCase() + ".xml";
		Writer fileWriter = new FileWriter(path);
		getTemplateComponenteXml(c).process(getComponentesFreeMakerXml(c), fileWriter);
		fileWriter.close();
	}

	private Template getTemplateComponenteXml(ComponenteAndroid componente)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException,
			IOException {

		if (componente.possuiFilhos()) {
			return config.getTemplate("TemplateComponenteComFilhos.ftl");
		} else {
			return config.getTemplate("TemplateComponenteSemFilhos.ftl");
		}
	}

	private void gerarArquivoJava(ComponenteAndroid componente)
			throws TemplateException, IOException {

		String path = tela.getPath() + "/" + "Activity" + tela.getNome() + ".java";
		Writer fileWriter = new FileWriter(path);

		Map<String, Object> root = new HashMap<String, Object>();

		root.put("listaComponentes", componente.getFilhos());
		root.put("nomeClasse", "Activity" + tela.getNome());
		root.put("nomeArquivoXml", "activity" + tela.getNome().toLowerCase());

		if (tela.getPacote() != null && !tela.getPacote().trim().equals("")) {
			root.put("nomePacote", tela.getPacote());
		}

		getTemplateActivity().process(root, fileWriter);
		fileWriter.close();
	}

	private Template getTemplateActivity() throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException {

		return config.getTemplate("TemplateActivity.ftl");
	}

	private Map<String, Object> getComponentesFreeMakerXml(ComponenteAndroid componente) {
		Map<String, Object> root = new HashMap<String, Object>();

		if (componente.isSupportLib()) {
			root.put(TemplateUtils.TIPO_COMPONENTE, componente.getCaminhoImport());
		} else {
			root.put(TemplateUtils.TIPO_COMPONENTE, componente.getTipo());
		}
		root.put(TemplateUtils.LISTA_ATRIBUTOS, componente.getAtributos());
		root.put(TemplateUtils.LISTA_COMPONENTES_FILHOS, componente.getFilhos());

		return root;
	}

}
