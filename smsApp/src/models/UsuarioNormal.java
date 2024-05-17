package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import models.abstracts.Mensaje;
import models.abstracts.Usuario;

public class UsuarioNormal extends Usuario{

	public UsuarioNormal(int numeroTelefono, String nombre) {
		super(numeroTelefono, nombre);
		// TODO Auto-generated constructor stub
	}
	
	public static boolean registrarNumeroTelefono(int numeroTelefono) {

		return true; 
	}

	public static boolean accederNumeroTelefono(int numeroTelefono) {

		return true; 
	}

	public boolean enviarMensajeTexto(String mensaje, int destinatario) {

		return true; 
	}

	public boolean enviarMensajeMultimedia(File archivo, int destinatario) {

		return true; 
	}

	public static List<Mensaje> verMensajesRecibidos(int numeroTelefono) {
		
		return new ArrayList<>(); 
	}

}
