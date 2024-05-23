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
		btnEnviarSMS.setPreferredSize(new Dimension(100, 200)); // Ajustar tamaño del botón
		btnEnviarMMS = new JButton("Enviar MMS");
		btnEnviarMMS.setPreferredSize(new Dimension(100, 200)); // Ajustar tamaño del botón
		btnVolver = new JButton("Volver");
		btnVolver.setPreferredSize(new Dimension(100, 200)); // Ajustar tamaño del botón

		panelBotones.add(btnEnviarSMS);
		panelBotones.add(btnEnviarMMS);
		panelBotones.add(btnVolver);

		// Agregar acción al botón "Enviar SMS"
		btnEnviarSMS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaFormulario(false);
			}
		});

		// Agregar acción al botón "Enviar MMS"
		btnEnviarMMS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaFormulario(true);
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
	}

	private void mostrarVentanaFormulario(boolean esMMS) {
		JDialog ventanaFormulario = new JDialog(this, "Enviar Mensaje", true);
		ventanaFormulario.setSize(400, 300);
		ventanaFormulario.setLocationRelativeTo(this);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout(0, 1, 10, 10));
		panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		lblRemitente = new JLabel("Remitente:");
		txtRemitente = new JTextField();
		lblDestinatario = new JLabel("Destinatario:");
		txtDestinatario = new JTextField();
		lblContenido = new JLabel("Contenido del mensaje:");
		txtContenido = new JTextArea();
		txtAdjunto = new JTextField();

		panelFormulario.add(lblRemitente);
		panelFormulario.add(txtRemitente);
		panelFormulario.add(lblDestinatario);
		panelFormulario.add(txtDestinatario);
		panelFormulario.add(lblContenido);
		panelFormulario.add(txtContenido);
		if (esMMS) {
			panelFormulario.add(new JLabel("Adjunto:"));
			panelFormulario.add(txtAdjunto);
		}

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Lógica para enviar el mensaje
				ventanaFormulario.dispose(); // Cerrar la ventana flotante
			}
		});

		ventanaFormulario.add(panelFormulario, BorderLayout.CENTER);
		ventanaFormulario.add(btnEnviar, BorderLayout.SOUTH);
		ventanaFormulario.setVisible(true);
	}

}
