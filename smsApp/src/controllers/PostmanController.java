package controllers;

import java.util.List;

import core.domain.interfaces.IMensaje;
import core.domain.interfaces.IUsuario;
import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.AgendaContactos;
import core.domain.services.MensajeService;
import core.domain.services.UserService;
import core.usecase.agenda.CargarContactosPorUsuarioUseCase;
import core.usecase.agenda.GuardarContactoUseCase;
import core.usecase.agenda.VerContactosUseCase;
import core.usecase.mensaje.EnviarMensajeUseCase;
import core.usecase.mensaje.VerMensajesUseCase;

public class PostmanController {
	   private EnviarMensajeUseCase enviarMensajeUseCase;
	    private VerContactosUseCase verContactosUseCase;
	    private VerMensajesUseCase verMensajesUseCase;

	    public PostmanController(EnviarMensajeUseCase enviarMensajeUseCase, VerContactosUseCase verContactosUseCase, VerMensajesUseCase verMensajesUseCase) {
	        this.enviarMensajeUseCase = enviarMensajeUseCase;
	        this.verContactosUseCase = verContactosUseCase;
	        this.verMensajesUseCase = verMensajesUseCase;
	    }

	    /**
	     * Envía un mensaje de un usuario a otro.
	     * @param remitenteNumero Número de teléfono del remitente.
	     * @param destinatarioNumero Número de teléfono del destinatario.
	     * @param texto Contenido del mensaje.
	     */
	    public void enviarMensaje(int remitenteNumero, int destinatarioNumero, String texto) {
	        enviarMensajeUseCase.enviarMensaje(remitenteNumero, destinatarioNumero, texto);
	    }

	    /**
	     * Obtiene todos los contactos del sistema.
	     * @return Lista de todos los usuarios.
	     */
	    public List<IUsuario> verContactos() {
	        return verContactosUseCase.verContactos();
	    }

	    /**
	     * Obtiene los mensajes enviados por un usuario específico.
	     * @param numeroTelefono Número de teléfono del remitente.
	     * @return Lista de mensajes enviados por el usuario.
	     */
	    public List<IMensaje> verMensajesEnviados(int numeroTelefono) {
	        return verMensajesUseCase.verMensajesEnviados(numeroTelefono);
	    }

	    /**
	     * Obtiene los mensajes recibidos por un usuario específico.
	     * @param numeroTelefono Número de teléfono del destinatario.
	     * @return Lista de mensajes recibidos por el usuario.
	     */
	    public List<IMensaje> verMensajesRecibidos(int numeroTelefono) {
	        return verMensajesUseCase.verMensajesRecibidos(numeroTelefono);
	    }

	    /**
	     * Obtiene los mensajes recibidos de un emisor específico.
	     * @param numeroTelefonoDestinatario Número de teléfono del destinatario.
	     * @param numeroTelefonoEmisor Número de teléfono del remitente.
	     * @return Lista de mensajes recibidos por el usuario de un emisor específico.
	     */
	    public List<IMensaje> verMensajesRecibidosDe(int numeroTelefonoDestinatario, int numeroTelefonoEmisor) {
	        return verMensajesUseCase.verMensajesRecibidosDe(numeroTelefonoDestinatario, numeroTelefonoEmisor);
	    }
}
