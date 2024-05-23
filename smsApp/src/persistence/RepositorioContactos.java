package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RespositorioContactos {
	 private static final String FILE_PATH = "resources/contactos.txt";

	    /**
	     * Guarda los contactos de un usuario en el fichero de texto.
	     * @param numeroUsuario Número de teléfono del usuario.
	     * @param contactos Lista de números de teléfono de los contactos.
	     */
	    public void guardarContacto(int numeroUsuario, List<Integer> contactos) {
	        List<String> lineas = cargarContactos();
	        boolean usuarioEncontrado = false;

	        for (int i = 0; i < lineas.size(); i++) {
	            String[] parts = lineas.get(i).split("\\|");
	            if (Integer.parseInt(parts[0]) == numeroUsuario) {
	                usuarioEncontrado = true;
	                StringBuilder sb = new StringBuilder();
	                sb.append(numeroUsuario).append("|");
	                for (int j = 0; j < contactos.size(); j++) {
	                    sb.append(contactos.get(j));
	                    if (j < contactos.size() - 1) {
	                        sb.append(":");
	                    }
	                }
	                sb.append("|");
	                lineas.set(i, sb.toString());
	                break;
	            }
	        }

	        if (!usuarioEncontrado) {
	            StringBuilder sb = new StringBuilder();
	            sb.append(numeroUsuario).append("|");
	            for (int j = 0; j < contactos.size(); j++) {
	                sb.append(contactos.get(j));
	                if (j < contactos.size() - 1) {
	                    sb.append(":");
	                }
	            }
	            sb.append("|");
	            lineas.add(sb.toString());
	        }

	        guardarTodosLosContactos(lineas);
	    }

	    /**
	     * Carga todos los contactos desde el fichero de texto.
	     * @return Lista de todas las líneas de contactos.
	     */
	    public List<String> cargarContactos() {
	        List<String> lineas = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                lineas.add(line);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return lineas;
	    }

	    /**
	     * Carga los contactos de un usuario específico desde el fichero de texto.
	     * @param numeroUsuario Número de teléfono del usuario.
	     * @return Lista de números de teléfono de los contactos del usuario.
	     */
	    public List<Integer> cargarContactosPorUsuario(int numeroUsuario) {
	        List<String> lineas = cargarContactos();
	        for (String linea : lineas) {
	            String[] parts = linea.split("\\|");
	            if (Integer.parseInt(parts[0]) == numeroUsuario) {
	                List<Integer> contactos = new ArrayList<>();
	                for (String contacto : parts[1].split(":")) {
	                    contactos.add(Integer.parseInt(contacto));
	                }
	                return contactos;
	            }
	        }
	        return new ArrayList<>();
	    }

	    /**
	     * Guarda todos los contactos en el fichero de texto.
	     * @param lineas Lista de todas las líneas de contactos.
	     */
	    private void guardarTodosLosContactos(List<String> lineas) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
	            for (String linea : lineas) {
	                writer.write(linea);
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

