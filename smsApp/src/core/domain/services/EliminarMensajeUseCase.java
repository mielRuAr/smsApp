package core.domain.services;

public class EliminarMensajeUseCase {
	private MensajeService mensajeService;

    public EliminarMensajeUseCase(MensajeService messageService) {
        this.mensajeService = messageService;
    }

    /**
     * Ejecuta el caso de uso de eliminar un mensaje.
     * @param remitente Número de teléfono del remitente.
     * @param destinatario Número de teléfono del destinatario.
     * @param texto Contenido del mensaje.
     */
    public void ejecutar(int remitente, int destinatario, String texto) {
        mensajeService.eliminarMensaje(remitente, destinatario, texto);
    }
}
