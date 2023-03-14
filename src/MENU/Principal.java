/*
 Clase principal, es la main de todo el programa. Aquí se ejecutará el menu con el que se podrán registrar los vehiculos, eliminarlos del parking, además de visualizarlos.
Necesitará un atributo de tipo array que guardará en orden los vehiculos.
Se definiran varios métodos que, junto con el array, podremos regitrar vehiculos, eliminarlos y comprobar sus fichas (visualizar parking).
Los metodos son:
-anadirVehiculo: Incluye la petición de datos y validación de los mismos. Dará a elegir tipo de vehículo a crear (coche o moto), creando el objeto y añadiéndolo al parking, no
permitiendo matrículas repetidas. Se buscará la primera plaza libre para añadir el vehículo siguiendo el orden de filas y columnas.
-visualizarParking: Recorre el array mostrando los datos de cada vehículo, llamando al método verFicha. Se debe indicar además la plaza que ocupa el vehículo
-elimianrVehiculo: Pide matrícula del vehículo a borrar dejando su plaza libre
 */
package MENU;

import CLASES.Coche;
import CLASES.Conductor;
import CLASES.Moto;
import CLASES.Vehiculo;
import VALIDACIONES.Validar;
import java.lang.invoke.SerializedLambda;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author DANIELA RIPOLL CABARGA
 */
public class Principal {

    //DECLARACIÓN DE ATRIBUTOS
    private static Vehiculo[][] parking = new Vehiculo[5][5];
    //Como el parking tiene un maximo de vehiculos, 25 se va a crear un atributo que servirá de contador cuando se agregen nuevos vehiculos o si se eliminan restarlos.
    //De esta forma si el parking esta lleno, con un if podremos mandar un aviso de que esta lleno y no se pueden registrar mas.
    private static int nVehicParking = 0;

