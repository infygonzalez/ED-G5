	package modelo_BDUtils;
	
	/**
	 * 
	 */
	
	public class SQLQueries { 
			//Selects
			public static final String SELECT_AGENCIA_USUARIO_CONTRASEÑA ="SELECT * FROM agenciaviajes WHERE Nombre = ? AND Contrasena  = ?";
			public static final String SELECT_NOMBRE_AGENCIA= "SELECT * FROM agenciaviajes WHERE Nombre = ?";
		    public static final String SELECT_VIAJES_IDAGENCIA="SELECT IdViajes, Nombre , Tipo , PaisDestino , Fecha_Inicio, Fecha_Fin , Duracion_dias ,ServNoIncluidos FROM viajes WHERE idAgencia = ?";
			public static final String SELECT_EVENTOS_IDVIAJE="SELECT Nombre, Tipo, Precio, Fecha FROM eventos WHERE idViajes = ?";
			public static final String SELECT_COLORyLogo_IDAGENCIA=" SELECT Logo , ColorMarca FROM agenciaviajes WHERE idAgencia = ? ";
	        public static final String SELECT_AEROPUERTOS="SELECT Nombre FROM aeropuerto";
	
			//Inserts
			public static final String INSERT_NUEVA_AGENCIA="INSERT INTO agenciaviajes(Nombre, Logo, ColorMarca, NumEmp, Tipo, Contrasena) VALUES (?, ?, ?, ?, ?, ?) ";
			public static final String INSERT_NUEVO_VIAJE="INSERT INTO viajes(Nombre, Descripcion, Tipo, PaisDestino, Fecha_Inicio, Fecha_Fin, Duracion_dias, ServNoIncluidos, IdAgencia) VALUES (?, ?, ?, ?, ?, ?, ?, ? , ?)";
	
			
			//Deletes
			// para el boton basura de viajes
			public static final String DELETE_VIAJE = "delete from viajes where IdViajes=?";
			public static final String DELETE_PAIS_PARA_VIAJE = "DELETE FROM Pais WHERE IdViajes = ?;" ;
			public static final String DELETE_VUELO_IDA_PARA_VIAJE = "DELETE FROM VueloIda WHERE IdViajes = ?";
			public static final String DELETE_VUELO_VUELTA_PARA_VIAJE = "DELETE FROM VueloVuelta WHERE IdViajes = ?;" ;
			public static final String DELETE_EVENTOS_PARA_VIAJE= "DELETE FROM Eventos WHERE IdViajes = ?";
			
			// para el boton basura de eventos
			public static final String DELETE_EVENTO= "DELETE FROM Eventos WHERE Nombre = ?";
			public static final String DELETE_ALOJA_PARA_EVENTO="DELETE FROM Alojamiento WHERE IdEventos IN (SELECT IdEventos FROM Eventos WHERE Nombre = ?)";
	
	}
