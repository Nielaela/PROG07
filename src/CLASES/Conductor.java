/*
 Clase que instanciará conductores de cada vehiculo registrado.
Contendrá como atributos el nombre y su dni.
 */
package CLASES;

/**
 *
 * @author DANIELA RIPOLL CABARGA
 */
public class Conductor {

    //DECLARACIÓN DE ATRIBUTOS
    private String nombre;
    private String DNI;

    //METODOS GET Y SET
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    //CONSTRUCTORES
    public Conductor() {
    }

    public Conductor(String nombre, String DNI) {
        this.nombre = nombre;
        this.DNI = DNI;
    }

}
