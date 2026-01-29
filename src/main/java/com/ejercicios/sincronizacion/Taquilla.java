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
        System.out.println(nombre + " abierta y esperando clientes.");
        try {
			Cliente cliente = cine.atenderCliente();
			if(cliente !=null) {
				System.out.println("Atendiendo al cliente " + cliente.getId());
				 int tiempoVenta = generator.nextInt(
	                        SimulacionCine.TIEMPO_VENTA_MAX - SimulacionCine.TIEMPO_VENTA_MIN + 1
	                    ) + SimulacionCine.TIEMPO_VENTA_MIN;
				 Thread.sleep(tiempoVenta);
				 
				 //Ahora intentamos finalizar la venta
				 if (cine.intentarVenderEntrada()) {
                     System.out.println( nombre + " ha vendido una entrada al Cliente " + cliente.getId());
                 } else {
                     System.out.println( nombre + " no pudo vender. CINE LLENO. Cliente " + cliente.getId() + " se queda sin entrada.");
                 }

			}
		} catch (InterruptedException e) {
			System.out.println(nombre + " cerrando caja (Interrupción recibida).");
		}
	}

}
