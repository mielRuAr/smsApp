package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.domain.interfaces.IUsuario;
import core.domain.models.concretes.UsuarioNormal;

public class RepositorioUsuario {
	  private static final String FILE_PATH = "resources/usuarios.txt";

	    /**
	     * Guarda un usuario en el fichero de texto.
	     * @param usuario El usuario a guardar.
	     */
	    public void guardarUsuario(IUsuario usuario) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
	            writer.write(usuario.getNumero() + ":" + usuario.getNombre() + ":" + usuario.getRol());
	            writer.newLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Busca un usuario por su número de teléfono.
	     * @param numero Número de teléfono del usuario.
	     * @return El usuario encontrado o null si no se encuentra.
	     */
	    public IUsuario buscarUsuarioPorNumero(int numero) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(":");
	                int num = Integer.parseInt(parts[0]);
	                if (num == numero) {
	                    return new UsuarioNormal(num, parts[1], parts[2]);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    /**
	     * Carga todos los usuarios desde el fichero de texto.
	     * @return Lista de todos los usuarios.
	     */
	    public List<IUsuario> cargarTodosLosUsuarios() {
	        List<IUsuario> usuarios = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(":");
	                int numero = Integer.parseInt(parts[0]);
	                String nombre = parts[1];
	                String rol = parts[2];
	                usuarios.add(new UsuarioNormal(numero, nombre, rol));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return usuarios;
	    }
}
