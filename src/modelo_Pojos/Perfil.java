package modelo_Pojos;

/**
 * 
 */
public class Perfil {
    
    // Atributos
    private static int contadorId = 1; // Variable estática para generar IDs únicos
    private int id;
    private String nombre;
    private String colorMarca;
    private int numeroEmpleados;
    private String logo;
    private String contr;

    // Constructor
    /**
     * @param nombre
     * @param colorMarca
     * @param numeroEmpleados
     * @param logo
     * @param contr
     */
    public Perfil(String nombre, String colorMarca, int numeroEmpleados, String logo, String contr) {
        this.id = contadorId++;  // Asigna un ID único y luego lo incrementa
        this.nombre = nombre;
        this.colorMarca = colorMarca;
        this.numeroEmpleados = numeroEmpleados;
        this.logo = logo;
        this.contr = contr;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

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

    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setNumeroEmpleados(int numeroEmpleados) {
        this.numeroEmpleados = numeroEmpleados;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getContr() {
        return contr;
    }

    public void setContr(String contr) {
        this.contr = contr;
    }

    // Método toString para mostrar los datos del perfil
    @Override
    public String toString() {
        return "Perfil [ID=" + id + ", Nombre=" + nombre + ", Color Marca=" + colorMarca + 
               ", Número Empleados=" + numeroEmpleados + ", Logo=" + logo + ", Contraseña=" + contr + "]";
    }
}
