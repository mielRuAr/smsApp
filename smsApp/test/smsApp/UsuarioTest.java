package smsApp;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;


import org.junit.Test;


import models.UsuarioNormal;
import models.abstracts.Mensaje;


public class UsuarioTest {
	
	
	@Test
    public void testRegistroNumeroTelefono() {
        int numeroTelefono = 123456789;
        boolean registrado = UsuarioNormal.registrarNumeroTelefono(numeroTelefono);
        assertTrue("Registro exitoso, validando que el sistema acepta números que cumplen con el formato y la unicidad requerida.", registrado);
    }

    @Test
    public void testAccesoNumeroTelefono() {
        int numeroTelefono = 123456789;
        boolean acceso = UsuarioNormal.accederNumeroTelefono(numeroTelefono);
        assertTrue("Acceso concedido, comprobando que los números registrados tienen acceso.", acceso);
    }

    @Test
    public void testEnvioSMS() {
        UsuarioNormal usuario = new UsuarioNormal(123456789, "Juan");
        boolean envio = usuario.enviarMensajeTexto("", 987654321);
        assertFalse("Envío fallido, texto vacío, asegurando que no se pueden enviar mensajes vacíos.", envio);
    }

    @Test
    public void testEnvioMMSConImagenValida() {
        UsuarioNormal usuario = new UsuarioNormal(123456789, "Juan");
        File archivo = new File("imagen_incorrecta.txt");
        boolean envio = usuario.enviarMensajeMultimedia(archivo, 987654321);
        assertFalse("Envío fallido, archivo no soportado, asegurando que se respetan las restricciones de tamaño y formato de archivo.", envio);
    }

    @Test
    public void testVerMensajesRecibidosPorUsuarioNoRegistrado() {
        int numeroTelefonoNoRegistrado = 111111111;
        List<Mensaje> mensajes = UsuarioNormal.verMensajesRecibidos(numeroTelefonoNoRegistrado);
        assertNull("Visualización fallida, usuario no encontrado, comprobando que solo usuarios registrados pueden ver mensajes.", mensajes);
    }
}
    
