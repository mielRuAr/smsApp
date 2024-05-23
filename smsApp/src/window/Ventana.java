package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import core.domain.services.LoginService;
import persistence.RepositorioUsuario;

public class Ventana extends JFrame {
    private JTextField textFieldNumeroTelefono;
    private JPasswordField passwordField;
    private JButton botonIniciarSesion;
    private LoginService loginService;
    private RepositorioUsuario repositorioUsuario;

    public Ventana(LoginService loginService, RepositorioUsuario repositorioUsuario) {
        this.loginService = loginService;
        this.repositorioUsuario = repositorioUsuario;

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelNumeroTelefono = new JLabel("Número de teléfono:");
        textFieldNumeroTelefono = new JTextField(15); // Ajustar el ancho del campo de texto
        JLabel labelContraseña = new JLabel("Contraseña:");
        passwordField = new JPasswordField(15); // Ajustar el ancho del campo de contraseña
        botonIniciarSesion = new JButton("Iniciar Sesión");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelNumeroTelefono, gbc);
        gbc.gridy++;
        panel.add(labelContraseña, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(textFieldNumeroTelefono, gbc);
        gbc.gridy++;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        panel.add(botonIniciarSesion, gbc);

        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroTelefonoStr = textFieldNumeroTelefono.getText();
                String contraseña = new String(passwordField.getPassword());

                // Validar que el número de teléfono sea un número
                if (!validarNumeroTelefono(numeroTelefonoStr)) {
                    JOptionPane.showMessageDialog(Ventana.this, "Por favor, introduzca solo números en el campo de número de teléfono", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar si los campos están vacíos
                if (numeroTelefonoStr.isEmpty() || contraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(Ventana.this, "Por favor, rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int numeroTelefono = Integer.parseInt(numeroTelefonoStr);

                // Autenticar al usuario
                if (loginService.autenticarUsuario(numeroTelefono, contraseña)) {
                    boolean esAdmin = repositorioUsuario.esAdmin(numeroTelefono);
                    VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(esAdmin);
                    ventanaPrincipal.setVisible(true);
                    dispose(); // Cerrar la ventana de inicio de sesión después de iniciar sesión
                } else {
                    JOptionPane.showMessageDialog(Ventana.this, "Número de teléfono o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
    }

    // Método para validar que el número de teléfono sea un número
    private boolean validarNumeroTelefono(String numeroTelefonoStr) {
        return numeroTelefonoStr.matches("\\d+");
    }

    public static void main(String[] args) {
        RepositorioUsuario repositorioUsuario = new RepositorioUsuario(); // Instanciar el repositorio de usuarios
        LoginService loginService = new LoginService(repositorioUsuario); // Instanciar el servicio de login con el repositorio

        Ventana ventana = new Ventana(loginService, repositorioUsuario);
        ventana.setVisible(true);
    }
}
