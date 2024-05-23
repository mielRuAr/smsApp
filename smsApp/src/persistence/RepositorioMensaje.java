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
            writer.write(mensaje.getRemitente() + "|" + mensaje.getDestinatario() + "|" + mensaje.getTexto() + ";");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda una lista de mensajes en el fichero de texto.
     * @param mensajes Lista de mensajes a guardar.
     */
    public void guardarMensajes(List<IMensaje> mensajes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (IMensaje mensaje : mensajes) {
                writer.write(mensaje.getRemitente() + "|" + mensaje.getDestinatario() + "|" + mensaje.getTexto() + ";");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un mensaje del fichero de texto.
     * @param remitente El número de teléfono del remitente.
     * @param destinatario El número de teléfono del destinatario.
     * @param texto El texto del mensaje a eliminar.
     */
    public void eliminarMensaje(int remitente, int destinatario, String texto) {
        List<IMensaje> mensajes = cargarTodosLosMensajes();
        mensajes.removeIf(m -> m.getRemitente() == remitente && m.getDestinatario() == destinatario && m.getTexto().equals(texto));
        guardarMensajes(mensajes);
    }

    /**
     * Carga todos los mensajes del fichero de texto.
     * @return Lista de todos los mensajes.
     */
    public List<IMensaje> cargarTodosLosMensajes() {
        List<IMensaje> mensajes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int remitente = Integer.parseInt(parts[0]);
                int destinatario = Integer.parseInt(parts[1]);
                String texto = parts[2].replace(";", ""); // Remove the semicolon
                IMensaje mensaje = new SMS(remitente, destinatario, LocalDate.now(), texto); // Using LocalDate.now() as a placeholder
                mensajes.add(mensaje);
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
    public List<IMensaje> cargarMensajesPorDestinatario(int destinatario) {
        List<IMensaje> mensajes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int dest = Integer.parseInt(parts[1]);
                if (dest == destinatario) {
                    IMensaje mensaje = new SMS(Integer.parseInt(parts[0]), dest, LocalDate.now(), parts[2].replace(";", ""));
                    mensajes.add(mensaje);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensajes;
    }

    /**
     * Carga todos los mensajes enviados por un remitente específico.
     * @param remitente Número de teléfono del remitente.
     * @return Lista de mensajes enviados por el remitente.
     */
    public List<IMensaje> cargarMensajesPorRemitente(int remitente) {
        List<IMensaje> mensajes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int rem = Integer.parseInt(parts[0]);
                if (rem == remitente) {
                    IMensaje mensaje = new SMS(rem, Integer.parseInt(parts[1]), LocalDate.now(), parts[2].replace(";", ""));
                    mensajes.add(mensaje);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensajes;
    }
}