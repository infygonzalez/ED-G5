package modelo_BDUtils;

/**
 * 
 */
public class SQLQueries {

	
		//Selects
		public static final String SELECT_AGENCIA_USUARIO_CONTRASEÑA ="SELECT * FROM agenciaviajes WHERE Nombre = ? AND Contrasena  = ?";
		public static final String SELECT_NOMBRE_AGENCIA= "SELECT * FROM agenciaviajes WHERE Nombre = ?";
	
		
		//Inserts
		public static final String INSERT_NUEVA_AGENCIA="INSERT INTO agenciaviajes(Nombre, Logo, ColorMarca, NumEmp, Tipo, Contrasena) VALUES (?, ?, ?, ?, ?, ?) ";
}
