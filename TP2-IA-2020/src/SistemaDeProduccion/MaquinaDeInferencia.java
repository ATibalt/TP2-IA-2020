package SistemaDeProduccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import importadasFaia.Criteria;
import utils.Regla;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.Solve;

public class MaquinaDeInferencia extends Solve{

    private List<Criteria> criteria;
    public List<Regla> listaDeReglas;
    
    //reglasUsadas contiene la lista de reglas utilizadas. Sirve para el criterio de no duplicidad
    public static List<Regla> reglasUsadas = new ArrayList<Regla>();
    
    //El problema en este agente es, mediante una lista de palabras claves ver que regla aplicar.
    //El problema contiene la lista de palabras claves percibidas desde el ambiente.
    public Problema problema;

    public MaquinaDeInferencia(List<Criteria> s, List<Regla> reglas){
        criteria = s;
        listaDeReglas = reglas;
    }

    @Override
    public void showSolution() {
    }

    @Override
    public Action solve(Object[] params) throws Exception {


        problema = (Problema) params[0]; //Aca tiene las palabras claves detectadas
        Regla r;
        String logDeFases = "";

        if(problema.getPalabrasClaves() != null){
            logDeFases = "Palabras clave detectadas: " + problema.getPalabrasClaves() + "\n" + "\n";
        }

        /////////////////////////////
        // PRIMERA FASE:    Cotejo //
        /////////////////////////////
        //Se obtienen las reglas activas, es decir las que se pueden aplicar.
        List<Regla> activeRules = match();

        if(activeRules.isEmpty()) {
            return null;
        }
        else{
            // para generar una lista de reglas que matchearon, para mostrar en el log
            logDeFases = logDeFases + "Reglas activas: \n";
            for(int j = 0; (j <= (activeRules.size() -1)); j++){
                logDeFases = logDeFases + activeRules.get(j).getId() +" -> " + activeRules.get(j).getCondition().toString() + " : " + activeRules.get(j).getThen() + "\n";
            }
        }

        /////////////////////////////////////////////
        // SEGUNDA FASE:    Solucion de conflictos //
        /////////////////////////////////////////////

        logDeFases = logDeFases + "==========================";
        for(Criteria i : criteria){
            Criteria actualCriteria = i;
            System.out.println("\nCriterio: " + actualCriteria.toString());
            logDeFases = logDeFases + "\nCriterio:" + actualCriteria.toString() + "\n";
            List<Regla> finalRules = actualCriteria.apply(activeRules);
            if(finalRules.size()==0){
                System.out.print( "\n" + "No hay reglas para aplicar" + "\n");
            }
            else{
                // para cargar el string del log con la informacion correspondiente del criterio
                if(actualCriteria.toString() == "Specificity" || actualCriteria.toString() == "Priority") {
                    if(actualCriteria.toString() == "Specificity"){
                        logDeFases = logDeFases + "\nValor de especificidad: " + finalRules.get(0).getSpecificity();
                    } else {
                        logDeFases = logDeFases + "\nValor de prioridad: " + finalRules.get(0).getPriority();
                    }
                }
                System.out.print("\nReglas en Conflicto: ");
                logDeFases = logDeFases + "\nReglas en Conflicto: ";

                for(Regla j : finalRules){
                    System.out.print("("+j.getId()+")");
                    logDeFases = logDeFases + "("+j.getId()+")";
                }
                logDeFases = logDeFases + "\n";
                activeRules = finalRules;
                if(activeRules.size()==1){
                    logDeFases = logDeFases + "==========================";
                    break;
                }
                logDeFases = logDeFases + "==========================";
            }
        }
        ////////////////////////////////
        // TERCERA FASE:    Ejecucion //
        ////////////////////////////////
        r = activeRules.get(0);
        this.ejecutar(r);
        logDeFases = logDeFases + "\nRegla a ejecutar: " + r.getId() + " -> " + r.getCondition().toString() + " : " + (String) r.getThen() + "\n";
         
        return new ProductionSystemAction(r,logDeFases);
    }

    protected List<Regla> match(){
        // Se hace el match entre la lista de palabras clave detectadas, y las palabras clave de cada regla
        List<Regla> listaReglasAplicables = new ArrayList<Regla>();
        Set<String> palabrasPercibidas = problema.getPalabrasClaves();

        for(Regla r : listaDeReglas){
            List<String> condiciones = (List<String>) r.getCondition();
            if(palabrasPercibidas.containsAll(condiciones)){
                listaReglasAplicables.add(r);
            }
        }
        return listaReglasAplicables;
    }

    protected void ejecutar(Regla r){
        reglasUsadas.add(r);
    }

}
