package modelo_Pojos;

public class Vuelo {
	
	private String trayecto; // "Sólo Ida" o "Ida y vuelta"
    private String aeropuertoOrigen;
    private String aeropuertoDestino;
    private String fechaIda; // Formato de fecha
    private String codigoVuelo;
    private String aerolinea;
    private double precio;
    private int horarioSalida; // Formato HH:MM
    private int duracion; // Formato HH:MM
    
	public Vuelo(String trayecto, String aeropuertoOrigen, String aeropuertoDestino, String fechaIda,
			String codigoVuelo, String aerolinea, double precio, int horarioSalida, int duracion) {
		super();
		this.trayecto = trayecto;
		this.aeropuertoOrigen = aeropuertoOrigen;
		this.aeropuertoDestino = aeropuertoDestino;
		this.fechaIda = fechaIda;
		this.codigoVuelo = codigoVuelo;
		this.aerolinea = aerolinea;
		this.precio = precio;
		this.horarioSalida = horarioSalida;
		this.duracion = duracion;
	}
	public String getTrayecto() {
		return trayecto;
	}
	public void setTrayecto(String trayecto) {
		this.trayecto = trayecto;
	}
	public String getAeropuertoOrigen() {
		return aeropuertoOrigen;
	}
	public void setAeropuertoOrigen(String aeropuertoOrigen) {
		this.aeropuertoOrigen = aeropuertoOrigen;
	}
	public String getAeropuertoDestino() {
		return aeropuertoDestino;
	}
	public void setAeropuertoDestino(String aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
	}
	public String getFechaIda() {
		return fechaIda;
	}
	public void setFechaIda(String fechaIda) {
		this.fechaIda = fechaIda;
	}
	public String getCodigoVuelo() {
		return codigoVuelo;
	}
	public void setCodigoVuelo(String codigoVuelo) {
		this.codigoVuelo = codigoVuelo;
	}
	public String getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getHorarioSalida() {
		return horarioSalida;
	}
	public void setHorarioSalida(int horarioSalida) {
		this.horarioSalida = horarioSalida;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	@Override
	public String toString() {
		return "Vuelo [trayecto=" + trayecto + ", aeropuertoOrigen=" + aeropuertoOrigen + ", aeropuertoDestino="
				+ aeropuertoDestino + ", fechaIda=" + fechaIda + ", codigoVuelo=" + codigoVuelo + ", aerolinea="
				+ aerolinea + ", precio=" + precio + ", horarioSalida=" + horarioSalida + ", duracion=" + duracion
				+ "]";
	}
}
