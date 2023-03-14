/*
 Aquí se crearán las validaciones necesarias para la mejora del funcionamiento del programa.
Se definirán validaciones para:
***CLASE CONDUCTOR***
-nombre entre 5 y 15 letras máximo, la primera en mayuscula
-dni validado con mod23.
***CLASE VEHÍCULO***
-matricula validada con formato NNNNLLL, siendo N un digito y L una letra mayuscula.
 */
package VALIDACIONES;

/**
 *
 * @author niela
 */
public class Validar {
    
      private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
    
    public static boolean validarNombre(String nombre){
       return nombre.matches("^[A-Z][a-zA-Z ]{4,14}$");  //con una expresión regular se puede indicar de forma sencilla, donde los primeros corchetes indican la mayuscula, y el resto en minusculas o mayusculas con espacio (por si hay nombre copuesto o apellido), y en las llaves el tamaño que puede tener.
    }
    public static boolean validarDni(String DNI){
        
        if (DNI.length() != 9) {
            return true;
        }
        //Obtención del número del DNI
        String NumeroDNI = DNI.substring(0, (DNI.length() - 1));
        //Obtención de la letra del DNI
        char letraDNI = DNI.charAt(DNI.length() - 1);

        //COMPROBACIÓN DEL NÚMERO DEL DNI
        int numDNI;
        try {
            numDNI = Integer.parseInt(NumeroDNI);   //conversión del dni string a numero integer
        } catch (NumberFormatException e) {
            return true;
        }
        //COMPROBACIÓN DE LA LETRA DEL DNI
        //La excepción saltará cuando la letra no sea alfabetica.
        if (!Character.isAlphabetic(letraDNI)) {
            return true;
        }
        //Calculamos la letra, segun el algoritmo del modulo 23

        char nuevaLetra = LETRAS_DNI.charAt(numDNI % 23);
        // Si la letra inicial, y la obtenida no son iguales, saltará la excepción.
        if (letraDNI != nuevaLetra) {
            return true;
        }

        return false;
    
       
    }
    
     public static boolean validarMatricula(String matricula) {
      
        return matricula.matches("^[0-9]{4}[B|C|D|F|G|H|J|K|L|M|N|P|Q|R|S|T|V|W|X|Y|Z]{3}$");   //con esta expresión regular, se indica que la matricula tiene que tener 4 números (cada uno entre 0 y 9) y 3 letras (desde la A a la Z)
    }
    
}
