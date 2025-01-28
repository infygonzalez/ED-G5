package modelo_Pojos;

public class Alojamiento {
	
	private String nombreEvento;
    private String tipoHabitacion; // "Doble", "Individual" o "Triple"
    private String ciudad;
    private String fechaEntrada; // Formato de fecha
    private String fechaSalida; // Formato de fecha
    private double precio;
    
	public Alojamiento(String nombreEvento, String tipoHabitacion, String ciudad, String fechaEntrada,
			String fechaSalida, double precio) {
		super();
		this.nombreEvento = nombreEvento;
		this.tipoHabitacion = tipoHabitacion;
		this.ciudad = ciudad;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.precio = precio;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public String getTipoHabitacion() {
		return tipoHabitacion;
	}
	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public String getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Alojamiento [nombreEvento=" + nombreEvento + ", tipoHabitacion=" + tipoHabitacion + ", ciudad="
				+ ciudad + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", precio=" + precio
				+ "]";
	}
}
