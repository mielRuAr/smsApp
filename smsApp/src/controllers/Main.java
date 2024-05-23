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
import core.usecase.CargarContactosPorUsuarioUseCase;
import core.usecase.CargarTodasLasAgendasUseCase;
import core.usecase.EnviarMensajeUseCase;
import core.usecase.GuardarContactoUseCase;
import core.usecase.LoginUseCase;
import core.usecase.VerContactosUseCase;
import core.usecase.VerMensajesUseCase;
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
        AgendaService agendaService = new AgendaService(repositorioContactos);

        // Instancia de los casos de uso
        loginUseCase = new LoginUseCase(loginService);
        EnviarMensajeUseCase enviarMensajeUseCase = new EnviarMensajeUseCase(messageService);
        VerContactosUseCase verContactosUseCase = new VerContactosUseCase(userService);
        VerMensajesUseCase verMensajesUseCase = new VerMensajesUseCase(messageService);
        GuardarContactoUseCase guardarContactoUseCase = new GuardarContactoUseCase(agendaService);
        CargarContactosPorUsuarioUseCase cargarContactosPorUsuarioUseCase = new CargarContactosPorUsuarioUseCase(agendaService);
        CargarTodasLasAgendasUseCase cargarTodasLasAgendasUseCase = new CargarTodasLasAgendasUseCase(agendaService);

        // Instancia del controlador
        postmanController = new PostmanController(guardarContactoUseCase, cargarContactosPorUsuarioUseCase, cargarTodasLasAgendasUseCase, enviarMensajeUseCase, verContactosUseCase, verMensajesUseCase);

        // Iniciar la aplicación
        iniciarAplicacion();
    }

    private static void iniciarAplicacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de mensajería");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (opcion) {
            case 1:
                iniciarSesion(scanner);
                break;
            case 2:
                System.out.println("Saliendo del sistema...");
                return;
            default:
                System.out.println("Opción no válida. Saliendo...");
                return;
        }
    }

    private static void iniciarSesion(Scanner scanner) {
        int intentos = 0;
        while (intentos < 3) {
            System.out.print("Ingrese su número de teléfono: ");
            int numeroTelefono = scanner.nextInt();
            System.out.print("Ingrese su contraseña: ");
            String password = scanner.next();

            if (loginUseCase.estaBloqueado(numeroTelefono)) {
                System.out.println("Usuario bloqueado. Contacte con el administrador.");
                return;
            }

            if (loginUseCase.autenticarUsuario(numeroTelefono, password)) {
                System.out.println("Autenticación exitosa");
                Usuario usuario = obtenerUsuarioPorNumero(numeroTelefono);
                mostrarMenuPrincipal(usuario);
                return;
            } else {
                System.out.println("Número de teléfono o contraseña incorrecta");
                intentos++;
                if (intentos >= 3) {
                    System.out.println("Usuario bloqueado después de 3 intentos fallidos.");
                    return;
                }
            }
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

    private static void mostrarMenuPrincipal(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Enviar mensaje");
            System.out.println("2. Ver contactos");
            System.out.println("3. Guardar contacto");
            if (usuario instanceof UsuarioAdmin) {
                System.out.println("4. Ver todas las agendas");
            }
            System.out.println("5. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    enviarNuevoMensaje(usuario.getNumero(), scanner);
                    break;
                case 2:
                    verContactos(usuario, scanner);
                    break;
                case 3:
                    guardarContacto(usuario, scanner);
                    break;
                case 4:
                    if (usuario instanceof UsuarioAdmin) {
                        verTodasLasAgendas(usuario);
                    } else {
                        System.out.println("Opción no válida. Intente de nuevo.");
                    }
                    break;
                case 5:
                    System.out.println("Sesión cerrada.");
                    iniciarAplicacion();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void enviarNuevoMensaje(int remitenteNumero, Scanner scanner) {
        System.out.print("Ingrese el número de teléfono del destinatario: ");
        int destinatarioNumero = scanner.nextInt();
        scanner.nextLine();  // consume newline
        System.out.print("Ingrese el contenido del mensaje: ");
        String texto = scanner.nextLine();

        postmanController.enviarMensaje(remitenteNumero, destinatarioNumero, texto);
        System.out.println("Mensaje enviado exitosamente.");
    }

    private static void verContactos(Usuario usuario, Scanner scanner) {
        List<Integer> contactos = postmanController.cargarContactosPorUsuario(usuario, usuario.getNumero());
        System.out.println("Contactos del usuario " + usuario.getNumero() + ": " + contactos);
    }

    private static void guardarContacto(Usuario usuario, Scanner scanner) {
        System.out.print("Ingrese el número de teléfono del nuevo contacto: ");
        int nuevoContacto = scanner.nextInt();
        scanner.nextLine();  // consume newline

        List<Integer> contactos = postmanController.cargarContactosPorUsuario(usuario, usuario.getNumero());
        contactos.add(nuevoContacto);
        postmanController.guardarContacto(usuario, usuario.getNumero(), contactos);
        System.out.println("Contacto guardado exitosamente.");
    }

    private static void verTodasLasAgendas(Usuario usuario) {
        List<AgendaContactos> agendas = postmanController.cargarTodasLasAgendas(usuario);
        System.out.println("Todas las agendas:");
        for (AgendaContactos agenda : agendas) {
            System.out.println("Usuario: " + agenda.getNumeroUsuario() + " - Contactos: " + agenda.getContactos());
        }
    }
}

