/*
 Subclase moto, una de las dos subclases de la superclase Vehiculo.
Heredará toda la informacion de Vehiculo (atributos, metodos y la misma interfaz que se aplico anteriormente)
Como el factor de Potencia se obtiene mediante calculo, en el constructor al ese atributo se le asigna el metodo que lo calcula.
Y en la redefinición del metodo calcularCuota se creará una booleana para tener en cuenta si es o no electrica, sumarle 25 euros más.
 */
package CLASES;

/**
 *
 * @author DANIELA RIPOLL CABARGA
 */
public class Moto extends Vehiculo {

    //DECLARACIÓN DE ATRIBUTOS PARA MOTO
    private int potencia;
    private boolean electrica;
    private int factorPot;

    //METODOS GET Y SET
    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public boolean isElectrica() {
        return electrica;
    }

    public void setElectrica(boolean electrica) {
        this.electrica = electrica;
    }

    //Calculo para factor de potencia
    public int calcularfactorPot() {
        if (potencia < 25) {
            factorPot = 1;
        } else if (potencia > 24 && potencia < 51) {
            factorPot = 2;
        } else {
            factorPot = 3;
        }
        return factorPot;

    }

    //CONSTRUCTOR
    public Moto(int potencia, boolean electrica, Conductor conductor, String matricula) {
        super(conductor, matricula);
        this.potencia = potencia;
        this.electrica = electrica;
        this.factorPot = calcularfactorPot();
    }

    //IMPLEMENTACIÓN DE LOS MÉTODOS VEHICULO
    @Override
    public double calcularCuota() {
        double cuota_base = super.calcularCuota();
        double cuota = cuota_base + (cuota_base * 0.04 * factorPot);
        if (electrica) {
            cuota = cuota + 25;
        }
        return cuota;
    }

    @Override
    public void verFicha() {
        super.verFicha();
        System.out.println("DATOS MOTO:");
        System.out.printf("Potencia de la moto %s.\n", potencia);
        if (electrica) {
            System.out.println("Moto electrica.");
        } else {
            System.out.println("Moto no electrica.");
        }
        System.out.printf("Factor de potencia %s.\n", factorPot);
    }
}
