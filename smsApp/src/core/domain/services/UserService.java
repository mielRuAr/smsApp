package core.domain.services;

import java.util.List;

import core.domain.interfaces.IUsuario;
import core.domain.models.concretes.UsuarioNormal;
import persistence.RepositorioUsuario;

public class UserService {
	private RepositorioUsuario repositorioUsuario;

    /**
     * Constructor para inicializar el repositorio de usuarios.
     * @param repositorioUsuario El repositorio de usuarios.
     */
    public UserService(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    /**
     * Añade un nuevo usuario al sistema.
     * @param numeroTelefono Número de teléfono del usuario.
     * @param nombre Nombre del usuario.
     * @param tipo Tipo de usuario (normal o administrador).
     * @param contraseña Contraseña del usuario.
     */
    public void añadirUsuario(int numeroTelefono, String nombre, String tipo, String contraseña) {
        IUsuario usuario = new UsuarioNormal(numeroTelefono, nombre, tipo);
        repositorioUsuario.guardarUsuario(usuario, contraseña);
    }

    /**
     * Obtiene un usuario por su número de teléfono.
     * @param numeroTelefono Número de teléfono del usuario.
     * @return Usuario encontrado.
     */
    public IUsuario obtenerUsuario(int numeroTelefono) {
        return repositorioUsuario.buscarUsuarioPorNumero(numeroTelefono);
    }

    /**
     * Obtiene todos los contactos del sistema.
     * @return Lista de todos los usuarios.
     */
    public List<IUsuario> obtenerTodosLosContactos() {
        return repositorioUsuario.cargarTodosLosUsuarios();
    }
}
