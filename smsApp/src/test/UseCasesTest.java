package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import core.domain.interfaces.IMensaje;
import core.domain.interfaces.IUsuario;
import core.domain.models.concretes.SMS;
import core.domain.models.concretes.UsuarioNormal;
import core.domain.services.MensajeService;
import core.domain.services.UserService;
import persistence.RepositorioMensaje;
import persistence.RepositorioUsuario;

public class UseCasesTest {
	
	  private MensajeService mensajeService;
	    private UserService userService;

	    @Before
	    public void setUp() {
	        RepositorioMensaje repositorioMensaje = new RepositorioMensaje();
	        RepositorioUsuario repositorioUsuario = new RepositorioUsuario();

	        mensajeService = new MensajeService(repositorioMensaje);
	        userService = new UserService(repositorioUsuario);

	        // Crear usuarios para los tests
	        repositorioUsuario.guardarUsuario(new UsuarioNormal(123456789, "Alice", "normal"), "password");
	        repositorioUsuario.guardarUsuario(new UsuarioNormal(987654321, "Bob", "normal"), "password");
	    }

	    @Test
	    public void testEnviarMensaje() {
	        IUsuario remitente = userService.obtenerUsuario(123456789);
	        IUsuario destinatario = userService.obtenerUsuario(987654321);
	        String texto = "Hola";
	        IMensaje mensaje = new SMS(remitente.getNumero(), destinatario.getNumero(), LocalDateTime.now(), texto);
	        mensajeService.enviarMensaje(mensaje);

	        List<IMensaje> mensajes = mensajeService.obtenerMensajesPorRemitente(remitente.getNumero());
	        boolean mensajeEncontrado = mensajes.stream()
	                .anyMatch(m -> m.getTexto().equals(texto) && m.getDestinatario() == destinatario.getNumero());
	        assertTrue(mensajeEncontrado);
	    }

	    @Test
	    public void testVerMensajesEnviados() {
	        IUsuario remitente = userService.obtenerUsuario(123456789);
	        IUsuario destinatario = userService.obtenerUsuario(987654321);
	        String texto = "Hola";
	        IMensaje mensaje = new SMS(remitente.getNumero(), destinatario.getNumero(), LocalDateTime.now(), texto);
	        mensajeService.enviarMensaje(mensaje);

	        List<IMensaje> mensajes = mensajeService.obtenerMensajesPorRemitente(remitente.getNumero());
	        assertFalse(mensajes.isEmpty());
	    }

	    @Test
	    public void testVerMensajesRecibidos() {
	        IUsuario remitente = userService.obtenerUsuario(123456789);
	        IUsuario destinatario = userService.obtenerUsuario(987654321);
	        String texto = "Hola";
	        IMensaje mensaje = new SMS(remitente.getNumero(), destinatario.getNumero(), LocalDateTime.now(), texto);
	        mensajeService.enviarMensaje(mensaje);

	        List<IMensaje> mensajes = mensajeService.obtenerMensajesPorDestinatario(destinatario.getNumero());
	        assertFalse(mensajes.isEmpty());
	    }

	    @Test
	    public void testFiltrarMensajesPorDestinatario() {
	        IUsuario remitente = userService.obtenerUsuario(123456789);
	        IUsuario destinatario = userService.obtenerUsuario(987654321);
	        String texto = "Hola";
	        IMensaje mensaje = new SMS(remitente.getNumero(), destinatario.getNumero(), LocalDateTime.now(), texto);
	        mensajeService.enviarMensaje(mensaje);

	        List<IMensaje> mensajes = mensajeService.obtenerMensajesPorRemitente(remitente.getNumero());
	        boolean mensajeEncontrado = mensajes.stream()
	                .anyMatch(m -> m.getDestinatario() == destinatario.getNumero());
	        assertTrue(mensajeEncontrado);
	    }

	    @Test
	    public void testEliminarMensaje() {
	        IUsuario remitente = userService.obtenerUsuario(123456789);
	        IUsuario destinatario = userService.obtenerUsuario(987654321);
	        String texto = "Hola";
	        IMensaje mensaje = new SMS(remitente.getNumero(), destinatario.getNumero(), LocalDateTime.now(), texto);
	        mensajeService.enviarMensaje(mensaje);

	        mensajeService.eliminarMensaje(remitente.getNumero(), destinatario.getNumero(), texto);

	        List<IMensaje> mensajes = mensajeService.obtenerMensajesPorRemitente(remitente.getNumero());
	        boolean mensajeEncontrado = mensajes.stream()
	                .noneMatch(m -> m.getTexto().equals(texto) && m.getDestinatario() == destinatario.getNumero());
	        assertTrue(mensajeEncontrado);
	    }

}
