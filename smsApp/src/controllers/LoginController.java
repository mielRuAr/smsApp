package controllers;

import java.util.Scanner;

import core.usecase.usuarios.LoginUseCase;

public class LoginController {
	private LoginUseCase loginUseCase;

    public LoginController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    /**
     * Inicia sesión en el sistema.
     * @param scanner2 
     * @return El número de teléfono del usuario si la autenticación es exitosa, -1 en caso contrario.
     */
    public int iniciarSesion(Scanner scanner2) {
        Scanner scanner = new Scanner(System.in);
        int intentos = 0;
        while (intentos < 3) {
        	  int numeroTelefono = leerNumeroTelefono(scanner);
              String password = leerContrasena(scanner);

            if (loginUseCase.estaBloqueado(numeroTelefono)) {
                System.out.println("Usuario bloqueado. Contacte con el administrador.");
                return -1;
            }

            if (loginUseCase.autenticarUsuario(numeroTelefono, password)) {
                System.out.println("Autenticación exitosa");
                return numeroTelefono;
            } else {
                System.out.println("Número de teléfono o contraseña incorrecta");
                intentos++;
                if (intentos >= 3) {
                    System.out.println("Usuario bloqueado después de 3 intentos fallidos.");
                    return -1;
                }
            }
        }
        return -1;
    }
    
    private int leerNumeroTelefono(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese su número de teléfono: ");
            String input = scanner.nextLine();

            if (input.matches("[67]\\d{8}")) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Número de teléfono inválido. Debe comenzar con 6 o 7 y tener 9 dígitos.");
            }
        }
    }

    private String leerContrasena(Scanner scanner) {
        System.out.print("Ingrese su contraseña: ");
        return scanner.nextLine();
    }
}
