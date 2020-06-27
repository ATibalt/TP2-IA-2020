package utils;

import javax.swing.*;

import agente.AgenteChatbot;
import ambiente.AmbienteChatbot;
import importadasFaia.KnowledgeBasedAgentSimulator;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Main extends JFrame{

    AgenteChatbot agent;
    AmbienteChatbot environment;
    KnowledgeBasedAgentSimulator simulator;

    private static final long serialVersionUID = 1L;

    private JTextArea mensaje = new JTextArea();

    private JLabel labelRta = new JLabel("MONITOR:");
    private JTextArea respuesta = new JTextArea();
    private JScrollPane sp = new JScrollPane(respuesta);

    private JLabel labelLog = new JLabel("LOG:");
    private JTextArea logFases = new JTextArea();
    private JScrollPane logSP = new JScrollPane(logFases);

    private JLabel NCP = new JLabel("North Central Positronics");

    JButton enviar = new JButton("ENVIAR");

    int aux = 0;

    public Main() {
        setResizable(false);

        // INICIALIZACION
        agent = new AgenteChatbot();
        environment = new AmbienteChatbot("");
        simulator = new KnowledgeBasedAgentSimulator(environment, agent);

        getContentPane().setLayout(null);

        // Panel de respuesta
        labelRta.setFont(new Font("Century Gothic", Font.BOLD, 16));
        getContentPane().add(labelRta);
        labelRta.setLocation(25,5);
        labelRta.setSize(200,15);

        respuesta.setFont(new Font("Tahoma", Font.PLAIN, 11));
        respuesta.setRows(1);
        respuesta.setEditable(false);
        respuesta.setBounds(25, 25, 350, 250);
        respuesta.setColumns(10);
        respuesta.setBackground(Color.WHITE);

        sp.setBounds(25, 25, 529, 334);
        getContentPane().add(sp);
        sp.setViewportView(respuesta);

        // Panel de informacion sobre las fases
        labelLog.setFont(new Font("Century Gothic", Font.BOLD, 16));
        getContentPane().add(labelLog);
        labelLog.setLocation(560,5);
        labelLog.setSize(200,15);

        logFases.setFont(new Font("Courier New", Font.PLAIN, 11));
        logFases.setRows(1);
        logFases.setEditable(false);
        logFases.setBounds(560,25,350,250);
        logFases.setColumns(10);
        logFases.setBackground(new Color(192, 192, 192));

        logSP.setBounds(560,25,400,334);
        getContentPane().add(logSP);
        logSP.setViewportView(logFases);

        // Panel de cargado de mensaje
        mensaje.setText(" ingrese aqui la percepcion del agente");
        mensaje.setFont(new Font("Segoe UI Historic", Font.ITALIC, 11));
        mensaje.setBounds(25, 370, 529, 91);
        getContentPane().add(mensaje);
        mensaje.setColumns(10);

        // Formato del boton de enviar
        enviar.setBounds(610, 377, 300, 69);
        enviar.setFont(new Font("Century Gothic", Font.BOLD, 20));
        enviar.setBackground(new Color(217, 217, 217));
        getContentPane().add(enviar);

        // EE
        NCP.setFont(new Font("OCR A", Font.BOLD, 9));
        getContentPane().add(NCP);
        NCP.setLocation(865,0);
        NCP.setSize(125,15);

        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(!mensaje.getText().equals(" ingrese aqui la percepcion del agente")){
                    buscarRespuesta();
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
                        mensaje.setFont(new Font("Arial", Font.PLAIN, 12));
                    }
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        if(!mensaje.getText().equals(" ingrese aqui la percepcion del agente")){
                            buscarRespuesta();
                        }
                    }
                }
            }
        });

        mensaje.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent feg) {
                mensaje.setFont(new Font("Arial", Font.PLAIN, 12));
                mensaje.setText("");
            }
            public void focusLost(FocusEvent fel) {
                if (mensaje.getText().equals("")) {
                    mensaje.setText(" ingrese aqui la percepcion del agente");
                    mensaje.setFont(new Font("Segoe UI Historic", Font.ITALIC, 11));
                }
            }
        });

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                System.out.println("Fin");
                System.exit(EXIT_ON_CLOSE);
            }
        });
        this.setBounds(300,100,990,500);

    }

    public void buscarRespuesta(){
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

            respuesta.setText(respuesta.getText()+time+"    <Percepcion>\n      " + oracionAux);
            respuesta.setText(respuesta.getText()+"\n \n");

            // el if esta para que cuando no existan problemas, no diga "<Accion>"
            if(rta.equals("** no privacy violations detected **")){
                respuesta.setText(respuesta.getText()+time+" :\n      " + rta);
            }
            else{
                respuesta.setText(respuesta.getText()+time+"    <Accion>\n      " + rta);
            }

            logFases.setText(log + "\n");
            respuesta.setText(respuesta.getText() + "\n");

            // Separador entre cada interaccion, que es tan largo como el ultimo renglo de la respuesta
            for(int k=0; k <= rta.length(); k++){
                respuesta.setText(respuesta.getText() + "--");
            }
            respuesta.setText(respuesta.getText() + "\n");

            aux = 0;
            enviar.setFocusPainted(true);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
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
