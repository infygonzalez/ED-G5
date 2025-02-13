package modelo_Vista;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import com.toedter.calendar.JDateChooser;
//import com.mysql.cj.xdevapi.Statement;
import com.toedter.calendar.JCalendar;
import javax.swing.JTextPane;

import modelo_BDUtils.BDUtils;
import modelo_BDUtils.SQLQueries;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.regex.Pattern;



public class NuevoEvento extends JFrame {
private static final long serialVersionUID = 1L;
private JPanel contentPane;
private JTextField textField_NombreEven;
private JTextField textField_Aerolinea;
private JTextField textField_Precio;
private JTextField textField_HSalida;
private JTextField textField_Duracion;
private JTextField textField_DuracionVuelta;
private JTextField textField_HVuelta;
private JTextField textField_PrecioTotal;
private JTextField textField_AerolineaReg;
private JTextField textField_Ciudad;
private JTextField textField_PrecioAloj;
private JTextField textField_PrecioAct;
private JComboBox comboBox_AerOrigen;
private String Nombre;
private JLabel lblNewLabel_Error;
private JComboBox comboBox_AerOrigen_1;
private JComboBox comboBox_AerDestino;
private JComboBox comboBox_TipoEven;
private JPanel panel_VueloIda;
private JPanel panel_Alojamiento;
private JPanel panel_Actividad;
private JTextPane textPaneAct;
private JComboBox comboBox_Trayecto;
private  JPanel panelColor;
private JPanel panelLogo;
private int idAgencia;
/**
* Create the frame.
* @param idViaje
* @param idAgencia
*/

public NuevoEvento(int idAgencia, int idViaje) {
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 1000, 737);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

setContentPane(contentPane);
contentPane.setLayout(null);

JPanel panelLogo = new JPanel();
panelLogo.setBounds(0, 0, 114, 96);
contentPane.add(panelLogo);

textField_NombreEven = new JTextField();
textField_NombreEven.setColumns(10);
textField_NombreEven.setBounds(199, 113, 178, 20);
contentPane.add(textField_NombreEven);

lblNewLabel_Error = new JLabel("");
lblNewLabel_Error.setBounds(199, 138, 178, 14);
contentPane.add(lblNewLabel_Error);

textField_NombreEven.getDocument().addDocumentListener(new DocumentListener(){

@Override
public void insertUpdate(DocumentEvent e) {
// TODO Auto-generated method stub
verificarNombre();
}

@Override
public void removeUpdate(DocumentEvent e) {
// TODO Auto-generated method stub
verificarNombre();
}

@Override
public void changedUpdate(DocumentEvent e) {
// TODO Auto-generated method stub
verificarNombre();
}

private void verificarNombre() {
// TODO Auto-generated method stub

String nombreAgencia = textField_NombreEven.getText().trim();
     if (nombreAgencia.isEmpty()) {
     lblNewLabel_Error.setText("");
         return;
     }
   
     try (Connection conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
          PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM agenciaviajes WHERE Nombre = ?")) {
         consulta.setString(1, nombreAgencia);
         try (ResultSet resultado = consulta.executeQuery()) {
             if (resultado.next() && resultado.getInt(1) > 0) {
           
             lblNewLabel_Error.setText("El nombre ya existe en la base de datos.");
             } else {
             lblNewLabel_Error.setText("");
             }
         }
     } catch (SQLException ex) {
         ex.printStackTrace();
     }
}

});



JButton btnGuardar = new JButton("Guardar");
btnGuardar.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
   if (!validarCampos()) {
            JOptionPane.showMessageDialog(null, "Por favor, corrija los errores en los campos.");
         
        } else {
         guardarEvento();
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente.");
        }
    }

});
btnGuardar.setBounds(54, 643, 89, 23);
contentPane.add(btnGuardar);

JButton btnCancelar = new JButton("Cancelar");
btnCancelar.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
setVisible(false);
ViajesyEventos viajeyeventos = new ViajesyEventos(idAgencia);
viajeyeventos.setVisible(true);
}

});

btnCancelar.setBounds(248, 643, 89, 23);
contentPane.add(btnCancelar);

JLabel lblNewLabel = new JLabel("Nombre Evento");
lblNewLabel.setBounds(54, 116, 105, 14);
contentPane.add(lblNewLabel);

JLabel lblNewLabel_1 = new JLabel("Tipo Evento");
lblNewLabel_1.setBounds(54, 161, 89, 14);
contentPane.add(lblNewLabel_1);

