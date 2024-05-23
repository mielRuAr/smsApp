package core.domain.services;

import java.util.List;

import core.domain.interfaces.IUsuario;
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


    /**
     * Agrega un contacto a la agenda de un usuario.
     * @param usuario El usuario que realiza la operación.
     * @param numeroUsuario El número del usuario cuya agenda se está modificando.
     * @param nombreContacto El nombre del contacto a agregar.
     * @param numeroContacto El número del contacto a agregar.
     * @return true si el contacto fue agregado, false si ya existe.
     */
    public boolean agregarContacto(IUsuario usuario, int numeroUsuario, String nombreContacto, String numeroContacto) {
        if (usuario instanceof UsuarioAdmin || usuario.getNumero() == numeroUsuario) {
            if (repositorioUsuario.buscarUsuarioPorNumero(Integer.parseInt(numeroContacto)) == null) {
                throw new IllegalArgumentException("El número de contacto no está registrado en el sistema.");
            }

            if (repositorioContactos.contactoExiste(numeroUsuario, numeroContacto)) {
                return false; // El contacto ya existe
            }

            repositorioContactos.guardarContacto(numeroUsuario, nombreContacto, numeroContacto);
            return true;
        } else {
            throw new UnsupportedOperationException("No tiene permiso para agregar contactos en esta agenda.");
        }
    }

    /**
     * Elimina un contacto de la agenda de un usuario.
     * @param usuario El usuario que realiza la operación.
     * @param numeroUsuario El número del usuario cuya agenda se está modificando.
     * @param nombreContacto El nombre del contacto a eliminar.
     * @return true si el contacto fue eliminado, false en caso contrario.
     */
    public boolean eliminarContacto(IUsuario usuario, int numeroUsuario, String nombreContacto) {
        if (usuario instanceof UsuarioAdmin || usuario.getNumero() == numeroUsuario) {
            return repositorioContactos.eliminarContacto(numeroUsuario, nombreContacto);
        } else {
            throw new UnsupportedOperationException("No tiene permiso para eliminar contactos en esta agenda.");
        }
    }

    /**
     * Carga la lista de contactos de un usuario.
     * @param usuario El usuario que realiza la operación.
     * @param numeroUsuario El número del usuario cuya agenda se está consultando.
     * @return Lista de contactos.
     */
    public List<String> cargarContactosPorUsuario(IUsuario usuario, int numeroUsuario) {
        if (usuario instanceof UsuarioAdmin || usuario.getNumero() == numeroUsuario) {
            return repositorioContactos.cargarContactosPorUsuario(numeroUsuario);
        } else {
            throw new UnsupportedOperationException("No tiene permiso para ver esta agenda.");
        }
    }
    
    
}
