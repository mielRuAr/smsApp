package smsApp;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import models.Miembro;
import models.Administrador;
import models.MensajeTexto;

import java.util.ArrayList;
import java.util.List;

public class MensajeUsuarioTest {
    private Miembro miembro;
    private Miembro miembroNoRegistrado;
    private Administrador admin;
    private MensajeTexto mensaje;

    @Before
    public void setUp() {
        miembro = new Usuario("UsuarioRegistrado", 123456789);
        miembroNoRegistrado = new Usuario("UsuarioNoRegistrado", 987654321);
        admin = new Administrador("Admin", 112233445);
        mensaje = new Mensaje(123456789, "Hola, ¿cómo estás?");

        // Simulamos que el usuario y el admin están registrados
        SistemaDeMensajes.registrarUsuario(miembro);
        SistemaDeMensajes.registrarUsuario(admin);

        // El usuario envía un mensaje que será recibido por otro usuario
        miembro.enviarMensaje(mensaje, 112233445);
    }
    
    //Ver Mensajes Recibidos por Usuario
    @Test
    public void testVerMensajesRecibidosPorUsuarioRegistrado() {
        List<Mensaje> mensajes = miembro.verMensajesRecibidos();
        assertFalse("La lista de mensajes recibidos no debe estar vacía", mensajes.isEmpty());
    }

    @Test
    public void testVerMensajesRecibidosPorUsuarioNoRegistrado() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            miembroNoRegistrado.verMensajesRecibidos();
        });
        assertEquals("Usuario no encontrado", exception.getMessage());
    }

    
    //Ver Mensajes Enviados por Usuario
    @Test
    public void testVerMensajesEnviadosPorUsuarioRegistrado() {
        List<Mensaje> mensajes = usuario.verMensajesEnviados();
        assertFalse("La lista de mensajes enviados no debe estar vacía", mensajes.isEmpty());
    }

    @Test
    public void testVerMensajesEnviadosPorUsuarioNoRegistrado() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            miembroNoRegistrado.verMensajesEnviados();
        });
        assertEquals("Usuario no encontrado", exception.getMessage());
    }
    
    //Administrador Ve Mensajes de Cualquier Usuario
    @Test
    public void testAdminVeMensajesDeUsuarioRegistrado() {
        List<Mensaje> mensajes = admin.verMensajesDeTodosUsuarios();
        assertFalse("El administrador debe poder ver los mensajes de todos los usuarios", mensajes.isEmpty());
    }

    @Test
    public void testAdminVeMensajesDeUsuarioNoRegistrado() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            admin.verMensajesDeUsuario(miembroNoRegistrado.getNumeroTelefono());
        });
        assertEquals("Usuario no registrado", exception.getMessage());
    }
    
    //Búsqueda de Contacto por Nombre
    @Test
    public void testBusquedaDeContactoPorNombreValido() {
        Miembro contacto = miembro.buscarContactoPorNombre("Admin");
        assertNotNull("Debe encontrar un contacto con el nombre dado", contacto);
    }

    @Test
    public void testBusquedaDeContactoPorNombreNoAsociado() {
        Miembro contacto = miembro.buscarContactoPorNombre("Desconocido");
        assertNull("No debe encontrar un contacto con un nombre no asociado", contacto);
    }


}