package core.usecase;

import java.util.List;

import core.domain.models.abstracts.Usuario;
import core.domain.services.AgendaService;

public class GuardarContactoUseCase {
	private AgendaService agendaService;

    public GuardarContactoUseCase(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    public void ejecutar(Usuario usuario, int numeroUsuario, List<Integer> contactos) {
        agendaService.guardarContacto(usuario, numeroUsuario, contactos);
    }
}
