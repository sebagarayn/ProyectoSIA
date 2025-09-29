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
    public String obtenerCuidadosEspeciales(){ //Se obtienen los cuidados especiales de una mascota geriatrica
        String cuidados = "Cuidados geriátricos especiales: ";
        cuidados += "- Chequeos cardíacos regulares, ";
        cuidados += "- Dieta senior baja en sodio, ";
        cuidados += "- Ejercicio moderado y controlado";
        cuidados += "- Revisión articular para artritis";
        return cuidados;
    }
    
    @Override
    public int obtenerFrecuenciaVisitas(){ //Se obtiene la frecuencia de visitas, que debiera ser de 3, en vez de 6 (regular)
        return 3;
    }
    
    @Override
    public String obtenerRecomendaciones() { //Obtener las recomendaciones relacionadas a las mascotas geriatricas
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
    public double calcularFactorPrecio() { //Se calcular el factor de precio, en este caso un descuento para las mascotas geriatricas
        return 0.90; //10% de descuento
    }
    
    public boolean isRequiereAnalisisRegulares(){ //Determinar si requiere de analisis regulares
        return requiereAnalisisRegulares;
    }
    
    public String getFechaInicioGeriatria(){ //Obtener fecho de inicio de geriatria
        return fechaInicioGeriatria;
    }
    
    public String getMedicamentosHabituales() { //Obtener los medicamentos habituales (AUN NO IMPLEMENTADO DEL TODO)
        return medicamentosHabituales;
    }
    
    public void setMedicamentosHabituales(String medicamentos){ //Settear los medicamentos habituales (AUN NO IMPLEMENTADO DEL TODO)
        this.medicamentosHabituales = medicamentos;
    }
}
