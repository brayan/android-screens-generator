package com.brayanbedritchuk.tcc;

import org.junit.Test;

import com.brayanbedritchuk.tcc.exceptions.ValidacaoException;
import com.brayanbedritchuk.tcc.model.ComponenteAndroid;
import com.brayanbedritchuk.tcc.model.TelaAndroid;

public class ValidacaoComponentesTest {

	@Test(expected = ValidacaoException.class)
	public void deveDispararExceptionParaNomeTela() throws ValidacaoException {

		TelaAndroid tela = new TelaAndroid();
		tela.setComponentePrincipal(new ComponenteAndroid());

		new ValidacaoComponentes().efetuarValidacoes(tela);
	}

	@Test(expected = ValidacaoException.class)
	public void deveDispararExceptionParaAtributosNaoPreenchidos() throws ValidacaoException {

		TelaAndroid tela = new TelaAndroid();
		tela.setNome("NomeTela");
		ComponenteAndroid componente = new ComponenteAndroid();
		tela.setComponentePrincipal(componente);

		new ValidacaoComponentes().efetuarValidacoes(tela);
	}
}
