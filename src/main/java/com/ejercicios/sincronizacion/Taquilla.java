package com.ejercicios.sincronizacion;

import java.util.Random;

public class Taquilla implements Runnable{
	
	private Cine cine;
    private String nombre;
    private Random generator;

    public Taquilla(Cine cine, String nombre) {
        this.cine = cine;
        this.nombre = nombre;
        this.generator = new Random();
    }

	
	@Override
	public void run() {
		 
	}

}
