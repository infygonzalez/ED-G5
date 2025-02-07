package vista;



import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import com.mysql.cj.xdevapi.Statement;
import com.toedter.calendar.JCalendar;
import javax.swing.JTextPane;

import modelo_BDUtils.BDUtils;
import modelo_BDUtils.SQLQueries;



public class NuevoEvento extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_NombreEven;
	private JTextField textField_CodVuelo;
	private JTextField textField_Aerolinea;
	private JTextField textField_Precio;
	private JTextField textField_HSalida;
	private JTextField textField_Duracion;
	private JTextField textField_DuracionVuelta;
	private JTextField textField_HVuelta;
	private JTextField textField_PrecioTotal;
	private JTextField textField_AerolineaReg;
	private JTextField textField_CodVuelReg;
	private JTextField textField_Ciudad;
	private JTextField textField_PrecioAloj;
	private JTextField textField_PrecioAct;
	private JComboBox comboBox_AerOrigen;
	private String Nombre;

	/**
	 * Create the frame.
	 * @param idViaje 
	 * @param idAgencia 
	 */

	public NuevoEvento(int idAgencia, int idViaje) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField_NombreEven = new JTextField();
		textField_NombreEven.setColumns(10);
		textField_NombreEven.setBounds(189, 25, 178, 20);
		contentPane.add(textField_NombreEven);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(44, 555, 89, 23);
		contentPane.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ViajesyEventos viajeyeventos = new ViajesyEventos(idAgencia);
				viajeyeventos.setVisible(true);
			}

		});

		btnCancelar.setBounds(238, 555, 89, 23);
		contentPane.add(btnCancelar);
	
		JLabel lblNewLabel = new JLabel("Nombre Evento");
		lblNewLabel.setBounds(44, 28, 105, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tipo Evento");
		lblNewLabel_1.setBounds(44, 73, 89, 14);
		contentPane.add(lblNewLabel_1);

		JComboBox comboBox_TipoEven = new JComboBox();
		comboBox_TipoEven.setModel(new DefaultComboBoxModel(new String[] {"Vuelo", "Hotel", "Actividad"}));
		comboBox_TipoEven.setToolTipText("");
		comboBox_TipoEven.setBounds(189, 69, 178, 22);
		contentPane.add(comboBox_TipoEven);

		JPanel panel_VueloIda = new JPanel();
		panel_VueloIda.setBounds(0, 0, 764, 599);
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


		JLabel lblCodVuelo = new JLabel("Código vuelo");
		lblCodVuelo.setBounds(44, 313, 78, 14);
		panel_VueloIda.add(lblCodVuelo);

		
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
		

		JComboBox comboBox_AerOrigen = new JComboBox();
		comboBox_AerOrigen.setBounds(189, 163, 178, 22);
		panel_VueloIda.add(comboBox_AerOrigen);


		JComboBox comboBox_AerDestino = new JComboBox();
		comboBox_AerDestino.setBounds(189, 208, 178, 22);
		panel_VueloIda.add(comboBox_AerDestino);

		
		textField_CodVuelo = new JTextField();
		textField_CodVuelo.setColumns(10);
		textField_CodVuelo.setBounds(189, 310, 178, 20);
		panel_VueloIda.add(textField_CodVuelo);

		
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
		

		JLabel lblCodVuelo_Reg = new JLabel("Código vuelo regreso");
		lblCodVuelo_Reg.setBounds(0, 42, 135, 14);
		panel_VueloVuelta.add(lblCodVuelo_Reg);

		
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


		textField_CodVuelReg = new JTextField();
		textField_CodVuelReg.setColumns(10);
		textField_CodVuelReg.setBounds(145, 39, 178, 20);
		panel_VueloVuelta.add(textField_CodVuelReg);


		JLabel lblPrecioTotal = new JLabel("Precio total");
		lblPrecioTotal.setBounds(0, 130, 91, 14);
		panel_VueloVuelta.add(lblPrecioTotal);
		

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(145, 4, 178, 20);
		panel_VueloVuelta.add(dateChooser_1);
		

		JDateChooser dateChooser_idaVuel = new JDateChooser();
		dateChooser_idaVuel.setBounds(189, 275, 178, 20);
		panel_VueloIda.add(dateChooser_idaVuel);
		

		JPanel panel_Alojamiento = new JPanel();
		panel_Alojamiento.setBounds(0, 98, 752, 489);
		contentPane.add(panel_Alojamiento);


		JPanel panel_Actividad = new JPanel();
		panel_Actividad.setBounds(44, 116, 340, 428);
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


		JTextPane textPaneAct = new JTextPane();
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
		panel_Trayecto.setBounds(21, 98, 398, 41);
		contentPane.add(panel_Trayecto);
		panel_Trayecto.setLayout(null);
		

		JComboBox comboBox_Trayecto = new JComboBox();
		comboBox_Trayecto.setModel(new DefaultComboBoxModel(new String[] {"Ida", "Ida y vuelta"}));
		comboBox_Trayecto.setToolTipText("");
		comboBox_Trayecto.setBounds(165, 11, 178, 22);
		panel_Trayecto.add(comboBox_Trayecto);
		

		JLabel lblTrayecto = new JLabel("Trayecto");
		lblTrayecto.setBounds(20, 15, 78, 14);
		panel_Trayecto.add(lblTrayecto);
		

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

	 public DefaultComboBoxModel<String> buscarAeropuertos() throws SQLException {
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
}	 