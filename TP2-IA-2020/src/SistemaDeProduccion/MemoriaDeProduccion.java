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
    	
    	listaReglas.add(new Regla(Arrays.asList("USUARIO", "NOMBRE"), "OK "+agState.getNombreUsuario()+", que edad tenes?", 2, 3, 5, 1));
    	
    	if(agState.getEdadUsuario() < 50) {
    		 listaReglas.add(new Regla(Arrays.asList("USUARIO", "EDAD"), "Muy bien "+agState.getNombreUsuario()+", por tu edad no entras dentro del grupo de pacientes de riesgo, pero de cualquier manera necesitas cuidarte."
    				 													 + "\n" + "¿A que te dedicas?", 3, 3, 5, 1));
    		 listaReglas.add(new Regla(Arrays.asList("USUARIO", "EDAD"), "Ok"+agState.getNombreUsuario()+", deberias tener cuidado con esta enfermedad."
						 												 + "\n" + "¿Tenes alguna patologia previa?", 6, 3, 7, 1));
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
    	
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "MORTALIDAD"), "- Lamentablemente el COVID-19 ya ha dejado 500,164 muertos en todo el mundo", 9, 5, 5, 1));
    	
    	listaReglas.add(new Regla(Arrays.asList("PREGUNTA", "MORTALIDAD", "LUGAR"), "- Lamentablemente el COVID-19 ya ha dejado 1,192 muertos en Argentina", 10, 6, 5, 1));
    	
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
														    			"		. Incapacidad para hablar o moverse", 12, 6, 5, 1)); 
    	
    	//TODO: Reglas sobre el tiempo de la enfermedad, regla de numeros de atencion para casos sospechosos
    	
    	
    	// PARA TODOS LOS ROLES
    	listaReglas.add(new Regla(Arrays.asList("SANTAFE"), "\n"+"Los numeros de la ciudad de Santa Fe es de:"+"\n"
				+"Contagios: 480 personas."+ "\n"
				+"Recuperados: 123 personas."+ "\n"
				+"Fallecidos: 17 personas."+ "\n", 3, 3, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("BUENOSAIRES"), "\n"+"Los numeros de la ciudad de Buenos Aires es de:"+"\n"
				+"Contagios: 780 personas."+ "\n"
				+"Recuperados: 330 personas."+ "\n"
				+"Fallecidos: 38 personas."+ "\n", 3, 3, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("CORDOBA"), "\n"+"Los numeros de la ciudad de Cordoba es de:"+"\n"
				+"Contagios: 148 personas."+ "\n"
				+"Recuperados: 87 personas."+ "\n"
				+"Fallecidos: 1 personas."+ "\n", 3, 3, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("ARGENTINA"), "\n"+"Los numeros de la ciudad de Argentina es de:"+"\n"
				+"Contagios: 1480 personas."+ "\n"
				+"Recuperados: 823 personas."+ "\n"
				+"Fallecidos: 98 personas."+ "\n", 3, 3, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("BRASIL"), "\n"+"Los numeros de la ciudad de Brasil es de:"+"\n"
				+"Contagios: 30587 personas."+ "\n"
				+"Recuperados: 3804 personas."+ "\n"
				+"Fallecidos: 10264 personas."+ "\n", 3, 3, 5, 1));
    	listaReglas.add(new Regla(Arrays.asList("FRANCIA"), "\n"+"Los numeros de la ciudad de Francia es de:"+"\n"
				+"Contagios: 25784 personas."+ "\n"
				+"Recuperados: 1872 personas."+ "\n"
				+"Fallecidos: 15467 personas."+ "\n", 3, 3, 5, 1));
    	
        return listaReglas;
    }

}