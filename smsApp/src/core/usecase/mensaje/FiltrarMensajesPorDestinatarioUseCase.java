package core.usecase.mensaje;

import core.domain.services.MensajeService;

import java.util.List;

import core.domain.interfaces.IMensaje;

public class FiltrarMensajesPorDestinatarioUseCase {
	private MensajeService mensajeService;

    public FiltrarMensajesPorDestinatarioUseCase(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    /**
     * Ejecuta el caso de uso para filtrar mensajes por destinatario.
     * @param remitenteNumero Número de teléfono del remitente.
     * @param destinatarioNumero Número de teléfono del destinatario.
     * @return Lista de mensajes filtrados por destinatario.
     */
    public List<IMensaje> ejecutar(int remitenteNumero, int destinatarioNumero) {
        return mensajeService.filtrarMensajesPorDestinatario(remitenteNumero, destinatarioNumero);
    }
}
