package importadasFaia;

import java.util.List;

/**
 * Clase que modela elas reglas del sistema de produccion.
 * @author Grupo 12: Blas,María Julia / Diaz Ferreyra,Nicolas/ Sarli, Juan Leonardo.
 */
public abstract class Rule {
	
	Object condition;
	Object then;
	int id;
	int specificity;
	int priority;
	int novelty;
	int type; //Indica si la regla es de tipo return (0), add (1), etc.
	
	public Rule(){	
		specificity = 0;
		priority = 0;
		novelty = 0;
	}
	
	public Rule(List<String> condition, String then, int id, int specifity, int priority, int novelty) {
		this.condition = condition;
		this.then = then;
		this.id = id;
		this.specificity = specifity;
		this.priority = priority;
		this.novelty = novelty;
	}
	
	public Integer getId(){
		return new Integer(id);
	}
	
	public void setId(int identificador){
		id = identificador;
	}
	
	public int getSpecificity(){
		return specificity;
	}	
	
	public void setSpecificity(int s){
		specificity = s;
	}
	
	public Integer getPriority(){
		return priority;
	}	
	
	public void setPriority(int p){
		priority = p;
	}
	
	public Integer getNovelty(){
		return novelty;
	}	
	
	public void setNovelty(int n){
		novelty = n;
	}
	
	public Integer getType(){
		return type;
	}	
	
	public void setType(int t){
		type = t;
	}
	
	public void setCondition(Object o){
		condition = o;
	}
	
	public Object getCondition(){
		return condition;
	}
	
	public void setThen(Object t){
		then = t;
	}
	
	public Object getThen(){
		return then;
	}
	
	public boolean equals(Rule aRule){		
		return (id==aRule.getId().intValue());
	}
	
	public abstract boolean isActive(Object status);
	
}
