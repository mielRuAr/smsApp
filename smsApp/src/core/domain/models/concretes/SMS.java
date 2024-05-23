package core.domain.models.concretes;

import java.time.LocalDate;


import core.domain.models.abstracts.Mensaje;

public class SMS extends Mensaje{
	
    // Constructor sin argumentos necesario para Gson
    public SMS() {}

    // Constructor con argumentos
    public SMS(int remitente, int destinatario, LocalDate timeStamp, String contenido) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.timeStamp = timeStamp;
        this.texto = contenido;
    }
	

	
	
	
}
