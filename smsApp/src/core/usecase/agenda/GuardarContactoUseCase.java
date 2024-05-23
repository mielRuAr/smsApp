package core.usecase.agenda;

import java.util.List;

import core.domain.interfaces.IUsuario;
import core.domain.models.abstracts.Usuario;
import core.domain.services.AgendaService;

public class GuardarContactoUseCase {
	private AgendaService agendaService;

    public GuardarContactoUseCase(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    /**
     * Ejecuta el caso de uso de guardar un contacto.
     * @param usuario El usuario que realiza la operación.
     * @param numeroUsuario El número del usuario cuya agenda se está modificando.
     * @param nombreContacto El nombre del contacto a agregar.
     * @param numeroContacto El número del contacto a agregar.
     * @return true si el contacto fue agregado, false si ya existe.
     */
    public boolean ejecutar(IUsuario usuario, int numeroUsuario, String nombreContacto, String numeroContacto) {
        if (esNumeroValido(numeroContacto)) {
            return agendaService.agregarContacto(usuario, numeroUsuario, nombreContacto, numeroContacto);
        } else {
            throw new IllegalArgumentException("Número de teléfono inválido. Debe comenzar con 6 o 7 y tener 9 dígitos.");
        }
    }

    /**
     * Verifica si un número de contacto es válido.
     * @param numero El número de contacto a verificar.
     * @return true si el número es válido, false en caso contrario.
     */
    private boolean esNumeroValido(String numero) {
        return numero.matches("[67]\\d{8}");
    }
}
