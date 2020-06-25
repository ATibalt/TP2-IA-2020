package agente;

import java.util.HashSet;
import java.util.Set;

import SistemaDeProduccion.BaseDeConocimiento;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.state.AgentState;
import ambiente.PercepcionChatbot;

public class EstadoAgente extends AgentState {
	
	private Set<String> listaClaves = new HashSet<String>();
	
	public EstadoAgente() {

    }
	
	

	@Override
	public void updateState(Perception p) {
		PercepcionChatbot cbp = (PercepcionChatbot) p;
        BaseDeConocimiento bdc = new BaseDeConocimiento();

        listaClaves.clear();
        listaClaves = bdc.getPalabrasClave(cbp.getMensaje());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initState() {
		// TODO Auto-generated method stub

	}

	public Set<String> getListaClaves() {
		return listaClaves;
	}



	public void setListaClaves(Set<String> listaClaves) {
		this.listaClaves = listaClaves;
	}

}
