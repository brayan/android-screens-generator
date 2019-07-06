package com.brayanbedritchuk.tcc.model.componentes;

public class CardViewConfiguracao implements ComponenteConfiguracao {

	@Override
	public String getTipo() {
		return "CardView";
	}

	@Override
	public String getImport() {
		return "android.support.v7.widget.CardView";
	}

	@Override
	public boolean isSupportLib() {
		return true;
	}

}
