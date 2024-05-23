package controllers;

import java.util.Scanner;

import core.domain.services.UserService;

public class UserController {
	 private UserService userService;

	    public UserController(UserService userService) {
	        this.userService = userService;
	    }

	    /**
	     * Bloquea a un usuario.
	     * @param scanner El objeto Scanner para leer entradas del usuario.
	     */
	    public void bloquearUsuario(Scanner scanner) {
	        System.out.print("Ingrese el número de teléfono del usuario a bloquear: ");
	        int numeroTelefono = scanner.nextInt();
	        scanner.nextLine(); // consume newline
	        userService.bloquearUsuario(numeroTelefono);
	        System.out.println("Usuario bloqueado exitosamente.");
	    }

	    /**
	     * Desbloquea a un usuario.
	     * @param scanner El objeto Scanner para leer entradas del usuario.
	     */
	    public void desbloquearUsuario(Scanner scanner) {
	        System.out.print("Ingrese el número de teléfono del usuario a desbloquear: ");
	        int numeroTelefono = scanner.nextInt();
	        scanner.nextLine(); // consume newline
	        userService.desbloquearUsuario(numeroTelefono);
	        System.out.println("Usuario desbloqueado exitosamente.");
	    }

	    /**
	     * Elimina a un usuario.
	     * @param scanner El objeto Scanner para leer entradas del usuario.
	     */
	    public void eliminarUsuario(Scanner scanner) {
	        System.out.print("Ingrese el número de teléfono del usuario a eliminar: ");
	        int numeroTelefono = scanner.nextInt();
	        scanner.nextLine(); // consume newline
	        userService.eliminarUsuario(numeroTelefono);
	        System.out.println("Usuario eliminado exitosamente.");
	    }

	    /**
	     * Verifica si un usuario está registrado.
	     * @param numeroTelefono El número de teléfono del usuario.
	     * @return true si el usuario está registrado, false en caso contrario.
	     */
	    public boolean usuarioRegistrado(int numeroTelefono) {
	        return userService.obtenerUsuario(numeroTelefono) != null;
	    }
}
