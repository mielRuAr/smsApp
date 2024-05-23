package controllers;


import java.util.List;
import java.util.Scanner;

import core.domain.interfaces.IUsuario;
import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.AgendaContactos;
import core.domain.models.concretes.UsuarioAdmin;
import core.domain.models.concretes.UsuarioNormal;
import core.domain.services.AgendaService;
import core.domain.services.EliminarMensajeUseCase;
import core.domain.services.LoginService;
import core.domain.services.MensajeService;
import core.domain.services.UserService;
import core.usecase.agenda.CargarContactosPorUsuarioUseCase;
import core.usecase.agenda.EliminarContactoUseCase;
import core.usecase.agenda.AgregarContactoUseCase;
import core.usecase.agenda.VerContactosUseCase;
import core.usecase.mensaje.EnviarMensajeUseCase;
import core.usecase.mensaje.FiltrarMensajesPorRemitenteUseCase;
import core.usecase.mensaje.VerMensajesEnviadosPorUsuarioUseCase;
import core.usecase.mensaje.VerMensajesRecibidosPorUsuarioUseCase;
import core.usecase.mensaje.VerMensajesUseCase;
import core.usecase.usuarios.LoginUseCase;
import persistence.RepositorioContactos;
import persistence.RepositorioMensaje;
import persistence.RepositorioUsuario;

public class Main {
	 private static LoginUseCase loginUseCase;
	    private static PostmanController postmanController;
	    private static AgendaController agendaController;
	    private static UserService userService;

	    public static void main(String[] args) {
	        // Instancia del repositorio
	        RepositorioUsuario repositorioUsuario = new RepositorioUsuario();
	        RepositorioMensaje repositorioMensaje = new RepositorioMensaje();
	        RepositorioContactos repositorioContactos = new RepositorioContactos();

	        // Instancia del servicio
	        LoginService loginService = new LoginService(repositorioUsuario);
	        MensajeService messageService = new MensajeService(repositorioMensaje);
	        userService = new UserService(repositorioUsuario);
	        AgendaService agendaService = new AgendaService(repositorioContactos, repositorioUsuario);

	        // Instancia de los casos de uso
	        loginUseCase = new LoginUseCase(loginService);
	        EnviarMensajeUseCase enviarMensajeUseCase = new EnviarMensajeUseCase(messageService);
	        VerContactosUseCase verContactosUseCase = new VerContactosUseCase(userService);
	        VerMensajesEnviadosPorUsuarioUseCase verMensajesEnviadosPorUsuarioUseCase = new VerMensajesEnviadosPorUsuarioUseCase(messageService);
	        VerMensajesRecibidosPorUsuarioUseCase verMensajesRecibidosPorUsuarioUseCase = new VerMensajesRecibidosPorUsuarioUseCase(messageService);
	        EliminarMensajeUseCase eliminarMensajeUseCase = new EliminarMensajeUseCase(messageService);
	        FiltrarMensajesPorRemitenteUseCase filtrarMensajesPorRemitenteUseCase = new FiltrarMensajesPorRemitenteUseCase(messageService);
	        AgregarContactoUseCase guardarContactoUseCase = new AgregarContactoUseCase(agendaService);
	        CargarContactosPorUsuarioUseCase cargarContactosPorUsuarioUseCase = new CargarContactosPorUsuarioUseCase(agendaService);
	        EliminarContactoUseCase eliminarContactoUseCase = new EliminarContactoUseCase(agendaService);

	        // Instancia de los controladores
	        LoginController loginController = new LoginController(loginUseCase);
	        postmanController = new PostmanController(enviarMensajeUseCase, verContactosUseCase, verMensajesEnviadosPorUsuarioUseCase, verMensajesRecibidosPorUsuarioUseCase, eliminarMensajeUseCase, filtrarMensajesPorRemitenteUseCase);
	        agendaController = new AgendaController(guardarContactoUseCase, cargarContactosPorUsuarioUseCase, eliminarContactoUseCase);

	        // Iniciar la aplicación
	        iniciarAplicacion(loginController, postmanController, agendaController);
	    }

	    private static void iniciarAplicacion(LoginController loginController, PostmanController postmanController, AgendaController agendaController) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Bienvenido al sistema de mensajería");
	        int numeroTelefono = loginController.iniciarSesion();
	        if (numeroTelefono != -1) {
	            IUsuario usuarioInterface = userService.obtenerUsuario(numeroTelefono);
	            if (usuarioInterface == null) {
	                System.out.println("Usuario no encontrado. Saliendo del sistema...");
	                return;
	            }

	            Usuario usuario;
	            if (usuarioInterface.getRol().equalsIgnoreCase("admin")) {
	                usuario = new UsuarioAdmin(usuarioInterface.getNumero(), usuarioInterface.getNombre(), usuarioInterface.getRol());
	            } else {
	                usuario = new UsuarioNormal(usuarioInterface.getNumero(), usuarioInterface.getNombre(), usuarioInterface.getRol());
	            }

	            MenuController menuController = new MenuController(usuario, postmanController, agendaController);
	            menuController.mostrarMenuPrincipal(scanner);
	        } else {
	            System.out.println("No se pudo iniciar sesión. Saliendo del sistema...");
	        }
	    }
	}