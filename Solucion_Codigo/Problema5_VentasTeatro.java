/**
 * Problema 5 - Venta de entradas al teatro
 * 
 * Se desea gestionar la venta de entradas para un espectáculo en un teatro. 
 * El patio de butacas del teatro se divide en varias zonas, cada una identificada por su nombre.
 * Los datos de las zonas son los mostrados en la siguiente tabla:
 * 
 * NOMBRE ZONA    NÚMERO DE LOCALIDADES   PRECIO NORMAL   PRECIO ABONADO
 * Principal      200                     25$             17.5$
 * PalcoB         40                      70$             40$
 * Central        400                     20$             14$
 * Lateral        100                     15.5$           10$
 * 
 * Para realizar la compra de una entrada, un espectador debe indicar la zona que desea y presentar 
 * al vendedor el documento que justifique que tiene algún tipo de descuento (estudiante, abonado o pensionista).
 * El vendedor sacará la entrada del tipo apropiado y de la zona indicada, en el momento de la compra se asignará 
 * a la entrada un identificador (un número entero) que permitirá la identificación de la entrada en todas las operaciones 
 * que posteriormente se desee realizar con ella.
 * 
 * Una entrada tiene como datos asociados su identificador, la zona a la que pertenece y el nombre del comprador.
 * 
 * Los precios de las entradas dependen de la zona y del tipo de entrada según lo explicado a continuación:
 * 
 * Entradas normales: su precio es el precio normal de la zona elegida sin ningún tipo de descuento.
 * Entradas reducidas (para estudiantes o pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de la zona elegida.
 * Entradas abonado: su precio es el precio para abonados de la zona elegida.
 * 
 * Caso de uso “Vende entrada”:
 * - El vendedor elige la opción “vende entrada” e introduce la zona deseada, el nombre del espectador y el tipo (normal, abonado o beneficiario de entrada reducida).
 * - Si la zona elegida no está completa, la aplicación genera una nueva entrada con los datos facilitados.
 * - Si no existe ninguna zona con ese nombre, se notifica y finaliza el caso de uso sin generar la entrada.
 * - Si la zona elegida está completa lo notifica y finaliza el caso de uso sin generar la entrada.
 * - La aplicación muestra el identificador y el precio de la entrada.
 * 
 * Caso de uso “Consulta entrada”:
 * - El vendedor elige la opción “consulta entrada” e introduce el identificador de la entrada.
 * - La aplicación muestra los datos de la entrada: nombre del espectador, precio y nombre de la zona. 
 *   Si no existe ninguna entrada con ese identificador, lo notifica y finaliza el caso de uso.
 * 
 * @author Lucas
 */

import java.util.ArrayList;
import java.util.List;

public class Problema5_VentasTeatro {
    public static void main(String[] args) {
        GestorEntradas gestor = new GestorEntradas();

        gestor.venderEntrada("Principal", "Lucas", "normal");
        gestor.venderEntrada("PalcoB", "Ana", "abonado");
        gestor.venderEntrada("Central", "Mario", "reducida");
        gestor.venderEntrada("Lateral", "Erika", "reducida");

        System.out.println("\n---BUSCAR ENTRADA---");
        gestor.consultarEntrada(2);
        gestor.consultarEntrada(10);
    }
}


class Zona {
    public String nombre;
    public int capacidadTotal;
    public int localidadesVendidas;
    public double precioNormal;
    public double precioAbonado;

    public Zona(String nombre, int capacidad, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.capacidadTotal = capacidad;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.localidadesVendidas = 0;
    }

    public boolean hayDisponibilidad() {
        return localidadesVendidas < capacidadTotal;
    }

    public void vender() {
        localidadesVendidas++;
    }
}

class Entrada {
    public int id;
    public Zona zona;
    public String comprador;

    public Entrada(int id, Zona zona, String comprador) {
        this.id = id;
        this.zona = zona;
        this.comprador = comprador;
    }

    public double getPrecio() {
        return zona.precioNormal;
    }

    public String toString() {
        return "ID: " + id + 
               "\nNombre: " + comprador + 
               "\nZona: " + zona.nombre +
               "\nPrecio: $" + getPrecio()+
               "\n--------------------------";
    }
}

class EntradaNormal extends Entrada {
    public EntradaNormal(int id, Zona zona, String comprador) {
        super(id, zona, comprador);
    }
}

class EntradaReducida extends Entrada {
    public EntradaReducida(int id, Zona zona, String comprador) {
        super(id, zona, comprador);
    }

    @Override
    public double getPrecio() {
        return zona.precioNormal * 0.85;
    }
}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(int id, Zona zona, String comprador) {
        super(id, zona, comprador);
    }

    @Override
    public double getPrecio() {
        return zona.precioAbonado;
    }
}

class GestorEntradas {
    public List<Zona> zonas = new ArrayList<>();
    public List<Entrada> entradas = new ArrayList<>();
    public int contadorId = 1;

    public GestorEntradas() {
        zonas.add(new Zona("Principal", 200, 25.0, 17.5));
        zonas.add(new Zona("PalcoB", 40, 70.0, 40.0));
        zonas.add(new Zona("Central", 400, 20.0, 14.0));
        zonas.add(new Zona("Lateral", 100, 15.5, 10.0));
    }

    public Zona buscarZona(String nombre) {
        for (Zona z : zonas) {
            if (z.nombre.equalsIgnoreCase(nombre)) {
                return z;
            }
        }
        return null;
    }

    public Entrada buscarEntrada(int id) {
        for (Entrada e : entradas) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    public void venderEntrada(String zonaNombre, String comprador, String tipo) {
        Zona zona = buscarZona(zonaNombre);
        if (zona == null) {
            System.out.println("Zona no existe.");
            return;
        }

        if (!zona.hayDisponibilidad()) {
            System.out.println("La zona está completa.");
            return;
        }

        Entrada entrada = null;
        if (tipo.equalsIgnoreCase("normal")) {
            entrada = new EntradaNormal(contadorId, zona, comprador);
        } else if (tipo.equalsIgnoreCase("abonado")) {
            entrada = new EntradaAbonado(contadorId, zona, comprador);
        } else if (tipo.equalsIgnoreCase("reducida")) {
            entrada = new EntradaReducida(contadorId, zona, comprador);
        } else {
            System.out.println("Tipo de entrada inválido.");
            return;
        }

        zona.vender();
        entradas.add(entrada);
        System.out.println("---ENTRADA VENDIDA---\nID: " + entrada.id + 
                           "\nPrecio: $" + entrada.getPrecio());
        contadorId++;
    }

    public void consultarEntrada(int id) {
        Entrada entrada = buscarEntrada(id);
        if (entrada == null) {
            System.out.println("No existe ninguna entrada con ese ID.");
        } else {
            System.out.println(entrada);
        }
    }
}