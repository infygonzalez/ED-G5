package modelo_Pojos;

public class Aeropuerto {

	 private String nombre;
	    private String ciudad;
	    private String pais;
	    private String telefono;

	    // Constructor
	    public Aeropuerto(String nombre, String ciudad, String pais, String telefono) {
	        this.nombre = nombre;
	        this.ciudad = ciudad;
	        this.pais = pais;
	        this.telefono = telefono;
	    }

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getCiudad() {
			return ciudad;
		}

		public void setCiudad(String ciudad) {
			this.ciudad = ciudad;
		}

		public String getPais() {
			return pais;
		}

		public void setPais(String pais) {
			this.pais = pais;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		@Override
		public String toString() {
			return "Aeropuerto [nombre=" + nombre + ", ciudad=" + ciudad + ", pais=" + pais + ", telefono=" + telefono
					+ "]";
		}
	    
		
}
