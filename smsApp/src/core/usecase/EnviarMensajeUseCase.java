package core.usecase;

import java.time.LocalDate;

import core.domain.interfaces.IUsuario;
import core.domain.models.abstracts.Mensaje;
import core.domain.models.concretes.SMS;
import core.domain.interfaces.IMensaje;
import core.domain.services.MessageService;
import core.domain.services.UserService;

public class EnviarMensajeUseCase {
	 private MessageService messageService;
	    private UserService userService;

	    /**
	     * Constructor para inicializar los servicios.
	     * @param messageService El servicio de mensajes.
	     * @param userService El servicio de usuarios.
	     */
	    public EnviarMensajeUseCase(MessageService messageService, UserService userService) {
	        this.messageService = messageService;
	        this.userService = userService;
	    }

	    /**
	     * Envía un mensaje de un usuario a otro.
	     * @param remitenteNumero Número de teléfono del remitente.
	     * @param destinatarioNumero Número de teléfono del destinatario.
	     * @param texto Contenido del mensaje.
	     */
	    public void enviarMensaje(int remitenteNumero, int destinatarioNumero, String texto) {
	        Mensaje mensaje = new SMS();
	        mensaje.setRemitente(remitenteNumero);
	        mensaje.setDestinatario(destinatarioNumero);
	        mensaje.setTexto(texto);
	        mensaje.setTimeStamp(LocalDate.now());
	        messageService.enviarMensaje(mensaje);
	    }
}
