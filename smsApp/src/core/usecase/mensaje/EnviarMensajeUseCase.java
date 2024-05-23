package core.usecase.mensaje;

import java.time.LocalDate;


import core.domain.models.abstracts.Mensaje;
import core.domain.models.concretes.SMS;
import core.domain.services.MensajeService;


public class EnviarMensajeUseCase {
	 private MensajeService messageService;


	    /**
	     * Constructor para inicializar los servicios.
	     * @param messageService El servicio de mensajes.
	     * @param userService El servicio de usuarios.
	     */
	    public EnviarMensajeUseCase(MensajeService messageService) {
	        this.messageService = messageService;
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
