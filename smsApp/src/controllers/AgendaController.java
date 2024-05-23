package controllers;

import java.util.List;
import java.util.Scanner;

import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.AgendaContactos;
import core.usecase.agenda.CargarContactosPorUsuarioUseCase;
import core.usecase.agenda.EliminarContactoUseCase;
import core.usecase.agenda.GuardarContactoUseCase;

public class AgendaController {
	   private GuardarContactoUseCase guardarContactoUseCase;
	    private CargarContactosPorUsuarioUseCase cargarContactosPorUsuarioUseCase;
	    private EliminarContactoUseCase eliminarContactoUseCase;

	    public AgendaController(GuardarContactoUseCase guardarContactoUseCase, CargarContactosPorUsuarioUseCase cargarContactosPorUsuarioUseCase, EliminarContactoUseCase eliminarContactoUseCase) {
	        this.guardarContactoUseCase = guardarContactoUseCase;
	        this.cargarContactosPorUsuarioUseCase = cargarContactosPorUsuarioUseCase;
	        this.eliminarContactoUseCase = eliminarContactoUseCase;
	    }

	    /**
	     * Guarda un nuevo contacto en la agenda del usuario.
	     * @param usuario El usuario que realiza la operación.
	     * @param scanner El objeto Scanner para leer entradas del usuario.
	     */
	    public void guardarContacto(Usuario usuario, Scanner scanner) {
	        System.out.print("Ingrese el nombre del nuevo contacto: ");
	        String nombreContacto = scanner.nextLine();
	        System.out.print("Ingrese el número de teléfono del nuevo contacto: ");
	        String numeroContacto = scanner.nextLine();

	        try {
	            boolean agregado = guardarContactoUseCase.ejecutar(usuario, usuario.getNumero(), nombreContacto, numeroContacto);
	            if (agregado) {
	                System.out.println("Contacto guardado exitosamente.");
	            } else {
	                System.out.println("El contacto ya existe.");
	            }
	        } catch (IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    /**
	     * Muestra los contactos de la agenda del usuario.
	     * @param usuario El usuario que realiza la operación.
	     * @param scanner El objeto Scanner para leer entradas del usuario.
	     */
	    public void verContactos(Usuario usuario, Scanner scanner) {
	        List<String> contactos = cargarContactosPorUsuarioUseCase.ejecutar(usuario, usuario.getNumero());
	        System.out.println("Contactos del usuario " + usuario.getNumero() + ": " + contactos);
	    }

	    /**
	     * Elimina un contacto de la agenda del usuario.
	     * @param usuario El usuario que realiza la operación.
	     * @param scanner El objeto Scanner para leer entradas del usuario.
	     */
	    public void eliminarContacto(Usuario usuario, Scanner scanner) {
	        System.out.print("Ingrese el nombre del contacto a eliminar: ");
	        String nombreContacto = scanner.nextLine();

	        try {
	            boolean eliminado = eliminarContactoUseCase.ejecutar(usuario, usuario.getNumero(), nombreContacto);
	            if (eliminado) {
	                System.out.println("Contacto eliminado exitosamente.");
	            } else {
	                System.out.println("No se encontró el contacto a eliminar.");
	            }
	        } catch (IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
	    }
}
	

