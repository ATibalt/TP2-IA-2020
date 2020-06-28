package SistemaDeProduccion;

import utils.Regla;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import agente.EstadoAgente;

public class MemoriaDeProduccion {
    private List<Regla> listaReglas;
    
    private static String consejoCiudadano = "Por los datos que nos diste, lo mejores consejos para evitar contagios son los siguientes"+"\n"
											   + "	- Evita salir de tu casa si no es necesario" + "\n"
											   + "	- Si tenes que salir:" + "\n"
											   + "		- Lleva tapaboca para ingresar a comercios" + "\n"
											   + "		- Mantene una distancia prudente del resto de las personas" + "\n"
											   + "		- Mantene cerca un alcohol en gel, si intercambias dinero con alguien es importante para desinfectar tus manos" + "\n"
											   + "	- Al volver a tu casa:"  + "\n"
											   + "		- Lavate bien las manos" + "\n"
											   + "		- Lava los productos que puedas haber traido desde afuera";
    
    private static String consejoRiesgo = "Segun tus datos sos paciente de riesgo, asi que debes tener mucho cuidado"+"\n"
											   + "	- No salgas de tu casa" + "\n"
											   + "	- Pedi a algun conocido cercano que te ayude con la compra de alimentos o articulos de necesidad" + "\n"
											   + "	- Cuando te los lleve intenta interactuar lo menos posible, mantene la distancia y ambos usen tapaboca" + "\n"
											   + "	- Si no tenes a quien pedirle:" + "\n"
											   + "		- Comunicate al 0800-777-5000 y consulta por algun voluntario disponible" + "\n"
											   + "	- Si no tenes otra opcion que salir, ultima los cuidados:"  + "\n"
											   + "		- Lleva tapaboca para ingresar a comercios" + "\n"
											   + "		- Mantene una distancia prudente del resto de las personas" + "\n"
											   + "		- Mantene cerca un alcohol en gel, si intercambias dinero con alguien es importante para desinfectar tus manos" + "\n"
											   + "		- Lavate bien las manos" + "\n"
											   + "		- Lava los productos que puedas haber traido desde afuera";
    
    private static String consejoComercio = "Por los datos que nos diste, lo mejores consejos para evitar contagios son los siguientes"+"\n"
											+"	- Usar guantes y barbijo mientras trabaja."+ "\n"
											+"	- Lavarse las manos cada vez que realiza una venta."+ "\n"
											+"	- Cumplir las medidas de distancia social cuando sea necesario."+ "\n"
											+"	- Prohibido tener contacto con un cliente."+ "\n"
											+"	- Tener un trapo de piso con lavandina en la entrada del establecimiento."+ "\n"
											+"	- Prohibido que los clientes ingresen en pareja."+ "\n"
											+"	- Controlar diariamente la limpieza del local.";
    
    private static String consejoSalud = "Por los datos que nos diste, lo mejores consejos para evitar contagios son los siguientes"+"\n"
												+"	- Usa siempre tu equipo de seguridad correspondiente."+ "\n"
												+"	- Evita tocarte la cara."+ "\n"
												+"	- Si tratas con un paciente toma todos los recaudos necesarios."+ "\n"
												+"	- Cuando llegues a tu casa, desinfecta tus prendas."+ "\n"
												+"Gracias por tu trabajo, es de suma importancia para superar esta pandemia.";

    public MemoriaDeProduccion(){
        listaReglas = new ArrayList<Regla>();
    }
    
