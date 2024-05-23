package core.usecase.mensaje;

import java.util.List;

import core.domain.interfaces.IMensaje;
import core.domain.services.MensajeService;

public class VerMensajesRecibidosPorUsuarioUseCase {
	private MensajeService mensajeService;

    public VerMensajesRecibidosPorUsuarioUseCase(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    /**
     * Ejecuta el caso de uso de ver mensajes recibidos por un usuario.
     * @param destinatario Número de teléfono del destinatario.
     * @return Lista de mensajes recibidos.
     */
    public List<IMensaje> ejecutar(int destinatario) {
        return mensajeService.obtenerMensajesPorDestinatario(destinatario);
    }
}
