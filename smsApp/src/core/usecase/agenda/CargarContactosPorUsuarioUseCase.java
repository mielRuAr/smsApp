package core.usecase.agenda;

import java.util.List;

import core.domain.interfaces.IUsuario;
import core.domain.models.abstracts.Usuario;
import core.domain.services.AgendaService;

public class CargarContactosPorUsuarioUseCase {
	  private AgendaService agendaService;

	    public CargarContactosPorUsuarioUseCase(AgendaService agendaService) {
	        this.agendaService = agendaService;
	    }

	    /**
	     * Ejecuta el caso de uso de cargar los contactos de un usuario.
	     * @param usuario El usuario que realiza la operación.
	     * @param numeroUsuario El número del usuario cuya agenda se está consultando.
	     * @return Lista de contactos.
	     */
	    public List<String> ejecutar(IUsuario usuario, int numeroUsuario) {
	        return agendaService.cargarContactosPorUsuario(usuario, numeroUsuario);
	    }
}
