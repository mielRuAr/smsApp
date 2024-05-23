package core.usecase.usuarios;

import core.domain.services.LoginService;

public class LoginUseCase {
	   private LoginService loginService;

	    public LoginUseCase(LoginService loginService) {
	        this.loginService = loginService;
	    }

	    /**
	     * Verifica si un usuario está bloqueado.
	     * @param numeroTelefono El número de teléfono del usuario.
	     * @return true si el usuario está bloqueado, false en caso contrario.
	     */
	    public boolean estaBloqueado(int numeroTelefono) {
	        return loginService.estaBloqueado(numeroTelefono);
	    }

	    /**
	     * Autentica a un usuario.
	     * @param numeroTelefono El número de teléfono del usuario.
	     * @param password La contraseña del usuario.
	     * @return true si la autenticación es exitosa, false en caso contrario.
	     */
	    public boolean autenticarUsuario(int numeroTelefono, String password) {
	        return loginService.autenticarUsuario(numeroTelefono, password);
	    }
}
