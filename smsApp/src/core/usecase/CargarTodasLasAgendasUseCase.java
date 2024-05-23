package core.usecase;

import java.util.List;

import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.AgendaContactos;
import core.domain.services.AgendaService;

public class CargarTodasLasAgendasUseCase {
	private AgendaService agendaService;

    public CargarTodasLasAgendasUseCase(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    public List<AgendaContactos> ejecutar(Usuario usuario) {
        return agendaService.cargarTodasLasAgendas(usuario);
    }
}
