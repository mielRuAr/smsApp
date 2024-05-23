package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JButton btnEnviarMensaje;
    private JButton btnVerMensajesRecibidos;
    private JButton btnVerMensajesEnviados;
    private JButton btnAgenda;
    private JButton btnBuscarMensaje;
    private JButton btnCerrarSesion;
    private JButton btnGestionUsuarios;

    public VentanaPrincipal(boolean isAdmin) {
        setTitle("Ventana Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnEnviarMensaje = new JButton("Enviar Mensaje");
        btnVerMensajesRecibidos = new JButton("Ver Mensajes Recibidos");
        btnVerMensajesEnviados = new JButton("Ver Mensajes Enviados");
        btnAgenda = new JButton("Agenda");
        btnBuscarMensaje = new JButton("Buscar Mensaje");
        btnCerrarSesion = new JButton("Cerrar Sesión");

        // Agregar botones comunes para usuarios normales
        panel.add(btnEnviarMensaje);
        panel.add(btnVerMensajesRecibidos);
        panel.add(btnVerMensajesEnviados);
        panel.add(btnAgenda);
        panel.add(btnBuscarMensaje);
        panel.add(btnCerrarSesion);

        // Agregar botones adicionales para administradores
        if (isAdmin) {
            btnGestionUsuarios = new JButton("Gestión de Usuarios");
            panel.add(btnGestionUsuarios);
        }

        add(panel);
    }

    public static void main(String[] args) {
        // Este booleano indica si el usuario logeado es un administrador o no
        boolean isAdmin = true; // Cambiar a true o false según corresponda

        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(isAdmin);
        ventanaPrincipal.setVisible(true);
    }
}
