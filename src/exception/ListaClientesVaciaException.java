package exception;

/*Excepción personalizada para cuando la lista de clientes está vacía.
Se lanza al intentar realizar operaciones que requieren al menos un cliente*/

public class ListaClientesVaciaException extends Exception { //Constructor con mensaje personalizado
    public ListaClientesVaciaException(String mensaje) {
        super(mensaje);
    }     
}
