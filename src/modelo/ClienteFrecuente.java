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
    public String obtenerTipoCliente(){
        return "Cliente Frecuente (" + numeroVisitasAnuales + " visitas anuales)";
    }
    
    @Override
    public double calcularDescuento(){
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
    public String obtenerBeneficios(){
        String beneficios = "Beneficios Cliente Frecuente: ";
        beneficios += "- Descuento del " + (calcularDescuento() * 100) + "%";
        beneficios += "- Consulta telefónica gratuita";
        if(tienePlanFidelidad){
            beneficios += "- Plan fidelidad ACTIVO";
        }
        return beneficios;
    }
    
//================GET/SET===================
    public int getNumeroVisitasAnuales() {
        return numeroVisitasAnuales;
    }
    
    public void setNumeroVisitasAnuales(int visitas){
        this.numeroVisitasAnuales = visitas;
        this.tienePlanFidelidad = visitas >= 6;
    }
    
    public String getFechaUltimaVisita() {
        return fechaUltimaVisita;
    }
    
    public void setFechaUltimaVisita(String fecha){
        this.fechaUltimaVisita = fecha;
    }
    
    public boolean isTienePlanFidelidad() {
        return tienePlanFidelidad;
    }
    
    public void registrarNuevaVisita(String fecha){
        this.fechaUltimaVisita = fecha;
        this.numeroVisitasAnuales ++;
        this.tienePlanFidelidad = numeroVisitasAnuales >= 6;
    }
}
