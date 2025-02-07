package modelo_Pojos;

public class Vuelo extends Evento {
    
	private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private String horaSalida;
    private String duracion;
    private Vuelo vueloIda;  // Puede ser útil para los vuelos de ida, pero depende de la lógica que implementes
    private Aerolinea aerolinea;  // Aerolinea asociada al vuelo
 
	
	public Vuelo(String id, String nombre, String tipo, double precio, String fecha, Viaje viaje,
			Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino, String horaSalida, String duracion,
			Vuelo vueloIda, Aerolinea aerolinea) {
		super(id, nombre, tipo, precio, fecha, viaje);
		this.aeropuertoOrigen = aeropuertoOrigen;
		this.aeropuertoDestino = aeropuertoDestino;
		this.horaSalida = horaSalida;
		this.duracion = duracion;
		this.vueloIda = vueloIda;
		this.aerolinea = aerolinea;
	}


	public Aeropuerto getAeropuertoOrigen() {
		return aeropuertoOrigen;
	}


	public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
		this.aeropuertoOrigen = aeropuertoOrigen;
	}


	public Aeropuerto getAeropuertoDestino() {
		return aeropuertoDestino;
	}


	public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
	}


	public String getHoraSalida() {
		return horaSalida;
	}


	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}


	public String getDuracion() {
		return duracion;
	}


	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}


	public Vuelo getVueloIda() {
		return vueloIda;
	}


	public void setVueloIda(Vuelo vueloIda) {
		this.vueloIda = vueloIda;
	}


	public Aerolinea getAerolinea() {
		return aerolinea;
	}


	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}


	@Override
	public String toString() {
		return "Vuelo [aeropuertoOrigen=" + aeropuertoOrigen + ", aeropuertoDestino=" + aeropuertoDestino
				+ ", horaSalida=" + horaSalida + ", duracion=" + duracion + ", vueloIda=" + vueloIda + ", aerolinea="
				+ aerolinea + "]";
	}

	
    
    
}