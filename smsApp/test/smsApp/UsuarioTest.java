package smsApp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import models.Administrador;
import models.MensajeMultimedia;
import models.MensajeTexto;
import models.Miembro;

public class UsuarioTest {
    private Miembro miembro;
    private Administrador admin;
    private MensajeTexto sms;
    private MensajeMultimedia mms;

    @Before
    public void setUp() {
        usuario = new Miembro(123456789, "UsuarioNormal");
        admin = new Administrador(987654321, "Admin");
        sms = new MensajeTexto("Hola", 123456789, 987654321);
        mms = new MensajeMultimedia(new File("path/to/file"), 123456789, 987654321);
    }
}