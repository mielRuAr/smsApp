package controllers;


import java.util.Scanner;

import core.domain.services.LoginService;
import core.domain.services.MensajeService;
import core.domain.services.UserService;
import core.usecase.EnviarMensajeUseCase;
import core.usecase.LoginUseCase;
import persistence.RepositorioMensaje;
import persistence.RepositorioUsuario;

public class Main {
	 private static LoginUseCase loginUseCase;
	    private static PostmanController postmanController;

	    public static void main(String[] args) {
	        // Instancia del repositorio
	        RepositorioUsuario repositorioUsuario = new RepositorioUsuario();
	        RepositorioMensaje repositorioMensaje = new RepositorioMensaje();

	        // Instancia del servicio
	        LoginService loginService = new LoginService(repositorioUsuario);
	        MensajeService messageService = new MensajeService(repositorioMensaje);
	        UserService userService = new UserService(repositorioUsuario);

	        // Instancia del caso de uso
	        loginUseCase = new LoginUseCase(loginService);

	        // Instancia del controlador
	        postmanController = new PostmanController(messageService, userService);

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
	                mostrarMenuPrincipal(numeroTelefono);
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

	    private static void mostrarMenuPrincipal(int numeroTelefono) {
	        Scanner scanner = new Scanner(System.in);
	        while (true) {
	            System.out.println("\nMenu Principal:");
	            System.out.println("1. Enviar mensaje");
	            System.out.println("2. Cerrar sesión");
	            System.out.print("Seleccione una opción: ");
	            int opcion = scanner.nextInt();

	            switch (opcion) {
	                case 1:
	                    enviarNuevoMensaje(numeroTelefono, scanner);
	                    break;
	                case 2:
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
}

