package exception;

/*Excepción personalizada para validar formatos de teléfono
Se lanza cuando un teléfono no cumple con el formato esperado*/

public class FormatoTelefonoInvalidoException extends Exception {
    
    public FormatoTelefonoInvalidoException() { //Constructor por defecto con mensaje estándar
        super("El formato del teléfono no es válido");
    }
    
    public FormatoTelefonoInvalidoException(String mensaje) { //Constructor con mensaje personalizado
        super(mensaje);
    }
    
    public FormatoTelefonoInvalidoException(String mensaje, Throwable causa) { //Constructor con mensaje personalizado y causa de la excepción
        super(mensaje, causa);
    }
    
    public FormatoTelefonoInvalidoException(Throwable causa) { //Constructor solo con la causa de la excepción
        super(causa);
    }
}