comboBox_TipoEven = new JComboBox();
comboBox_TipoEven.setModel(new DefaultComboBoxModel(new String[] {"Vuelo", "Hotel", "Actividad"}));
comboBox_TipoEven.setToolTipText("");
comboBox_TipoEven.setBounds(199, 157, 178, 22);
contentPane.add(comboBox_TipoEven);

  panel_VueloIda = new JPanel();
panel_VueloIda.setBounds(10, 88, 764, 599);
contentPane.add(panel_VueloIda);
panel_VueloIda.setLayout(null);

JLabel lblAeropuerto_Or = new JLabel("Aeropuerto Origen");
lblAeropuerto_Or.setBounds(44, 167, 135, 14);
panel_VueloIda.add(lblAeropuerto_Or);

JLabel lblAeropuerto_Des = new JLabel("Aeropuerto Destino");
lblAeropuerto_Des.setBounds(44, 212, 135, 14);
panel_VueloIda.add(lblAeropuerto_Des);

JLabel lblFechIda = new JLabel("Fecha Ida");
lblFechIda.setBounds(44, 275, 91, 14);
panel_VueloIda.add(lblFechIda);


JLabel lblAerolinea = new JLabel("Aerolinea");
lblAerolinea.setBounds(44, 357, 85, 14);
panel_VueloIda.add(lblAerolinea);


JLabel lblPrecio = new JLabel("Precio");
lblPrecio.setBounds(44, 401, 46, 14);
panel_VueloIda.add(lblPrecio);


JLabel lblHSalida = new JLabel("Hora salida");
lblHSalida.setBounds(44, 439, 78, 14);
panel_VueloIda.add(lblHSalida);


JLabel lblDuracion = new JLabel("Duración");
lblDuracion.setBounds(44, 484, 91, 14);
panel_VueloIda.add(lblDuracion);


comboBox_AerOrigen_1 = new JComboBox();
comboBox_AerOrigen_1.setBounds(189, 163, 178, 22);
panel_VueloIda.add(comboBox_AerOrigen_1);
// Llamamos al método para cargar los aeropuertos
cargarAeropuertosOrigen();


comboBox_AerDestino = new JComboBox();
comboBox_AerDestino.setBounds(189, 208, 178, 22);
panel_VueloIda.add(comboBox_AerDestino);
// Llamamos al método para cargar los aeropuertos
cargarAeropuertosDestino();


textField_Aerolinea = new JTextField();
textField_Aerolinea.setColumns(10);
textField_Aerolinea.setBounds(189, 354, 178, 20);
panel_VueloIda.add(textField_Aerolinea);


textField_Precio = new JTextField();
textField_Precio.setColumns(10);
textField_Precio.setBounds(189, 398, 178, 20);
panel_VueloIda.add(textField_Precio);


textField_HSalida = new JTextField();
textField_HSalida.setColumns(10);
textField_HSalida.setBounds(189, 436, 178, 20);
panel_VueloIda.add(textField_HSalida);


textField_Duracion = new JTextField();
textField_Duracion.setColumns(10);
textField_Duracion.setBounds(189, 481, 178, 20);
panel_VueloIda.add(textField_Duracion);


JButton btnBuscarViaje = new JButton("Buscar Viaje");
btnBuscarViaje.setBounds(409, 185, 119, 23);
panel_VueloIda.add(btnBuscarViaje);


JPanel panel_VueloVuelta = new JPanel();
panel_VueloVuelta.setBounds(402, 275, 352, 313);
panel_VueloIda.add(panel_VueloVuelta);
panel_VueloVuelta.setLayout(null);


JLabel lblFechVuelta = new JLabel("Fecha vuelta");
lblFechVuelta.setBounds(0, 4, 91, 14);
panel_VueloVuelta.add(lblFechVuelta);


JLabel lblAerolinea_Reg = new JLabel("Aerolinea regreso");
lblAerolinea_Reg.setBounds(0, 86, 135, 14);
panel_VueloVuelta.add(lblAerolinea_Reg);


JLabel lblHVuelta = new JLabel("Hora vuelta");
lblHVuelta.setBounds(0, 168, 111, 14);
panel_VueloVuelta.add(lblHVuelta);


JLabel lblDuracion_Vuelta = new JLabel("Duración vuelta");
lblDuracion_Vuelta.setBounds(0, 213, 122, 14);
panel_VueloVuelta.add(lblDuracion_Vuelta);


