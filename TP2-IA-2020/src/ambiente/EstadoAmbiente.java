package ambiente;

import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {
	
	private String mensaje;
	
	public EstadoAmbiente() {
        this.initState();
    }
	
	public EstadoAmbiente(String mensaje) {
		this.setMensaje(mensaje);
	}

	/**
     * This method is used to setup the initial real world.
     */
	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
