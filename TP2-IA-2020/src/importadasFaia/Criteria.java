package importadasFaia;

import java.util.List;

import utils.Regla;

/**
 * Clase que engloba el comportamiento de los criterios del sistema de produccion.
 * @author Grupo 12: Blas,María Julia / Diaz Ferreyra,Nicolas/ Sarli, Juan Leonardo.
 */
public abstract class Criteria {	

	public abstract List<Regla> apply(List<Regla> list);
	public abstract String toString();
	
}
