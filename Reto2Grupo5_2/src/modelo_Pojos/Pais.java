package modelo_Pojos;

public class Pais {
	 private String nombre;
	 private String codigo;
	 
	 // Constructor
	    public Pais(String nombre, String codigo) {
	        this.nombre = nombre;
	        this.codigo = codigo;
	    }

	 
	 
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	@Override
	public String toString() {
		return "Pais [nombre=" + nombre + ", codigo=" + codigo + "]";
	}
	
	
	
}
