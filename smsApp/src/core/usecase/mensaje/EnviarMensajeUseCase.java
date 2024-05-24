package core.usecase.mensaje;

import java.time.LocalDate;
import java.time.LocalDateTime;

import core.domain.models.abstracts.Mensaje;
import core.domain.models.concretes.SMS;
import core.domain.services.MensajeService;
import core.domain.interfaces.IMensaje;


public class EnviarMensajeUseCase {
	 private MensajeService mensajeService;

	    public EnviarMensajeUseCase(MensajeService mensajeService) {
	        this.mensajeService = mensajeService;
	    }

	    /**
	     * Ejecuta el caso de uso de enviar un mensaje.
	     * @param remitenteNumero Número de teléfono del remitente.
	     * @param destinatarioNumero Número de teléfono del destinatario.
	     * @param texto Contenido del mensaje.
	     */
	    public void ejecutar(int remitenteNumero, int destinatarioNumero, String texto) {
	        IMensaje mensaje = new SMS(remitenteNumero, destinatarioNumero, LocalDateTime.now(), texto);
	        mensajeService.enviarMensaje(mensaje);
	    }
}
