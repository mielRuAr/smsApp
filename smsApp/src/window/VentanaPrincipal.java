package window;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import core.domain.interfaces.IUsuario;
import core.domain.models.concretes.UsuarioAdmin;

public class VentanaPrincipal extends JFrame {
    private JButton btnEnviarMensaje;
    private JButton btnVerMensajesRecibidos;
    private JButton btnVerMensajesEnviados;
    private JButton btnAgenda;
    private JButton btnBuscarMensaje;
    private JButton btnCerrarSesion;
    private JButton btnGestionUsuarios;

    public VentanaPrincipal(IUsuario iUsuario) {
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

        panel.add(btnEnviarMensaje);
        panel.add(btnVerMensajesRecibidos);
        panel.add(btnVerMensajesEnviados);
        panel.add(btnAgenda);
        panel.add(btnBuscarMensaje);
        panel.add(btnCerrarSesion);

        // Verificar si el usuario es administrador para mostrar el botón de gestión de usuarios
        if (iUsuario instanceof UsuarioAdmin) {
            btnGestionUsuarios = new JButton("Gestión de Usuarios");
            panel.add(btnGestionUsuarios);
        }

        // Agregar ActionListener al botón "Enviar Mensaje"
        btnEnviarMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar la ventana de enviar mensaje
                VentanaEnviarMensaje ventanaEnviarMensaje = new VentanaEnviarMensaje(VentanaPrincipal.this);
                ventanaEnviarMensaje.setVisible(true);
                
            }
        });
        
        btnAgenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar la ventana de enviar mensaje
                VentanaAgenda ventanaAgenda = new VentanaAgenda(VentanaPrincipal.this, null, null);
                ventanaAgenda.setVisible(true);
                
            }
        });
        
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // cerrar app
                dispose();
                
            }
        });

        add(panel);
    }

}