textField_DuracionVuelta = new JTextField();
textField_DuracionVuelta.setColumns(10);
textField_DuracionVuelta.setBounds(145, 210, 178, 20);
panel_VueloVuelta.add(textField_DuracionVuelta);


textField_HVuelta = new JTextField();
textField_HVuelta.setColumns(10);
textField_HVuelta.setBounds(145, 165, 178, 20);
panel_VueloVuelta.add(textField_HVuelta);


textField_PrecioTotal = new JTextField();
textField_PrecioTotal.setColumns(10);
textField_PrecioTotal.setBounds(145, 127, 178, 20);
panel_VueloVuelta.add(textField_PrecioTotal);


textField_AerolineaReg = new JTextField();
textField_AerolineaReg.setColumns(10);
textField_AerolineaReg.setBounds(145, 83, 178, 20);
panel_VueloVuelta.add(textField_AerolineaReg);


JLabel lblPrecioTotal = new JLabel("Precio total");
lblPrecioTotal.setBounds(0, 130, 91, 14);
panel_VueloVuelta.add(lblPrecioTotal);

JDateChooser dateChooser_idaVuelta = new JDateChooser();
dateChooser_idaVuelta.setBounds(145, 4, 178, 20);
panel_VueloVuelta.add(dateChooser_idaVuelta);

JDateChooser dateChooser_ida = new JDateChooser();
dateChooser_ida.setBounds(189, 275, 178, 20);
panel_VueloIda.add(dateChooser_ida);

// Agregar un PropertyChangeListener para escuchar cambios en las fechas de dateChooser_ida
dateChooser_ida.addPropertyChangeListener(new PropertyChangeListener() {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("date".equals(evt.getPropertyName())) {
            Date fechaIda = dateChooser_ida.getDate(); // Obtener fecha seleccionada en dateChooser_ida (ida)
            Date fechaVuelta = dateChooser_idaVuelta.getDate(); // Obtener fecha seleccionada en dateChooser_idaVuelta (vuelta)

            // Establecer la fecha mínima en dateChooser_idaVuelta solo si la fecha de ida está seleccionada
            if (fechaIda != null) {
                dateChooser_idaVuelta.setMinSelectableDate(fechaIda); // Establecer la fecha mínima en dateChooser_idaVuelta

                // Si la fecha de vuelta es anterior a la de ida, corregirla
                if (fechaVuelta != null && fechaVuelta.before(fechaIda)) {
                    dateChooser_idaVuelta.setDate(fechaIda); // Establecer la fecha de vuelta igual a la de ida
                }
            }
        }
    }
});

// Agregar un PropertyChangeListener para escuchar cambios en las fechas de dateChooser_idaVuelta
dateChooser_idaVuelta.addPropertyChangeListener(new PropertyChangeListener() {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("date".equals(evt.getPropertyName())) {
            Date fechaIda = dateChooser_ida.getDate(); // Obtener fecha seleccionada en dateChooser_ida (ida)
            Date fechaVuelta = dateChooser_idaVuelta.getDate(); // Obtener fecha seleccionada en dateChooser_idaVuelta (vuelta)

            // Si la fecha de vuelta es anterior a la de ida, corregirla
            if (fechaIda != null && fechaVuelta != null && fechaVuelta.before(fechaIda)) {
                dateChooser_idaVuelta.setDate(fechaIda); // Establecer la fecha de vuelta igual a la de ida
            }
        }
    }
});


panel_Alojamiento = new JPanel();
panel_Alojamiento.setBounds(10, 186, 752, 489);
contentPane.add(panel_Alojamiento);


panel_Actividad = new JPanel();
panel_Actividad.setBounds(54, 204, 340, 428);
contentPane.add(panel_Actividad);
panel_Actividad.setLayout(null);


JLabel lblDescripcionAct = new JLabel("Descripción");
lblDescripcionAct.setBounds(25, 178, 72, 14);
panel_Actividad.add(lblDescripcionAct);


JLabel lblPrecioAct = new JLabel("Precio");
lblPrecioAct.setBounds(25, 241, 46, 14);
panel_Actividad.add(lblPrecioAct);


textField_PrecioAct = new JTextField();
textField_PrecioAct.setColumns(10);
textField_PrecioAct.setBounds(107, 238, 198, 20);
panel_Actividad.add(textField_PrecioAct);


JLabel lblFechaAct = new JLabel("Fecha");
lblFechaAct.setBounds(25, 302, 46, 14);
panel_Actividad.add(lblFechaAct);


JDateChooser dateChooser_FechaAtc = new JDateChooser();
dateChooser_FechaAtc.setBounds(107, 296, 198, 20);
panel_Actividad.add(dateChooser_FechaAtc);


