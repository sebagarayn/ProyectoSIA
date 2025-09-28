package exception;

/*Excepcion para cuando se ingresa un RUT que no tenga el formato requrido, es
decir XX.XXX.XXX-X o XXXXXXXX-X*/

public class FormatoRUTInvalidoException extends Exception {
    
    public FormatoRUTInvalidoException(){ //Constructor por defecto con mensaje generico sobre el formato del RUT invalido.
        super("El formato del RUT no es válido");
    }
    
    public FormatoRUTInvalidoException(String mensaje){ //Constructor con mensaje personalizado
        super(mensaje);
    }
    
    public FormatoRUTInvalidoException(String mensaje, Throwable causa){ //Constructor con mensaje personalizado y causa original
        super(mensaje, causa);
    }
    
    public FormatoRUTInvalidoException(Throwable causa){ //Constructor solo con causa
        super(causa);
    }
}
