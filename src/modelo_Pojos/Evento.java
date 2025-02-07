package modelo_Pojos;

public class Evento {
    private String id;
    private String nombre;
    private String tipo; // "Vuelo", "Alojamiento", "Actividad"
    private double precio;
    private String fecha;
    private Viaje viaje; // La agencia asociada al evento

    // Constructor
    public Evento(String id, String nombre, String tipo, double precio, String fecha, Viaje viaje) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.fecha = fecha;
        this.setViaje(viaje);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", fecha=" + fecha
				+ ", viaje=" + viaje + "]";
	}

	
	
	
    
    
}
