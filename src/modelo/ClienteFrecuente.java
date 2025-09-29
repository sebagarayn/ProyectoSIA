package modelo;

public class ClienteFrecuente extends Cliente {
    private int numeroVisitasAnuales;
    private String fechaUltimaVisita;
    private boolean tienePlanFidelidad;
    
    public ClienteFrecuente(String nombre, String rut, String telefono, String direccion, int numeroVisitasAnuales, String fechaUltimaVisita){
        super(nombre, rut, telefono, direccion);
        this.numeroVisitasAnuales = numeroVisitasAnuales;
        this.fechaUltimaVisita = fechaUltimaVisita;
        this.tienePlanFidelidad = numeroVisitasAnuales >= 6;
    }
    
    @Override
    public String obtenerTipoCliente(){ //Para obtener el tipos de cliente
        return "Cliente Frecuente (" + numeroVisitasAnuales + " visitas anuales)";
    }
    
    @Override
    public double calcularDescuento(){ //Para calcular el descuento segun el numero de visitas
        if(numeroVisitasAnuales >= 12){
            return 0.18; //Descuento del 18%
        }
        if(numeroVisitasAnuales >= 8){
            return 0.15; //Descuento del 15%
        }
        if(numeroVisitasAnuales >= 6){
            return 0.12; //Descuento del 12%
        }
        return 0.05; //Descuento minimo 5%
    }
    
    @Override
    public String obtenerBeneficios(){ //Se obtiene los beneficios de un cliente frecuente
        String beneficios = "Beneficios Cliente Frecuente: ";
        beneficios += "- Descuento del " + (calcularDescuento() * 100) + "%";
        beneficios += "- Consulta telefónica gratuita";
        if(tienePlanFidelidad){
            beneficios += "- Plan fidelidad ACTIVO";
        }
        return beneficios;
    }
    
//================GET/SET===================
    public int getNumeroVisitasAnuales() { ///Para obtener el numero de visitas anuales
        return numeroVisitasAnuales;
    }
    
    public void setNumeroVisitasAnuales(int visitas){ //Para settear el numero de visitas anuales
        this.numeroVisitasAnuales = visitas;
        this.tienePlanFidelidad = visitas >= 6;
    }
    
    public String getFechaUltimaVisita() { //Para obtener la fecha de la ultima visita
        return fechaUltimaVisita;
    }
    
    public void setFechaUltimaVisita(String fecha){ //Para settear la fecha de la ultima visita
        this.fechaUltimaVisita = fecha;
    }
    
    public boolean isTienePlanFidelidad() { //Para determinar si el cliente tiene plan de fidelidad (Frecuente)
        return tienePlanFidelidad;
    }
    
    public void registrarNuevaVisita(String fecha){ //Para registrar una nueva visita
        this.fechaUltimaVisita = fecha;
        this.numeroVisitasAnuales ++;
        this.tienePlanFidelidad = numeroVisitasAnuales >= 6;
    }
}
