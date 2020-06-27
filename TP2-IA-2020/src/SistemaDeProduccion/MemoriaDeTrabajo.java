package SistemaDeProduccion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class MemoriaDeTrabajo {
    private static final String conTildes = "�?áÉé�?íÓóÚúÑñÜü";
    private static final String sinTildes = "AaEeIiOoUuNnUu";
    private static final String puntuacion = ",.;:¿?¡!()'´$%&";

    public static Set<ArrayList<String>> convertir(String oracion){
    	
        Set<ArrayList<String>> listaClaves = new HashSet<ArrayList<String>>();
        String siguiente;
        System.out.println(oracion);
        oracion = normalizar(oracion);
        System.out.println(oracion);
        StringTokenizer tokens = new StringTokenizer(oracion);
        
        while(tokens.hasMoreTokens()){
        	
            siguiente = tokens.nextToken();

            if(siguiente.equals("HOLA") || siguiente.equals("HOLAS") || siguiente.equals("OLA")|| siguiente.equals("BUENAS")){
            	ArrayList<String> saludo = new ArrayList<String>();
            	saludo.add("SALUDO");
                listaClaves.add(saludo);
            }

            else if(siguiente.equals("MI")|| siguiente.equals("YO")|| siguiente.equals("SOY")|| siguiente.equals("ESTOY") || siguiente.equals("TENGO")){
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
            
            else if(siguiente.matches("^\\d{2}$")){
            	ArrayList<String> edad = new ArrayList<String>();
            	edad.add("EDAD");
            	edad.add(siguiente);
                listaClaves.add(edad);
            }
//            else if(siguiente.equals("NAME")||siguiente.equals("LASTNAME")||siguiente.equals("NICKNAME")){
//                listaClaves.add("NAME");
//            }
//            else if(siguiente.equals("OLD")){
//                listaClaves.add("OLD");
//            }
//            else if(siguiente.equals("BORN")||siguiente.equals("FROM")){
//                listaClaves.add("BORN");
//            }
//            else if(siguiente.equals("TEACHER")|| siguiente.equals("TEACHERS")){
//                listaClaves.add("TEACHER");
//            }
//            else if(siguiente.equals("BANK")||siguiente.equals("MONEY")||siguiente.equals("CASH")||siguiente.equals("CREDITCARD")||siguiente.equals("CARD")||siguiente.equals("DEBITCARD")||siguiente.equals("BILL")||siguiente.equals("DOLLAR")||siguiente.equals("GOLD")||siguiente.equals("EXPENSIVE")){
//                listaClaves.add("MONEY");
//            }
//            else if(siguiente.equals("JEWELLERY")||siguiente.equals("NECKLACE")||siguiente.equals("RING")){
//                listaClaves.add("JEWELLERY");
//            }
//            else if(siguiente.equals("HOBBY")||siguiente.equals("ACTIVITY")||siguiente.equals("DOING")){
//                listaClaves.add("ACTIVITY");
//            }
//            else if(siguiente.equals("WEAR")||siguiente.equals("CLOTHES")||siguiente.equals("WEARING")){
//                listaClaves.add("WEAR");
//            }
//            else if(siguiente.equals("TAKE")){
//                listaClaves.add("TAKE");
//            }
//            else if(siguiente.equals("PICTURE")||siguiente.equals("PHOTO")||siguiente.equals("SELFIE")){
//                listaClaves.add("PHOTO");
//            }
//            else if(siguiente.equals("SEX")||siguiente.equals("PORN")||siguiente.equals("TRIPLEX")||siguiente.equals("XXX")){
//                listaClaves.add("XXX");
//            }
//            else if(siguiente.equals("GO")||siguiente.equals("WENT")||siguiente.equals("GONE")||siguiente.equals("GOING")) {
//                listaClaves.add("GO");
//            }
//            else if(siguiente.equals("OUT")||siguiente.equals("OFF")) {
//                listaClaves.add("OUT");
//            }

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
}
