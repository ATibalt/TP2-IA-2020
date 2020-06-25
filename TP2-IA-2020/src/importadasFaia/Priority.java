package importadasFaia;

import java.util.ArrayList;
import java.util.List;

import utils.Regla;

/**
 * Clase que implementa el criterio de prioridad.
 * @author Grupo 12: Blas,María Julia / Diaz Ferreyra,Nicolas/ Sarli, Juan Leonardo.
 */
public class Priority extends Criteria {

	@Override
	public List<Regla> apply(List<Regla> list) {

		int priority, mayor = 0;		
    	for(Regla r : list)
    	{
    		priority = r.getPriority();
    		if(priority > mayor) mayor = priority;
    	}
    	List<Regla> ret = new ArrayList<Regla>();
    	for(Regla r : list)
    	{
    		if(r.getPriority() == mayor) ret.add(r);
    	}    	
		return ret;
		
	}

	@Override
	public String toString() {
		return "Priority (Prioridad)";
	}

}
