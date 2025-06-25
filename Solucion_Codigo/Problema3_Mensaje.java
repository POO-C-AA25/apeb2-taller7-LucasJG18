/**
 * Problema 3 - Sistema de envío de mensajes a móviles
 * 
 * Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes que se pueden enviar entre móviles,
 * mensajes de texto (SMS) y mensajes que contienen imágenes (MMS). Por un lado, los mensajes de texto contienen un mensaje
 * Por otro lado, los mensajes que contienen imágenes almacenan información sobre la imagen a enviar,la cual se representará
 * por el nombre del fichero que la contiene. Independientemente del tipo de mensaje, cada mensaje tendrá asociado un remitente
 * de dicho mensaje y un destinatario. Ambos estarán definidos obligatoriamente por un número de móvil, y opcionalmente se podrá
 * guardar información sobre su nombre. Además, los métodos enviarMensaje y visualizarMensaje deben estar definidos.
 * @author Lucas
 */

public class Problema3_Mensaje {
    public static void main(String[] args) {
        Persona p1 = new Persona("Lucas", "0991234567");
        Persona p2 = new Persona("David", "0987654321");

        SMS sms = new SMS(p1, p2, "Nos vemos a las 5.");
        MMS mms = new MMS(p2, p1, "imagenGatos.jpg");

        sms.enviarMensaje();
        sms.visualizarMensaje();

        System.out.println(" ");
        mms.enviarMensaje();
        mms.visualizarMensaje();
    }
}

class Persona {
    public String nombre;
    public String numTelefono;

    public Persona(String nombre, String numTelefono) {
        this.nombre = nombre;
        this.numTelefono = numTelefono;
    }
}

class Mensaje {
    public Persona remitente;
    public Persona destinatario;

    public Mensaje(Persona remitente, Persona destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    public void enviarMensaje() {
        System.out.println("Mensaje de " + remitente.nombre + " (" + remitente.numTelefono + ")" +
                           " a " + destinatario.nombre + " (" + destinatario.numTelefono + ")");
    }

    public void visualizarMensaje() {
        System.out.println("Mensaje genérico");
    }
}

class SMS extends Mensaje {
    public String mensaje;

    public SMS(Persona remitente, Persona destinatario, String texto) {
        super(remitente, destinatario);
        this.mensaje = texto;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("Enviando SMS de " + remitente.nombre + " (" + remitente.numTelefono + ")" +
                           " a " + destinatario.nombre + " (" + destinatario.numTelefono + ")");
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("Contenido del SMS: \"" + mensaje + "\"");
    }
}

class MMS extends Mensaje {
    public String nombreImagen;

    public MMS(Persona remitente, Persona destinatario, String nombreArchivo) {
        super(remitente, destinatario);
        this.nombreImagen = nombreArchivo;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("Enviando MMS de " + remitente.nombre + " (" + remitente.numTelefono + ")" +
                           " a " + destinatario.nombre + " (" + destinatario.numTelefono + ")");
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("MMS con imagen: " + nombreImagen);
    }
}