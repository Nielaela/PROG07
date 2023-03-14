/*
 Superclase vehículo, no creará objetos (no instanciable) se tratará de una clase abstracta de la que las subclases Moto y Coche heredarán sus atributos y metodos.
Los atributos de la superclase Vehiculo son la clase conductor y la matricula del vehiculo.
Queremos utilizar la interfaz definida Mostrable, para ello en la declaración de la clase se añade con "implements".
Los metodos serán verFicha, que será redefinida de la interfaz y en ella se mostrarán los datos del conductor, la matricula y la cuota a pagar.
Y el metodo calcularCuota que también será redefinida de la interfaz. Devolverá el valor de la cuota basica por vehiculo que es de 275€
 */
package CLASES;

import INTERFACES.Mostrable;

/**
 *
 * @author DANIELA RIPOLL CABARGA
 */
public abstract class Vehiculo implements Mostrable {

    //DECLARACIÓN DE ATRIBUTOS
    private Conductor conductor;
    private String matricula;

    //METODOS GET Y SET
    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    //CONSTRUCTORES
    public Vehiculo(Conductor conductor, String matricula) {
        this.conductor = conductor;
        this.matricula = matricula;
    }

    //REDEFINICIÓN DE LOS METODOS DE LA INTERFAZ MOSTRABLE
    @Override
    public double calcularCuota() {
        return 275;
    }

    @Override
    public void verFicha() {
        System.out.println("\n*****FICHA VEHÍCULO*****");
        System.out.printf("MATRÍCULA VEHÍCULO: %s.\n", matricula);
        System.out.printf("CONDUCTOR: %s.\n", conductor.getNombre());
        System.out.printf("DNI: %s.\n", conductor.getDNI());
        System.out.println("CUOTA DEL VEHÍCULO: " + calcularCuota() + "€");
        

    }

}
