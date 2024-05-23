package controllers;

import java.util.List;

import core.domain.interfaces.IMensaje;
import core.domain.interfaces.IUsuario;
import core.domain.services.MensajeService;
import core.domain.services.UserService;
import core.usecase.EnviarMensajeUseCase;
import core.usecase.VerContactosUseCase;
import core.usecase.VerMensajesUseCase;

public class PostmanController {
    private EnviarMensajeUseCase enviarMensajeUseCase;
    private VerContactosUseCase verContactosUseCase;
    private VerMensajesUseCase verMensajesUseCase;

    /**
     * Constructor para inicializar los casos de uso.
     * @param messageService El servicio de mensajes.
     * @param userService El servicio de usuarios.
     */
    public PostmanController(MensajeService messageService, UserService userService) {
        this.enviarMensajeUseCase = new EnviarMensajeUseCase(messageService);
        this.verContactosUseCase = new VerContactosUseCase(userService);
        this.verMensajesUseCase = new VerMensajesUseCase(messageService);
    }

    /**
     * Envía un mensaje de un usuario a otro.
     * @param remitenteNumero Número de teléfono del remitente.
     * @param destinatarioNumero Número de teléfono del destinatario.
     * @param texto Contenido del mensaje.
     */
    public void enviarMensaje(int remitenteNumero, int destinatarioNumero, String texto) {
        enviarMensajeUseCase.enviarMensaje(remitenteNumero, destinatarioNumero, texto);
    }

    /**
     * Obtiene todos los contactos del sistema.
     * @return Lista de todos los usuarios.
     */
    public List<IUsuario> verContactos() {
        return verContactosUseCase.verContactos();
    }

    /**
     * Obtiene los mensajes enviados por un usuario específico.
     * @param numeroTelefono Número de teléfono del remitente.
     * @return Lista de mensajes enviados por el usuario.
     */
    public List<IMensaje> verMensajesEnviados(int numeroTelefono) {
        return verMensajesUseCase.verMensajesEnviados(numeroTelefono);
    }

    /**
     * Obtiene los mensajes recibidos por un usuario específico.
     * @param numeroTelefono Número de teléfono del destinatario.
     * @return Lista de mensajes recibidos por el usuario.
     */
    public List<IMensaje> verMensajesRecibidos(int numeroTelefono) {
        return verMensajesUseCase.verMensajesRecibidos(numeroTelefono);
    }

    /**
     * Obtiene los mensajes recibidos de un emisor específico.
     * @param numeroTelefonoDestinatario Número de teléfono del destinatario.
     * @param numeroTelefonoEmisor Número de teléfono del remitente.
     * @return Lista de mensajes recibidos por el usuario de un emisor específico.
     */
    public List<IMensaje> verMensajesRecibidosDe(int numeroTelefonoDestinatario, int numeroTelefonoEmisor) {
        return verMensajesUseCase.verMensajesRecibidosDe(numeroTelefonoDestinatario, numeroTelefonoEmisor);
    }
}
