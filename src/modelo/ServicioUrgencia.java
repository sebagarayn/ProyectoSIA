package modelo;

public class ServicioUrgencia extends Servicio{
    private int nivelUrgencia;
    private String motivoUrgencia;
    private boolean requiereAtencionInmediata;
    
    public ServicioUrgencia(String tipo, String fecha, String hora, String descripcion, int precio, String estado, int nivelUrgencia, String motivoUrgencia, boolean requiereAtencionInmediata){
        super(tipo, fecha, hora, descripcion, precio, estado);
        this.nivelUrgencia = nivelUrgencia;
        this.motivoUrgencia = motivoUrgencia;
        this.requiereAtencionInmediata = requiereAtencionInmediata;
    }
    
    @Override
    public String obtenerTipoServicio(){ //Se obtiene el tipo de servicio, en este caso para los de urgencia
        return "URGENCIA - " + getTipoServicio() + " (Nivel " + nivelUrgencia + ")";
    }
    
    @Override
    public int calcularPrecioFinal(){ //Para calcular el precio final, a partir de un recargo segun el nivel de urgencia
        double recargo;
        switch(nivelUrgencia){
            case 5: recargo = 1.75; break;
            case 4: recargo = 1.60; break;
            case 3: recargo = 1.45; break;
            case 2: recargo = 1.30; break;
            default: recargo = 1.15; break;
        }
        return (int)(getPrecio()*recargo);
    }
    
    @Override
    public String obtenerInstruccionesEspeciales(){ //Se obtienen las instrucciones de un servicio de urgencia
        String instrucciones = "PROTOCOLO DE URGENCIA: ";
        if(nivelUrgencia >= 4){
            instrucciones += "- Atención inmediata requerida, ";
        }
        instrucciones += "- Motivo: " + motivoUrgencia + ", ";
        instrucciones += "- Preparar equipo de emergencia, ";
        if(requiereAtencionInmediata){
            instrucciones += "- Saltar lista de espera";
        }
        else{
            instrucciones += "- Priorizar en lista de espera";
        }
        return instrucciones;
    }   
    
    public int getNivelUrgencia() {return nivelUrgencia;}
    public void setNivelUrgencia(int nivel){this.nivelUrgencia = nivel;}
    public String getMotivoUrgencia() {return motivoUrgencia;}
    public void setMotivoUrgencia(String motivo) {this.motivoUrgencia = motivo;}
    public boolean isRequiereAtencionInmediata() {return requiereAtencionInmediata;}
    public void setRequiereAtencionInmediata(boolean requiere){this.requiereAtencionInmediata = requiere;}
    
    public boolean esEmergenciaCritica(){
        return nivelUrgencia >= 4 && requiereAtencionInmediata;
    }
}
