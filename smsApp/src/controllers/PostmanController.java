package controllers;

import java.util.List;

import core.domain.interfaces.IMensaje;
import core.domain.interfaces.IUsuario;
import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.AgendaContactos;
import core.domain.services.MensajeService;
import core.domain.services.UserService;
import core.usecase.agenda.CargarContactosPorUsuarioUseCase;
import core.usecase.agenda.CargarTodasLasAgendasUseCase;
import core.usecase.agenda.GuardarContactoUseCase;
import core.usecase.agenda.VerContactosUseCase;
import core.usecase.mensaje.EnviarMensajeUseCase;
import core.usecase.mensaje.VerMensajesUseCase;

public class PostmanController {
    private EnviarMensajeUseCase enviarMensajeUseCase;
    private VerContactosUseCase verContactosUseCase;
    private VerMensajesUseCase verMensajesUseCase;

    public PostmanController(EnviarMensajeUseCase enviarMensajeUseCase, VerContactosUseCase verContactosUseCase, VerMensajesUseCase verMensajesUseCase) {
        this.enviarMensajeUseCase = enviarMensajeUseCase;
        this.verContactosUseCase = verContactosUseCase;
        this.verMensajesUseCase = verMensajesUseCase;
    }

    public void enviarMensaje(int remitenteNumero, int destinatarioNumero, String texto) {
        enviarMensajeUseCase.enviarMensaje(remitenteNumero, destinatarioNumero, texto);
    }

    public List<IUsuario> verContactos() {
        return verContactosUseCase.verContactos();
    }

    public List<IMensaje> verMensajesEnviados(int numeroTelefono) {
        return verMensajesUseCase.verMensajesEnviados(numeroTelefono);
    }

    public List<IMensaje> verMensajesRecibidos(int numeroTelefono) {
        return verMensajesUseCase.verMensajesRecibidos(numeroTelefono);
    }

    public List<IMensaje> verMensajesRecibidosDe(int numeroTelefonoDestinatario, int numeroTelefonoEmisor) {
        return verMensajesUseCase.verMensajesRecibidosDe(numeroTelefonoDestinatario, numeroTelefonoEmisor);
    }
}
