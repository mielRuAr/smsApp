package controllers;

import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.UsuarioAdmin;

import java.util.Scanner;

import controllers.AgendaController;
public class MenuController {
	  private Usuario usuario;
	    private PostmanController postmanController;
	    private AgendaController agendaController;

	    public MenuController(Usuario usuario, PostmanController postmanController, AgendaController agendaController) {
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
	            System.out.println("2. Ver contactos");
	            System.out.println("3. Guardar contacto");
	            System.out.println("4. Eliminar contacto");
	            System.out.println("5. Cerrar sesión");
	            System.out.print("Seleccione una opción: ");
	            int opcion = scanner.nextInt();
	            scanner.nextLine(); // Consume newline left-over

	            switch (opcion) {
	                case 1:
	                    enviarNuevoMensaje(scanner);
	                    break;
	                case 2:
	                    agendaController.verContactos(usuario, scanner);
	                    break;
	                case 3:
	                    agendaController.guardarContacto(usuario, scanner);
	                    break;
	                case 4:
	                    agendaController.eliminarContacto(usuario, scanner);
	                    break;
	                case 5:
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
}
