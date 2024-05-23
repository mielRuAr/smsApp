package core.usecase.agenda;

import java.util.List;

import core.domain.models.abstracts.Usuario;
import core.domain.services.AgendaService;

public class CargarContactosPorUsuarioUseCase {
	private AgendaService agendaService;

    public CargarContactosPorUsuarioUseCase(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    public List<String> ejecutar(Usuario usuario, int numeroUsuario) {
        return agendaService.cargarContactosPorUsuario(usuario, numeroUsuario);
    }
}
