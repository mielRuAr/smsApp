package core.domain.services;

import java.util.List;

import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.AgendaContactos;
import core.domain.models.concretes.UsuarioAdmin;
import persistence.RepositorioContactos;
import persistence.RepositorioUsuario;

public class AgendaService {
	  private RepositorioContactos repositorioContactos;
	    private RepositorioUsuario repositorioUsuario;

	    public AgendaService(RepositorioContactos repositorioContactos, RepositorioUsuario repositorioUsuario) {
	        this.repositorioContactos = repositorioContactos;
	        this.repositorioUsuario = repositorioUsuario;
	    }

	    public void guardarContacto(Usuario usuario, int numeroUsuario, List<String> contactos) {
	        if (usuario instanceof UsuarioAdmin || usuario.getNumero() == numeroUsuario) {
	            AgendaContactos agendaContactos = new AgendaContactos(numeroUsuario, contactos);
	            repositorioContactos.guardarContacto(agendaContactos);
	        } else {
	            throw new UnsupportedOperationException("No tiene permiso para guardar contactos en esta agenda.");
	        }
	    }

	    public void agregarContacto(Usuario usuario, int numeroUsuario, String nombreContacto, String numeroContacto) {
	        if (usuario instanceof UsuarioAdmin || usuario.getNumero() == numeroUsuario) {
	            if (repositorioUsuario.buscarUsuarioPorNumero(Integer.parseInt(numeroContacto)) == null) {
	                throw new IllegalArgumentException("El número de contacto no está registrado en el sistema.");
	            }
	            
	            List<String> contactos = repositorioContactos.cargarContactosPorUsuario(numeroUsuario);
	            contactos.add(numeroContacto);
	            repositorioContactos.guardarContacto(new AgendaContactos(numeroUsuario, contactos));
	        } else {
	            throw new UnsupportedOperationException("No tiene permiso para agregar contactos en esta agenda.");
	        }
	    }

	    public List<String> cargarContactosPorUsuario(Usuario usuario, int numeroUsuario) {
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
