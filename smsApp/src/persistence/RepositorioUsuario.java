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
     * @param contraseña La contraseña del usuario.
     */
    public void guardarUsuario(IUsuario usuario, String contraseña) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(usuario.getNumero() + ":" + usuario.getNombre() + ":" + usuario.getRol() + ":" + contraseña + ":activo");
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



    /**
     * Autentica un usuario mediante su número de teléfono y contraseña.
     * @param numeroTelefono Número de teléfono del usuario.
     * @param password Contraseña del usuario.
     * @return true si la autenticación es exitosa, false en caso contrario.
     */
    public boolean autenticarUsuario(int numeroTelefono, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                int telefono = Integer.parseInt(partes[0]);
                String pass = partes[3];
                String estado = partes[4]; // Asumiendo que el estado del usuario está en la quinta posición

                if (telefono == numeroTelefono && pass.equals(password) && !estado.equals("bloqueado")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Verifica si un usuario está bloqueado.
     * @param numeroTelefono Número de teléfono del usuario.
     * @return true si el usuario está bloqueado, false en caso contrario.
     */
    public boolean estaBloqueado(int numeroTelefono) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                int telefono = Integer.parseInt(partes[0]);
                String estado = partes[4]; // Asumiendo que el estado del usuario está en la quinta posición

                if (telefono == numeroTelefono && estado.equals("bloqueado")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Bloquea un usuario en el fichero de texto.
     * @param numeroTelefono Número de teléfono del usuario.
     */
    public void bloquearUsuario(int numeroTelefono) {
        modificarEstadoUsuario(numeroTelefono, "bloqueado");
    }

    /**
     * Desbloquea un usuario en el fichero de texto.
     * @param numeroTelefono Número de teléfono del usuario.
     */
    public void desbloquearUsuario(int numeroTelefono) {
        modificarEstadoUsuario(numeroTelefono, "activo");
    }

    /**
     * Modifica el estado de un usuario en el fichero de texto.
     * @param numeroTelefono Número de teléfono del usuario.
     * @param estado El nuevo estado del usuario.
     */
    private void modificarEstadoUsuario(int numeroTelefono, String estado) {
        List<String> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (Integer.parseInt(parts[0]) == numeroTelefono) {
                    parts[4] = estado;
                }
                usuarios.add(String.join(":", parts));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String usuario : usuarios) {
                writer.write(usuario);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un usuario en el fichero de texto.
     * @param numeroTelefono Número de teléfono del usuario.
     */
    public void eliminarUsuario(int numeroTelefono) {
        List<String> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (Integer.parseInt(parts[0]) != numeroTelefono) {
                    usuarios.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String usuario : usuarios) {
                writer.write(usuario);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
