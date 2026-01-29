###Taquillas de Cine - Simulación de Sincronización en Java
Este proyecto es un ejercicio práctico de programación concurrente en Java. Simula el sistema de venta de entradas de un cine, gestionando la concurrencia entre múltiples clientes (hilos productores) y varias taquillas (hilos consumidores) que compiten por recursos limitados (entradas y espacio en colas).

#📋 Descripción del Proyecto
El sistema simula un cine con un aforo limitado y varias colas de espera.

Clientes: Llegan periódicamente y eligen una cola al azar. Si la cola está llena, se marchan.

Taquillas: Atienden a los clientes de las colas. Tardan un tiempo en procesar la venta.

Venta: Al finalizar la atención, la taquilla intenta vender una entrada. Si el cine está lleno (aforo completo), el cliente se queda sin entrada.

#🛠️ Estructura del Código
El proyecto está organizado en el paquete com.ejercicios.sincronizacion y consta de las siguientes clases principales:

SimulacionCine:

Clase principal (main).

Configura los parámetros de la simulación (número de taquillas, aforo, colas, tiempos).

Inicia los hilos de las taquillas.

Genera hilos de Cliente durante un tiempo determinado (TIEMPO_SIMULACION).

Muestra las estadísticas finales.

Cine:

Actúa como el recurso compartido (Monitor).

Gestiona las listas de espera (colas de clientes).

Controla el aforo mediante un Semáforo (Semaphore).

Utiliza métodos synchronized, wait() y notifyAll() para gestionar el acceso concurrente a las colas.

Taquilla:

Implementa Runnable.

Representa a un taquillero que saca clientes de las colas y procesa la venta.

Simula el tiempo de procesamiento con Thread.sleep().

Cliente:

Extiende de Thread.

Representa a una persona que intenta ponerse en una de las colas del cine.

#⚙️ Mecanismos de Sincronización
El ejercicio implementa varios conceptos clave de la concurrencia:

Semáforos (Semaphore):

Utilizado en la clase Cine (semaforoAforo) para controlar estrictamente el número total de entradas vendidas según el aforo total.

tryAcquire() se usa para intentar vender una entrada de forma atómica.

Monitores (synchronized, wait, notifyAll):

Acceso a Colas: Los métodos ponerseEnCola y atenderCliente están sincronizados para evitar corrupciones de datos en las listas.

Coordinación: Las taquillas usan wait() cuando no hay clientes, esperando a que llegue alguien. Los clientes usan notifyAll() al entrar en una cola para despertar a las taquillas.

Gestión de Hilos:

Uso de Thread para clientes y Runnable para taquillas.

Interrupción controlada de hilos al finalizar la simulación.

#🚀 Configuración de la Simulación
Puedes ajustar los parámetros de la simulación en las constantes de la clase SimulacionCine.java:

Java

static final int NUM_TAQUILLAS = 5;       // Número de hilos taquilla
static final int TOTAL_ASIENTOS = 10;     // Aforo total (permisos del semáforo)
static final int NUM_COLAS = 4;           // Número de colas disponibles
static final int MAX_PERSONAS_COLA = 5;   // Capacidad máxima por cola
static final int TASA_LLEGADA_CLIENTES = 50; // Milisegundos entre cada nuevo cliente
public static final int TIEMPO_VENTA_MIN = 2000; // Tiempo mín. de atención
public static final int TIEMPO_VENTA_MAX = 3000; // Tiempo máx. de atención
#📊 Estadísticas
Al finalizar la ejecución, el programa muestra un resumen:

Entradas vendidas: Total de clientes que consiguieron entrar.

Clientes sin entrada (Aforo): Clientes que fueron atendidos pero el cine ya estaba lleno.

Clientes que se fueron (Cola llena): Clientes que llegaron pero encontraron su cola llena y desistieron.

#📦 Requisitos y Ejecución
Java JDK: 17 o superior (configurado en el pom.xml).

Maven: Para la gestión de dependencias y construcción.

Para ejecutar desde la terminal:

Bash

mvn clean compile exec:java -Dexec.mainClass="com.ejercicios.sincronizacion.SimulacionCine"
O simplemente ejecuta la clase SimulacionCine como una Java Application desde tu IDE (Eclipse, IntelliJ, etc.).
