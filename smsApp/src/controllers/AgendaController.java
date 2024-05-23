package controllers;

import java.util.List;
import java.util.Scanner;

import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.AgendaContactos;
import core.usecase.agenda.CargarContactosPorUsuarioUseCase;
import core.usecase.agenda.CargarTodasLasAgendasUseCase;
import core.usecase.agenda.GuardarContactoUseCase;

public class AgendaController {
	private GuardarContactoUseCase guardarContactoUseCase;
    private CargarContactosPorUsuarioUseCase cargarContactosPorUsuarioUseCase;
    private CargarTodasLasAgendasUseCase cargarTodasLasAgendasUseCase;

    public AgendaController(GuardarContactoUseCase guardarContactoUseCase, CargarContactosPorUsuarioUseCase cargarContactosPorUsuarioUseCase, CargarTodasLasAgendasUseCase cargarTodasLasAgendasUseCase) {
        this.guardarContactoUseCase = guardarContactoUseCase;
        this.cargarContactosPorUsuarioUseCase = cargarContactosPorUsuarioUseCase;
        this.cargarTodasLasAgendasUseCase = cargarTodasLasAgendasUseCase;
    }

    public void guardarContacto(Usuario usuario, Scanner scanner) {
        System.out.print("Ingrese el nombre del nuevo contacto: ");
        String nombreContacto = scanner.nextLine();
        System.out.print("Ingrese el número de teléfono del nuevo contacto: ");
        String nuevoContacto = scanner.nextLine();

        if (!guardarContactoUseCase.esNumeroValido(nuevoContacto)) {
            System.out.println("Número de teléfono inválido. Debe comenzar con 6 o 7 y tener 9 dígitos.");
            return;
        }

        List<String> contactos = cargarContactosPorUsuarioUseCase.ejecutar(usuario, usuario.getNumero());
        contactos.add(nombreContacto + ":" + nuevoContacto);
        guardarContactoUseCase.ejecutar(usuario, usuario.getNumero(), contactos);
        System.out.println("Contacto guardado exitosamente.");
    }

    public void verContactos(Usuario usuario, Scanner scanner) {
        List<String> contactos = cargarContactosPorUsuarioUseCase.ejecutar(usuario, usuario.getNumero());
        System.out.println("Contactos del usuario " + usuario.getNumero() + ": " + contactos);
    }

    public void verTodasLasAgendas(Usuario usuario) {
        List<AgendaContactos> agendas = cargarTodasLasAgendasUseCase.ejecutar(usuario);
        System.out.println("Todas las agendas:");
        for (AgendaContactos agenda : agendas) {
            System.out.println("Usuario: " + agenda.getNumeroUsuario() + " - Contactos: " + agenda.getContactos());
        }
    }
	}

