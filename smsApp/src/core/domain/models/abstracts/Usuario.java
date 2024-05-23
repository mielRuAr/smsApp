package core.domain.models.abstracts;

import core.domain.interfaces.IUsuario;

public abstract class Usuario implements IUsuario{
	protected int numero;
	protected String nombre;
	protected String rol;
	
	
    // Constructor
    public Usuario(int numero, String nombre, String rol) {
        this.numero = numero;
        this.nombre = nombre;
        this.rol = rol;
    }

	
	@Override
	public int getNumero() {
		// TODO Auto-generated method stub
		return this.numero;
	}
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombre;
	}
	@Override
	public String getRol() {
		// TODO Auto-generated method stub
		return this.rol;
	}


	@Override
	public void setNumero(int numero) {
		this.numero= numero;
		
	}


	@Override
	public void setNombre(String nombre) {
		// TODO Auto-generated method stub
		this.nombre = nombre;
	}


	@Override
	public void setRol(String rol) {
		// TODO Auto-generated method stub
		this.rol = rol;
	}
}
