package SistemaDeProduccion;

import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import ambiente.EstadoAmbiente;
import importadasFaia.Rule;

/**
 * Clase que implementa las acciones que el sistema de produccion puede
 * devolverle al ambiente.
 */
public class ProductionSystemAction extends frsf.cidisi.faia.agent.Action {

    Rule rule;
    String log;

    public ProductionSystemAction(Rule r, String logDeLasFases){
        rule = r;
        log = logDeLasFases;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est){
        
        EstadoAmbiente cest = (EstadoAmbiente) est;
        //cest.update(ast,rule);
        return cest;
    }

    @Override
    public String toString() {
        return "Ejecutar";
    }

    public Rule getRegla(){
        return rule;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}