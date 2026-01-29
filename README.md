# Taquillas de Cine - Simulación de Sincronización en Java

Este proyecto implementa una simulación concurrente de un sistema de venta de entradas de cine utilizando **Java Threads**, **Semáforos** y **Monitores**. El objetivo es gestionar el acceso concurrente de múltiples clientes a colas limitadas y controlar el aforo total del cine mediante taquillas sincronizadas.

## 📋 Descripción

El sistema simula el flujo de clientes que intentan comprar entradas en un cine con las siguientes características:
* **Aforo limitado:** Controlado estrictamente para no vender más entradas de las permitidas.
* **Múltiples colas:** Los clientes eligen una cola aleatoria. Si la cola está llena, el cliente desiste y se marcha.
* **Taquillas independientes:** Varias taquillas atienden a los clientes concurrentemente, procesando la venta (simulando un tiempo de espera).

## 📂 Estructura del Proyecto

El código se encuentra en el paquete `com.ejercicios.sincronizacion`.

| Clase | Responsabilidad |
| :--- | :--- |
| **`SimulacionCine`** | Clase principal (`main`). Inicia la simulación, crea las taquillas y genera el flujo de clientes durante un tiempo determinado. |
| **`Cine`** | Recurso compartido (Monitor). Gestiona las listas de clientes (colas), controla el aforo global y sincroniza el acceso. |
| **`Taquilla`** | Hilo consumidor (`Runnable`). Atiende a los clientes de las colas y procesa las ventas. |
| **`Cliente`** | Hilo productor (`Thread`). Intenta entrar en una de las colas del cine. |

## ⚙️ Mecanismos de Sincronización

El proyecto demuestra el uso de varios mecanismos clave de concurrencia:

1.  **Semáforo (`Semaphore`)**:
    * Ubicado en la clase `Cine` (`semaforoAforo`).
    * Se inicializa con el `TOTAL_ASIENTOS`.
    * Se utiliza `tryAcquire()` para asegurar que no se vendan más entradas que el aforo permitido, garantizando la exclusión mutua en el conteo final.

2.  **Monitores (`synchronized`, `wait`, `notifyAll`)**:
    * **Protección de Colas**: Los métodos `ponerseEnCola` y `atenderCliente` son `synchronized` para proteger la integridad de las listas `ArrayList`.
    * **Coordinación**:
        * Las taquillas esperan (`wait()`) si no hay clientes en las colas.
        * Cuando un cliente entra en una cola, notifica (`notifyAll()`) a las taquillas para que se despierten y procesen la venta.

## 🚀 Configuración

Los parámetros de la simulación son constantes modificables en `SimulacionCine.java`:

```java
static final int NUM_TAQUILLAS = 5;       // Hilos atendiendo
static final int TOTAL_ASIENTOS = 10;     // Capacidad máxima del cine
static final int NUM_COLAS = 4;           // Número de filas disponibles
static final int MAX_PERSONAS_COLA = 5;   // Longitud máxima de cada fila
static final int TASA_LLEGADA_CLIENTES = 50; // Frecuencia de llegada (ms)
