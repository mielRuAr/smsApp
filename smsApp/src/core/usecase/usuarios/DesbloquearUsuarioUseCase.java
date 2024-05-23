package core.usecase.usuarios;

import core.domain.services.UserService;

public class DesbloquearUsuarioUseCase {
	 private UserService userService;

	    public DesbloquearUsuarioUseCase(UserService userService) {
	        this.userService = userService;
	    }

	    /**
	     * Ejecuta el caso de uso para desbloquear un usuario.
	     * @param numeroTelefono El número de teléfono del usuario a desbloquear.
	     */
	    public void ejecutar(int numeroTelefono) {
	        userService.desbloquearUsuario(numeroTelefono);
	    }
}
