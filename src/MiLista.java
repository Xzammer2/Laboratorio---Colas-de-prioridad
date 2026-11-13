public class MiLista {
    Nodo cabeza;

    public MiLista() {
        this.cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    // Método sencillo para insertar manteniendo el orden de urgencia y llegada
    public void insertarOrdenado(String paciente) {
        Nodo nuevo = new Nodo(paciente);

        int prioridadNueva = Urgencias.prioridadDe(paciente);
        int ordenNuevo = Urgencias.ordenDe(paciente);

        // Si la lista está vacía o el nuevo va antes que la cabeza
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            int priCabeza = Urgencias.prioridadDe(cabeza.dato);
            int ordCabeza = Urgencias.ordenDe(cabeza.dato);

            if (prioridadNueva < priCabeza || (prioridadNueva == priCabeza && ordenNuevo < ordCabeza)) {
                nuevo.siguiente = cabeza;
                cabeza = nuevo;
            } else {
                // Buscamos en dónde debe ir ubicado
                Nodo actual = cabeza;
                while (actual.siguiente != null) {
                    int priSig = Urgencias.prioridadDe(actual.siguiente.dato);
                    int ordSig = Urgencias.ordenDe(actual.siguiente.dato);

                    if (prioridadNueva < priSig || (prioridadNueva == priSig && ordenNuevo < ordSig)) {
                        break;
                    }
                    actual = actual.siguiente;
                }
                nuevo.siguiente = actual.siguiente;
                actual.siguiente = nuevo;
            }
        }
    }

    public String eliminarPrimero() {
        if (cabeza == null) {
            return null;
        }
        String temp = cabeza.dato;
        cabeza = cabeza.siguiente;
        return temp;
    }
}