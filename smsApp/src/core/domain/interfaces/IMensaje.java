package core.domain.interfaces;

import java.time.LocalDate;

public interface IMensaje {
	LocalDate getTimeStamp();
	int getRemitente();
	int getDestinatario();
	String getTexto();

	void setRemitente(int remitente);
	void setDestinatario(int destinatario);
	void setTexto(String texto);
	void setTimeStamp(LocalDate timeStamp);
}
