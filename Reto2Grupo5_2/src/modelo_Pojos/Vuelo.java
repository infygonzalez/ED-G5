package modelo_Pojos;

public class Vuelo extends Evento {
    
    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private String horaSalida;
    private String duracion;
    private Vuelo vueloIda;  // Puede ser útil para los vuelos de ida, pero depende de la lógica que implementes
    private Aerolinea aerolinea;  // Aerolinea asociada al vuelo
    
    // Constructor
    /**
     * @param id
     * @param nombre
     * @param tipo
     * @param precio
     * @param fecha
     * @param agencia
     * @param aeropuertoOrigen
     * @param aeropuertoDestino
     * @param horaSalida
     * @param duracion
     * @param aerolinea
     */
    public Vuelo(String id, String nombre, String tipo, double precio, String fecha, Agencia agencia, 
                 Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino, String horaSalida, 
                 String duracion, Aerolinea aerolinea) {
        super(id, nombre, tipo, precio, fecha, agencia);  // Llamamos al constructor de la clase base Evento
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.horaSalida = horaSalida;
        this.duracion = duracion;
        this.aerolinea = aerolinea;
    }
    
    // Getters y setters
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
                + ", horaSalida=" + horaSalida + ", duracion=" + duracion + ", aerolinea=" + aerolinea + "]";
    }
}
