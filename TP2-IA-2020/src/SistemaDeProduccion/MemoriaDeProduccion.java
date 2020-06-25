package SistemaDeProduccion;

import utils.Regla;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoriaDeProduccion {
    private List<Regla> listaReglas;

    public MemoriaDeProduccion(){
        listaReglas = new ArrayList<Regla>();
    }
    
    public List<Regla> crearReglas(){
    	
    	
    	listaReglas.add(new Regla(Arrays.asList("SALUDO"), "Hola ¿En que puedo ayudarte?", 1, 3, 5, 1));
    	
    	listaReglas.add(new Regla(Arrays.asList("USUARIO", "NOMBRE"), "OK", 2, 3, 5, 1));
    	
        return listaReglas;
    }

}