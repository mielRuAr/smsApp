package core.usecase.mensaje;

import java.util.List;
import java.util.stream.Collectors;

import core.domain.interfaces.IMensaje;
import core.domain.services.MensajeService;

public class VerMensajesUseCase {
	 private MensajeService messageService;

	    /**
	     * Constructor para inicializar el servicio de mensajes.
	     * @param messageService El servicio de mensajes.
	     */
	    public VerMensajesUseCase(MensajeService messageService) {
	        this.messageService = messageService;
	    }

	    /**
	     * Obtiene los mensajes enviados por un usuario específico.
	     * @param numeroTelefono Número de teléfono del remitente.
	     * @return Lista de mensajes enviados por el usuario.
	     */
	    public List<IMensaje> verMensajesEnviados(int numeroTelefono) {
	        return messageService.obtenerMensajesPorRemitente(numeroTelefono);
	    }

	    /**
	     * Obtiene los mensajes recibidos por un usuario específico.
	     * @param numeroTelefono Número de teléfono del destinatario.
	     * @return Lista de mensajes recibidos por el usuario.
	     */
	    public List<IMensaje> verMensajesRecibidos(int numeroTelefono) {
	        return messageService.obtenerMensajesPorDestinatario(numeroTelefono);
	    }

	    /**
	     * Obtiene los mensajes recibidos de un emisor específico.
	     * @param numeroTelefonoDestinatario Número de teléfono del destinatario.
	     * @param numeroTelefonoEmisor Número de teléfono del remitente.
	     * @return Lista de mensajes recibidos por el usuario de un emisor específico.
	     */
	    public List<IMensaje> verMensajesRecibidosDe(int numeroTelefonoDestinatario, int numeroTelefonoEmisor) {
	        List<IMensaje> mensajesRecibidos = messageService.obtenerMensajesPorDestinatario(numeroTelefonoDestinatario);
	        return mensajesRecibidos.stream()
	                                .filter(mensaje -> mensaje.getRemitente() == numeroTelefonoEmisor)
	                                .collect(Collectors.toList());
	    }
}
