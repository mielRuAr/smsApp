package core.usecase.agenda;

import core.domain.models.abstracts.Usuario;
import core.domain.services.AgendaService;

public class EliminarContactoUseCase {
	 private AgendaService agendaService;

	    public EliminarContactoUseCase(AgendaService agendaService) {
	        this.agendaService = agendaService;
	    }

	    /**
	     * Ejecuta el caso de uso de eliminar un contacto.
	     * @param usuario El usuario que realiza la operación.
	     * @param numeroUsuario El número del usuario cuya agenda se está modificando.
	     * @param nombreContacto El nombre del contacto a eliminar.
	     * @return true si el contacto fue eliminado, false en caso contrario.
	     */
	    public boolean ejecutar(Usuario usuario, int numeroUsuario, String nombreContacto) {
	        return agendaService.eliminarContacto(usuario, numeroUsuario, nombreContacto);
	    }
	    
	    
}
