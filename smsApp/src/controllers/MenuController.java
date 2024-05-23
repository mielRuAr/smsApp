package controllers;

import core.domain.interfaces.IMensaje;
import core.domain.interfaces.IUsuario;
import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.UsuarioAdmin;

import java.util.List;
import java.util.Scanner;

import controllers.AgendaController;
public class MenuController {
	 private IUsuario usuario;
	    private PostmanController postmanController;
	    private AgendaController agendaController;

	    public MenuController(IUsuario usuario, PostmanController postmanController, AgendaController agendaController) {
	        this.usuario = usuario;
	        this.postmanController = postmanController;
	        this.agendaController = agendaController;
	    }

	    /**
	     * Muestra el menú principal y maneja las opciones del usuario.
	     * @param scanner El objeto Scanner para leer entradas del usuario.
	     */
	    public void mostrarMenuPrincipal(Scanner scanner) {
	        while (true) {
	            System.out.println("\nMenu Principal:");
	            System.out.println("1. Enviar mensaje");
	            System.out.println("2. Ver mensajes");
	            System.out.println("3. Agenda");
	            System.out.println("4. Cerrar sesión");
	            System.out.print("Seleccione una opción: ");
	            int opcion = scanner.nextInt();
	            scanner.nextLine(); // Consume newline left-over

	            switch (opcion) {
	                case 1:
	                    enviarNuevoMensaje(scanner);
	                    break;
	                case 2:
	                    mostrarMenuMensajes(scanner);
	                    break;
	                case 3:
	                    mostrarMenuAgenda(scanner);
	                    break;
	                case 4:
	                    System.out.println("Sesión cerrada.");
	                    return;
	                default:
	                    System.out.println("Opción no válida. Intente de nuevo.");
	            }
	        }
	    }

	    private void enviarNuevoMensaje(Scanner scanner) {
	        System.out.print("Ingrese el número de teléfono del destinatario: ");
	        int destinatarioNumero = scanner.nextInt();
	        scanner.nextLine();  // consume newline
	        System.out.print("Ingrese el contenido del mensaje: ");
	        String texto = scanner.nextLine();

	        postmanController.enviarMensaje(usuario.getNumero(), destinatarioNumero, texto);
	        System.out.println("Mensaje enviado exitosamente.");
	    }

	    private void mostrarMenuMensajes(Scanner scanner) {
	        while (true) {
	            System.out.println("\nMenu de Mensajes:");
	            System.out.println("1. Ver mensajes enviados");
	            System.out.println("2. Ver mensajes recibidos");
	            System.out.println("3. Volver al menú principal");
	            System.out.print("Seleccione una opción: ");
	            int opcion = scanner.nextInt();
	            scanner.nextLine(); // Consume newline left-over

	            switch (opcion) {
	                case 1:
	                    verMensajesEnviados(scanner);
	                    break;
	                case 2:
	                    verMensajesRecibidos(scanner);
	                    break;
	                case 3:
	                    return;
	                default:
	                    System.out.println("Opción no válida. Intente de nuevo.");
	            }
	        }
	    }

	    private void verMensajesEnviados(Scanner scanner) {
	        System.out.print("Ingrese el número de teléfono para filtrar (o presione enter para ver todos): ");
	        String filtro = scanner.nextLine();
	        List<IMensaje> mensajes = filtro.isEmpty() ? postmanController.verMensajesEnviados(usuario.getNumero())
	                : postmanController.filtrarMensajesPorRemitente(usuario.getNumero());
	        for (IMensaje mensaje : mensajes) {
	            System.out.println(mensaje);
	        }
	    }

	    private void verMensajesRecibidos(Scanner scanner) {
	        System.out.print("Ingrese el número de teléfono para filtrar (o presione enter para ver todos): ");
	        String filtro = scanner.nextLine();
	        List<IMensaje> mensajes = filtro.isEmpty() ? postmanController.verMensajesRecibidos(usuario.getNumero())
	                : postmanController.filtrarMensajesPorRemitente(Integer.parseInt(filtro));
	        for (IMensaje mensaje : mensajes) {
	            System.out.println(mensaje);
	        }
	    }

	    private void mostrarMenuAgenda(Scanner scanner) {
	        while (true) {
	            System.out.println("\nMenu de Agenda:");
	            System.out.println("1. Ver contactos");
	            System.out.println("2. Guardar contacto");
	            System.out.println("3. Eliminar contacto");
	            System.out.println("4. Volver al menú principal");
	            System.out.print("Seleccione una opción: ");
	            int opcion = scanner.nextInt();
	            scanner.nextLine(); // Consume newline left-over

	            switch (opcion) {
	                case 1:
	                    agendaController.verContactos(usuario, scanner);
	                    break;
	                case 2:
	                    agendaController.guardarContacto(usuario, scanner);
	                    break;
	                case 3:
	                    agendaController.eliminarContacto(usuario, scanner);
	                    break;
	                case 4:
	                    return;
	                default:
	                    System.out.println("Opción no válida. Intente de nuevo.");
	            }
	        }
	    }
	}