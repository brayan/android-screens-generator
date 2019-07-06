function isFormComponentesValido() {
	try {
		validarNomeTela();
		validarCamposComponentePrincipal();
		validarSePossuiFilhosAdicionados();
		validarCamposFilhos();
		
		return true;
		
	} catch (mensagemErro) {
		mostrarMensagem(mensagemErro);
		return false;
	}
}

function validarNomeTela() {
	var campo = getCampo("componentePrincipalNome");

	if (!isCampoValido(campo)) {
		throw "É necessário preencher corretamente o nome da tela";
	}
}

function validarSePossuiFilhosAdicionados() {
	if ($('#componentesAdicionados').children(".componenteAdicionado").length == 0) {
		throw "É necessário adicionar pelo menos um componente";
	}
}

function validarCamposComponentePrincipal() {
	validarTipoComponentePrincipal();
	validarLarguraComponentePrincipal();
	validarAlturaComponentePrincipal();
	validarOrientacao();
}

function validarLarguraComponentePrincipal() {
	var campo = getCampo("componentePrincipalLargura");

	if (!isCampoValido(campo)) {
		throw "É necessário preencher corretamente o width do componente principal";
	}
}

function validarAlturaComponentePrincipal() {
	var campo = getCampo("componentePrincipalAltura");

	if (!isCampoValido(campo)) {
		throw "É necessário preencher corretamente o height do componente principal";
	}
}

function validarTipoComponentePrincipal() {
	var campo = getCampo("componentePrincipalTipo");

	if (!isCampoValido(campo)) {
		throw "É necessário preencher corretamente o tipo do componente principal";
	}
}

function validarOrientacao() {
	var tipoComponente = getCampo("componentePrincipalTipo");
	var campoOrientacao = getCampo("componentePrincipalOrientacao");
	
	if (tipoComponente.trim() == "LinearLayout" && !isCampoValido(campoOrientacao)) {
		throw "É necessário preencher corretamente uma orientação para o componente LinearLayout";
	}
}

function validarCamposFilhos() {
	$("#componentesAdicionados").find("input").each(function () {
		validarLargura(this);
		validarAltura(this);
	});
}

function validarLargura(componente) {
	var id = $(componente).attr("name");
	
	if (id.toLowerCase().indexOf("largura") >= 0) {
		if (!isCampoValido($(componente).val())) {
			throw "É necessário preencher corretamente o campo Width do componente " + $(componente).attr("name");
		}
	}
}

function validarAltura(componente) {
	var id = $(componente).attr("name");
	
	if (id.toLowerCase().indexOf("altura") >= 0) {
		if (!isCampoValido($(componente).val())) {
			throw "É necessário preencher corretamente o campo Height do componente " + $(componente).attr("name");
		}
	}
}

function getCampo(nome) {
	return document.forms["formComponentes"][nome].value;
}

function isCampoValido(campo) {
	 
	if (campo != null && campo.trim() != "") {
		return true;
	}
}

function mostrarMensagem(mensagem) {
	alert(mensagem);
}