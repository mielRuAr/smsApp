package core.usecase.usuarios;

import core.domain.services.UserService;

public class BloquearUsuarioUseCase {
	  private UserService userService;

	    public BloquearUsuarioUseCase(UserService userService) {
	        this.userService = userService;
	    }

	    /**
	     * Ejecuta el caso de uso para bloquear un usuario.
	     * @param numeroTelefono El número de teléfono del usuario a bloquear.
	     */
	    public void ejecutar(int numeroTelefono) {
	        userService.bloquearUsuario(numeroTelefono);
	    }
}
