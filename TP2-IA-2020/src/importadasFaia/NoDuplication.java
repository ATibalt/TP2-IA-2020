package importadasFaia;

import java.util.ArrayList;
import java.util.List;

import SistemaDeProduccion.MaquinaDeInferencia;
import utils.Regla;

/**
 * Clase que implementa el criterio de no duplicacion.
 * @author Grupo 12: Blas,María Julia / Diaz Ferreyra,Nicolas/ Sarli, Juan Leonardo.
 */
public class NoDuplication extends Criteria {
	
	@Override
	public List<Regla> apply(List<Regla> lista) {
			
		//la lista list contiene las posibles reglas activas para poder aplicar
		List<Regla> ret = new ArrayList<Regla>();
        
        for(Regla r: lista){
            //Como es No duplicidad, se tiene que analizar si la regla no fue utilizada con anterioridad
            if(!used(r)){
                ret.add(r);
            }
        }
        
        //Si no hay otra posible respuesta se debe borrar la lista de usadas para poder usar nuevamente
        //las reglas ya usadas
        if(ret.isEmpty()){
            MaquinaDeInferencia.reglasUsadas.removeAll(lista);
            return lista;
        }
        return ret;
		
	}

	@Override
	public String toString() {	
		return "No Duplication (No duplicación)";
	}

	private boolean used(Regla r){
		for(Regla i : MaquinaDeInferencia.reglasUsadas){
            if(r.getId()==i.getId()){
                return true;
            }
        }
        return false;
	}
}
