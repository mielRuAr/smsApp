package core.domain.models.concretes;

import java.util.List;

public class AgendaContactos {
	private int numeroUsuario;
    private List<Integer> contactos;

    public AgendaContactos(int numeroUsuario, List<Integer> contactos) {
        this.numeroUsuario = numeroUsuario;
        this.contactos = contactos;
    }

    public int getNumeroUsuario() {
        return numeroUsuario;
    }

    public void setNumeroUsuario(int numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }

    public List<Integer> getContactos() {
        return contactos;
    }

    public void setContactos(List<Integer> contactos) {
        this.contactos = contactos;
    }
}
