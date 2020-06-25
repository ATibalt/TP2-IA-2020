package importadasFaia;

import java.util.ArrayList;
import java.util.List;

import utils.Regla;

/**
 * Clase que implementa el criterio de novedad.
 * @author Grupo 12: Blas,María Julia / Diaz Ferreyra,Nicolas/ Sarli, Juan Leonardo.
 */
public class Novelty extends Criteria {

	@Override
	public List<Regla> apply(List<Regla> list) {

		int novelty, mayor = 0;	
		
    	for(Regla r : list)
    	{
    		novelty = r.getNovelty();
    		if(novelty > mayor) mayor = novelty;
    	}
    	
    	List<Regla> ret = new ArrayList<Regla>();
    	
    	for(Regla r : list)
    	{
    		if(r.getNovelty() == mayor) ret.add(r);
    	}
    	
		return ret;
		
	}

	@Override
	public String toString() {
		return "Novelty (Novedad)";
	}

}
