package core.usecase.usuarios;

import core.domain.services.UserService;

public class EliminarUsuarioUseCase {
	  private UserService userService;

	    public EliminarUsuarioUseCase(UserService userService) {
	        this.userService = userService;
	    }

	    /**
	     * Ejecuta el caso de uso para eliminar un usuario.
	     * @param numeroTelefono El número de teléfono del usuario a eliminar.
	     */
	    public void ejecutar(int numeroTelefono) {
	        userService.eliminarUsuario(numeroTelefono);
	    }
}
