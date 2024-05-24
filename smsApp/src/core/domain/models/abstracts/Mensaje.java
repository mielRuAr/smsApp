package core.domain.models.abstracts;

import java.time.LocalDate;
import java.time.LocalDateTime;

import core.domain.interfaces.IMensaje;
import core.domain.interfaces.IUsuario;

public abstract class Mensaje implements IMensaje{
	protected int remitente;
	protected int destinatario;
	protected LocalDateTime timeStamp;
	protected String texto;
	
    // Constructor sin argumentos necesario para Gson
    public Mensaje() {}

    
    
	@Override
	public LocalDateTime getTimeStamp() {
		// TODO Auto-generated method stub
		return this.timeStamp;
	}
	@Override
	public int getRemitente() {
		// TODO Auto-generated method stub
		return this.remitente;
	}
	@Override
	public int getDestinatario() {
		// TODO Auto-generated method stub
		return this.destinatario;
	}
	
	@Override
	public String getTexto() {
		// TODO Auto-generated method stub
		return this.texto;
	}



	@Override
	public void setRemitente(int remitente) {
		this.remitente = remitente;
		
	}



	@Override
	public void setDestinatario(int destinatario) {
		this.destinatario = destinatario;
		
	}



	@Override
	public void setTexto(String texto) {
		// TODO Auto-generated method stub
		this.texto = texto;
	}



	@Override
	public void setTimeStamp(LocalDateTime timeStamp) {
		// TODO Auto-generated method stub
		this.timeStamp = timeStamp;
	}



	
}
