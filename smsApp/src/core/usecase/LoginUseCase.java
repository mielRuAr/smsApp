package core.usecase;

import core.domain.services.LoginService;

public class LoginUseCase {
	  private LoginService loginService;
	    private int intentosFallidos = 0;

	    /**
	     * Constructor para inicializar el servicio de login.
	     * @param loginService El servicio de login.
	     */
	    public LoginUseCase(LoginService loginService) {
	        this.loginService = loginService;
	    }

	    /**
	     * Autentica un usuario mediante su número de teléfono y contraseña.
	     * Si falla tres veces, bloquea el usuario.
	     * @param numeroTelefono Número de teléfono del usuario.
	     * @param password Contraseña del usuario.
	     * @return true si la autenticación es exitosa, false en caso contrario.
	     */
	    public boolean autenticarUsuario(int numeroTelefono, String password) {
	    	if (loginService.estaBloqueado(numeroTelefono)) {
	            return false;
	        }
	        if (loginService.autenticarUsuario(numeroTelefono, password)) {
	            intentosFallidos = 0;
	            return true;
	        } else {
	            intentosFallidos++;
	            if (intentosFallidos >= 3) {
	                loginService.bloquearUsuario(numeroTelefono);
	            }
	            return false;
	        }
	    }

	    /**
	     * Verifica si el usuario está bloqueado.
	     * @param numeroTelefono Número de teléfono del usuario.
	     * @return true si el usuario está bloqueado, false en caso contrario.
	     */
	    public boolean estaBloqueado(int numeroTelefono) {
	        return loginService.estaBloqueado(numeroTelefono);
	    }
}
