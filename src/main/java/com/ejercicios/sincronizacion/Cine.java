package com.ejercicios.sincronizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Cine {
	private List<List<Cliente>> colas; 
    private int maxPorCola; 
    
    private Semaphore semaforoAforo;
    private int entradasVendidas = 0;
    private int clientesSinEntrada = 0;
    private int clientesPerdidosCola = 0; 
    
    public Cine(int totalAsientos, int numColas, int maxPorCola) {
        this.maxPorCola = maxPorCola;
        
        this.semaforoAforo = new Semaphore(totalAsientos); // [cite: 242]
        
        colas = new ArrayList<>();
        for (int i = 0; i < numColas; i++) {
            colas.add(new ArrayList<>());
        }
    }
}
