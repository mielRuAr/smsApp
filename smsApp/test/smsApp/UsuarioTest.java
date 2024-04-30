package smsApp;

import static org.junit.Assert.*;

import org.junit.Test;

import models.abstracts.Usuario;

public class UsuarioTest {
    private Usuario usuario;
    private Administrador admin;
    private MensajeTexto sms;
    private MensajeMultimedia mms;

    @Before
    public void setUp() {
        usuario = new UsuarioNormal(123456789, "UsuarioNormal");
        admin = new Administrador(987654321, "Admin");
        sms = new MensajeTexto("Hola", 123456789, 987654321);
        mms = new MensajeMultimedia(new File("path/to/file"), 123456789, 987654321);
    }
}