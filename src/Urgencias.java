/* =====================================================================
 *  EXAMEN PARCIAL 1 - ESTRUCTURAS DE DATOS
 *  Escenario A - "Sala de Urgencias"
 * =====================================================================
 *
 *  JUSTIFICACION DE LA ESTRUCTURA DE DATOS ELEGIDA
 *
 *  Estructura elegida .....: Lista Simplemente Enlazada
 *  Laboratorio de origen ..: ( 01 )
 *
 *  Por que esta estructura y no otra?
 *  - Elegimos esta lista simple porque es la estructura básica que vimos
 *    en el primer laboratorio y la entendemos bien para manejar nodos.
 *  - Nos sirve para guardar a los pacientes uno tras otro en orden.
 *    Así, cuando llega alguien nuevo, lo acomodamos de una vez según su
 *    prioridad para que el más grave quede siempre de primero.
 *  - De esta forma, cuando el médico va a atender o a mirar la sala,
 *    el paciente que está de primero en la lista es siempre el que le
 *    toca pasar, haciendo que el programa sea más fácil de programar
 *    sin complicarnos con métodos avanzados.
 * ===================================================================== */

import java.util.Scanner;

public class Urgencias {

    static final String SEP = ";";
    int contadorLlegadas = 0;

    MiLista sala = new MiLista();

    public void llega(String nombre, int prioridad) {
        String paciente = nombre + SEP + prioridad + SEP + contadorLlegadas;
        contadorLlegadas++;
        sala.insertarOrdenado(paciente);
    }

    public String estado() {
        if (sala.estaVacia()) {
            return "SALA_VACIA";
        }

        StringBuilder resultado = new StringBuilder("ESPERANDO: ");
        Nodo actual = sala.cabeza;
        boolean primero = true;

        while (actual != null) {
            String p = actual.dato;
            if (!primero) {
                resultado.append(", ");
            }
            resultado.append(nombreDe(p)).append("(p").append(prioridadDe(p)).append(")");
            primero = false;
            actual = actual.siguiente;
        }

        return resultado.toString();
    }

    public String atender() {
        if (sala.estaVacia()) {
            return "SIN_PACIENTES";
        }

        String p = sala.eliminarPrimero();
        return "ATENDIDO: " + nombreDe(p) + " (prioridad " + prioridadDe(p) + ")";
    }

    static String nombreDe(String paciente) {
        return paciente.split(SEP)[0];
    }

    static int prioridadDe(String paciente) {
        return Integer.parseInt(paciente.split(SEP)[1]);
    }

    static int ordenDe(String paciente) {
        return Integer.parseInt(paciente.split(SEP)[2]);
    }

    public static void main(String[] args) {
        Urgencias sistema = new Urgencias();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println();
            System.out.println("===== SALA DE URGENCIAS =====");
            System.out.println("1. Llega un paciente");
            System.out.println("2. Ver estado de la sala");
            System.out.println("3. Atender al siguiente paciente");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");

            String opcion = sc.hasNextLine() ? sc.nextLine().trim() : "0";

            switch (opcion) {
                case "1": {
                    System.out.print("Nombre: ");
                    String nombre = sc.hasNextLine() ? sc.nextLine().trim() : "";
                    System.out.print("Prioridad (1-5): ");
                    String raw = sc.hasNextLine() ? sc.nextLine().trim() : "";

                    int prioridad;
                    try {
                        prioridad = Integer.parseInt(raw);
                    } catch (NumberFormatException e) {
                        System.out.println("ENTRADA_INVALIDA");
                        break;
                    }
                    if (nombre.isEmpty() || nombre.contains(SEP)
                            || prioridad < 1 || prioridad > 5) {
                        System.out.println("ENTRADA_INVALIDA");
                        break;
                    }
                    sistema.llega(nombre, prioridad);
                    break;
                }
                case "2":
                    System.out.println(sistema.estado());
                    break;
                case "3":
                    System.out.println(sistema.atender());
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("OPCION_INVALIDA");
            }
        }

        sc.close();
    }
}