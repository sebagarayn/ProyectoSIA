//Revisión: 27-09-2025
package exception;

/*Excepción que se lanza cuando se proporcione un rango de valores invalido.*/

public class RangoInvalidoException extends Exception{
    
    public RangoInvalidoException(){ //Constructor por defecto
        super();
    }
    
    public RangoInvalidoException(String mensaje){ //Constructor con mensaje personalizado
        super(mensaje);
    }
    
    public RangoInvalidoException(String mensaje, Throwable causa){ //Constructor con mensaje personalizado y causa
        super(mensaje, causa);
    }
    
    public RangoInvalidoException(Throwable causa){ //Constructor con causa pero sin mensaje personalizado
        super(causa);
    }
}
