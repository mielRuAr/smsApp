package core.domain.services;

import persistence.RepositorioMensaje;


import java.util.List;

import core.domain.interfaces.*;



public class MessageService {
	private RepositorioMensaje repositorioMensaje;

    /**
     * Constructor para inicializar el repositorio de mensajes.
     * @param repositorioMensaje El repositorio de mensajes.
     */
    public MessageService(RepositorioMensaje repositorioMensaje) {
        this.repositorioMensaje = repositorioMensaje;
    }

    /**
     * Envía un mensaje.
     * @param mensaje El mensaje a enviar.
     */
    public void enviarMensaje(IMensaje mensaje) {
        repositorioMensaje.guardarMensaje(mensaje);
    }

    /**
     * Obtiene los mensajes enviados por un remitente específico.
     * @param remitente Número de teléfono del remitente.
     * @return Lista de mensajes enviados por el remitente.
     */
    public List<IMensaje> obtenerMensajesPorRemitente(int remitente) {
        return repositorioMensaje.buscarMensajesPorNumeroRemitente(remitente);
    }

    /**
     * Obtiene los mensajes recibidos por un destinatario específico.
     * @param destinatario Número de teléfono del destinatario.
     * @return Lista de mensajes recibidos por el destinatario.
     */
    public List<IMensaje> obtenerMensajesPorDestinatario(int destinatario) {
        return repositorioMensaje.cargarTodosLosMensajesPorDestinatario(destinatario);
    }
}
