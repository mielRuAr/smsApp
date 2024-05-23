package window;

import controllers.AgendaController;
import core.domain.models.abstracts.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgenda extends JFrame {
    private VentanaPrincipal ventanaPrincipal; // Referencia a la ventana principal
    private JButton btnAgregarContacto;
    private JButton btnEliminarContacto;
    private JButton btnVerContactos;
    private JButton btnVolver;
    private JPanel panel;
    private AgendaController agendaController;
    private Usuario usuario; // El usuario actual

    public VentanaAgenda(VentanaPrincipal ventanaPrincipal, AgendaController agendaController, Usuario usuario) {
        this.ventanaPrincipal = ventanaPrincipal; // Guardar referencia a la ventana principal
        this.agendaController = agendaController; // Guardar referencia al controlador de agenda
        this.usuario = usuario; // Guardar el usuario actual

        ventanaPrincipal.setVisible(false);
        setTitle("Agenda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnAgregarContacto = new JButton("Agregar Contacto");
        btnEliminarContacto = new JButton("Eliminar Contacto");
        btnVerContactos = new JButton("Ver Contactos");
        btnVolver = new JButton("Volver");

        // Ajustar tamaño de los botones
        btnAgregarContacto.setPreferredSize(new Dimension(100, 40));
        btnEliminarContacto.setPreferredSize(new Dimension(100, 40));
        btnVerContactos.setPreferredSize(new Dimension(100, 40));
        btnVolver.setPreferredSize(new Dimension(100, 40));

        panel.add(btnAgregarContacto);
        panel.add(btnEliminarContacto);
        panel.add(btnVerContactos);
        panel.add(btnVolver);

        btnAgregarContacto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioAgregarContacto();
            }
        });

        btnEliminarContacto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioEliminarContacto();
            }
        });

        btnVerContactos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioVerContactos();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.setVisible(true); // Mostrar ventana principal
                dispose(); // Cerrar ventana actual
            }
        });

        add(panel);
    }

    private void mostrarFormularioAgregarContacto() {
        JPanel agregarContactoPanel = new JPanel();
        agregarContactoPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblNumero = new JLabel("Número:");
        JTextField txtNumero = new JTextField();

        agregarContactoPanel.add(lblNombre);
        agregarContactoPanel.add(txtNombre);
        agregarContactoPanel.add(lblNumero);
        agregarContactoPanel.add(txtNumero);

        int result = JOptionPane.showConfirmDialog(null, agregarContactoPanel,
                "Agregar Contacto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText();
            String numero = txtNumero.getText();
            // Aquí puedes llamar al método correspondiente del controlador de agenda
        }
    }

    private void mostrarFormularioEliminarContacto() {
        JPanel eliminarContactoPanel = new JPanel();
        eliminarContactoPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        eliminarContactoPanel.add(lblNombre);
        eliminarContactoPanel.add(txtNombre);

        int result = JOptionPane.showConfirmDialog(null, eliminarContactoPanel,
                "Eliminar Contacto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText();
            eliminarContacto(nombre);
        }
    }

    private void eliminarContacto(String nombre) {
        try {
            boolean eliminado = agendaController.eliminarContactoUseCase.ejecutar(usuario, usuario.getNumero(), nombre);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Contacto eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el contacto a eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarFormularioVerContactos() {
        JPanel verContactosPanel = new JPanel();
        verContactosPanel.setLayout(new GridLayout(0, 1, 10, 10));

        // Aquí puedes cargar los contactos usando el controlador de agenda
        JTextArea txtAreaContactos = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtAreaContactos);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        verContactosPanel.add(scrollPane);

        JOptionPane.showMessageDialog(this, verContactosPanel, "Ver Contactos",
                JOptionPane.PLAIN_MESSAGE);
    }

}
