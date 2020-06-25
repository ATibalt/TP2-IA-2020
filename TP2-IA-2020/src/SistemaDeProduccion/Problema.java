package SistemaDeProduccion;

import java.util.Set;

public class Problema {

    protected Set<String> palabrasClave;

    public Problema() {
    }

    public Set<String> getPalabrasClaves() {
        return palabrasClave;
    }

    public void setPalabrasClaves(Set<String> set) {
        this.palabrasClave = set;
    }

}