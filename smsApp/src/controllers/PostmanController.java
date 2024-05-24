package controllers;

import java.util.List;

import core.domain.interfaces.IMensaje;
import core.domain.interfaces.IUsuario;
import core.domain.models.abstracts.Usuario;
import core.domain.models.concretes.AgendaContactos;
import core.domain.services.EliminarMensajeUseCase;
import core.domain.services.MensajeService;
import core.domain.services.UserService;
import core.usecase.agenda.CargarContactosPorUsuarioUseCase;
import core.usecase.agenda.AgregarContactoUseCase;
import core.usecase.agenda.VerContactosUseCase;
import core.usecase.mensaje.EnviarMensajeUseCase;
import core.usecase.mensaje.FiltrarMensajesPorDestinatarioUseCase;
import core.usecase.mensaje.VerMensajesEnviadosPorUsuarioUseCase;
import core.usecase.mensaje.VerMensajesRecibidosPorUsuarioUseCase;
import core.usecase.mensaje.VerMensajesUseCase;

public class PostmanController {
    private EnviarMensajeUseCase enviarMensajeUseCase;
    private VerContactosUseCase verContactosUseCase;
    private VerMensajesEnviadosPorUsuarioUseCase verMensajesEnviadosPorUsuarioUseCase;
    private VerMensajesRecibidosPorUsuarioUseCase verMensajesRecibidosPorUsuarioUseCase;
    private EliminarMensajeUseCase eliminarMensajeUseCase;
    private FiltrarMensajesPorDestinatarioUseCase filtrarMensajePorDestinatarioUseCase;

    public PostmanController(EnviarMensajeUseCase enviarMensajeUseCase, VerContactosUseCase verContactosUseCase,
                             VerMensajesEnviadosPorUsuarioUseCase verMensajesEnviadosPorUsuarioUseCase,
                             VerMensajesRecibidosPorUsuarioUseCase verMensajesRecibidosPorUsuarioUseCase,
                             EliminarMensajeUseCase eliminarMensajeUseCase,
                             FiltrarMensajesPorDestinatarioUseCase filtrarMensajePorDestinatarioUseCase) {
        this.enviarMensajeUseCase = enviarMensajeUseCase;
        this.verContactosUseCase = verContactosUseCase;
        this.verMensajesEnviadosPorUsuarioUseCase = verMensajesEnviadosPorUsuarioUseCase;
        this.verMensajesRecibidosPorUsuarioUseCase = verMensajesRecibidosPorUsuarioUseCase;
        this.eliminarMensajeUseCase = eliminarMensajeUseCase;
        this.filtrarMensajePorDestinatarioUseCase = filtrarMensajePorDestinatarioUseCase;
    }

    /**
     * Envía un mensaje de un usuario a otro.
     * @param remitenteNumero Número de teléfono del remitente.
     * @param destinatarioNumero Número de teléfono del destinatario.
     * @param texto Contenido del mensaje.
     */
    public void enviarMensaje(int remitenteNumero, int destinatarioNumero, String texto) {
        enviarMensajeUseCase.ejecutar(remitenteNumero, destinatarioNumero, texto);
    }

    /**
     * Obtiene todos los contactos del sistema.
     * @return Lista de todos los usuarios.
     */
    public List<IUsuario> verContactos() {
        return verContactosUseCase.ejecutar();
    }

    /**
     * Obtiene los mensajes enviados por un usuario específico.
     * @param numeroTelefono Número de teléfono del remitente.
     * @return Lista de mensajes enviados por el usuario.
     */
    public List<IMensaje> verMensajesEnviados(int numeroTelefono) {
        return verMensajesEnviadosPorUsuarioUseCase.ejecutar(numeroTelefono);
    }

    /**
     * Obtiene los mensajes recibidos por un usuario específico.
     * @param numeroTelefono Número de teléfono del destinatario.
     * @return Lista de mensajes recibidos por el usuario.
     */
    public List<IMensaje> verMensajesRecibidos(int numeroTelefono) {
        return verMensajesRecibidosPorUsuarioUseCase.ejecutar(numeroTelefono);
    }

    /**
     * Elimina un mensaje de un usuario a otro.
     * @param remitenteNumero Número de teléfono del remitente.
     * @param destinatarioNumero Número de teléfono del destinatario.
     * @param texto Contenido del mensaje.
     */
    public void eliminarMensaje(int remitenteNumero, int destinatarioNumero, String texto) {
        eliminarMensajeUseCase.ejecutar(remitenteNumero, destinatarioNumero, texto);
    }

    
    /**
     * Filtra los mensajes por destinatario.
     * @param destinatarioNumero Número de teléfono del destsinatario.
     * @return Lista de mensajes filtrados por destinatario.
     */
    public List<IMensaje> filtrarMensajesPorDestinatario(int remitenteNumero, int destinatarioNumero) {
        return filtrarMensajePorDestinatarioUseCase.ejecutar(remitenteNumero, destinatarioNumero);
    }
    
}