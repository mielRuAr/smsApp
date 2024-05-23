package controllers;


import java.util.List;
import java.util.Scanner;

import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.AgendaContactos;
import core.domain.models.concretes.UsuarioAdmin;
import core.domain.models.concretes.UsuarioNormal;
import core.domain.services.AgendaService;
import core.domain.services.LoginService;
import core.domain.services.MensajeService;
import core.domain.services.UserService;
import core.usecase.agenda.CargarContactosPorUsuarioUseCase;
import core.usecase.agenda.EliminarContactoUseCase;
import core.usecase.agenda.GuardarContactoUseCase;
import core.usecase.agenda.VerContactosUseCase;
import core.usecase.mensaje.EnviarMensajeUseCase;
import core.usecase.mensaje.VerMensajesUseCase;
import core.usecase.usuarios.LoginUseCase;
import persistence.RepositorioContactos;
import persistence.RepositorioMensaje;
import persistence.RepositorioUsuario;

public class Main {
	private static LoginUseCase loginUseCase;
    private static PostmanController postmanController;

    public static void main(String[] args) {
        // Instancia del repositorio
        RepositorioUsuario repositorioUsuario = new RepositorioUsuario();
        RepositorioMensaje repositorioMensaje = new RepositorioMensaje();
        RepositorioContactos repositorioContactos = new RepositorioContactos();

        // Instancia del servicio
        LoginService loginService = new LoginService(repositorioUsuario);
        MensajeService messageService = new MensajeService(repositorioMensaje);
        UserService userService = new UserService(repositorioUsuario);
        AgendaService agendaService = new AgendaService(repositorioContactos, repositorioUsuario);

        // Instancia de los casos de uso
        loginUseCase = new LoginUseCase(loginService);
        EnviarMensajeUseCase enviarMensajeUseCase = new EnviarMensajeUseCase(messageService);
        VerContactosUseCase verContactosUseCase = new VerContactosUseCase(userService);
        VerMensajesUseCase verMensajesUseCase = new VerMensajesUseCase(messageService);
        GuardarContactoUseCase guardarContactoUseCase = new GuardarContactoUseCase(agendaService);
        CargarContactosPorUsuarioUseCase cargarContactosPorUsuarioUseCase = new CargarContactosPorUsuarioUseCase(agendaService);
        EliminarContactoUseCase eliminarContactoUseCase = new EliminarContactoUseCase(agendaService);

        // Instancia de los controladores
        LoginController loginController = new LoginController(loginUseCase);
        postmanController = new PostmanController(enviarMensajeUseCase, verContactosUseCase, verMensajesUseCase);
        AgendaController agendaController = new AgendaController(guardarContactoUseCase, cargarContactosPorUsuarioUseCase, eliminarContactoUseCase);

        // Iniciar la aplicación
        iniciarAplicacion(loginController, postmanController, agendaController);
    }

    private static void iniciarAplicacion(LoginController loginController, PostmanController postmanController, AgendaController agendaController) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de mensajería");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (opcion) {
            case 1:
                int numeroTelefono = loginController.iniciarSesion();
                if (numeroTelefono != -1) {
                    Usuario usuario = obtenerUsuarioPorNumero(numeroTelefono);
                    MenuController menuController = new MenuController(usuario, postmanController, agendaController);
                    menuController.mostrarMenuPrincipal(scanner);
                }
                break;
            case 2:
                System.out.println("Saliendo del sistema...");
                return;
            default:
                System.out.println("Opción no válida. Saliendo...");
                return;
        }
    }

    private static Usuario obtenerUsuarioPorNumero(int numeroTelefono) {
        // Simulación para obtener el usuario por su número de teléfono
        // En la implementación real, se obtendría de la base de datos o repositorio
        if (numeroTelefono == 123456789) {
            return new UsuarioAdmin(numeroTelefono, "Admin", "admin");
        } else {
            return new UsuarioNormal(numeroTelefono, "User", "normal");
        }
    }
}