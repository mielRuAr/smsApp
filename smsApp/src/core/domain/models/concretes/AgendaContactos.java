package core.domain.models.concretes;

import java.util.List;

public class AgendaContactos {
	private int numeroUsuario;
	private List<String> contactos;

	public AgendaContactos(int numeroUsuario, List<String> contactos) {
		this.numeroUsuario = numeroUsuario;
		this.contactos = contactos;
	}

	public int getNumeroUsuario() {
		return numeroUsuario;
	}

	public void setNumeroUsuario(int numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
	}

	public List<String> getContactos() {
		return contactos;
	}

	public void setContactos(List<String> contactos) {
		this.contactos = contactos;
	}
}
