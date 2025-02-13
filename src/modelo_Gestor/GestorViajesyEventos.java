package modelo_Gestor;

import java.awt.Color;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo_BDUtils.BDUtils;
import modelo_BDUtils.SQLQueries;

public class GestorViajesyEventos {
	 

	public void ImportarColoryLogo(JPanel panelColor, JPanel panelLogo, int idAgencia) {
		// TODO Auto-generatWed method stub
		   if (panelColor == null || panelLogo == null) {
	            System.err.println("Error: Uno de los paneles es null.");
	            return;
	        }
	        try (Connection conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
	                PreparedStatement sentencia = conexion.prepareStatement(SQLQueries.SELECT_COLORyLogo_IDAGENCIA)) {
	            
	               sentencia.setInt(1, idAgencia);
	               ResultSet resultado = sentencia.executeQuery();
	            while(resultado.next()){
	                
	                
	                  String colorHex = resultado.getString("ColorMarca"); 
	                  Color color = convertirColor(colorHex);
	                  panelColor.setBackground(color);
	                
	                  String logo = resultado.getString("Logo");
	                  if (logo != null && !logo.isEmpty()) {
	                      ImageIcon logoIcon = convertirImg(logo);
	                      if (logoIcon != null) {
	                          JLabel labelLogo = new JLabel(logoIcon);
	                          panelLogo.add(labelLogo);
	                      }
	            }
	                  
	            }
	                    
	    }catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private ImageIcon convertirImg(String logo) {
		// TODO Auto-generated method stub
		try {
            URL url = new URL(logo);
            ImageIcon imageIcon = new ImageIcon(url);
            Image scaledImage = imageIcon.getImage().getScaledInstance(114, 96, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (MalformedURLException e) {
            System.err.println("URL inválida: " + logo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

	}
	private Color convertirColor(String colorHex) {
		// TODO Auto-generated method stub
		return Color.decode(colorHex);
	}

	/********************************CARGAR DATOS TABLA VIAJES***************************************/

	public void cargarDatosViajes(int idAgencia, JTable tableViajes, JTable tableEventos) {
        DefaultTableModel modelo = (DefaultTableModel) tableViajes.getModel();
        modelo.setRowCount(0);

        try (Connection conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
             PreparedStatement sentencia = conexion.prepareStatement(SQLQueries.SELECT_VIAJES_IDAGENCIA)) {
           
            sentencia.setInt(1, idAgencia);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
            String IdViajes = resultado.getString("IdViajes");
                String Nombre = resultado.getString("Nombre");
                String Tipo = resultado.getString("Tipo");
                String PaisDestino = resultado.getString("PaisDestino");
                String Fecha_Inicio = resultado.getString("Fecha_Inicio");
                String Fecha_Fin = resultado.getString("Fecha_Fin");
                int Duracion_dias = resultado.getInt("Duracion_dias");
                String ServNoIncluidos = resultado.getString("ServNoIncluidos");
                // Se agrega el idViaje como octava columna (columna oculta)
                modelo.addRow(new Object[]{IdViajes, Nombre, Tipo, PaisDestino, Fecha_Inicio, Fecha_Fin, Duracion_dias, ServNoIncluidos});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	/********************************CARGAR DATOS TABLA EVENTOS***************************************/

	public void cargarDatosEventos(int idViaje, JTable tableEventos) {
		   DefaultTableModel modelo = (DefaultTableModel) tableEventos.getModel();
		   modelo.setRowCount(0);

		   try (Connection conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
		        PreparedStatement sentencia = conexion.prepareStatement(SQLQueries.SELECT_EVENTOS_IDVIAJE)) {
		     
		       sentencia.setInt(1, idViaje);
		       ResultSet resultado = sentencia.executeQuery();

		       while (resultado.next()) {
		           String nombre = resultado.getString("Nombre");
		           String tipo = resultado.getString("Tipo");
		           double precio = resultado.getDouble("Precio");
		           String fecha = resultado.getString("Fecha");
		           modelo.addRow(new Object[]{nombre, tipo, precio, fecha});
		       }
		   } catch (SQLException e) {
		       e.printStackTrace();
		   }
		}

	public void añadirNuevoViaje(String nombreViaje, String descripcion, String tipo, String pais, Date fechaInicio, Date fechaFin, int dias, String servicios, int idAgencia) {
		// TODO Auto-generated method stub
		  try (Connection conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
			         PreparedStatement viaje = conexion.prepareStatement(SQLQueries.INSERT_NUEVO_VIAJE)) {
			        
			        viaje.setString(1, nombreViaje);
			        viaje.setString(2, descripcion);
			        viaje.setString(3, tipo);
			        viaje.setString(4, pais);
			        viaje.setDate(5, new java.sql.Date(fechaInicio.getTime()));
			        viaje.setDate(6, new java.sql.Date(fechaFin.getTime()));
			        viaje.setInt(7, dias);
			        viaje.setString(8, servicios);
			        viaje.setInt(9, idAgencia);

			        int filasInsertadas = viaje.executeUpdate();
			        if (filasInsertadas > 0) {
			            JOptionPane.showMessageDialog(null, "El viaje se ha guardado correctamente.");
			        } else {
			            JOptionPane.showMessageDialog(null, "Error al guardar el viaje.");
			        }
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Error al guardar el viaje.", "Error", JOptionPane.ERROR_MESSAGE);
	}

}
	
	
}
