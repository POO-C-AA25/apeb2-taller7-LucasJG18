/**
 * Un videoclub dispone de una serie de películas que pueden estar en DVD (con capacidad en Gb.) o en VHS (una sola cinta por película
 * y con cierto tipo de cinta magnetica). De las películas interesa guardar el título, el autor, el año de edición y el idioma
 * (o los idiomas, en caso de DVD). El precio de alquiler de las películas varía en función del tipo de película.
 * Las DVD siempre son 10% mas caras que las de VHS.
 * Note
 * Analice los tipos de relación de las siguientes posibles clases: Pelicula, Dvd, Vhs, Soporte, etc, y justifique su diseño.
 * Para probar el diseño jerarquico de clases, instancia en el clase de prueba Ejecutor (la-s clase-s respectiva-s), con datos aleatorios.
 * Los escenarios de prueba pueden darse para el alquiler de una o varias peliculas según la preferencia del usuario.
 * @author Lucas
 */

import java.util.ArrayList;
public class Problema2_Pelicula {
    public static void main(String[] args) {
        Pelicula pel1 = new Pelicula("Shark", "Stephen King", 1997);
        Pelicula pel2 = new Pelicula("Shark", "Stephen King", 1997);
        
        VHS vhs1 = new VHS("Spanish", pel1, 1.5);
        
        System.out.println(vhs1);
    }
}

class SoportePelicula{
    public double precioAlquiler;

    public SoportePelicula(double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    @Override
    public String toString() {
        return "SoportePelicula{" + "precioAlquiler=" + precioAlquiler + '}';
    }
}

class DVD extends SoportePelicula{
    public String[] idioma;
    public ArrayList<Pelicula> pelicula;

    public DVD(String[] idiomal, ArrayList<Pelicula> pelicula, double precioAlquiler) {
        super(precioAlquiler);
        this.idioma = idiomal;
        this.pelicula = pelicula;
    }
    
    public void calcularPrecioAlquiler(){
        this.precioAlquiler += this.precioAlquiler * 0.1; 
    }

    @Override
    public String toString() {
        return "DVD{" + "idiomal=" + idioma + ", pelicula=" + pelicula + '}';
    }
}

class VHS extends SoportePelicula{
    public String idioma;
    public Pelicula pelicula;

    public VHS(String idioma, Pelicula pelicula, double precioAlquiler) {
        super(precioAlquiler);
        this.idioma = idioma;
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "VHS{" + "idioma=" + idioma + ", pelicula=" + pelicula + "} " + super.toString();
    }
}

class Pelicula{
    public String titulo;
    public String autor;
    public int anioEdicion;

    public Pelicula(String titulo, String autor, int anioEdicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioEdicion = anioEdicion;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", autor=" + autor + ", anioEdicion=" + anioEdicion + '}';
    }
}