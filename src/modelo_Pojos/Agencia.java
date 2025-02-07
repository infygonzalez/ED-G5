package modelo_Pojos;

import java.util.ArrayList;

public class Agencia {

	private String nombreAgencia;
    private String logo;
    private String colorMarca;
    private String numeroEmpleados; // Cambiado a String
    private String tipoAgencia; // Tipo de agencia: "mayorista", "minorista", "mayorista-minorista"
    private String contrasena;
    private ArrayList<Viaje> viajes; // ArrayList con los viajes asociados

// Constructor
public Agencia(String nombreAgencia, String logo, String colorMarca, String numeroEmpleados, String tipoAgencia, String contrasena) {
    this.nombreAgencia = nombreAgencia;
    this.logo = logo;
    this.colorMarca = colorMarca;
    this.numeroEmpleados = numeroEmpleados;
    this.tipoAgencia = tipoAgencia;
    this.contrasena = contrasena;
    this.viajes = new ArrayList<>();
}

public String getNombreAgencia() {
	return nombreAgencia;
}

public void setNombreAgencia(String nombreAgencia) {
	this.nombreAgencia = nombreAgencia;
}

public String getLogo() {
	return logo;
}

public void setLogo(String logo) {
	this.logo = logo;
}

public String getColorMarca() {
	return colorMarca;
}

public void setColorMarca(String colorMarca) {
	this.colorMarca = colorMarca;
}

public String getNumeroEmpleados() {
	return numeroEmpleados;
}

public void setNumeroEmpleados(String numeroEmpleados) {
	this.numeroEmpleados = numeroEmpleados;
}

public String getTipoAgencia() {
	return tipoAgencia;
}

public void setTipoAgencia(String tipoAgencia) {
	this.tipoAgencia = tipoAgencia;
}

public String getContrasena() {
	return contrasena;
}

public void setContrasena(String contrasena) {
	this.contrasena = contrasena;
}

public ArrayList<Viaje> getViajes() {
	return viajes;
}

public void setViajes(ArrayList<Viaje> viajes) {
	this.viajes = viajes;
}

@Override
public String toString() {
	return "Agencia [nombreAgencia=" + nombreAgencia + ", logo=" + logo + ", colorMarca=" + colorMarca
			+ ", numeroEmpleados=" + numeroEmpleados + ", tipoAgencia=" + tipoAgencia + ", contrasena=" + contrasena
			+ ", viajes=" + viajes + "]";
}





}
