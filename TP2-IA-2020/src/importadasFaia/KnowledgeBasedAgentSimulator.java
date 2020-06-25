package importadasFaia;

import java.util.ArrayList;
import java.util.Vector;

import SistemaDeProduccion.ProductionSystemAction;
import agente.AgenteChatbot;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;

/**
 * Clase que implementa el simulador de un agente basado en conocimiento.
 * @author Grupo 12: Blas,María Julia / Diaz Ferreyra,Nicolas/ Sarli, Juan Leonardo.
 */
public class KnowledgeBasedAgentSimulator extends frsf.cidisi.faia.simulator.Simulator {

	/**
	 * Constructor.
	 * @param environment
	 * @param agents
	 */
    public KnowledgeBasedAgentSimulator(Environment environment, Vector<Agent> agents) {
        super(environment,agents);
    }
    
    /**
     * Constructor.
     * @param environment
     * @param agent
     */
    public KnowledgeBasedAgentSimulator(Environment environment, Agent agent) {
   		Vector<Agent> v = new Vector<Agent>();
   		v.add(agent);
    	this.environment = environment;
    	this.agents = v;
    }
	
    @Override
    public ArrayList<String> start() {

        ArrayList<String> mensaje = new ArrayList<String>();

        ProductionSystemAction act;
        //int contador = 0;
        System.out.println("----------------------------------------------------");
        System.out.println("--- " + this.getSimulatorName() + " ---");
        System.out.println("----------------------------------------------------");
        System.out.println();

        Perception perception;
        Action action = null;
        //TODO Futuro: Esta hecho para el ChatBot pero deberia incluirse el metodo learn en la clase agente.
        AgenteChatbot agent;
        agent = (AgenteChatbot) this.getAgents().firstElement();

        //do {
        System.out.println("------------------------------------");
        System.out.println("Sending perception to agent...");
        perception = this.getPercept();
        agent.see(perception);
        System.out.println("Detected phrase: " + perception);
        System.out.println("Environment: " + environment);
        System.out.println(agent.getAgentState().toString());

        try {
            action = agent.learn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (action == null){
            mensaje.add("** no privacy violations detected **");
        }
        else{
            act = (ProductionSystemAction) action;
            // Agregamos en primer lugar la respuesta que dara el agente
            mensaje.add((String) act.getRegla().getThen());
            // Agregamos en segundo lugar los detalles de las 3 fases
            mensaje.add(act.getLog());
        }
        System.out.println();

        this.ruleReturned(agent, action);
        System.out.println();

        // This call can be moved to the Simulator class
        this.environment.close();

        // Launch simulationFinished event
        SimulatorEventNotifier.runEventHandlers(EventType.SimulationFinished, null);

        //return null;
        return mensaje;

    }

    protected boolean finishForAgentState(Agent agent) {
        // TODO Futuro: Ver como deberia ser este metodo
        return false;
    }

    protected boolean finishForRule(Action action) {
        // TODO Futuro: Ver como deberia ser este metodo
        if(action == null){
            return false;
        }
        else{
            ProductionSystemAction act = (ProductionSystemAction) action;
            return (((String) act.getRegla().getThen()).equalsIgnoreCase("chau")||(((String) act.getRegla().getThen()).equalsIgnoreCase("hasta luego"))||((String) act.getRegla().getThen()).equalsIgnoreCase("nos vemos"));
        }
    }
    public String getSimulatorName() {
        return "Knowledge Based Simulator";
    }

    public void ruleReturned(Agent agent,Action action) {
        if(action!=null){
            this.updateState(action);
        }
    }

    protected void updateState(Action action) {
        //TODO Futuro: Esta hecho para el ChatBot pero deberia incluirse en otro lugar el metodo requerido.
        this.getEnvironment().updateState(((AgenteChatbot) agents.elementAt(0)).getAgentState(), action);
    }
    
}
