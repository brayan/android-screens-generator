package com.brayanbedritchuk.tcc;

import java.io.File;

import com.brayanbedritchuk.tcc.exceptions.ConfiguracaoException;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class ConfiguracaoTemplate {

	public static final String ENCODING = "UTF-8";

	private Configuration config;
	private String pathTemplate;

	public ConfiguracaoTemplate(String pathTemplate) throws ConfiguracaoException {
		this.pathTemplate = pathTemplate;
		efetuarConfiguracao();
	}

	private void efetuarConfiguracao() throws ConfiguracaoException {
		try {
			config = new Configuration(Configuration.VERSION_2_3_22);
			config.setDirectoryForTemplateLoading(new File(pathTemplate));
			config.setDefaultEncoding(ENCODING);
			config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		} catch (Exception e) {
			throw new ConfiguracaoException(
					"Ocorreu um erro ao efetuar a configuração do framework", e);
		}
	}

	public Configuration getConfiguracao() {
		return config;
	}

	public void setConfiguracaoTemplate(Configuration config) {
		this.config = config;
	}

}
