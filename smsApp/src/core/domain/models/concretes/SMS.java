package core.domain.models.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import core.domain.models.abstracts.Mensaje;

public class SMS extends Mensaje{
	
    // Constructor sin argumentos necesario para Gson
    public SMS() {}

    // Constructor con argumentos
    public SMS(int remitente, int destinatario, LocalDateTime timeStamp, String contenido) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.timeStamp = timeStamp;
        this.texto = contenido;
    }

    
    @Override
    public String toString() {
        return "Número " + (this.getRemitente() != 0 ? "remitente: " + this.getRemitente() : "destinatario: " + this.getDestinatario()) + "\nContenido: " + this.getTexto();
    }
    
    
	

	
	
	
}
