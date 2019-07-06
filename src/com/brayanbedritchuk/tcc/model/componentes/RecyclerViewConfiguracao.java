package com.brayanbedritchuk.tcc.model.componentes;

public class RecyclerViewConfiguracao implements ComponenteConfiguracao {

	@Override
	public String getTipo() {
		return "RecyclerView";
	}

	@Override
	public String getImport() {
		return "android.support.v7.widget.RecyclerView";
	}

	@Override
	public boolean isSupportLib() {
		return true;
	}

}
