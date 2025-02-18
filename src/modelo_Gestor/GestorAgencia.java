 package modelo_Gestor;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo_BDUtils.BDUtils;
import modelo_BDUtils.SQLQueries;
import modelo_Vista.Login;

public class GestorAgencia {
	
	// MÉTODO PARA VALIDAR LOGIN Y DEVOLVER EL idAgencia
	public int validarLogin(String usu, String contr) {
		 Connection conexion = null;
	        PreparedStatement sentencia = null;
	        ResultSet resultado = null;
	        int idAgencia = -1; // Valor por defecto si no se encuentra

	        try {
	            Class.forName(BDUtils.DRIVER);
	            conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
	            String sql = SQLQueries.SELECT_AGENCIA_USUARIO_CONTRASEÑA;

	            sentencia = conexion.prepareStatement(sql);
	            sentencia.setString(1, usu);
	            sentencia.setString(2, contr);
	            resultado = sentencia.executeQuery();

	            if (resultado.next()) { // Si el usuario y contraseña son correctos
	                idAgencia = resultado.getInt("idAgencia"); // Obtener el ID de la agencia
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL: " + ex.getMessage());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage());
	        } finally {
	            try {
	                if (resultado != null) resultado.close();
	                if (sentencia != null) sentencia.close();
	                if (conexion != null) conexion.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }

	        return idAgencia; // Devuelve el ID de la agencia (o -1 si no se encontró)
	}

	public void añadirNuevaAgencia(String agenNuevo, String numEmpStr, int numEmp, String tipoAgen, String contr, String urlLogo, Color colorSeleccionado, JPanel panel) {
	    // Validación de campos obligatorios
	    if (agenNuevo.isEmpty() || numEmpStr.isEmpty() || tipoAgen.isEmpty() || contr.isEmpty() || colorSeleccionado == null) {
	        JOptionPane.showMessageDialog(panel, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Convertir número de empleados
	    switch (numEmpStr) {
		    case "Entre 2 y 10 empleados":
		        numEmp = 2;
		        break;
		    case "Entre 10 y 100 empleados":
		        numEmp = 10;
		        break;
		    case "Entre 100 y 1000 empleados":
		        numEmp = 100;
		        break;
		    default:
		        JOptionPane.showMessageDialog(panel, "Por favor, selecciona un número de empleados válido.", "Error", JOptionPane.ERROR_MESSAGE);
		        return; 
	    }


	    // Validación de URL del logo
	    if (!esUrlValida(urlLogo)) {
	        JOptionPane.showMessageDialog(panel, "La URL del logo no es válida. Debe ser una URL con formato correcto.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }



	    Connection conexion = null;
	    PreparedStatement sentencia = null;

	    try {
	        // Conexión a la base de datos
	        Class.forName(BDUtils.DRIVER);
	        conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);

	        // Consulta SQL
	        String sql = SQLQueries.INSERT_NUEVA_AGENCIA;
	        sentencia = conexion.prepareStatement(sql);

	        // Asignar parámetros a la consulta
	        sentencia.setString(1, agenNuevo);  // Nombre
	        sentencia.setString(2, urlLogo);  // Logo
	        sentencia.setString(3, "#" + Integer.toHexString(colorSeleccionado.getRGB()).substring(2)); // Color en hexadecimal
	        sentencia.setInt(4, numEmp);
	        sentencia.setString(5, tipoAgen);  // Tipo de agencia
	        sentencia.setString(6, contr);  // Contraseña

	        // Ejecutar la consulta
	        int datosInsertados = sentencia.executeUpdate();

	        if (datosInsertados > 0) {
	            JOptionPane.showMessageDialog(panel, "Agencia añadida correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            Login login = new Login();
	            login.setVisible(true);
	        } else {
	            JOptionPane.showMessageDialog(panel, "No se pudo añadir la agencia.", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(panel, "Error al ejecutar la consulta SQL: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(panel, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        // Cerrar recursos
	        try {
	            if (sentencia != null) sentencia.close();
	            if (conexion != null) conexion.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

	// Método para validar la URL
	private boolean esUrlValida(String urlLogo) {
	    return urlLogo.matches("^(http|https)://.*$");
	}


	public void verificarNombre(JTextField textfield_nombre, JLabel lbl_nombreError, JButton btn_guardar) {
	    String nombreAgencia = textfield_nombre.getText().trim();
	    
	    if (nombreAgencia.isEmpty()) {
	        lbl_nombreError.setText("");
	        btn_guardar.setEnabled(false); // Desactivar el botón hasta que se ingrese un nombre válido
	        return;
	    }

	    try (Connection conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
	         PreparedStatement consulta = conexion.prepareStatement("SELECT COUNT(*) FROM agenciaviajes WHERE Nombre = ?")) {
	        consulta.setString(1, nombreAgencia);
	        try (ResultSet resultado = consulta.executeQuery()) {
	            if (resultado.next() && resultado.getInt(1) > 0) {
	                lbl_nombreError.setText("El nombre ya existe en la base de datos.");
	                btn_guardar.setEnabled(false); // Desactiva el botón si el nombre ya existe
	            } else {
	                lbl_nombreError.setText("");
	                btn_guardar.setEnabled(true); // Activa el botón si el nombre no existe
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}


}
