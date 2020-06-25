package importadasFaia;

import java.util.ArrayList;
import java.util.List;

import utils.Regla;

/**
 * Clase que implementa el criterio de especificidad.
 * @author Grupo 12: Blas,María Julia / Diaz Ferreyra,Nicolas/ Sarli, Juan Leonardo.
 */
public class Specificity extends Criteria {

	@Override
	public List<Regla> apply(List<Regla> list) {
		
		int specifity, mayor = 0;		
    	for(Regla r : list)
    	{
    		specifity = r.getSpecificity();
    		if(specifity > mayor) mayor = specifity;
    	}
    	List<Regla> ret = new ArrayList<Regla>();
    	for(Regla r : list)
    	{
    		if(r.getSpecificity() == mayor) ret.add(r);
    	}    	
		return ret;
		
	}

	@Override
	public String toString() {
		return "Specificity (Especificidad)";
	}

}
