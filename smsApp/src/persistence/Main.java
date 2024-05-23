package persistence;

import java.time.LocalDate;
import java.util.List;

import core.domain.interfaces.IMensaje;
import core.domain.interfaces.IUsuario;
import core.domain.models.concretes.SMS;
import core.domain.models.concretes.UsuarioNormal;

public class Main {

	    public static void main(String[] args) {
	        RepositorioUsuario repositorioUsuario = new RepositorioUsuario();
	        RepositorioMensaje repositorioMensaje = new RepositorioMensaje();

	        // Añadir usuarios
	        repositorioUsuario.guardarUsuario(new UsuarioNormal(637474747, "Mihai", "Normal"));
	        repositorioUsuario.guardarUsuario(new UsuarioNormal(645775744, "Ariel", "Admin"));

	        // Añadir mensajes
	        IMensaje mensaje1 = new SMS();
	        mensaje1.setRemitente(637474747);
	        mensaje1.setDestinatario(645775744);
	        mensaje1.setTimeStamp(LocalDate.of(2024, 5, 10));
	        mensaje1.setTexto("Hola Ariel");
	        repositorioMensaje.guardarMensaje(mensaje1);

	        IMensaje mensaje2 = new SMS();
	        mensaje2.setRemitente(645775744);
	        mensaje2.setDestinatario(637474747);
	        mensaje2.setTimeStamp(LocalDate.of(2024, 5, 11));
	        mensaje2.setTexto("Hola Mihai");
	        repositorioMensaje.guardarMensaje(mensaje2);

	        // Buscar usuario por número
	        IUsuario usuario = repositorioUsuario.buscarUsuarioPorNumero(637474747);
	        System.out.println("Usuario encontrado: " + usuario.getNombre() + " - " + usuario.getRol());

	        // Cargar todos los usuarios
	        List<IUsuario> usuarios = repositorioUsuario.cargarTodosLosUsuarios();
	        System.out.println("Todos los usuarios:");
	        for (IUsuario u : usuarios) {
	            System.out.println(u.getNombre() + " - " + u.getNumero() + " - " + u.getRol());
	        }

	        // Buscar mensajes por remitente
	        List<IMensaje> mensajesEnviados = repositorioMensaje.buscarMensajesPorNumeroRemitente(637474747);
	        System.out.println("Mensajes enviados por 637474747:");
	        for (IMensaje mensaje : mensajesEnviados) {
	            System.out.println("Para: " + mensaje.getDestinatario() + " - " + mensaje.getTexto());
	        }

	        // Cargar todos los mensajes recibidos por destinatario
	        List<IMensaje> mensajesRecibidos = repositorioMensaje.cargarTodosLosMensajesPorDestinatario(645775744);
	        System.out.println("Mensajes recibidos por 645775744:");
	        for (IMensaje mensaje : mensajesRecibidos) {
	            System.out.println("De: " + mensaje.getRemitente() + " - " + mensaje.getTexto());
	        }
	    }
	}
