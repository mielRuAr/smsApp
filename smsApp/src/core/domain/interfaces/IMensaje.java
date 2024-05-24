package core.domain.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IMensaje {
	LocalDateTime getTimeStamp();
	int getRemitente();
	int getDestinatario();
	String getTexto();

	void setRemitente(int remitente);
	void setDestinatario(int destinatario);
	void setTexto(String texto);
	void setTimeStamp(LocalDateTime timeStamp);
}
