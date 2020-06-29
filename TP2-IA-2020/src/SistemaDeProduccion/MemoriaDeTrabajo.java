package SistemaDeProduccion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class MemoriaDeTrabajo {
    private static final String conTildes = "¡·…ÈÕÌ”Û⁄˙—Ò‹¸";
    private static final String sinTildes = "AaEeIiOoUuNnUu";
    private static final String puntuacion = ",.;:ø?°!()'$%&";

    public static Set<ArrayList<String>> convertir(String oracion){
    	
        Set<ArrayList<String>> listaClaves = new HashSet<ArrayList<String>>();
        String siguiente;
        System.out.println(oracion);
        oracion = normalizar(oracion);
        oracion = oracion.replaceAll("a", "");
        System.out.println(oracion);
        oracion = funcionIneficiente(oracion);
        StringTokenizer tokens = new StringTokenizer(oracion);
        
        while(tokens.hasMoreTokens()){
        	
            siguiente = tokens.nextToken();

            if(siguiente.equals("HOLA") || siguiente.equals("HOLAS") || siguiente.equals("OLA")|| siguiente.equals("BUENAS")){
            	ArrayList<String> saludo = new ArrayList<String>();
            	saludo.add("SALUDO");
                listaClaves.add(saludo);
            }
            else if(siguiente.equals("MI")|| siguiente.equals("YO")|| siguiente.equals("SOY")|| siguiente.equals("ESTOY") || siguiente.equals("TENGO") || siguiente.matches("SUFRO") || siguiente.matches("PADEZCO") || siguiente.matches("NECESITO")){
            	ArrayList<String> usuario = new ArrayList<String>();
            	usuario.add("USUARIO");
                listaClaves.add(usuario);
            }
            else if(siguiente.equals("AUGUSTO")|| siguiente.equals("MAURO")|| siguiente.equals("FRANCISCO")|| siguiente.equals("JORGE")|| siguiente.equals("MILAGROS")|| siguiente.equals("JUAN")){
            	ArrayList<String> nombre = new ArrayList<String>();
            	nombre.add("NOMBRE");
            	nombre.add(siguiente);
                listaClaves.add(nombre);
            }            
            else if(siguiente.equals("COVID")|| siguiente.equals("CORONONA")|| siguiente.equals("VIRUS")|| siguiente.equals("CORONAVIRUS")|| siguiente.equals("COVID19")|| siguiente.equals("COVID-19")){
            	ArrayList<String> enfermedad = new ArrayList<String>();
            	enfermedad.add("ENFERMEDAD");
                listaClaves.add(enfermedad);
            }
            
            ///////////////////////////////////////////////////////////
            //Nuevas
            else if(siguiente.equals("SUGERENCIA")|| siguiente.equals("CONSEJO")|| siguiente.equals("RECOMENDACION")|| siguiente.equals("RECOMENDACIONES")|| siguiente.equals("CONSEJOS")|| siguiente.equals("SUGERENCIAS") || siguiente.equals("MEDIDAS") || siguiente.equals("REGLAS")){
            	ArrayList<String> consejo = new ArrayList<String>();
            	consejo.add("CONSEJO");
                listaClaves.add(consejo);
            }
            else if(siguiente.equals("EVITAR") || siguiente.equals("EVITO")){
            	ArrayList<String> evitar = new ArrayList<String>();
            	evitar.add("EVITAR");
                listaClaves.add(evitar);
            }
            else if(siguiente.equals("HAY") ||siguiente.equals("DEBO") ||siguiente.equals("QUE") || siguiente.equals("PORQUE") || siguiente.equals("COMO") || siguiente.equals("CUANDO") || siguiente.equals("POR") || siguiente.equals("DONDE")|| siguiente.equals("CUAL") || siguiente.equals("CUALES")){
            	ArrayList<String> pregunta = new ArrayList<String>();
            	pregunta.add("PREGUNTA");
                listaClaves.add(pregunta);
            }
            else if(siguiente.equals("CUANTA") || siguiente.equals("CUANTAS") || siguiente.equals("CUANTO") || siguiente.equals("CUANTOS")){
            	ArrayList<String> preguntacant = new ArrayList<String>();
            	preguntacant.add("PREGUNTACANT");
                listaClaves.add(preguntacant);
            }
            else if(siguiente.equals("CONTAGIO") || siguiente.equals("CONTAGIOS") || siguiente.equals("CONTAGIADOS") || siguiente.equals("CONTAGIARME") || siguiente.equals("ENFERMARME") || siguiente.equals("INFECTARME") || siguiente.equals("MORIRME") || siguiente.equals("CONTAGIA") || siguiente.equals("INFECCION") || siguiente.equals("CONTAGIAR") ||  siguiente.equals("INFECTADOS") ){
            	ArrayList<String> contagio = new ArrayList<String>();
            	contagio.add("CONTAGIO");
                listaClaves.add(contagio);
            }
            else if(siguiente.equals("TASA") || siguiente.equals("CANTIDAD") || siguiente.equals("NIVEL")){
            	ArrayList<String> tasa = new ArrayList<String>();
            	tasa.add("TASA");
                listaClaves.add(tasa);
            }
            else if(siguiente.equals("ARGENTINA") || siguiente.equals("PAIS") || siguiente.equals("BRASIL") || siguiente.equals("CABA")  || siguiente.equals("PBA") || siguiente.equals("CHACO") || 
            		siguiente.equals("RIONEGRO") || siguiente.equals("CORDOBA") || siguiente.equals("NEUQUEN") || siguiente.equals("SANTAFE") || siguiente.equals("ENTRERIOS") || siguiente.equals("MENDOZA")  || 
            		siguiente.equals("TIERRADELFUEGO")  || siguiente.equals("CHUBUT") || siguiente.equals("CORRIENTES") || siguiente.equals("JUJUY") || siguiente.equals("LARIOJA") || siguiente.equals("FORMOSA") || 
            		siguiente.equals("TUCUMAN") || siguiente.equals("SANTACRUZ") || siguiente.equals("MISIONES") || siguiente.equals("SALTA") || siguiente.equals("SANTIAGODELESTERO")  || siguiente.equals("SANLUIS") || 
            		siguiente.equals("SANJUAN") || siguiente.equals("LAPAMPA") || siguiente.equals("CATAMARCA") || siguiente.equals("USA") || siguiente.equals("BRASIL") || siguiente.equals("RUSIA") || siguiente.equals("INDIA")
            		|| siguiente.equals("ITALIA") || siguiente.equals("CHILE")){
            	ArrayList<String> lugar = new ArrayList<String>();
            	lugar.add("LUGAR");
            	lugar.add(siguiente);
                listaClaves.add(lugar);
            }
            else if(siguiente.equals("MUERTE") || siguiente.equals("MUERTES") || siguiente.equals("MORTALIDAD") || siguiente.equals("MUERTOS")|| siguiente.equals("MUERTO")){
            	ArrayList<String> muerte = new ArrayList<String>();
            	muerte.add("MORTALIDAD");
                listaClaves.add(muerte);
            }
            else if(siguiente.equals("SINTOMAS") || siguiente.equals("SINTOMA") || siguiente.equals("SE—AL") || siguiente.equals("SE—ALES") || siguiente.equals("CAUSAS")){
            	ArrayList<String> sintoma = new ArrayList<String>();
            	sintoma.add("SINTOMA");
                listaClaves.add(sintoma);
            }
            
            else if(siguiente.matches("PERMISOS")||siguiente.matches("HABILITACION")||siguiente.matches("PERMISO")||siguiente.matches("PAPELES")){
            	ArrayList<String> permisos = new ArrayList<String>();
            	permisos.add("PERMISOS");
                listaClaves.add(permisos);
            }
            else if(siguiente.matches("TRABAJO")||siguiente.matches("TRABAJAR")||siguiente.matches("LABURAR")||siguiente.matches("CIRCULAR")){
            	ArrayList<String> actividad = new ArrayList<String>();
            	actividad.add("ACTIVIDAD");
                listaClaves.add(actividad);
            }
            else if(siguiente.matches("TELEFONO")||siguiente.matches("CELULAR")||siguiente.matches("CONTACTO")){
            	ArrayList<String> contacto = new ArrayList<String>();
            	contacto.add("CONTACTO");
                listaClaves.add(contacto);
            }
            
            else if(siguiente.matches("VACUNA")||siguiente.matches("REMEDIOS")||siguiente.matches("REMEDIO")){
            	ArrayList<String> vacuna = new ArrayList<String>();
            	vacuna.add("VACUNA");
                listaClaves.add(vacuna);
            }
            else if(siguiente.matches("RECUPERADO")||siguiente.matches("RECUPERADOS")||siguiente.matches("CURADO")||siguiente.matches("CURADOS")||siguiente.matches("ALTA")){
            	ArrayList<String> vacuna = new ArrayList<String>();
            	vacuna.add("RECUPERADO");
                listaClaves.add(vacuna);
            }
            else if(siguiente.matches("CUARENTENA")||siguiente.matches("AISLAMIENTO")||siguiente.matches("ENCIERRO")||siguiente.matches("ENCERRADO")){
            	ArrayList<String> vacuna = new ArrayList<String>();
            	vacuna.add("CUARENTENA");
                listaClaves.add(vacuna);
            }
            else if(siguiente.matches("RIESGO")||siguiente.matches("PELIGRO")||siguiente.matches("INSEGURO")){
            	ArrayList<String> vacuna = new ArrayList<String>();
            	vacuna.add("RIESGO");
                listaClaves.add(vacuna);
            }
            else if(siguiente.matches("CIRCULACION")||siguiente.matches("INTERNO")||siguiente.matches("INTERNA")){
            	ArrayList<String> vacuna = new ArrayList<String>();
            	vacuna.add("CIRCULACION");
                listaClaves.add(vacuna);
            }
            else if(siguiente.matches("DATOS")||siguiente.matches("DATO")|| siguiente.matches("NUMERO") || siguiente.matches("INDICADORES") || siguiente.matches("INFORME")){
            	ArrayList<String> dato = new ArrayList<String>();
            	dato.add("DATO");
                listaClaves.add(dato);
            }
            else if(siguiente.matches("TELEFONO")||siguiente.matches("CELULAR")||siguiente.matches("CONTACTO") ||siguiente.matches("NUMERO") ||siguiente.matches("ASISTENCIA")||siguiente.matches("CONTACTOS")||siguiente.matches("NUMEROS")||siguiente.matches("CELULARES")){
            	ArrayList<String> contacto = new ArrayList<String>();
            	contacto.add("CONTACTO");
                listaClaves.add(contacto);
            }
            ///////////////////////////////////////////////////////////
            
            else if(siguiente.matches("^\\d{2}$")){
            	ArrayList<String> edad = new ArrayList<String>();
            	edad.add("EDAD");
            	edad.add(siguiente);
                listaClaves.add(edad);
            }
            else if(siguiente.matches("ENFERMERO") || siguiente.matches("MEDICO") || siguiente.matches("KINESIOLOGO") || siguiente.matches("AMBULANCIERO")){
            	ArrayList<String> trabajo = new ArrayList<String>();
            	trabajo.add("TRABAJO");
            	trabajo.add("salud");
                listaClaves.add(trabajo);
            }
            else if(siguiente.matches("EMPLEADO") || siguiente.matches("COMERCIANTE") || siguiente.matches("VENDEDOR")){
            	ArrayList<String> trabajo = new ArrayList<String>();
            	trabajo.add("TRABAJO");
            	trabajo.add("comercio");
                listaClaves.add(trabajo);
            }
            else if(siguiente.matches("DIABETES") || siguiente.matches("HIPERTENSION") || siguiente.matches("OBESIDAD") || siguiente.matches("DIABETICO") || siguiente.matches("HIPERTENSO") || siguiente.matches("OBESO")){
            	ArrayList<String> patologia = new ArrayList<String>();
            	patologia.add("PATOLOGIA");
                listaClaves.add(patologia);
            }

        }
        
        if(listaClaves.isEmpty()) {
        	ArrayList<String> porDefecto = new ArrayList<String>();
        	porDefecto.add("PORDEFECTO");
            listaClaves.add(porDefecto);
        }
        
        System.out.println(listaClaves);
        return listaClaves;
    }

    public static String normalizar(String oracion){
        char[] temporal = oracion.toCharArray();
        for (int i = 0; i < temporal.length; i++) {
            int pos = conTildes.indexOf(temporal[i]);
            if (pos > -1) {
                temporal[i] = sinTildes.charAt(pos);
            }
            pos = puntuacion.indexOf(temporal[i]);
            if(pos > -1){
                for(int j = i; j < temporal.length-1; j++){
                    temporal[j] = temporal[j+1];
                }
                temporal[temporal.length-1] = ' ';
            }
        }
        
        return new String(temporal);
    }
    
    public static String funcionIneficiente(String oracion) {
    	
    	oracion = oracion.toUpperCase();
    	
    	if(oracion.contains("RIO NEGRO")) {
    		oracion = oracion.replaceAll("RIO NEGRO", "RIONEGRO");
    	} else if (oracion.contains("SANTA FE")) {
    		oracion = oracion.replaceAll("SANTA FE", "SANTAFE");
    	} else if (oracion.contains("SAN JUAN")) {
    		oracion = oracion.replaceAll("SAN JUAN", "SANJUAN");
    	} else if (oracion.contains("LA PAMPA")) {
    		oracion = oracion.replaceAll("LA PAMPA", "LAPAMPA");
    	} else if (oracion.contains("ENTRE RIOS")) {
    		oracion = oracion.replaceAll("ENTRE RIOS", "ENTRERIOS");
    	} else if (oracion.contains("TIERRA DEL FUEGO")) {
    		oracion = oracion.replaceAll("TIERRA DEL FUEGO", "TIERRADELFUEGO");
    	} else if (oracion.contains("LA RIOJA")) {
    		oracion = oracion.replaceAll("LA RIOJA", "LARIOJA");
    	} else if (oracion.contains("SANTA CRUZ")) {
    		oracion = oracion.replaceAll("SANTA CRUZ", "SANTACRUZ");
    	} else if (oracion.contains("SANTIAGO DEL ESTERO")) {
    		oracion = oracion.replaceAll("SANTIAGO DEL ESTERO", "SANTIAGODELESTERO");
    	} else if (oracion.contains("SAN LUIS")) {
    		oracion = oracion.replaceAll("SAN LUIS", "SANLUIS");
    	}
    	return oracion;
    }
}
