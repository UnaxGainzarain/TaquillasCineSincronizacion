package com.ejercicios.sincronizacion;

public class SimulacionCine {
	 static final int NUM_TAQUILLAS = 5;
	    static final int TOTAL_ASIENTOS = 10;
	    static final int NUM_COLAS = 4;           
	    static final int MAX_PERSONAS_COLA = 5;  
	    
	    static final int TIEMPO_SIMULACION = 10000; 
	    static final int TASA_LLEGADA_CLIENTES = 50; 
	    
	    public static final int TIEMPO_VENTA_MIN = 2000;
	    public static final int TIEMPO_VENTA_MAX = 3000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cine cine = new Cine(TOTAL_ASIENTOS, NUM_COLAS, MAX_PERSONAS_COLA);
        System.out.println("--- INICIO SIMULACIÓN CINE ---");
        System.out.println("Aforo: " + TOTAL_ASIENTOS + " | Colas: " + NUM_COLAS);
        
        Thread[] hilosTaquillas = new Thread[NUM_TAQUILLAS];
        
        for (int i = 0; i < NUM_TAQUILLAS; i++) {
            Taquilla t = new Taquilla(cine, "Taquilla-" + (i + 1));
            hilosTaquillas[i] = new Thread(t);
            hilosTaquillas[i].start();
        }
        
        //Generador de Clientes (Hilo Productor en el main)
        long tiempoFin = System.currentTimeMillis() + TIEMPO_SIMULACION;
        int idCliente = 1;
        
        try {
			while(System.currentTimeMillis()<tiempoFin) {
				Cliente c = new Cliente(idCliente ++, cine);
				c.start();
				Thread.sleep(TASA_LLEGADA_CLIENTES);
			}
				System.out.println("\n--- FIN DEL TIEMPO DE ENTRADA. CERRANDO PUERTAS... ---");
	            Thread.sleep(2000); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
      //Parar las Taquillas 
        for (Thread t : hilosTaquillas) {
            t.interrupt();
        }

        // Mostrar resultados finales
        try {
            // Pequeña espera para asegurar que los hilos cierran sus mensajes
            Thread.sleep(1000);
            cine.mostrarEstadisticas();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}
