/**
 * Problema 4 - Sistema de nómina para trabajadores
 * 
 * Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. Los datos personales de
 * los trabajadores son nombre y apellidos, dirección y DNI. Además, existen diferentes tipos de trabajadores:
 * - Fijos Mensuales: que cobran una cantidad fija al mes.
 * - Comisionistas: cobran un porcentaje fijo por las ventas que han realizado.
 * - Por Horas: cobran un precio por cada una de las horas que han realizado durante el mes. 
 *   El precio es fijo para las primeras 40 horas y es otro para las horas realizadas a partir de la 40ª hora mensual.
 * - Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene obligatoriamente un jefe 
 *   (exceptuando los jefes que no tienen ninguno).
 * 
 * El programa debe permitir dar de alta a trabajadores, así como fijar horas o ventas realizadas e imprimir la nómina
 * correspondiente al final de mes. Diseñe la jerarquía de clases UML basado en herencia, que defina de mejor forma el
 * escenario planteado. Para probar el diseño de clases, instancia en la clase de prueba Ejecutor (las clases respectivas),
 * con datos aleatorios. En los escenarios de prueba verifique su solución con al menos 2 tipos de trabajadores.
 * @author Lucas
 */

public class Problema4_Trabajadores{
    public static void main(String[] args){
        FijoMensual jefe = new FijoMensual("Maria", "Gonzalez", "Av. Quito", "234565", null, 3000);
        Comisionista vendedor = new Comisionista("Carlos", "Perez", "Calle Luna", "1234567654", jefe, 10);
        vendedor.registrarVentas(15000);
        PorHoras obrero = new PorHoras("Luis", "Mora", "Calle Sol", "22245645", jefe, 10, 15);
        obrero.registrarHoras(50);
        
        jefe.mostrarNomina();
        vendedor.mostrarNomina();
        obrero.mostrarNomina();
    }
}

class Trabajador{
    public String nombre;
    public String apellidos;
    public String direccion;
    public String dni;
    public Trabajador jefe;

    public Trabajador(String nombre, String apellidos, String direccion, String dni, Trabajador jefe){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }

    public double calcularSueldo(){
        return 0;
    }

    public void mostrarNomina(){
        System.out.println("Nombre: " + nombre + " " + apellidos);
        System.out.println("DNI: " + dni);
        System.out.println("Direccion: " + direccion);
        if (jefe != null){
            System.out.println("Jefe: " + jefe.nombre + " " + jefe.apellidos);
        } else {
            System.out.println("Jefe: (sin jefe)");
        }
        System.out.println("Sueldo: $" + calcularSueldo());
        System.out.println("------");
    }
}

class FijoMensual extends Trabajador{
    public double sueldoFijo;

    public FijoMensual(String nombre, String apellidos, String direccion, String dni, Trabajador jefe, double sueldoFijo){
        super(nombre, apellidos, direccion, dni, jefe);
        this.sueldoFijo = sueldoFijo;
    }

    public double calcularSueldo(){
        return sueldoFijo;
    }
}

class Comisionista extends Trabajador{
    public double porcentajeVenta;
    public double totalVentas;

    public Comisionista(String nombre, String apellidos, String direccion, String dni, Trabajador jefe, double porcentajeVenta){
        super(nombre, apellidos, direccion, dni, jefe);
        this.porcentajeVenta = porcentajeVenta;
        this.totalVentas = 0;
    }

    public void registrarVentas(double monto){
        totalVentas += monto;
    }

    public double calcularSueldo(){
        return totalVentas * (porcentajeVenta / 100.0);
    }
}

class PorHoras extends Trabajador{
    public int horasTrabajadas;
    public double precioHoraNormal;
    public double precioHoraExtra;

    public PorHoras(String nombre, String apellidos, String direccion, String dni, Trabajador jefe, double precioHoraNormal, double precioHoraExtra){
        super(nombre, apellidos, direccion, dni, jefe);
        this.precioHoraNormal = precioHoraNormal;
        this.precioHoraExtra = precioHoraExtra;
        this.horasTrabajadas = 0;
    }

    public void registrarHoras(int horas){
        horasTrabajadas += horas;
    }

    public double calcularSueldo(){
        int horasNormales;
        int horasExtras;

        if (horasTrabajadas <= 40){
            horasNormales = horasTrabajadas;
            horasExtras = 0;
        } else {
            horasNormales = 40;
            horasExtras = horasTrabajadas - 40;
        }
        return (horasNormales * precioHoraNormal) + (horasExtras * precioHoraExtra);
    }
}