package core.domain.services;

import java.util.List;

import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.AgendaContactos;
import core.domain.models.concretes.UsuarioAdmin;
import persistence.RepositorioContactos;

public class AgendaService {
	  private RepositorioContactos repositorioContactos;

	    public AgendaService(RepositorioContactos repositorioContactos) {
	        this.repositorioContactos = repositorioContactos;
	    }

	    public void guardarContacto(Usuario usuario, int numeroUsuario, List<Integer> contactos) {
	        if (usuario instanceof UsuarioAdmin || usuario.getNumero() == numeroUsuario) {
	            AgendaContactos agendaContactos = new AgendaContactos(numeroUsuario, contactos);
	            repositorioContactos.guardarContacto(agendaContactos);
	        } else {
	            throw new UnsupportedOperationException("No tiene permiso para guardar contactos en esta agenda.");
	        }
	    }

	    public List<Integer> cargarContactosPorUsuario(Usuario usuario, int numeroUsuario) {
	        if (usuario instanceof UsuarioAdmin || usuario.getNumero() == numeroUsuario) {
	            return repositorioContactos.cargarContactosPorUsuario(numeroUsuario);
	        } else {
	            throw new UnsupportedOperationException("No tiene permiso para ver esta agenda.");
	        }
	    }

	    public List<AgendaContactos> cargarTodasLasAgendas(Usuario usuario) {
	        if (usuario instanceof UsuarioAdmin) {
	            return repositorioContactos.cargarTodasLasAgendas();
	        } else {
	            throw new UnsupportedOperationException("No tiene permiso para ver todas las agendas.");
	        }
	    }
}
