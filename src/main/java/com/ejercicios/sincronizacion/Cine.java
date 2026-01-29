package com.ejercicios.sincronizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    
    public boolean intentarVenderEntrada() {
        if (semaforoAforo.tryAcquire()) { 
            synchronized (this) {
                entradasVendidas++;
            }
            return true;
        } else {
            synchronized (this) {
                clientesSinEntrada++;
            }
            return false;
        }
    }
    
    public synchronized boolean ponerseEnCola(Cliente c) {
        Random rand = new Random();
        int indiceCola = rand.nextInt(colas.size());
        
        List<Cliente> colaElegida = colas.get(indiceCola);
        
        if (colaElegida.size() < maxPorCola) {
            colaElegida.add(c);
            System.out.println("Cliente " + c.getId() + " entra en la cola " + indiceCola);
            
            notifyAll(); 
            return true; 
        } else {
            System.out.println("Cliente " + c.getId() + " se marcha (Cola " + indiceCola + " llena).");
            clientesPerdidosCola++;
            return false;
        }
    }
    
    public synchronized Cliente atenderCliente() throws InterruptedException {
        while (todasVacias()) {
            wait(); 
        }
        
        for (List<Cliente> unaCola : colas) {
            if (!unaCola.isEmpty()) {
                return unaCola.remove(0); 
            }
        }
        return null; 
    }
    private boolean todasVacias() {
        for (List<Cliente> unaCola : colas) {
            if (!unaCola.isEmpty()) {
                return false; 
            }
        }
        return true; 
    }
}
