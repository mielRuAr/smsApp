package core.usecase.agenda;

import java.util.List;

import core.domain.models.abstracts.Usuario;
import core.domain.services.AgendaService;

public class GuardarContactoUseCase {
    private AgendaService agendaService;

    public GuardarContactoUseCase(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    public void ejecutar(Usuario usuario, int numeroUsuario, String nombreContacto, String numeroContacto) {
        if (esNumeroValido(numeroContacto)) {
            agendaService.agregarContacto(usuario, numeroUsuario, nombreContacto, numeroContacto);
        } else {
            throw new IllegalArgumentException("Número de teléfono inválido. Debe comenzar con 6 o 7 y tener 9 dígitos.");
        }
    }

    public boolean esNumeroValido(String numero) {
        return numero.matches("^[67]\\d{8}$");
    }
}
