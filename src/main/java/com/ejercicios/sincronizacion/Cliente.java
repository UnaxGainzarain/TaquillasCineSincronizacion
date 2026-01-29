package com.ejercicios.sincronizacion;

public class Cliente extends Thread{
	private int id;
    private Cine cine;

    public Cliente(int id, Cine cine) {
        this.id = id;
        this.cine = cine;
    }

	public long getId() {
		return id;
	}
	  @Override
	    public void run() {
	        
	        cine.ponerseEnCola(this);
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
