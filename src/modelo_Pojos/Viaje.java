package modelo_Pojos;

import java.util.ArrayList;

public class Viaje {
	
	  private String nombreViaje;
	    private String tipoViaje;
	    private String inicioViaje; // Formato de fecha
	    private String finViaje; // Formato de fecha
	    private int numeroDias;
	    private Pais pais; // Pais como objeto
	    private String descripcion;
	    private String serviciosNoIncluidos;
	    private Agencia agencia; // Agencia asociada
	    private ArrayList<Evento> eventos; // Lista de eventos asociados a este viaje

    
    
	public Viaje(String nombreViaje, String tipoViaje, String inicioViaje, String finViaje, int numeroDias, Pais pais,
			String descripcion, String serviciosNoIncluidos) {
		super();
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
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
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
	
	
	   public void agregarEvento(Evento evento) {
	        this.eventos.add(evento);
	    }
	
	
	@Override
	public String toString() {
		return "Viaje [nombreViaje=" + nombreViaje + ", tipoViaje=" + tipoViaje + ", inicioViaje=" + inicioViaje
				+ ", finViaje=" + finViaje + ", numeroDias=" + numeroDias + ", pais=" + pais + ", descripcion="
				+ descripcion + ", serviciosNoIncluidos=" + serviciosNoIncluidos + "]";
	}
}
