package modelo_Vista;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo_BDUtils.BDUtils;
import modelo_BDUtils.SQLQueries;
import modelo_Gestor.GestorAgencia;
import modelo_Pojos.Agencia;

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
private JLabel lbl_nombreError;
private Agencia agencia;
GestorAgencia conexion = new  GestorAgencia();


/**
* Create the frame.
*/
@SuppressWarnings("unchecked")
	public NuevoPerfil() {
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
	
	
	
	//EL FONDO
	JLabel lbl_nombreEmpresa = new JLabel("TRAVEL FIVE");
	lbl_nombreEmpresa.setFont(new Font("Stencil", Font.BOLD, 33));
	lbl_nombreEmpresa.setForeground(new Color(255, 255, 255));
	lbl_nombreEmpresa.setBounds(418, 72, 287, 153);
	contentPane.add(lbl_nombreEmpresa);
	
	JLabel lbl_imagen = new JLabel("");
	lbl_imagen.setIcon(new ImageIcon("imagenes/fotoFondo_1.png"));
	lbl_imagen.setBounds(0, 0, 956, 612);
	contentPane.add(lbl_imagen);
	
	JLabel lbl_nombreAgencia = new JLabel("Nombre Agencia:");
	lbl_nombreAgencia.setForeground(new Color(255, 255, 255));
	lbl_nombreAgencia.setBounds(10, 38, 111, 14);
	panel.add(lbl_nombreAgencia);
	
	lbl_nombreError = new JLabel("");
	lbl_nombreError.setForeground(Color.RED);
	lbl_nombreError.setBounds(148, 11, 341, 14);
	panel.add(lbl_nombreError);
	
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
	txtfield_colorr.setBounds(130, 82, 100, 20); 
	panel.add(txtfield_colorr); 
	
	
	
	
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
		
		// un desplegable con las siguientes opciones: Entre 2 y 10 empleadoss, Entre 10 y 100 empleados y Entre 100 y 1000 empleados.
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
			String agenNuevo = textfield_nombre.getText().trim();  // Nombre de la agencia
			String numEmpStr = comboBox_empleados.getSelectedItem().toString();
			int numEmp = 0; // Valor por defecto si no se selecciona nada
			String tipoAgen = comboBox_tipoAgencia.getSelectedItem().toString();  // Tipo de agencia
			String contr = new String(passwordField.getPassword());  // Contraseña
			String urlLogo = textField_logo.getText().trim();  // URL del logo
		    conexion.añadirNuevaAgencia(agenNuevo,numEmpStr,numEmp,tipoAgen,contr,urlLogo,colorSeleccionado,panel);
	    }
		});
		btn_guardar.setBounds(208, 300, 89, 23);
		panel.add(btn_guardar);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		// llamar a login
		setVisible(false);
		Login login = new Login();
		login.setVisible(true);
		}
		});
		btn_cancelar.setBounds(340, 300, 89, 23);
		panel.add(btn_cancelar);
		
		lbl_contr = new JLabel("Crea una contraseña:");
		lbl_contr.setForeground(Color.WHITE);
		lbl_contr.setBounds(340, 59, 127, 14);
		panel.add(lbl_contr);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(340, 84, 149, 20);
		panel.add(passwordField);
		
		textfield_nombre.getDocument().addDocumentListener(new DocumentListener() {
			@Override
		   public void insertUpdate(DocumentEvent e) {
		       conexion.verificarNombre(textfield_nombre,lbl_nombreError, btn_guardar);
		   }
		   @Override
		   public void removeUpdate(DocumentEvent e) {
			   conexion.verificarNombre(textfield_nombre,lbl_nombreError, btn_guardar);
		   }
		   @Override
		   public void changedUpdate(DocumentEvent e) {
			   conexion.verificarNombre(textfield_nombre,lbl_nombreError, btn_guardar);
		   }
		});
	}

}
