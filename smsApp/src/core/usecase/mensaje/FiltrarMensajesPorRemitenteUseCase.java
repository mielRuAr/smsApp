package core.usecase.mensaje;

import java.util.List;

import core.domain.interfaces.IMensaje;
import core.domain.services.MensajeService;

public class FiltrarMensajesPorRemitenteUseCase {
	private MensajeService mensajeService;

    public FiltrarMensajesPorRemitenteUseCase(MensajeService messageService) {
        this.mensajeService = messageService;
    }

    /**
     * Ejecuta el caso de uso de filtrar mensajes por remitente.
     * @param remitente Número de teléfono del remitente.
     * @return Lista de mensajes enviados por el remitente.
     */
    public List<IMensaje> ejecutar(int remitente) {
        return mensajeService.filtrarMensajesPorRemitente(remitente);
    }
}
