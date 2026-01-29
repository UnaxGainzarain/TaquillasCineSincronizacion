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
		while (!Thread.currentThread().isInterrupted()) {
            
            //Intentar coger un cliente de la cola.
            Cliente cliente = cine.atenderCliente();

            if (cliente != null) {
                System.out.println(nombre + " atendiendo al Cliente " + cliente.getId());

               
                int tiempoVenta = generator.nextInt(
                    SimulacionCine.TIEMPO_VENTA_MAX - SimulacionCine.TIEMPO_VENTA_MIN + 1
                ) + SimulacionCine.TIEMPO_VENTA_MIN;

                Thread.sleep(tiempoVenta);

                //Intentar finalizar la venta (comprobar semáforo de aforo)
                if (cine.intentarVenderEntrada()) {
                    System.out.println( nombre + " ha vendido una entrada al Cliente " + cliente.getId());
                } else {
                    System.out.println( nombre + " no pudo vender. CINE LLENO. Cliente " + cliente.getId() + " se queda sin entrada.");
                }
            }
        }
		}catch(Exception e) {
			System.out.println(nombre + " cerrando caja (Interrupción recibida).");		}
	}

}
