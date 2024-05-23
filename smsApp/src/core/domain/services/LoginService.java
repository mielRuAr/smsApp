package core.domain.services;

import persistence.RepositorioUsuario;

public class LoginService {
    private RepositorioUsuario repositorioUsuario;

    /**
     * Constructor para inicializar el repositorio de usuarios.
     * @param repositorioUsuario El repositorio de usuarios.
     */
    public LoginService(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    /**
     * Autentica un usuario mediante su número de teléfono y contraseña.
     * @param numeroTelefono Número de teléfono del usuario.
     * @param password Contraseña del usuario.
     * @return true si la autenticación es exitosa, false en caso contrario.
     */
    public boolean autenticarUsuario(int numeroTelefono, String password) {
        return repositorioUsuario.autenticarUsuario(numeroTelefono, password);
    }

    /**
     * Bloquea un usuario después de tres intentos fallidos.
     * @param numeroTelefono Número de teléfono del usuario.
     */
    public void bloquearUsuario(int numeroTelefono) {
        repositorioUsuario.bloquearUsuario(numeroTelefono);
    }

    /**
     * Verifica si el usuario está bloqueado.
     * @param numeroTelefono Número de teléfono del usuario.
     * @return true si el usuario está bloqueado, false en caso contrario.
     */
    public boolean estaBloqueado(int numeroTelefono) {
        return repositorioUsuario.estaBloqueado(numeroTelefono);
    }
}	
