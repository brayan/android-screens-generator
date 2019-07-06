package com.brayanbedritchuk.tcc.model.componentes;

public class CheckBoxConfiguracao implements ComponenteConfiguracao {

	@Override
	public String getTipo() {
		return "CheckBox";
	}

	@Override
	public String getImport() {
		return "android.widget.CheckBox";
	}

	@Override
	public boolean isSupportLib() {
		return false;
	}

}
