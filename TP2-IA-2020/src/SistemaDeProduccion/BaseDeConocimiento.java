package SistemaDeProduccion;

import utils.Regla;

import java.util.List;
import java.util.Set;

public class BaseDeConocimiento {

    private MemoriaDeTrabajo memoriaDeTrabajo;
    private MemoriaDeProduccion memoriaDeProduccion;


    public BaseDeConocimiento(){
        memoriaDeTrabajo = new MemoriaDeTrabajo();
        memoriaDeProduccion = new MemoriaDeProduccion();
    }

    public Set<String> getPalabrasClave(String mensaje){
        return memoriaDeTrabajo.convertir(mensaje);
    }

    public List<Regla> crearReglas(){
        return memoriaDeProduccion.crearReglas();
    }

    public MemoriaDeTrabajo getMemoriaDeTrabajo() {
        return memoriaDeTrabajo;
    }

    public void setMemoriaDeTrabajo(MemoriaDeTrabajo memoriaDeTrabajo) {
        this.memoriaDeTrabajo = memoriaDeTrabajo;
    }

    public MemoriaDeProduccion getMemoriaDeProduccion() {
        return memoriaDeProduccion;
    }

    public void setMemoriaDeProduccion(MemoriaDeProduccion memoriaDeProduccion) {
        this.memoriaDeProduccion = memoriaDeProduccion;
    }
}
