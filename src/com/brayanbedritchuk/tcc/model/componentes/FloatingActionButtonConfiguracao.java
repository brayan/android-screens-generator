package com.brayanbedritchuk.tcc.model.componentes;

public class FloatingActionButtonConfiguracao implements ComponenteConfiguracao {

	@Override
	public String getTipo() {
		return "FloatingActionButton";
	}

	@Override
	public String getImport() {
		return "android.support.design.widget.FloatingActionButton";
	}

	@Override
	public boolean isSupportLib() {
		return true;
	}

}
