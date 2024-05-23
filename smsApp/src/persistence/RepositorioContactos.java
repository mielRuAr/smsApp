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

	    public void guardarContacto(AgendaContactos agendaContactos) {
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

	    public List<AgendaContactos> cargarTodasLasAgendas() {
	        List<AgendaContactos> agendas = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split("\\|");
	                int numeroUsuario = Integer.parseInt(parts[0]);
	                List<String> contactos = new ArrayList<>();
	                for (String contacto : parts[1].split(":")) {
	                    contactos.add(contacto);
	                }
	                agendas.add(new AgendaContactos(numeroUsuario, contactos));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return agendas;
	    }

	    public List<String> cargarContactosPorUsuario(int numeroUsuario) {
	        List<AgendaContactos> agendas = cargarTodasLasAgendas();
	        for (AgendaContactos agenda : agendas) {
	            if (agenda.getNumeroUsuario() == numeroUsuario) {
	                return agenda.getContactos();
	            }
	        }
	        return new ArrayList<>();
	    }

	    private void guardarTodasLasAgendas(List<AgendaContactos> agendas) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
	            for (AgendaContactos agenda : agendas) {
	                writer.write(agenda.getNumeroUsuario() + "|");
	                for (int i = 0; i < agenda.getContactos().size(); i++) {
	                    writer.write(agenda.getContactos().get(i));
	                    if (i < agenda.getContactos().size() - 1) {
	                        writer.write(":");
	                    }
	                }
	                writer.write("|");
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