textPaneAct = new JTextPane();
textPaneAct.setBounds(107, 24, 198, 168);
panel_Actividad.add(textPaneAct);
panel_Alojamiento.setLayout(null);


JLabel lblCiudad = new JLabel("Ciudad");
lblCiudad.setBounds(51, 82, 46, 14);
panel_Alojamiento.add(lblCiudad);


JLabel lblHabitacion = new JLabel("Tipo dormitorio");
lblHabitacion.setBounds(51, 30, 118, 34);
panel_Alojamiento.add(lblHabitacion);


JLabel lblPrecio_Aloj = new JLabel("Precio");
lblPrecio_Aloj.setBounds(51, 125, 46, 14);
panel_Alojamiento.add(lblPrecio_Aloj);


JLabel lblFechEntre_Aloj = new JLabel("Fecha de entrada");
lblFechEntre_Aloj.setBounds(51, 167, 118, 14);
panel_Alojamiento.add(lblFechEntre_Aloj);


JLabel lblFechSalida_Aloj = new JLabel("Fecha de salida");
lblFechSalida_Aloj.setBounds(51, 206, 103, 14);
panel_Alojamiento.add(lblFechSalida_Aloj);


textField_Ciudad = new JTextField();
textField_Ciudad.setColumns(10);
textField_Ciudad.setBounds(179, 79, 178, 20);
panel_Alojamiento.add(textField_Ciudad);


textField_PrecioAloj = new JTextField();
textField_PrecioAloj.setColumns(10);
textField_PrecioAloj.setBounds(179, 122, 178, 20);
panel_Alojamiento.add(textField_PrecioAloj);


JComboBox comboBox_TipoDorm = new JComboBox();
comboBox_TipoDorm.setToolTipText("Vuelo\r\nHotel\r\nActividad\r\n");
comboBox_TipoDorm.setBounds(179, 36, 178, 22);
panel_Alojamiento.add(comboBox_TipoDorm);


JButton btnBuscAloj = new JButton("Buscar alojamiento");
btnBuscAloj.setBounds(388, 98, 146, 23);
panel_Alojamiento.add(btnBuscAloj);


JDateChooser dateChooser_EntHotel = new JDateChooser();
dateChooser_EntHotel.setBounds(179, 161, 178, 20);
panel_Alojamiento.add(dateChooser_EntHotel);


JDateChooser dateChooser_SalHotel = new JDateChooser();
dateChooser_SalHotel.setBounds(179, 206, 178, 20);
panel_Alojamiento.add(dateChooser_SalHotel);


JPanel panel_Trayecto = new JPanel();
panel_Trayecto.setBounds(31, 186, 398, 41);
contentPane.add(panel_Trayecto);
panel_Trayecto.setLayout(null);


comboBox_Trayecto = new JComboBox();
comboBox_Trayecto.setModel(new DefaultComboBoxModel(new String[] {"Ida", "Ida y vuelta"}));
comboBox_Trayecto.setToolTipText("");
comboBox_Trayecto.setBounds(165, 11, 178, 22);
panel_Trayecto.add(comboBox_Trayecto);


JLabel lblTrayecto = new JLabel("Trayecto");
lblTrayecto.setBounds(20, 15, 78, 14);
panel_Trayecto.add(lblTrayecto);

JPanel panelColor = new JPanel();
panelColor.setBounds(0, 0, 974, 96);
contentPane.add(panelColor);



//--------------------------------------------------CONTROL VISTA PANELES-----------------------------------------------------

panel_Trayecto.setVisible(false);
panel_VueloIda.setVisible(false);
panel_VueloVuelta.setVisible(false);
panel_Alojamiento.setVisible(false);
panel_Actividad.setVisible(false);



comboBox_TipoEven.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
String eventoSelect = (String) comboBox_TipoEven.getSelectedItem();

switch (eventoSelect) {
case "Vuelo":
panel_Trayecto.setVisible(true);
panel_VueloIda.setVisible(false);
panel_VueloVuelta.setVisible(false);
panel_Alojamiento.setVisible(false);
panel_Actividad.setVisible(false);
break;

case "Hotel":
panel_Alojamiento.setVisible(true);
panel_Trayecto.setVisible(false);
panel_VueloIda.setVisible(false);
panel_VueloVuelta.setVisible(false);
panel_Actividad.setVisible(false);
break;

case "Actividad":
panel_Actividad.setVisible(true);
panel_Trayecto.setVisible(false);
panel_VueloIda.setVisible(false);
panel_VueloVuelta.setVisible(false);
panel_Alojamiento.setVisible(false);

}
          }

});



