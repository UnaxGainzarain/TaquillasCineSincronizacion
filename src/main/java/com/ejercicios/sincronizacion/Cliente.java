package com.ejercicios.sincronizacion;

public class Cliente {
	private int id;
    private Cine cine;

    public Cliente(int id, Cine cine) {
        this.id = id;
        this.cine = cine;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}


}
