package agente;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import SistemaDeProduccion.BaseDeConocimiento;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.state.AgentState;
import ambiente.PercepcionChatbot;

public class EstadoAgente extends AgentState {
	
	private Set<ArrayList<String>> listaDatos = new HashSet<ArrayList<String>>();
	private Set<String> listaClaves = new HashSet<String>();
	private String nombreUsuario = "";
	private String tipoUsuario = "ciudadano";
	private Integer edadUsuario = 0;
	
	public EstadoAgente() {

    }
	
	

	@Override
	public void updateState(Perception p) {
		PercepcionChatbot cbp = (PercepcionChatbot) p;
        BaseDeConocimiento bdc = new BaseDeConocimiento();

        listaDatos.clear();
        listaClaves.clear();
        listaDatos = bdc.getPalabrasClave(cbp.getMensaje());
        
        actualizarDatosUsuario(listaDatos);
	}

	private void actualizarDatosUsuario(Set<ArrayList<String>> listaClaves) {
		// TODO Auto-generated method stub
		for(ArrayList<String> s : listaClaves) {
			if(s.get(0).equals("NOMBRE")) {
				setNombreUsuario(s.get(1));
			}
			else if (s.get(0).equals("EDAD")) {
				setEdadUsuario(Integer.parseInt(s.get(1)));
				if(Integer.parseInt(s.get(1)) > 65) setTipoUsuario("riesgo");
			}
			else if (s.get(0).equals("TRABAJO")) {
				setTipoUsuario(s.get(1));
			}
			else if (s.get(0).equals("PATOLOGIA")) {
				setTipoUsuario("riesgo");
			}
			this.listaClaves.add(s.get(0));
		}
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

	public Set<ArrayList<String>> getListaDatos() {
		return listaDatos;
	}

	public void setListaDatos(Set<ArrayList<String>> listaDatos) {
		this.listaDatos = listaDatos;
	}
	
	public Set<String> getListaClaves() {
		return listaClaves;
	}



	public void setListaClaves(Set<String> listaClaves) {
		this.listaClaves = listaClaves;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}



	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}



	public String getTipoUsuario() {
		return tipoUsuario;
	}



	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}



	public Integer getEdadUsuario() {
		return edadUsuario;
	}



	public void setEdadUsuario(Integer edadUsuario) {
		this.edadUsuario = edadUsuario;
	}

}
