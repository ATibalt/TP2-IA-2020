package SistemaDeProduccion;

import utils.Regla;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import agente.EstadoAgente;

public class MemoriaDeProduccion {
    private List<Regla> listaReglas;

    public MemoriaDeProduccion(){
        listaReglas = new ArrayList<Regla>();
    }
    
    public List<Regla> crearReglas(EstadoAgente agState){
    	
    	listaReglas.add(new Regla(Arrays.asList("SALUDO"), "Hola, soy tu asistente COVID-19, si me indicas tus datos puedo darte consejos personalizados." +
    			                                           "\n" + "Cual es tu nombre?", 1, 3, 5, 1));
    	
    	listaReglas.add(new Regla(Arrays.asList("USUARIO", "NOMBRE"), "OK "+agState.getNombreUsuario()+", que edad tenes?", 2, 3, 5, 1));
    	
    	if(agState.getEdadUsuario() < 50) {
    		 listaReglas.add(new Regla(Arrays.asList("USUARIO", "EDAD"), "Muy bien "+agState.getNombreUsuario()+", por tu edad no entras dentro del grupo de pacientes de riesgo, pero de cualquier manera necesitas cuidarte."
    				 													 + "\n" + "¿A que te dedicas?", 3, 3, 5, 1));
    	}	
    	else if(agState.getEdadUsuario() > 50 && agState.getEdadUsuario() < 65) {
    		listaReglas.add(new Regla(Arrays.asList("USUARIO", "EDAD"), "Muy bien "+agState.getNombreUsuario()+", estas cercano a las edades de riesgo, deberias cuidarte lo máximo posible."
    																	 + "\n" + "¿A que te dedicas?", 3, 3, 5, 1));
    	}
    	else if(agState.getEdadUsuario() > 65) {
    		listaReglas.add(new Regla(Arrays.asList("USUARIO", "EDAD"), "Muy bien "+agState.getNombreUsuario()+", por tu edad entras dentro del grupo de pacientes de riesgo, asi que necesitas cuidarte mucho." + "\n" + "No salgas de no ser necesario!", 3, 3, 5, 1));
    	}
    	
        return listaReglas;
    }

}