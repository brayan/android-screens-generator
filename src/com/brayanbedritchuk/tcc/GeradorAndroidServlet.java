package com.brayanbedritchuk.tcc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brayanbedritchuk.tcc.conversores.ConversorTelaAndroid;
import com.brayanbedritchuk.tcc.exceptions.ComponenteInvalidoException;
import com.brayanbedritchuk.tcc.exceptions.ConfiguracaoException;
import com.brayanbedritchuk.tcc.exceptions.ValidacaoException;
import com.brayanbedritchuk.tcc.geradores.GeradorPaginaErro;
import com.brayanbedritchuk.tcc.geradores.GeradorTela;
import com.brayanbedritchuk.tcc.model.TelaAndroid;
import com.brayanbedritchuk.tcc.util.DownloadUtils;
import com.brayanbedritchuk.tcc.util.ZipUtils;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@WebServlet("/geradorAndroid")
public class GeradorAndroidServlet extends HttpServlet {

	private static final long serialVersionUID = 6578299788501823430L;

	private TelaAndroid tela;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			converterTela(request);
			efetuarValidacoes();
			gerarTela();
			compactarArquivos();
			retornarDownload(response);

		} catch (ValidacaoException e) {
			retornarPaginaErro(e.getMessage(), response);

		} catch (Exception e) {
			e.printStackTrace();
			retornarPaginaErro(e.getMessage(), response);
		}
	}

	private void converterTela(HttpServletRequest request) throws ComponenteInvalidoException {
		this.tela = new ConversorTelaAndroid().converter(request);
	}

	private void efetuarValidacoes() throws ValidacaoException {
		new ValidacaoComponentes().efetuarValidacoes(tela);
	}

	private void gerarTela() throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException, ConfiguracaoException {

		new GeradorTela(getServletContext()).gerar(this.tela);
	}

	private void compactarArquivos() throws IOException {
		ZipUtils.compactarArquivos(tela.getPath());
	}

	private void retornarDownload(HttpServletResponse response) throws IOException {
		DownloadUtils.efetuarDownload(getServletContext(), tela, response);
	}

	private void retornarPaginaErro(String msg, HttpServletResponse response) throws IOException {
		new GeradorPaginaErro().gerarPagina(response.getWriter(), msg);
	}
}
