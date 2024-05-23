package core.usecase.mensaje;

import java.util.List;

import core.domain.interfaces.IMensaje;
import core.domain.services.MensajeService;

public class VerMensajesEnviadosPorUsuarioUseCase {
	private MensajeService mensajeService;

    public VerMensajesEnviadosPorUsuarioUseCase(MensajeService messageService) {
        this.mensajeService = messageService;
    }

    /**
     * Ejecuta el caso de uso de ver mensajes enviados por un usuario.
     * @param remitente Número de teléfono del remitente.
     * @return Lista de mensajes enviados.
     */
    public List<IMensaje> ejecutar(int remitente) {
        return mensajeService.obtenerMensajesPorRemitente(remitente);
    }
}
