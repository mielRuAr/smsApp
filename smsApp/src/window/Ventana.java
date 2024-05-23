package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import core.domain.services.LoginService;
import persistence.RepositorioUsuario;

public class Ventana extends JFrame {
    private JTextField textFieldNumeroTelefono;
    private JPasswordField passwordField;
    private JButton botonIniciarSesion;
    private LoginService loginService;

    public Ventana(LoginService loginService) {
        this.loginService = loginService;

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
                
                // Lógica de autenticación
                if (loginService.autenticarUsuario(numeroTelefono, contraseña)) {
                    VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
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
        return Pattern.matches("\\d+", numeroTelefonoStr);
    }

    public static void main(String[] args) {
        // Aquí deberías inicializar tu repositorio de usuarios y pasarlos al constructor de LoginService
        RepositorioUsuario repositorioUsuario = new RepositorioUsuario(); // Debes reemplazar esto con tu inicialización real
        LoginService loginService = new LoginService(repositorioUsuario);

        Ventana ventana = new Ventana(loginService);
        ventana.setVisible(true);
    }
}
