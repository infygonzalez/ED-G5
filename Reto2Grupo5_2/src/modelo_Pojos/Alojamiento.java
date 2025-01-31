package modelo_Pojos;

public class Alojamiento  extends Evento{
	
	private String nombreHotel;
    private String ciudad;
    private String tipoHabitacion;
    
    public Alojamiento(String id, String nombre, String tipo, double precio, String fecha, Agencia agencia, 
            String nombreHotel, String ciudad, String tipoHabitacion) {
super(id, nombre, tipo, precio, fecha, agencia);
this.nombreHotel = nombreHotel;
this.ciudad = ciudad;
this.tipoHabitacion = tipoHabitacion;

}

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	@Override
	public String toString() {
		return "Alojamiento [nombreHotel=" + nombreHotel + ", ciudad=" + ciudad + ", tipoHabitacion=" + tipoHabitacion
				+ "]";
	}
    
    
    
    	
}
