package modelo_Pojos;

public class Evento {
	
	private String nombreEvento;
	   private String tipoEvento; // "Vuelo", "Hotel" o "Actividad"
	   
	public Evento(String nombreEvento, String tipoEvento) {
		super();
		this.nombreEvento = nombreEvento;
		this.tipoEvento = tipoEvento;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public String getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	   @Override
		public String toString() {
			return "Evento [nombreEvento=" + nombreEvento + ", tipoEvento=" + tipoEvento + "]";
		}
}
