/**
 * Problema 6 - Sistema Un Banco
 * 
 * El banco UN BANCO mantiene las cuentas de varios clientes. Los datos que describen a cada una de las cuentas consisten en el número de cuenta, el nombre del cliente y el balance actual.
 * Escriba una clase para implementar dicha cuenta bancaria. El método constructor debe aceptar como parámetros el número de cuenta y el nombre. Debe proporcionarse métodos para depositar o retirar una cantidad de dinero y obtener el balance actual.
 * 
 * El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS. Una cuenta de cheques puede sobregirarse (el balance puede ser menor que cero), pero una cuenta de ahorros no. Al final de cada mes, se calcula el interés sobre la cantidad que tenga la cuenta de ahorros. Este interés se suma al balance. Escriba clases para describir cada uno de estos tipos de cuentas, haciendo un máximo uso de la herencia. La clase de la cuenta de ahorros debe proporcionar un método que sea invocado para calcular el interés. Además, el banco está pensando en implementar una cuenta PLATINO que viene siendo similar a los otros dos tipos anteriores de cuentas bancarias, ésta tiene el interés del 10%, sin cargos ni castigos por sobregiro.
 * 
 * Ud. debe implementar una clase de PRUEBA (Clase de ejecución) desde la cual se pueda evidenciar el correcto funcionamiento de cada clase con n clientes, y que además se pueda mostrar el balance (estado de cuenta) para cada cliente.
 * 
 * @author Lucas
 */

public class Problema6_Banco {
    public static void main(String[] args) {
        CuentaAhorros c1 = new CuentaAhorros("001", "Ana", 0.05);
        c1.depositar(1000);
        c1.retirar(200);
        c1.aplicarInteres();

        CuentaCheques c2 = new CuentaCheques("002", "Luis");
        c2.depositar(500);
        c2.retirar(700);

        CuentaPlatino c3 = new CuentaPlatino("003", "Valeria");
        c3.depositar(100);
        c3.retirar(200);
        c3.aplicarInteres();

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}

class CuentaBancaria {
    public String numeroCuenta;
    public String nombreCliente;
    public double balance;

    public CuentaBancaria(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = 0.0;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            balance += monto;
        }
    }

    public void retirar(double monto) {
        if (monto > 0 && balance >= monto) {
            balance -= monto;
        }
    }

    public double obtenerBalance() {
        return balance;
    }

    public String toString() {
        return "Cuenta: " + numeroCuenta + ", Cliente: " + nombreCliente + ", Balance: $" + balance;
    }
}

class CuentaCheques extends CuentaBancaria {
    public CuentaCheques(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public void retirar(double monto) {
        if (monto > 0) {
            balance -= monto;
        }
    }
}

class CuentaAhorros extends CuentaBancaria {
    public double tasaInteres;

    public CuentaAhorros(String numeroCuenta, String nombreCliente, double tasaInteres) {
        super(numeroCuenta, nombreCliente);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void retirar(double monto) {
        if (monto > 0 && balance >= monto) {
            balance -= monto;
        }
    }

    public void aplicarInteres() {
        double interes = balance * tasaInteres;
        balance += interes;
    }
}

class CuentaPlatino extends CuentaBancaria {
    public final double tasaInteres = 0.10;

    public CuentaPlatino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public void retirar(double monto) {
        if (monto > 0) {
            balance -= monto;
        }
    }

    public void aplicarInteres() {
        double interes = balance * tasaInteres;
        balance += interes;
    }
}