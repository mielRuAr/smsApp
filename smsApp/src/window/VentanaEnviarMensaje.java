package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEnviarMensaje extends JFrame {
    private VentanaPrincipal ventanaPrincipal; // Referencia a la ventana principal
    private JButton btnEnviarSMS;
    private JButton btnEnviarMMS;
    private JButton btnVolver;
    private JLabel lblRemitente;
    private JLabel lblDestinatario;
    private JLabel lblContenido;
    private JTextField txtRemitente;
    private JTextField txtDestinatario;
    private JTextArea txtContenido;
    private JTextField txtAdjunto;
    private JPanel panelFormulario;

    public VentanaEnviarMensaje(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal; // Guardar referencia a la ventana principal
        ventanaPrincipal.setVisible(false);
        setTitle("Enviar Mensaje");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(0, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnEnviarSMS = new JButton("Enviar SMS");
        btnEnviarMMS = new JButton("Enviar MMS");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnEnviarSMS);
        panelBotones.add(btnEnviarMMS);
        panelBotones.add(btnVolver);

        // Crear el panel del formulario y ocultarlo inicialmente
        panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(0, 1, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelFormulario.setVisible(false);

        lblRemitente = new JLabel("Remitente:");
        txtRemitente = new JTextField();
        lblDestinatario = new JLabel("Destinatario:");
        txtDestinatario = new JTextField();
        lblContenido = new JLabel("Contenido del mensaje:");
        txtContenido = new JTextArea();
        txtAdjunto = new JTextField(); // Campo para adjuntar archivo (visible solo para MMS)

        panelFormulario.add(lblRemitente);
        panelFormulario.add(txtRemitente);
        panelFormulario.add(lblDestinatario);
        panelFormulario.add(txtDestinatario);
        panelFormulario.add(lblContenido);
        panelFormulario.add(txtContenido);

        // Agregar acción al botón "Enviar SMS"
        btnEnviarSMS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormulario(false);
            }
        });

        // Agregar acción al botón "Enviar MMS"
        btnEnviarMMS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormulario(true);
            }
        });

        // Agregar acción al botón "Volver"
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.setVisible(true);
                dispose();
            }
        });

        // Agregar paneles al frame
        add(panelBotones, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.CENTER);
    }

    private void mostrarFormulario(boolean esMMS) {
        panelFormulario.setVisible(true);
        btnEnviarSMS.setVisible(!esMMS);
        btnEnviarMMS.setVisible(esMMS);
        lblContenido.setVisible(!esMMS);
        txtContenido.setVisible(!esMMS);
        txtAdjunto.setVisible(esMMS);
    }
}
