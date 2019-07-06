package com.brayanbedritchuk.tcc.model.componentes;

public class RectangularButtonConfiguracao implements ComponenteConfiguracao {

	@Override
	public String getTipo() {
		return "Button";
	}

	@Override
	public String getImport() {
		return "android.widget.Button";
	}

	@Override
	public boolean isSupportLib() {
		return false;
	}

}
