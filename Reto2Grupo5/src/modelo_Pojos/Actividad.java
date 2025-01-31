package modelo_Pojos;

/**
 * 
 */
public class Actividad {

	  private String nombreEvento;
	    private String descripcion;
	    private String fecha; // Formato de fecha
	    private double precio;
	    
	    
		public Actividad(String nombreEvento, String descripcion, String fecha, double precio) {
			this.nombreEvento = nombreEvento;
			this.descripcion = descripcion;
			this.fecha = fecha;
			this.precio = precio;
		}
		
		public String getNombreEvento() {
			return nombreEvento;
		}
		public void setNombreEvento(String nombreEvento) {
			this.nombreEvento = nombreEvento;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public double getPrecio() {
			return precio;
		}
		public void setPrecio(double precio) {
			this.precio = precio;
		}
		
		@Override
		public String toString() {
			return "Actividad [nombreEvento=" + nombreEvento + ", descripcion=" + descripcion + ", fecha=" + fecha
					+ ", precio=" + precio + "]";
		}
		
	    
}
