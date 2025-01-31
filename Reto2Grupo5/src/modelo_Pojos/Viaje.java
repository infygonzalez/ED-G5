package modelo_Pojos;


/**
 * 
 */
public class Viaje {

    
	private String nombreViaje;
    private String tipoViaje;
    private String inicioViaje; // Formato de fecha
    private String finViaje; // Formato de fecha
    private int numeroDias; // Campo autocalculado, no editable
    private String pais; // País seleccionado de un desplegable
    private String descripcion;//Texto Largo
    private String serviciosNoIncluidos;//Texto Largo
    
    
	public Viaje(String nombreViaje, String tipoViaje, String inicioViaje, String finViaje, int numeroDias, String pais,
			String descripcion, String serviciosNoIncluidos) {
		this.nombreViaje = nombreViaje;
		this.tipoViaje = tipoViaje;
		this.inicioViaje = inicioViaje;
		this.finViaje = finViaje;
		this.numeroDias = numeroDias;
		this.pais = pais;
		this.descripcion = descripcion;
		this.serviciosNoIncluidos = serviciosNoIncluidos;
	}
	
	public String getNombreViaje() {
		return nombreViaje;
	}
	public void setNombreViaje(String nombreViaje) {
		this.nombreViaje = nombreViaje;
	}
	public String getTipoViaje() {
		return tipoViaje;
	}
	public void setTipoViaje(String tipoViaje) {
		this.tipoViaje = tipoViaje;
	}
	public String getInicioViaje() {
		return inicioViaje;
	}
	public void setInicioViaje(String inicioViaje) {
		this.inicioViaje = inicioViaje;
	}
	public String getFinViaje() {
		return finViaje;
	}
	public void setFinViaje(String finViaje) {
		this.finViaje = finViaje;
	}
	public int getNumeroDias() {
		return numeroDias;
	}
	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getServiciosNoIncluidos() {
		return serviciosNoIncluidos;
	}
	public void setServiciosNoIncluidos(String serviciosNoIncluidos) {
		this.serviciosNoIncluidos = serviciosNoIncluidos;
	}
	
	
	@Override
	public String toString() {
		return "Viaje [nombreViaje=" + nombreViaje + ", tipoViaje=" + tipoViaje + ", inicioViaje=" + inicioViaje
				+ ", finViaje=" + finViaje + ", numeroDias=" + numeroDias + ", pais=" + pais + ", descripcion="
				+ descripcion + ", serviciosNoIncluidos=" + serviciosNoIncluidos + "]";
	}
	
	

}