comboBox_Trayecto.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
String vueloSelect = (String) comboBox_Trayecto.getSelectedItem();

switch (vueloSelect) {
case "Ida":
panel_Trayecto.setVisible(false);
panel_VueloVuelta.setVisible(false);
panel_Alojamiento.setVisible(false);
panel_Actividad.setVisible(false);
panel_VueloIda.setVisible(true);
break;

case "Ida y vuelta":
panel_Trayecto.setVisible(false);
panel_Alojamiento.setVisible(false);
panel_Actividad.setVisible(false);
panel_VueloIda.setVisible(true);
panel_VueloVuelta.setVisible(true);

}
          }

      });

//-------------------------------------------------------------------------------------------------------------------------//
//----------------------------------------------------VALIDACIONES----------------------------------------------------------
//--------Validaciones vuelos----------


}

protected void guardarEvento() {
// TODO Auto-generated method stub
if (!validarCampos()) {
       return;
   }

   String nombreEvento = textField_NombreEven.getText().trim();
   String tipoEvento = comboBox_TipoEven.getSelectedItem().toString();
   
   String sql = "INSERT INTO eventos (nombre, tipo, aeropuerto_origen, aeropuerto_destino, aerolinea, precio, ciudad, precio_aloj, descripcion, precio_act) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
   
   try (Connection conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
        PreparedStatement statement = conexion.prepareStatement(sql)) {
       
       statement.setString(1, nombreEvento);
       statement.setString(2, tipoEvento);

       if (tipoEvento.equals("Vuelo")) {
           statement.setString(3, comboBox_AerOrigen_1.getSelectedItem().toString());
           statement.setString(4, comboBox_AerDestino.getSelectedItem().toString());
           statement.setString(6, textField_Aerolinea.getText().trim());
           statement.setDouble(7, Double.parseDouble(textField_Precio.getText().trim()));
       } else {
           statement.setNull(3, java.sql.Types.VARCHAR);
           statement.setNull(4, java.sql.Types.VARCHAR);
           statement.setNull(5, java.sql.Types.VARCHAR);
           statement.setNull(6, java.sql.Types.VARCHAR);
           statement.setNull(7, java.sql.Types.DOUBLE);
       }

       if (tipoEvento.equals("Hotel")) {
           statement.setString(8, textField_Ciudad.getText().trim());
           statement.setDouble(9, Double.parseDouble(textField_PrecioAloj.getText().trim()));
       } else {
           statement.setNull(8, java.sql.Types.VARCHAR);
           statement.setNull(9, java.sql.Types.DOUBLE);
       }

       if (tipoEvento.equals("Actividad")) {
           statement.setString(10, textPaneAct.getText().trim());
           statement.setDouble(11, Double.parseDouble(textField_PrecioAct.getText().trim()));
       } else {
           statement.setNull(10, java.sql.Types.VARCHAR);
           statement.setNull(11, java.sql.Types.DOUBLE);
       }

       int filasInsertadas = statement.executeUpdate();

       if (filasInsertadas > 0) {
           JOptionPane.showMessageDialog(null, "Evento guardado correctamente.");
       } else {
           JOptionPane.showMessageDialog(null, "Error al guardar el evento.");
       }
   } catch (SQLException e) {
       e.printStackTrace();
       JOptionPane.showMessageDialog(null, "Error de conexión con la base de datos.");
   }
}

