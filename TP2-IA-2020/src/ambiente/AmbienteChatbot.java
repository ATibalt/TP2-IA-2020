package ambiente;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class AmbienteChatbot extends Environment{
	
	public AmbienteChatbot(String mensaje) {
        this.environmentState = new EstadoAmbiente(mensaje);
    }
	
	public EstadoAmbiente getEnvironmentState() {
        return (EstadoAmbiente) super.getEnvironmentState();
    }

	@Override
	public Perception getPercept() {
		// Create a new perception to return
        PercepcionChatbot perception = new PercepcionChatbot();

        perception.setMensaje(this.getEnvironmentState().getMensaje());

        return perception;
	}
	
	public String toString() {
        return environmentState.toString();
    }
}
