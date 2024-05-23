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
     * Busca los mensajes enviados por un número de remitente específico.
     * @param numero Número de teléfono del remitente.
     * @return Lista de mensajes enviados por el remitente.
     */
    public List<IMensaje> buscarMensajesPorNumeroRemitente(int numero) {
        List<IMensaje> mensajes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                int remitente = Integer.parseInt(parts[0]);
                if (remitente == numero) {
                    Mensaje mensaje = new SMS();
                    mensaje.setRemitente(remitente);
                    mensaje.setDestinatario(Integer.parseInt(parts[1]));
                    mensaje.setTimeStamp(LocalDate.parse(parts[2]));
                    mensaje.setTexto(parts[3]);
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
     * @param numero Número de teléfono del destinatario.
     * @return Lista de mensajes recibidos por el destinatario.
     */
    public List<IMensaje> cargarTodosLosMensajesPorDestinatario(int numero) {
        List<IMensaje> mensajes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                int destinatario = Integer.parseInt(parts[1]);
                if (destinatario == numero) {
                    Mensaje mensaje = new SMS();
                    mensaje.setRemitente(Integer.parseInt(parts[0]));
                    mensaje.setDestinatario(destinatario);
                    mensaje.setTimeStamp(LocalDate.parse(parts[2]));
                    mensaje.setTexto(parts[3]);
                    mensajes.add(mensaje);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensajes;
    }
}