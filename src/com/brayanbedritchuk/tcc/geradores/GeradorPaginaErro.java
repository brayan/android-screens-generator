package com.brayanbedritchuk.tcc.geradores;

import java.io.PrintWriter;

public class GeradorPaginaErro {

	public void gerarPagina(PrintWriter out, String mensagem) {
		out.println("<html>");
		definirConteudoHead(out);
		definirConteudoBody(out, mensagem);
		out.println("</html>");
	}

	private void definirConteudoBody(PrintWriter out, String mensagem) {
		out.println("<body>");
		out.println("<div class='container'>");
		out.println("<div id='divTitulo'>");
		out.println("<img src='img/sad_android.jpg' height='256' width='256'/>");
		out.println("<p id='titulo'>Oops</p>");
		out.println("<br/><br/><br/><br/>");
		out.println("<div class='alert alert-warning'>");
		out.println(mensagem);
		out.println("  </div>");
		out.println("</div>");
		out.println(" </div>");
		out.println(" <body>");
	}

	private void definirConteudoHead(PrintWriter out) {
		out.println(" <head>");
		out.println("<title>Gerador Android</title>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.println("<link href='css/index.css' rel='stylesheet' media='screen'>");
		out.println("<link href='css/bootstrap.min.css' rel='stylesheet' media='screen'>");
		out.println("<script src='js/jquery.js'></script>");
		out.println("<script src='js/jquery-ui.min.js'></script>");
		out.println("<script src='js/bootstrap.min.js'></script>");
		out.println("<link rel='stylesheet' href='css/material.min.css'>");
		out.println("<script src='js/material.min.js'></script>");
		out.println("</head>");
	}

}
