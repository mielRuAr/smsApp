package core.domain.services;

import persistence.RepositorioMensaje;


import java.util.List;

import core.domain.interfaces.*;



public class MensajeService {
    private RepositorioMensaje repositorioMensaje;

    public MensajeService(RepositorioMensaje repositorioMensaje) {
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
     * Elimina un mensaje.
     * @param remitente Número de teléfono del remitente.
     * @param destinatario Número de teléfono del destinatario.
     * @param texto Contenido del mensaje.
     */
    public void eliminarMensaje(int remitente, int destinatario, String texto) {
        repositorioMensaje.eliminarMensaje(remitente, destinatario, texto);
    }

    /**
     * Obtiene los mensajes recibidos por un destinatario específico.
     * @param destinatario Número de teléfono del destinatario.
     * @return Lista de mensajes recibidos.
     */
    public List<IMensaje> obtenerMensajesPorDestinatario(int destinatario) {
        return repositorioMensaje.cargarMensajesPorDestinatario(destinatario);
    }

    /**
     * Obtiene los mensajes enviados por un remitente específico.
     * @param remitente Número de teléfono del remitente.
     * @return Lista de mensajes enviados.
     */
    public List<IMensaje> obtenerMensajesPorRemitente(int remitente) {
        return repositorioMensaje.cargarMensajesPorRemitente(remitente);
    }

    /**
     * Filtra los mensajes por remitente.
     * @param remitente Número de teléfono del remitente.
     * @return Lista de mensajes enviados por el remitente.
     */
    public List<IMensaje> filtrarMensajesPorRemitente(int remitente) {
        return repositorioMensaje.cargarMensajesPorRemitente(remitente);
    }
    
    // Métodos existentes...

    /**
     * Filtra los mensajes por remitente y destinatario.
     * @param remitenteNumero Número de teléfono del remitente.
     * @param destinatarioNumero Número de teléfono del destinatario.
     * @return Lista de mensajes filtrados por destinatario.
     */
    public List<IMensaje> filtrarMensajesPorDestinatario(int remitenteNumero, int destinatarioNumero) {
        return repositorioMensaje.filtrarMensajesPorDestinatario(remitenteNumero, destinatarioNumero);
    }
}