private boolean validarCampos() {
   String nombreEvento = textField_NombreEven.getText().trim();
   String tipoEvento = comboBox_TipoEven.getSelectedItem().toString();
   
   // Validación del nombre del evento
   if (nombreEvento.isEmpty()) {
       JOptionPane.showMessageDialog(null, "El nombre del evento no puede estar vacío.");
       return false;
   }

   if (tipoEvento.equals("Vuelo")) {
       // Validación de los aeropuertos
       if (comboBox_AerOrigen_1.getSelectedIndex() == -1 || comboBox_AerDestino.getSelectedIndex() == -1) {
           JOptionPane.showMessageDialog(null, "Seleccione los aeropuertos de origen y destino.");
           return false;
       }
     
       
       // Validación del nombre de la aerolínea (solo palabras)
       String aerolinea = textField_Aerolinea.getText().trim();
       if (aerolinea.isEmpty()) {
           JOptionPane.showMessageDialog(null, "Ingrese el nombre de la aerolínea.");
           return false;
       }
       if (!Pattern.matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ ]+", aerolinea)) {
           JOptionPane.showMessageDialog(null, "El nombre de la aerolínea solo puede contener letras.");
           return false;
       }

       // Validación del precio (debe ser un número decimal positivo)
       String precioStr = textField_Precio.getText().trim();
       if (precioStr.isEmpty()) {
           JOptionPane.showMessageDialog(null, "Ingrese el precio del vuelo.");
           return false;
       }
       try {
           double precio = Double.parseDouble(precioStr);
           if (precio <= 0) {
               JOptionPane.showMessageDialog(null, "El precio debe ser un número positivo.");
               return false;
           }
       } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(null, "El precio debe ser un número válido.");
           return false;
       }

       // Validación de la hora (formato correcto)
       String horaStr = textField_HSalida.getText().trim();
       if (horaStr.isEmpty()) {
           JOptionPane.showMessageDialog(null, "Ingrese la hora de salida.");
           return false;
       }
       SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
       formatoHora.setLenient(false);  // Para que no acepte fechas erróneas (por ejemplo, "25:00")
       try {
           formatoHora.parse(horaStr);  // Intenta parsear la hora
       } catch (ParseException e) {
           JOptionPane.showMessageDialog(null, "El formato de la hora es incorrecto. Use HH:mm.");
           return false;
       }

       // Validación de la duración (formato correcto)
       String duracionStr = textField_Duracion.getText().trim();
       if (duracionStr.isEmpty()) {
           JOptionPane.showMessageDialog(null, "Ingrese la duración del vuelo.");
           return false;
       }
       try {
           formatoHora.parse(duracionStr);  // Intenta parsear la duración
       } catch (ParseException e) {
           JOptionPane.showMessageDialog(null, "El formato de la duración es incorrecto. Use HH:mm.");
           return false;
       }
   } else if (tipoEvento.equals("Hotel")) {
       // Validación del alojamiento
       if (textField_Ciudad.getText().trim().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Ingrese la ciudad del alojamiento.");
           return false;
       }
       if (textField_PrecioAloj.getText().trim().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Ingrese el precio del alojamiento.");
           return false;
       }
   } else if (tipoEvento.equals("Actividad")) {
       // Validación de la actividad
       if (textPaneAct.getText().trim().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Ingrese la descripción de la actividad.");
           return false;
       }
       if (textField_PrecioAct.getText().trim().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Ingrese el precio de la actividad.");
           return false;
       }
   }

   return true;

}


protected void calcularDias(Date fechaInicio, Date fechaFin) {
// TODO Auto-generated method stub
long diferencia = fechaFin.getTime() - fechaInicio.getTime();
     long dias = diferencia / (1000 * 60 * 60 * 24);
}

public void cargarAeropuertosOrigen() {
    // Ejecutar la consulta SELECT_AEROPUERTOS
    try (Connection conn = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(SQLQueries.SELECT_AEROPUERTOS)) {

        // Vaciar el JComboBox antes de llenarlo
        comboBox_AerOrigen_1.removeAllItems();

        // Recorrer el ResultSet y agregar los elementos al JComboBox
        while (rs.next()) {
            String aeropuerto = rs.getString("Nombre");
            comboBox_AerOrigen_1.addItem(aeropuerto);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar los aeropuertos: " + e.getMessage());
    }
}

public void cargarAeropuertosDestino() {
    // Ejecutar la consulta SELECT_AEROPUERTOS
    try (Connection conn = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(SQLQueries.SELECT_AEROPUERTOS)) {

        // Vaciar el JComboBox antes de llenarlo
    comboBox_AerDestino.removeAllItems();

        // Recorrer el ResultSet y agregar los elementos al JComboBox
        while (rs.next()) {
            String aeropuerto = rs.getString("Nombre");
            comboBox_AerDestino.addItem(aeropuerto);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar los aeropuertos: " + e.getMessage());
    }
   
}

public DefaultComboBoxModel<String> buscarAeropuertos()  {
       DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
       try (Connection conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
            PreparedStatement sentencia = conexion.prepareStatement(SQLQueries.SELECT_AEROPUERTOS)) {
           ResultSet resultado = sentencia.executeQuery();
           
           while (resultado.next()) {
               String aeropuertos = resultado.getString("nombre");
               modelo.addElement(aeropuertos);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }      
       return modelo;

}

private void ImportarColoryLogo() {
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
return Color.decode(colorHex);
}

}  
