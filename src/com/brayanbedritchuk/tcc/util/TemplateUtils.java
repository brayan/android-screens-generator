package com.brayanbedritchuk.tcc.util;

import javax.servlet.ServletContext;

public class TemplateUtils {

	public static final String TIPO_COMPONENTE = "tipoComponente";
	public static final String LISTA_ATRIBUTOS = "listaAtributos";
	public static final String LISTA_COMPONENTES_FILHOS = "listaFilhos";

	public static String getDiretorioTemplates(ServletContext ctx) {
		return ctx.getRealPath("") + "/WEB-INF/templates";
	}

}
