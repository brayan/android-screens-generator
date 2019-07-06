package com.brayanbedritchuk.tcc.model.componentes;

public class ToolbarConfiguracao implements ComponenteConfiguracao {

	@Override
	public String getTipo() {
		return "Toolbar";
	}

	@Override
	public String getImport() {
		return "android.support.v7.widget.Toolbar";
	}

	@Override
	public boolean isSupportLib() {
		return true;
	}

}
