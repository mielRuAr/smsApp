package controllers;

import java.util.Scanner;

import core.usecase.usuarios.LoginUseCase;

public class LoginController {
	private LoginUseCase loginUseCase;

    public LoginController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    public int iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        int intentos = 0;
        while (intentos < 3) {
            System.out.print("Ingrese su número de teléfono: ");
            int numeroTelefono = scanner.nextInt();
            System.out.print("Ingrese su contraseña: ");
            String password = scanner.next();

            if (loginUseCase.estaBloqueado(numeroTelefono)) {
                System.out.println("Usuario bloqueado. Contacte con el administrador.");
                return -1;
            }

            if (loginUseCase.autenticarUsuario(numeroTelefono, password)) {
                System.out.println("Autenticación exitosa");
                // Devolver el número de teléfono para su uso en el menú principal
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
}
