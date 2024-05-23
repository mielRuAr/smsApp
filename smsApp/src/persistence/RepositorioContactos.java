package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.domain.models.concretes.AgendaContactos;

public class RepositorioContactos {
	private static final String FILE_PATH = "resources/contactos.txt";

    /**
     * Guarda la lista de contactos para un usuario.
     * @param agendaContactos La agenda de contactos a guardar.
     */
    public void guardarContactos(AgendaContactos agendaContactos) {
        List<AgendaContactos> agendas = cargarTodasLasAgendas();
        boolean usuarioEncontrado = false;

        for (AgendaContactos agenda : agendas) {
            if (agenda.getNumeroUsuario() == agendaContactos.getNumeroUsuario()) {
                agenda.getContactos().addAll(agendaContactos.getContactos());
                usuarioEncontrado = true;
                break;
            }
        }

        if (!usuarioEncontrado) {
            agendas.add(agendaContactos);
        }

        guardarTodasLasAgendas(agendas);
    }

    /**
     * Guarda un solo contacto en la agenda de un usuario.
     * @param numeroUsuario El número del usuario cuya agenda se está modificando.
     * @param nombreContacto El nombre del contacto.
     * @param numeroContacto El número del contacto.
     */
    public void guardarContacto(int numeroUsuario, String nombreContacto, String numeroContacto) {
        List<AgendaContactos> agendas = cargarTodasLasAgendas();
        boolean usuarioEncontrado = false;

        for (AgendaContactos agenda : agendas) {
            if (agenda.getNumeroUsuario() == numeroUsuario) {
                String nuevoContacto = numeroContacto + ":" + nombreContacto;
                agenda.getContactos().add(nuevoContacto);
                usuarioEncontrado = true;
                break;
            }
        }

        if (!usuarioEncontrado) {
            List<String> contactos = new ArrayList<>();
            contactos.add(numeroContacto + ":" + nombreContacto);
            agendas.add(new AgendaContactos(numeroUsuario, contactos));
        }

        guardarTodasLasAgendas(agendas);
    }

    /**
     * Elimina un contacto de la agenda de un usuario por su nombre.
     * @param numeroUsuario El número del usuario cuya agenda se está modificando.
     * @param nombreContacto El nombre del contacto a eliminar.
     * @return true si el contacto fue eliminado, false en caso contrario.
     */
    public boolean eliminarContacto(int numeroUsuario, String nombreContacto) {
        List<AgendaContactos> agendas = cargarTodasLasAgendas();
        boolean contactoEliminado = false;

        for (AgendaContactos agenda : agendas) {
            if (agenda.getNumeroUsuario() == numeroUsuario) {
                boolean eliminado = agenda.getContactos().removeIf(contacto -> contacto.toLowerCase().endsWith(":" + nombreContacto.toLowerCase()));
                if (eliminado) {
                    contactoEliminado = true;
                    break;
                }
            }
        }

        if (contactoEliminado) {
            guardarTodasLasAgendas(agendas);
        }

        return contactoEliminado;
    }

    /**
     * Carga todas las agendas de contactos desde el archivo.
     * @return Lista de todas las agendas.
     */
    public List<AgendaContactos> cargarTodasLasAgendas() {
        List<AgendaContactos> agendas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    int numeroUsuario = Integer.parseInt(parts[0]);
                    List<String> contactos = new ArrayList<>();
                    if (!parts[1].isEmpty()) {
                        for (String contacto : parts[1].split(";")) {
                            contactos.add(contacto);
                        }
                    }
                    agendas.add(new AgendaContactos(numeroUsuario, contactos));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agendas;
    }
    /**
     * Carga los contactos de un usuario específico.
     * @param numeroUsuario El número del usuario cuya agenda se está cargando.
     * @return Lista de contactos.
     */
    public List<String> cargarContactosPorUsuario(int numeroUsuario) {
        List<AgendaContactos> agendas = cargarTodasLasAgendas();
        for (AgendaContactos agenda : agendas) {
            if (agenda.getNumeroUsuario() == numeroUsuario) {
                return agenda.getContactos();
            }
        }
        return new ArrayList<>();
    }

    /**
     * Guarda todas las agendas de contactos en el archivo.
     * @param agendas Lista de todas las agendas.
     */
    private void guardarTodasLasAgendas(List<AgendaContactos> agendas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (AgendaContactos agenda : agendas) {
                writer.write(agenda.getNumeroUsuario() + "|");
                for (int i = 0; i < agenda.getContactos().size(); i++) {
                    writer.write(agenda.getContactos().get(i));
                    if (i < agenda.getContactos().size() - 1) {
                        writer.write(";");
                    }
                }
                writer.write("|");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Verifica si un contacto ya existe en la agenda de un usuario.
     * @param numeroUsuario El número del usuario cuya agenda se está verificando.
     * @param numeroContacto El número del contacto a verificar.
     * @return true si el contacto ya existe, false en caso contrario.
     */
    public boolean contactoExiste(int numeroUsuario, String numeroContacto) {
        List<AgendaContactos> agendas = cargarTodasLasAgendas();
        for (AgendaContactos agenda : agendas) {
            if (agenda.getNumeroUsuario() == numeroUsuario) {
                for (String contacto : agenda.getContactos()) {
                    if (contacto.startsWith(numeroContacto + ":")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}