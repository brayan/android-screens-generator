package com.brayanbedritchuk.tcc.model.componentes;

public class EditTextConfiguracao implements ComponenteConfiguracao {

	@Override
	public String getTipo() {
		return "EditText";
	}

	@Override
	public String getImport() {
		return "android.widget.EditText";
	}

	@Override
	public boolean isSupportLib() {
		return false;
	}
}