    public List<Regla> crearReglas(EstadoAgente agState){
    	
    	///////////////////////////////////
    	//ETAPA DE SETUP DE LA CONVERSACION
    	listaReglas.add(new Regla(Arrays.asList("SALUDO"), "Hola, soy tu asistente COVID-19, si me indicas tus datos puedo darte consejos personalizados." +
    			                                           "\n" + "Cual es tu nombre?", 1, 3, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("SALUDO"), "Hola, soy tu asistente COVID-19, si necesitas datos de paises/pronvincias puedo responderte." + "\n", 30, 3, 5, 1));
    	
    	listaReglas.add(new Regla(Arrays.asList("USUARIO", "NOMBRE"), "OK "+agState.getNombreUsuario()+", que edad tenes?", 2, 3, 5, 1));
    	
    	if(agState.getEdadUsuario() < 50) {
    		 listaReglas.add(new Regla(Arrays.asList("USUARIO", "EDAD"), "Muy bien "+agState.getNombreUsuario()+", por tu edad no entras dentro del grupo de pacientes de riesgo, pero de cualquier manera necesitas cuidarte."
    				 													 + "\n" + "¿A que te dedicas?", 3, 3, 5, 1));
    		 listaReglas.add(new Regla(Arrays.asList("USUARIO", "EDAD"), "Ok"+agState.getNombreUsuario()+", deberias tener cuidado con esta enfermedad."
						 												 + "\n" + "¿Tenes alguna patologia previa?", 6, 3, 5, 1));
    	}	
    	else if(agState.getEdadUsuario() > 50 && agState.getEdadUsuario() < 60) {
    		listaReglas.add(new Regla(Arrays.asList("USUARIO", "EDAD"), "Muy bien "+agState.getNombreUsuario()+", estas cercano a las edades de riesgo, deberias cuidarte lo máximo posible."
    																	 + "\n" + "¿Sufris alguna patologia?", 3, 3, 5, 1));
    	}
    	else if(agState.getEdadUsuario() > 60) {
    		listaReglas.add(new Regla(Arrays.asList("USUARIO", "EDAD"), "Muy bien "+agState.getNombreUsuario()+", por tu edad entras dentro del grupo de pacientes de riesgo, asi que necesitas cuidarte mucho." + "\n" + "No salgas de no ser necesario!", 3, 3, 5, 1));
    	}
    	///////////////////////////////////
    	
    	
		///////////////////////////////////
		//MENSAJE POR DEFECTO O ERROR
    	listaReglas.add(new Regla(Arrays.asList("PORDEFECTO"), "Parece que no puedo procesar tu mensaje, recorda que puedo:" + "\n"
    			                                               + "	- Darte informacion sobre el COVID-19" + "\n"
    			                                               + "	- Darte consejos sobre como evitar el contagio" + "\n"
    			                                               + "	- Darte consejos segun tu tipo de trabajo o condicion de salud", 4, 3, 5, 1));
    	///////////////////////////////////
    	
    	//REGLAS PARA CIUDADANO COMUN
    	if(agState.getTipoUsuario().equals("ciudadano")) {
    		listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "EVITAR", "CONTAGIO"), consejoCiudadano, 5, 5, 5, 1));
    		listaReglas.add(new Regla(Arrays.asList("USUARIO", "CONSEJO"), consejoCiudadano, 6, 3, 5, 1));
    		
    	}
    	//REGLAS PARA COMERCIANTES
    	if(agState.getTipoUsuario().equals("comercio")) {
    		listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "EVITAR", "CONTAGIO"), consejoComercio, 5, 5, 5, 1));
    		listaReglas.add(new Regla(Arrays.asList("USUARIO", "CONSEJO"), consejoComercio, 6, 3, 5, 1));
    		
    	}
    	//REGLAS PARA EMPLEADOS DE LA SALUD
    	if(agState.getTipoUsuario().equals("salud")) {
    		listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "EVITAR", "CONTAGIO"), consejoSalud, 5, 5, 5, 1));
    		listaReglas.add(new Regla(Arrays.asList("USUARIO", "CONSEJO"), consejoSalud, 6, 3, 5, 1));
    		
    	}
    	//REGLAS PARA POBLACION DE RIESGO
    	else if(agState.getTipoUsuario().equals("riesgo")) {
    		listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "EVITAR", "CONTAGIO"), consejoRiesgo, 5, 3, 5, 1));
    		listaReglas.add(new Regla(Arrays.asList("USUARIO", "CONSEJO"), consejoRiesgo, 6, 3, 5, 1));
    	}
    	
    	//PERMISOS DE TRABAJO
    	listaReglas.add(new Regla(Arrays.asList("PERMISOS", "ACTIVIDAD"), "\n"+"Recuerde que tiene que tener los siguientes permisos para salidas laborales:"+"\n"
									    						+"	- Permisos de Circulacion."+ "\n"
									    						+"	- Permisos de Habilitacion de la Nacion."+ "\n"
									    						+"	- Permisos de Habilitacion de la AFIP."+ "\n"
									    						+"	- DNI.", 3, 3, 5, 1));
    	
    	
    	//REGLAS INFORMACION COVID
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "CONTAGIO"), "- Informacion publicada por el Ministerio de salud de la Nacion:" + "\n" +
    																	 "	Se transmite de una persona a otra a través de las gotas procedentes de la nariz o la boca que salen despedidas cuando la persona infectada tose, estornuda o habla," + "\n" +
    																	 "	por contacto con manos, superficies u objetos contaminados." + "\n" +
    																	 "	Por eso es importante mantener distanciamiento social y tomar las precauciones de contacto que se describen más abajo.", 7, 3, 5, 1));
    	
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "CONTAGIO", "TASA"), "- El COVID-19 ha demostrado que puede transmitirse de una persona a otra con bastante facilidad." + "\n"
    																		   + " 	De momento, la OMS estima que la tasa de contagio (R0) del virus es de 1,4 a 2,5." + "\n"
    																		   + "	Esto significa que cada infectado puede a su vez contagiar a 1 o 2 personas más.", 8, 5, 5, 1));
    	
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "TASA", "MORTALIDAD"), "- La tasa de mortalidad del COVID-19 a nivel mundial es del 8% sobre el total de infectados", 11, 6, 5, 1));
    	
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "TASA", "MORTALIDAD", "LUGAR"), "- Hasta ahora la tasa de mortalidad del COVID-19 en Argentina es 6% sobre la cantidad de infectados", 12, 6, 5, 1));
    	
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "SINTOMA"), "	- Segun la OMS los sintomas que presentan las personas con COVID-19 son los siguientes:Los síntomas más habituales son los siguientes:\r\n" + 
														    			"		. Fiebre\r\n" + 
														    			"		. Tos seca\r\n" + 
														    			"		. Cansancio\r\n" + 
														    			"	Otros síntomas menos comunes son los siguientes:\r\n" + 
														    			"		. Molestias y dolores\r\n" + 
														    			"		. Dolor de garganta\r\n" + 
														    			"		. Diarrea\r\n" + 
														    			"		. Conjuntivitis\r\n" + 
														    			"		. Dolor de cabeza\r\n" + 
														    			"		. Pérdida del sentido del olfato o del gusto\r\n" + 
														    			"		. Erupciones cutáneas o pérdida del color en los dedos de las manos o de los pies\r\n" + 
														    			"	Los síntomas graves son los siguientes:\r\n" + 
														    			"		. Dificultad para respirar o sensación de falta de aire\r\n" + 
														    			"		. Dolor o presión en el pecho\r\n" + 
														    			"		. Incapacidad para hablar o moverse", 13, 6, 5, 1)); 
    	
    	//TODO: Reglas sobre el tiempo de la enfermedad
    	
    	//DATOS POR LUGAR
    	ArrayList datosLugar = seleccionarDatosLugar(agState);
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "MORTALIDAD", "LUGAR"), "- Hasta ahora la cantidad de muertos por COVID-19 en "+ datosLugar.get(0) +" es " + datosLugar.get(2), 14, 6, 7, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "CONTAGIO", "LUGAR"), "- Hasta ahora la cantidad de contagiados de COVID-19 en "+ datosLugar.get(0) +" es " + datosLugar.get(1), 15, 6, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "RECUPERADO", "LUGAR"), "- Hasta ahora la cantidad de personas recuperadas de COVID-19 en "+ datosLugar.get(0) +" es " + datosLugar.get(3), 16, 6, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "CUARENTENA", "LUGAR"), "- En "+ datosLugar.get(0) + datosLugar.get(6) + " existe una cuarentena vigente.", 16, 6, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "RIESGO", "LUGAR"), "- En "+ datosLugar.get(0) + datosLugar.get(6) + " existe riesgo de contagio, si tiene que ir por favor tenga mucho cuidado.", 17, 6, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "RIESGO", "CONTAGIO", "LUGAR"), "- "+ datosLugar.get(0) +" "+ datosLugar.get(6) + " se considera una zona con riesgo de contagio.", 18, 7, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "CIRCULACION", "LUGAR"), "- En "+ datosLugar.get(0) +" "+ datosLugar.get(6) + " existe circulacion interna del virus. Consulte en la pagina del Ministerio de Salud para ver cuales son las zonas mas riesgosas", 19, 6, 5, 1));
    	//Preguntas generales
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "MORTALIDAD"), "- Lamentablemente el COVID-19 ya ha dejado "+datosLugar.get(2)+" muertos en todo el mundo", 20, 6, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "CONTAGIO"), "- Hasta ahora la cantidad de contagiados de COVID-19 en el "+ datosLugar.get(0) +" es " + datosLugar.get(1), 21, 6, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "RECUPERADO"), "- Hasta ahora la cantidad de personas recuperadas de COVID-19 en el "+ datosLugar.get(0) +" es " + datosLugar.get(3), 22, 6, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "CUARENTENA"), "- Las cuarentenas suelen ser locales, asi que deberias preguntar si hay una en tu provincia/ciudad/pais", 23, 6, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "RIESGO"), "- El mundo sigue siendo un lugar de riesgo, al menos hasta que exista una vacuna.", 24, 6, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "RIESGO", "CONTAGIO"), "- El mundo sigue siendo un lugar de riesgo, al menos hasta que exista una vacuna.", 25, 7, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "CIRCULACION"), "- Muchos paises en el mundo aun tienen circulacion interna del virus, si preguntas por uno especificamente quizas podria responderte.", 26, 6, 5, 1));
    	//Datos
    	listaReglas.add(new Regla(Arrays.asList("DATO", "LUGAR"), "- Los datos relacionados con el COVID-19 en " + datosLugar.get(0) +" son los siguientes:" + "\n"
    															  + "	-Cantidad de muertes: " + datosLugar.get(1) + "\n"
    															  + "	-Cantidad de contagiados: " + datosLugar.get(2) + "\n"
    															  + "	-Cantidad de personas recuperadas: " + datosLugar.get(3) + "\n"
    															  + "	-Hay circulacion interna: " + datosLugar.get(4) + "\n"
    															  + "	-Es zona de riesgo: " +datosLugar.get(5) + "\n"
    															  + "	-Hay cuarentena obligatoria: "+datosLugar.get(6) + "\n", 27, 6, 5, 1));
    	
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA","CONTACTO"), "\n"+"Los numeros de telefono son:"+"\n"
																			+"	-Ministerio de Salud llamar al 120 las 24hs del dia."+ "\n"
																			+"	-Por Whatsapp: +54 9 11 2256-0566"+ "\n", 28, 3, 5, 1));
    	
        return listaReglas;
    }

	private ArrayList<String> seleccionarDatosLugar(EstadoAgente agState) {
		
		for(ArrayList<String> a : agState.getDatosCovid()) {	
			
			String lugarUsuario = agState.getLugarDeInteres().replaceAll(" ", "");
			String lugarLista = a.get(0).replaceAll("ï»¿", "");
			lugarLista = a.get(0).replaceAll(" ", "");
			
			if(lugarUsuario.matches(lugarLista)) {
				return a;
			}
			
		}
		
		return agState.getDatosCovid().get(0);	
	}

}