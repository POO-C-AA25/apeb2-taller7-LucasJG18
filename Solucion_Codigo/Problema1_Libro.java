/**
 * Problema 1 - Jerarquía de clases para el capítulo de libro
 * 
 * Dibujad un diagrama de clases que muestre la estructura de un capítulo de libro; 
 * un capítulo está compuesto por varias secciones, cada una de las cuales comprende varios párrafos y figuras. 
 * Un párrafo incluye varias sentencias, cada una de las cuales contiene varias palabras.
 * 
 * Nota:
 * - Suponga que en un futuro se prevé que el sistema gestione además de párrafos y figuras 
 *   otros componentes, como tablas, listas, viñetas, etc.
 * - Suponga además que una palabra puede aparecer en varias sentencias.
 * 
 * @author Lucas
 */

import java.util.List;
import java.util.ArrayList;

class Capitulo {
    public List<Seccion> secciones = new ArrayList<>();

    public void agregarSeccion(Seccion seccion) {
        secciones.add(seccion);
    }

    public List<Seccion> getSecciones() {
        return secciones;
    }
}

class Seccion {
    public List<Sentencia> componentes = new ArrayList<>();

    public void agregarComponente(Sentencia componente) {
        componentes.add(componente);
    }

    public List<Sentencia> getComponentes() {
        return componentes;
    }
}

class Palabra {
    public String texto;

    public Palabra(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}

class Sentencia {
    public List<Palabra> palabras = new ArrayList<>();

    public Sentencia(List<Palabra> palabras) {
        this.palabras = palabras;
    }

    public void mostrar() {
        for (Palabra p : palabras) {
            System.out.print(p.getTexto() + " ");
        }
        System.out.println();
    }
}

class Parrafo extends Sentencia {
    public Parrafo(List<Palabra> palabras) {
        super(palabras);
    }
}

class Figura extends Sentencia {
    public Figura(List<Palabra> palabras) {
        super(palabras);
    }
}

public class Problema1_Libro {
    public static void main(String[] args) {
        Palabra w1 = new Palabra("Hola");
        Palabra w2 = new Palabra("Gente");
        Palabra w3 = new Palabra("!");

        List<Palabra> lista1 = new ArrayList<>();
        lista1.add(w1);
        lista1.add(w2);
        lista1.add(w3);

        Parrafo p = new Parrafo(lista1);
        Figura f = new Figura(lista1);

        p.mostrar();
        f.mostrar();
    }
}