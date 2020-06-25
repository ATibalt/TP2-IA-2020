package ambiente;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PercepcionChatbot extends Perception {
	
	private String mensaje;
	
	public PercepcionChatbot () {
		
	}
	
	public PercepcionChatbot(Agent agent, Environment environment) {
		super(agent, environment);       
	}


	/**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {

    }
    
    @Override
    public String toString() {
        return this.mensaje;
    }

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
