package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


import core.domain.interfaces.IMensaje;
import core.domain.models.abstracts.Mensaje;
import core.domain.models.concretes.SMS;

/**
 * Repositorio para manejar operaciones relacionadas con mensajes en Firebase.
 */
public class RepositorioMensaje {
	private static final String FILE_PATH = "resources/mensajes.txt";

    /**
     * Guarda un mensaje en el fichero de texto.
     * @param mensaje El mensaje a guardar.
     */
    public void guardarMensaje(IMensaje mensaje) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(mensaje.getRemitente() + ":" + mensaje.getDestinatario() + ":" + mensaje.getTimeStamp() + ":" + mensaje.getTexto());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca mensajes por el número de remitente.
     * @param remitente Número de teléfono del remitente.
     * @return Lista de mensajes enviados por el remitente.
     */
    public List<IMensaje> buscarMensajesPorNumeroRemitente(int remitente) {
        List<IMensaje> mensajes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                int remit = Integer.parseInt(parts[0]);
                if (remit == remitente) {
                    IMensaje mensaje = new SMS(remit, Integer.parseInt(parts[1]), LocalDate.parse(parts[2]), parts[3]);
                    mensajes.add(mensaje);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensajes;
    }

    /**
     * Carga todos los mensajes recibidos por un destinatario específico.
     * @param destinatario Número de teléfono del destinatario.
     * @return Lista de mensajes recibidos por el destinatario.
     */
    public List<IMensaje> cargarTodosLosMensajesPorDestinatario(int destinatario) {
        List<IMensaje> mensajes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                int dest = Integer.parseInt(parts[1]);
                if (dest == destinatario) {
                    IMensaje mensaje = new SMS(Integer.parseInt(parts[0]), dest, LocalDate.parse(parts[2]), parts[3]);
                    mensajes.add(mensaje);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensajes;
    }
}