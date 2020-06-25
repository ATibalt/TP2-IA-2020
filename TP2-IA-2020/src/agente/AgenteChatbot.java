package agente;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import SistemaDeProduccion.MaquinaDeInferencia;
import SistemaDeProduccion.Problema;
import SistemaDeProduccion.BaseDeConocimiento;
import importadasFaia.Criteria;
import importadasFaia.NoDuplication;
import importadasFaia.Novelty;
import importadasFaia.Priority;
import importadasFaia.Random;
import importadasFaia.Specificity;
import utils.Regla;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.state.AgentState;

public class AgenteChatbot extends Agent {
	
	EstadoAgente agState;
    List<Criteria> listaCriterios;
    List<Regla> listaReglas;
    MaquinaDeInferencia mdi;
    Problema problema;

    public AgenteChatbot(){

        agState = new EstadoAgente();
        this.setAgentState(agState);

        listaCriterios = new ArrayList<Criteria>();
        listaCriterios.add(new NoDuplication());
        listaCriterios.add(new Priority());
        listaCriterios.add(new Specificity());
        listaCriterios.add(new Novelty());
        listaCriterios.add(new Random());

        listaReglas = new ArrayList<Regla>();
        cargarReglas();

        mdi = new MaquinaDeInferencia(listaCriterios, listaReglas);

        problema = new Problema();
    }
    
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }
    
    public Action learn() throws Exception{

        Action selectedAction = null;
        problema.setPalabrasClaves(agState.getListaClaves());

        try {
            selectedAction = mdi.solve(new Object[]{problema});
        } catch (Exception ex) {
            Logger.getLogger(AgenteChatbot.class.getName(), null).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;
    }

    public AgentState getAgentState() {
        return agState;
    }

    public void setAgentState(EstadoAgente agentState) {
        this.agState = agentState;
    }
    
    public void cargarReglas(){
//		Estructura de una regla:
//		Condiciones - Then - ID - specificity - priority - novelty
        listaReglas = new BaseDeConocimiento().crearReglas();
    }
    
	@Override
	public Action selectAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
