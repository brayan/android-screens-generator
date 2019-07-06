package com.brayanbedritchuk.tcc.model.componentes;

public class TextViewConfiguracao implements ComponenteConfiguracao {

	@Override
	public String getTipo() {
		return "TextView";
	}

	@Override
	public String getImport() {
		return "android.widget.TextView";
	}

	@Override
	public boolean isSupportLib() {
		return false;
	}
}
