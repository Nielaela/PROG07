/*
Subclase coche, una de las dos subclases de la superclase Vehiculo.
Heredará toda la informacion de Vehiculo (atributos, metodos y la misma interfaz que se aplico anteriormente)
 */
package CLASES;

/**
 *
 * @author DANIELA RIPOLL CABARGA
 */
public class Coche extends Vehiculo {

    //DECLARACIÓN DE ATRIBUTOS PARA COCHE
    private int numPuertas;
    private int numPlazas;
    private int factorPot;

    //METODOS GET Y SET
    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    public int getFactorPot() {
        return factorPot;
    }

    public void setFactorPot(int factorPot) {
        this.factorPot = factorPot;
    }

    //CONSTRUCTOR (como es heredado de vehiculo, tambien se podrá generar el constructor con los atributos de vehiculo)
    public Coche(int numPuertas, int numPlazas, int factorPot, Conductor conductor, String matricula) {
        super(conductor, matricula);
        this.numPuertas = numPuertas;
        this.numPlazas = numPlazas;
        this.factorPot = factorPot;
    }

    //IMPLEMENTACIÓN DE LOS METODOS DE VEHICULO REDIFINIENDOLOS (NUEVO CALCULO PARA CUOTA Y TRAER LOS VALORES DE CONDUCTOR DESDE VEHICULO JUNTO CON LOS NUEVOS ATRIBUTOS DE COCHE)
    @Override
    public double calcularCuota() {
        double cuota_base = super.calcularCuota();
        double cuota = (cuota_base + (factorPot * 0.05 * cuota_base) + (numPlazas * 5.25));
        return cuota;
    }
  
    @Override
    public void verFicha() {
        super.verFicha();
        System.out.println("DATOS COCHE:");
        System.out.printf("Número de puertas %s.\n", numPuertas);
        System.out.printf("Número de plazas %s.\n", numPlazas);
        System.out.printf("Factor de potencia %s.\n", factorPot);
    }
   

}
