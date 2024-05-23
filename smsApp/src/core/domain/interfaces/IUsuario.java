package core.domain.interfaces;

public interface IUsuario {
	int getNumero();
	String getNombre();
	String getRol();
	
	void setNumero(int numero);
	void setNombre(String nombre);
	void setRol(String rol);
}
