package utils;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import agente.AgenteChatbot;
import ambiente.AmbienteChatbot;
import importadasFaia.KnowledgeBasedAgentSimulator;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Main extends JFrame{

    AgenteChatbot agent;
    AmbienteChatbot environment;
    KnowledgeBasedAgentSimulator simulator;
    
    FileWriter fichero;
    
    private static final long serialVersionUID = 1L;

    private JTextArea mensaje = new JTextArea();
    private JTextPane  respuesta = new JTextPane();
    private JScrollPane sp = new JScrollPane(respuesta);
    private String auxRespuesta = new String();

    JButton enviar = new JButton("");

    int aux = 0;
    private final JPanel panel_2 = new JPanel();
    private final JLabel lblNewLabel_1 = new JLabel("ASISTENTE ANTI COVID 19");
    private final JLabel lblNewLabel_2 = new JLabel("Online");

    public Main() throws IOException {
    	
    	//Se abre el fichero donde se guardará el log
    	String filePath = new File("").getAbsolutePath();
		fichero = new FileWriter(filePath + "/src/reporte/log.txt");
    	
        setResizable(false);

        // INICIALIZACION
        agent = new AgenteChatbot();
        environment = new AmbienteChatbot("");
        simulator = new KnowledgeBasedAgentSimulator(environment, agent);

        getContentPane().setLayout(null);
        respuesta.setContentType("text/html");
        respuesta.setToolTipText("");
//        respuesta.setLineWrap(true); aca
//        respuesta.setWrapStyleWord(true); aca
        respuesta.setForeground(Color.BLACK);
        respuesta.setBorder(BorderFactory.createCompoundBorder(
        		respuesta.getBorder(), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        respuesta.setFont(new Font("Tahoma", Font.PLAIN, 14));
//        respuesta.setRows(1); aca
        respuesta.setEditable(false);
        respuesta.setBounds(25, 25, 350, 250);
//        respuesta.setColumns(10); aca
        respuesta.setBackground(Color.WHITE);
        
        Font font = respuesta.getFont();
        auxRespuesta = auxRespuesta + "<html><body style=\"font-family: " + font.getFamily() + "; font-size: "+ font.getSize() + "\">";
        
        sp.setViewportBorder(null);

        sp.setBounds(10, 113, 405, 318);
        getContentPane().add(sp);
        sp.setViewportView(respuesta);
        
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.GRAY));
        panel.setBounds(10, 442, 326, 69);
        getContentPane().add(panel);
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                mensaje.setLineWrap(true);
                mensaje.setWrapStyleWord(true);
                panel.add(mensaje);
        
                // Panel de cargado de mensaje (ingrese aqui la percepcion del agente)
                mensaje.setText(" Escriba aquí su mensaje");
                mensaje.setFont(new Font("Tahoma", Font.ITALIC, 14));
                mensaje.setColumns(10);
                mensaje.setBorder(BorderFactory.createCompoundBorder(
                		mensaje.getBorder(), 
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                
                JPanel panel_1 = new JPanel();
                panel_1.setBorder(null);
                panel_1.setBounds(346, 442, 69, 69);
                getContentPane().add(panel_1);
                panel_1.setLayout(null);
                enviar.setBounds(1, 1, 67, 67);
                enviar.setIcon(new ImageIcon(Main.class.getResource("/img/enviar.png")));
                enviar.setFont(new Font("Century Gothic", Font.BOLD, 20));
                enviar.setBackground(new Color(217, 217, 217));
                panel_1.add(enviar);
                panel_2.setBorder(new LineBorder(Color.GRAY));
                panel_2.setBackground(Color.WHITE);
                panel_2.setBounds(10, 11, 405, 91);
                
                getContentPane().add(panel_2);
                
                JLabel lblNewLabel = new JLabel("");
                lblNewLabel.setBounds(17, 11, 69, 69);
                lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/img/img-perfil.png")));
                lblNewLabel_1.setBounds(105, 20, 245, 27);
                lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
                lblNewLabel_2.setBounds(105, 47, 69, 20);
                lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
                panel_2.setLayout(null);
                panel_2.add(lblNewLabel);
                panel_2.add(lblNewLabel_1);
                panel_2.add(lblNewLabel_2);
                
                
                
                
                enviar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        if(!mensaje.getText().equals(" Escriba aquí su mensaje")){
                            try {
						buscarRespuesta();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                        }
                    }
                });
                
                        mensaje.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyReleased(KeyEvent e) {
                                if(!(e.getKeyCode() == KeyEvent.VK_ENTER)){
                                    mensaje.setText(mensaje.getText());
                                }else{
                                    mensaje.setText("");
                                }
                            }
                            public void keyPressed(KeyEvent e){
                                if(mensaje.getText() != ""){
                                    // para borrar el placeholder text
                                    if(aux == 0){
                                        aux++;
                                        mensaje.setText("");
                                        mensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                    }
                                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                        if(!mensaje.getText().equals(" Escriba aquí su mensaje")){
                                            try {
                								buscarRespuesta();
                							} catch (IOException e1) {
                								// TODO Auto-generated catch block
                								e1.printStackTrace();
                							}
                                        }
                                    }
                                }
                            }
                        });
                        
                                mensaje.addFocusListener(new FocusAdapter() {
                                    @Override
                                    public void focusGained(FocusEvent feg) {
                                        mensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                        mensaje.setText("");
                                    }
                                    public void focusLost(FocusEvent fel) {
                                        if (mensaje.getText().equals("")) {
                                            mensaje.setText(" Escriba aquí su mensaje");
                                            mensaje.setFont(new Font("Tahoma", Font.ITALIC, 14));
                                        }
                                    }
                                });

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
            	try {
					fichero.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                System.out.println("Fin");
                System.exit(EXIT_ON_CLOSE);
            }
        });
        this.setBounds(300,100,433,551);

    }

    public void buscarRespuesta() throws IOException {
        if(!mensaje.getText().isEmpty()){
            Calendar calendario = Calendar.getInstance();
            int hora =calendario.get(Calendar.HOUR_OF_DAY);
            int minutos = calendario.get(Calendar.MINUTE);
            int segundos = calendario.get(Calendar.SECOND);
            String time = hora+":"+minutos+":"+segundos;
            String oracionAux = mensaje.getText();
            environment.getEnvironmentState().setMensaje(mensaje.getText().toUpperCase());

            /////////////// PRIMER PASO ///////////////////////
            ArrayList<String> resultados = simulator.start();
            ///////////////////////////////////////////////////

            String log = "";
            // Operacion a realizar por el ChatBot
            String rta = resultados.get(0);
            // Log con detalles sobre las 3 fases que realiza el ChatBot para decidir que hacer
            if(resultados.size()>1) {
                log = resultados.get(1);
            }
            
            auxRespuesta = auxRespuesta + "<p><b>" + time + " - Usuario</b><br>" + oracionAux + "</p>";
            
//            auxRespuesta = auxRespuesta + "<br>";
            
//            respuesta.setText(respuesta.getText() + "<p>[ " + time+" - Usuario ]" + oracionAux + "</p><p>asd</p>");
//            respuesta.setText(respuesta.getText()+"<br><br>");
            
            auxRespuesta = auxRespuesta + "<p><b style=\"color:blue\">" + time + " - Asistente</b><br>" + rta + "</p>";
            
//            auxRespuesta = auxRespuesta + "<br>";
            
            // el if esta para que cuando no existan problemas, no diga "<Accion>"
//            if(rta.equals("** no privacy violations detected **")){
//                respuesta.setText(respuesta.getText()+ "<p>[ " +time+" - <b>Asistente</b> ]<br>" + rta + "</p>");
//            }
//            else{
//                respuesta.setText(respuesta.getText()+ "<p>[ " +time+" - <b>Asistente</b> ]<br>" + rta + "</p>");
//            }

            //Guardar el log en el fichero
            fichero.write(log);

//            respuesta.setText(respuesta.getText() + "<br>");

            // Separador entre cada interaccion, que es tan largo como el ultimo renglo de la respuesta
//            for(int k=0; k <= rta.length(); k++){
//            for(int k=0; k <= 37; k++){
//                respuesta.setText(respuesta.getText() + "--");
//            }
//            respuesta.setText(respuesta.getText() + "<br>");
            
            respuesta.setText(auxRespuesta);
            aux = 0;
            enviar.setFocusPainted(true);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {                	
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
