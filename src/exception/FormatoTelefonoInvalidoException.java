package exception;

public class FormatoTelefonoInvalidoException extends Exception {
    
    public FormatoTelefonoInvalidoException() {
        super("El formato del teléfono no es válido");
    }
    
    public FormatoTelefonoInvalidoException(String mensaje) {
        super(mensaje);
    }
    
    public FormatoTelefonoInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    
    public FormatoTelefonoInvalidoException(Throwable causa) {
        super(causa);
    }
}