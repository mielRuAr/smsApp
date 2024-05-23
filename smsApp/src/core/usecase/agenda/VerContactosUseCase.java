package core.usecase.agenda;


import core.domain.services.UserService;

import java.util.List;

import core.domain.interfaces.*;

public class VerContactosUseCase {
	  private UserService userService;

	    /**
	     * Constructor para inicializar el servicio de usuarios.
	     * @param userService El servicio de usuarios.
	     */
	    public VerContactosUseCase(UserService userService) {
	        this.userService = userService;
	    }

	    /**
	     * Ejecuta el caso de uso para obtener todos los contactos del sistema.
	     * @return Lista de todos los usuarios.
	     */
	    public List<IUsuario> ejecutar() {
	        return userService.obtenerTodosLosContactos();
	    }
}
