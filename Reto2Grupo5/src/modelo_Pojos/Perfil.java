package modelo_Pojos;

public class Perfil {

	    //Atributos
	    private String nombre;
		private String colorMarca;
	    private int numeroEmpleados; // "Entre 2 y 10 empleados", "Entre 10 y 100 empleados", "Entre 100 y 1000 empleados"
	    private String logo; //"Importado por URL"
	    
	    
	    public Perfil(String nombre, String colorMarca, int numeroEmpleados, String logo) {
			this.nombre = nombre;
			this.colorMarca = colorMarca;
			this.numeroEmpleados = numeroEmpleados;
			this.logo = logo;
		}
		//Getters y Setters
	  	public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getColorMarca() {
			return colorMarca;
		}
		public void setColorMarca(String colorMarca) {
			this.colorMarca = colorMarca;
		}
	
		public String getLogo() {
			return logo;
		}
		public void setLogo(String logo) {
			this.logo = logo;
		}
		public int getNumeroEmpleados() {
			return numeroEmpleados;
		}
		public void setNumeroEmpleados(int numeroEmpleados) {
			this.numeroEmpleados = numeroEmpleados;
		}
		
		@Override
		public String toString() {
			return "Perfil [nombre=" + nombre + ", colorMarca=" + colorMarca + ", numeroEmpleados=" + numeroEmpleados
					+ ", logo=" + logo + "]";
		}
		
}
