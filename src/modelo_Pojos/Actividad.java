package modelo_Pojos;

public class Actividad extends Evento {
	
	private String descripcion;
    
	public Actividad(String id, String nombre, String tipo, double precio, String fecha, Viaje viaje, String descripcion) {
		super(id, nombre, tipo, precio, fecha, viaje);
		this.descripcion=descripcion;
		
		// TODO Auto-generated constructor stub
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Actividad [descripcion=" + descripcion + "]";
	}

	

    
	
}