package modelo_Pojos;

public class Aerolinea {
	
	private String nombre;
	private int codigo;
	
	public Aerolinea(String nombre, int codigo) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
	}
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	@Override
	public String toString() {
		return "Aerolinea [nombre=" + nombre + ", codigo=" + codigo + "]";
	}

	

}
