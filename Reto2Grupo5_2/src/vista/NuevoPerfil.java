package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import modelo_BDUtils.BDUtils;
import modelo_BDUtils.SQLQueries;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class NuevoPerfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textfield_nombre;
	private JTextField txtfield_colorr;
	private JTextField textField_cambiarColor;
	private JTextField textField_logo;
	private JPasswordField passwordField;
	private JLabel lbl_contr ;
	private Color colorSeleccionado;



	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public NuevoPerfil(Login Login) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 986, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(218, 175, 535, 334);
		contentPane.add(panel);
		panel.setLayout(null);
		// poner el panel invisible y un borde blanco
		panel.setOpaque(false);
		Border border = BorderFactory.createLineBorder(Color.WHITE, 3);
		panel.setBorder(border);
		
		// String con el nombre de la agencia a crear
		JLabel lbl_nombreAgencia = new JLabel("Nombre Agencia:");
		lbl_nombreAgencia.setForeground(new Color(255, 255, 255));
		lbl_nombreAgencia.setBounds(10, 38, 111, 14);
		panel.add(lbl_nombreAgencia);
		
		textfield_nombre = new JTextField();
		textfield_nombre.setBounds(148, 35, 149, 20);
		panel.add(textfield_nombre);
		textfield_nombre.setColumns(10);
		
		// Color de marca (Hexadecimal)
		JLabel lbl_color = new JLabel("Color de la marca:");
		lbl_color.setForeground(new Color(255, 255, 255));
		lbl_color.setBounds(10, 87, 111, 14);
		panel.add(lbl_color);
		
		// panel asociado a ese color
		textField_cambiarColor = new JTextField();
		textField_cambiarColor.setEditable(false);
		textField_cambiarColor.setBounds(267, 82, 30, 20);
		panel.add(textField_cambiarColor);
		textField_cambiarColor.setColumns(10);
		
		txtfield_colorr = new JTextField();
		txtfield_colorr.setBounds(130, 82, 100, 20); // Asegúrate de que tenga un tamaño visible
		panel.add(txtfield_colorr); // Añadir al panel
		
		txtfield_colorr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   String colorHex = txtfield_colorr.getText().trim();

			        // Validar si falta el símbolo '#' al inicio
			        if (!colorHex.startsWith("#")) {
			            colorHex = "#" + colorHex;
			        }

			        try {
			            // Intentar convertir el texto en un color
			             colorSeleccionado = Color.decode(colorHex);
			            
			            // Cambiar el color de fondo del textField_cambiarColor
			            textField_cambiarColor.setBackground(colorSeleccionado);

			        } catch (NumberFormatException ex) {
			            // Mostrar mensaje de error si el color no es válido
			            JOptionPane.showMessageDialog(panel, "El color hexadecimal no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
		});
		txtfield_colorr.setForeground(new Color(0, 0, 0));
		txtfield_colorr.setColumns(10);
		txtfield_colorr.setBounds(148, 82, 93, 20);
		panel.add(txtfield_colorr);
		
		 // Número de empleados:
		JLabel lbl_empleados = new JLabel("Numero de empleados:");
		lbl_empleados.setForeground(new Color(255, 255, 255));
		lbl_empleados.setBounds(10, 140, 111, 14);
		panel.add(lbl_empleados);
		
		// un desplegable con las siguientes opciones: Entre 2 y 10 empleados, Entre 10 y 100 empleados y Entre 100 y 1000 empleados.
		JComboBox comboBox_empleados = new JComboBox();
		comboBox_empleados.setModel(new DefaultComboBoxModel(new String[] {"", "Entre 2 y 10 empleados", "Entre 10 y 100 empleados", "Entre 100 y 1000 empleados"}));
		comboBox_empleados.setBounds(148, 138, 149, 18);
		panel.add(comboBox_empleados);
		
		// un deplgable para el tipo de agencias
		JLabel lbl_tipoAgencia = new JLabel("Tipo de agencia:");
		lbl_tipoAgencia.setForeground(new Color(255, 255, 255));
		lbl_tipoAgencia.setBounds(10, 193, 111, 14);
		panel.add(lbl_tipoAgencia);
		
		JComboBox comboBox_tipoAgencia = new JComboBox();
		comboBox_tipoAgencia.setModel(new DefaultComboBoxModel(new String[] {"", "Mayorista", "Minorista", "Mayorista-Monirista"}));
		comboBox_tipoAgencia.setBounds(148, 189, 149, 18);
		panel.add(comboBox_tipoAgencia);
		
		// Logo: URL de una imagen disponible en Internet.
		JLabel lbl_logo = new JLabel("Logo (URL):");
		lbl_logo.setForeground(new Color(255, 255, 255));
		lbl_logo.setBounds(10, 239, 111, 14);
		panel.add(lbl_logo);

		textField_logo = new JTextField();
		textField_logo.setBounds(148, 236, 281, 20);
		panel.add(textField_logo);
		textField_logo.setColumns(10);
		
		JButton btn_guardar = new JButton("Guardar");
		btn_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//-----------Variables------------------
			
				
				añadirNuevaAgencia();
							}

			private void añadirNuevaAgencia() {
			    //// TODO Auto-generated method stub			
				//Nombre 
				String agenNuevo= lbl_nombreAgencia.getText().trim();
				//Color ---					
				//Numero de Empleados
				String numEmp = comboBox_empleados.getSelectedItem().toString();
				//Tipo de Agencia
				String tipoAgen = comboBox_tipoAgencia.getSelectedItem().toString();
				//Contraseña
				String contr = new String(passwordField.getPassword());
				//Logo
				String urlLogo =  textField_logo.getText().trim();
				ImageIcon logo = new ImageIcon(urlLogo);	
				
			   

			    // Verificar campos vacíos
			    if (agenNuevo.isEmpty() || numEmp.isEmpty() || tipoAgen.isEmpty() || contr.isEmpty() || colorSeleccionado == null) {
			        JOptionPane.showMessageDialog(panel, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    // Configuración para la base de datos
			    Connection conexion = null;
			    PreparedStatement sentencia = null;

			    try {
			        // Establecer conexión
			        Class.forName(BDUtils.DRIVER); // Controlador de la base de datos
			        conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);

			        // Preparar la consulta SQL
			        String sql = SQLQueries.INSERT_NUEVA_AGENCIA; // Definir la consulta en SQLQueries (por ejemplo: INSERT INTO agencias ...)
			        sentencia = conexion.prepareStatement(sql);

			        // Establecer los parámetros en la consulta preparada
			        sentencia.setString(1, agenNuevo); // Nombre de la agencia
			        sentencia.setString(2, numEmp); // Número de empleados
			        sentencia.setString(3, tipoAgen); // Tipo de agencia
			        sentencia.setString(4, contr); // Contraseña
			        sentencia.setString(5, "#" + Integer.toHexString(colorSeleccionado.getRGB()).substring(2)); // Color en hexadecimal
			        sentencia.setString(6, urlLogo); // URL del logo

			        // Ejecutar la consulta
			        int datosInsertados = sentencia.executeUpdate();

			        if (datosInsertados > 0) {
			            JOptionPane.showMessageDialog(panel, "Agencia añadida correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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

		});
		btn_guardar.setBounds(208, 300, 89, 23);
		panel.add(btn_guardar);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamar a login
				setVisible(false);
				Login.setVisible(true);
			}
		});
		btn_cancelar.setBounds(340, 300, 89, 23);
		panel.add(btn_cancelar);
		
	    lbl_contr = new JLabel("Crea una contraseña:");
		lbl_contr.setForeground(Color.WHITE);
		lbl_contr.setBounds(362, 38, 127, 14);
		panel.add(lbl_contr);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(340, 137, 149, 20);
		panel.add(passwordField);
		
		
		
		// EL FONDO
		JLabel lbl_nombreEmpresa = new JLabel("TRAVEL FIVE");
		lbl_nombreEmpresa.setFont(new Font("Stencil", Font.BOLD, 33));
		lbl_nombreEmpresa.setForeground(new Color(255, 255, 255));
		lbl_nombreEmpresa.setBounds(418, 72, 287, 153);
		contentPane.add(lbl_nombreEmpresa);
		
		JLabel lbl_imagen = new JLabel("");
		lbl_imagen.setIcon(new ImageIcon("imagenes/fotoFondo_1.png"));
		lbl_imagen.setBounds(0, 0, 956, 612);
		contentPane.add(lbl_imagen);
	}
}
