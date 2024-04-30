package smsApp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import models.Administrador;
import models.Miembro;

public class UsuarioTest {
    private Miembro miembro;
    private Administrador admin;

    @Before
    public void setUp() {
        miembro = new Miembro(123456789, "Miembro");
        admin = new Administrador(987654321, "Admin");
    }
    
    @Test // Registro con Número Ya Registrado
    public void testRegistroNumeroYaRegistrado() {
        miembro.registrar(234567890); // Asumimos que este método cambia el estado interno para reflejar el registro
        assertFalse("El registro debe fallar si el número ya está en uso", miembro.registrar(234567890));
    }

    @Test //Acceso con Número Registrado
    public void testAccesoConNumeroRegistrado() {
        miembro.registrar(234567890);
        assertTrue("El acceso debe ser concedido para número registrado", miembro.acceder(234567890));
    }

    
    @Test // Acceso con Número No Registrado
    public void testAccesoConNumeroNoRegistrado() {
        assertFalse("El acceso debe ser denegado para número no registrado", miembro.acceder(234567890));
    }
    

    
    
}