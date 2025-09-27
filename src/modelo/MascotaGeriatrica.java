package modelo;

public class MascotaGeriatrica extends Mascota {
    private boolean requiereAnalisisRegulares;
    private String fechaInicioGeriatria;
    private String medicamentosHabituales;
    
    public MascotaGeriatrica(String nombre, String tipo, String raza, int edad, String fechaInicioGeriatria){
        super(nombre, tipo, raza, edad);
        this.fechaInicioGeriatria = fechaInicioGeriatria;
        this.requiereAnalisisRegulares = true;
        this.medicamentosHabituales = "Ninguno registrado";
    }
    
    @Override
    public String obtenerCuidadosEspeciales(){
        String cuidados = "Cuidados geriátricos especiales: ";
        cuidados += "- Chequeos cardíacos regulares, ";
        cuidados += "- Dieta senior baja en sodio, ";
        cuidados += "- Ejercicio moderado y controlado";
        cuidados += "- Revisión articular para artritis";
        return cuidados;
    }
    
    @Override
    public int obtenerFrecuenciaVisitas(){
        return 3;
    }
    
    @Override
    public String obtenerRecomendaciones() {
        String recomendaciones = "Recomendaciones geriátricas: ";
        recomendaciones += "- Análisis de sangre trimestral, ";
        recomendaciones += "- Suplementos articulares, ";
        recomendaciones += "- Ambiente cálido y cómodo, ";
        if(!medicamentosHabituales.equals("Ninguno registrado")){
            recomendaciones += "- Continuar con medicamentos: " + medicamentosHabituales;
        }
        else{
            recomendaciones += "- Evaluación para medicamentos preventivos";
        }
        return recomendaciones;
    }
    
    @Override
    public double calcularFactorPrecio() {
        return 0.90; //10% de descuento
    }
    
    public boolean isRequiereAnalisisRegulares(){
        return requiereAnalisisRegulares;
    }
    
    public String getFechaInicioGeriatria(){
        return fechaInicioGeriatria;
    }
    
    public String getMedicamentosHabituales() {
        return medicamentosHabituales;
    }
    
    public void setMedicamentosHabituales(String medicamentos){
        this.medicamentosHabituales = medicamentos;
    }
}