    public static void main(String[] args) {

        //DECLARACIÓN DE VARIABLES, PARA EL MENU
        Scanner teclado = new Scanner(System.in);
        boolean salida;
        salida = true;
        int menu = 0;

        while (salida) {

            try {
                System.out.println();
                System.out.println("--------------------------");
                System.out.println("PARKING");
                System.out.println("1. Añadir vehículo");
                System.out.println("2. Visualizar parking");
                System.out.println("3. Eliminar vehículo");
                System.out.println("0. Salir");
                System.out.println("Introduce una opción");
                System.out.println("--------------------------");

                menu = teclado.nextInt();

                switch (menu) {

                    case 1:
                        if (nVehicParking == 25) {
                            System.out.println("El parking está lleno, si quiere registrar un nuevo vehículo debe eliminar uno antes.");
                        } else {
                            System.out.println("1. AÑADIR VEHICULO");
                            anadirVehiculo();
                        }
                        break;

                    case 2:
                        System.out.println("2. VISUALIZAR PARKING");
                        visualizarParking();
                        break;

                    case 3:
                        System.out.println("3. ELIMINAR VEHICULO");
                        eliminarVehiculo();
                        break;

                    case 0:
                        System.out.println("0. SALIR");
                        System.out.println("Ha salido de la aplicación");
                        salida = false;
                        break;

                    default:
                        System.out.println("Elige entre una opciónes indicadas.");
                        System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("Pon un numero, no una letra");
                teclado.next();
            } catch (Exception o) {
                System.out.println(o.getMessage());
            }

        }

    }

    //METODOS DE LA CLASE PRINCIPAL
    //METODO COMPROBACIÓN MATRICULA. Se usará para comprobar si ya existe registrado un vehiculo con la matricula escrita.
    //Se utilizará a la hora de querer registrar un vehiculo nuevo o para eliminarlo.
    public static boolean comprobarMatricula(String matricula) {
        for (int f = 0; f < 5; f++) {
            for (int c = 0; c < 5; c++) {

                if (parking[f][c] != null && parking[f][c].getMatricula().equals(matricula)) {
                    return true;
                }
            }
        }
        return false;
    }

    //METODO AÑADIR VEHÍCULO.
    //Método en el que se irán solicitando la entrada de datos a registrar y con ello crear las instancias de conductores y el tipo de vehiculo asignado (coche, moto) con las caracteristicas que sean.
    //Para "guardar" el vehiculo en la matriz, al final de cada tipo de vehiculo con dos bucles for que recorran la matriz por filas y columnas y junto la condición de que no exista vehiculo y una booleana se registrará en el primer "hueco" libre.
    //Se creará un contador de numero de vehiculos para tener mejor control.
    public static void anadirVehiculo() {
        //VARIABLES con las que voy a identificar las entradas de datos.
        int tipoVehiculo = 0;

        String matricula, nombre, DNI = "";
        int nPuertas = 0;
        int nPlazas = 0;
        int factorPot = 0;
        int potencia = 0;
        boolean electrica = true;
        String esElectrica = "";

        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce la matrícula del vehículo");
        try {
            matricula = teclado.nextLine();
            matricula = matricula.toUpperCase();
            if (!Validar.validarMatricula(matricula)) {
                throw new Exception("La matrícula introducida no es correcta, pruebe de nuevo");
            }
            //utilizo método de comprobar matricula
            if (comprobarMatricula(matricula)) {
                System.out.println("Ya existe un vehículo registrado con la matricula " + matricula);
            } else {

                System.out.println("Introduce los datos del conductor:");
                System.out.println("Nombre:");
                nombre = teclado.nextLine();
                if (!Validar.validarNombre(nombre)) {
                    throw new Exception("El nombre introducido no es correcto, tiene que empezar por mayuscula y tener un tamaño de entre 5 y 15 letras");
                }

                System.out.println("DNI:");
                DNI = teclado.nextLine();
                DNI = DNI.toUpperCase();
                if (Validar.validarDni(DNI)) {
                    throw new Exception("El DNI no es correcto, pruebe de nuevo");
                }
                //Creamos la instancia de conductor con el constructor de su clase
                Conductor conductor = new Conductor(nombre, DNI);
                //Codigo para guardar los datos del vehiculo
                System.out.println("Indica el tipo de vehículo a añadir: (1) coche o (2) moto");
                tipoVehiculo = teclado.nextInt();
                if (tipoVehiculo == 1) {
                    System.out.println("VEHICULO TIPO COCHE SELECCIONADO");
                    System.out.println("\nINGRESE SUS DATOS");
                    System.out.println("*******************");
                    do {
                        System.out.println("Indica el número de puertas (entre 2 y 5)");
                        nPuertas = teclado.nextInt();
                    } while (nPuertas < 2 || nPuertas > 5);
                    do {
                        System.out.println("Indica el número de plazas (entre 1 y 9)");
                        nPlazas = teclado.nextInt();
                    } while (nPlazas < 1 || nPlazas > 9);
                    do {
                        System.out.println("Indica el factor de potencia del vehículo (entre 1 y 5)");
                        factorPot = teclado.nextInt();
                    } while (factorPot < 1 || factorPot > 5);

                    //Creamos la instancia de coche con el constructor de la clase.
                    Coche coche = new Coche(nPuertas, nPlazas, factorPot, conductor, matricula);
                    //Añadimos el vehiculo al array de parking.
                    boolean salida = true;
                    for (int f = 0; f < 5; f++) {
                        for (int c = 0; c < 5; c++) {
                            if ((parking[f][c] == null) && salida) {
                                parking[f][c] = coche;
                                System.out.println("Vehículo tipo coche registrado correctamente en la plaza(" + f + "" + c + ").");
                                salida = false;
                                nVehicParking++;
                            }
                        }

                    }

                } else if (tipoVehiculo == 2) {
                    System.out.println("VEHICULO TIPO MOTO SELECCIONADO");
                    System.out.println("\nINGRESE SUS DATOS");
                    System.out.println("*******************");
                    System.out.println("Indica la potencia");
                    potencia = teclado.nextInt();
                    System.out.println("Indica si es electrica. (SI/NO)");
                    esElectrica = teclado.next();
                    esElectrica = esElectrica.toUpperCase();
                    while (!((esElectrica.equals("SI")) || (esElectrica.equals("NO")))) {

                        System.out.println("Eliga una opción correcta");
                        esElectrica = teclado.next();
                        esElectrica = esElectrica.toUpperCase();
                    }

                    if (esElectrica.equals("SI")) {
                        electrica = true;
                    } else {
                        electrica = false;

                    }

                    //Creamos la instancia de moto con el constructor de la clase.
                    Moto moto = new Moto(potencia, electrica, conductor, matricula);

                    //Añadimos el vehiculo al array de parking.
                    boolean salida = true;
                    for (int f = 0; f < 5; f++) {
                        for (int c = 0; c < 5; c++) {
                            if ((parking[f][c] == null) && salida) {
                                parking[f][c] = moto;
                                System.out.println("Vehículo tipo moto registrado correctamente(" + f + "," + c + ").");
                                salida = false;
                                nVehicParking++;
                            }
                        }

                    }

                } else {
                    System.out.println("Debe seleccionar un tipo de vehículo: (1) coche o (2) moto");
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Pon un numero, no una letra");
            teclado.next();
        } catch (Exception o) {
            System.out.println(o.getMessage());
        }

    }

//METODO VISUALIZAR PARKING
//De la misma forma que al añadir vehiculo, con los bucles for recorrerá las posiciones de los vehiculos y cuando se compruebe que hay vehiculo realizará el metodo verFicha para ese tipo de vehiculo.
//El método verFicha al estar definido como interfaz y luego redefinido en la super clase y en sus subclases moto y coche, no es necesario "llamar" a cada instancias de estas subclases,
//Con realizarlo sobre el array de parking que es de tipo Vehiculo, este podrá mostrar el método verFicha de cualquier instancia que se encuentre dentro que haya heredado de Vehiculo (moto y coche)
    public static void visualizarParking() {

        System.out.println("NÚMERO DE VEHICULOS EN EL PARKING REGISTRADOS: " + nVehicParking);

        for (int f = 0; f < 5; f++) {
            for (int c = 0; c < 5; c++) {

                if (parking[f][c] != null) {
                    parking[f][c].verFicha();
                    System.out.println("Plaza parking: " + f + "" + c + ".");
                    System.out.println("*************************");
                    System.out.println("NÚMERO DE VEHICULOS EN EL PARKING REGISTRADOS: " + nVehicParking);
                }
            }
        }
    }

    //METODO ELIMINAR VEHICULO
    //Igual que el metodo añadir, solo que en este caso, cuando encuentre la posición de la instancia en la matriz correspondiente a la matricula introducida, convertirá ese objeto a null.
    //Tras cada eliminación se descontará un coche del total.
    public static void eliminarVehiculo() {
        //VARIABLES
        Scanner teclado = new Scanner(System.in);
        String matricula = "";

        String plazaParking = "";

        System.out.println("Introduce la matrícula del vehículo que desea eliminar");
        matricula = teclado.nextLine();
        matricula = matricula.toUpperCase();
        if (comprobarMatricula(matricula)) {
            for (int f = 0; f < 5; f++) {
                for (int c = 0; c < 5; c++) {
                    if ((parking[f][c] != null) && (parking[f][c].getMatricula().equals(matricula))) {

                        plazaParking = String.valueOf(f) + String.valueOf(c);
                        System.out.println("Plaza " + f + "" + c + ".");

                        System.out.println("El vehículo con matrícula " + matricula + " perteneciente a la plaza " + plazaParking + " se ha eliminado su registro.");

                        parking[f][c] = null;
                        nVehicParking--;
                    }
                }
            }

        } else {
            System.out.println("La matrícula introducida no existe en el registro del parking.");
        }

    }
}
