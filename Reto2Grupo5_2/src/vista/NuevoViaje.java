package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo_BDUtils.BDUtils;

public class NuevoViaje extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 private JTextField textFieldNombre;
	    private JTextArea textAreaDescripcion;
	    private JTextField textFieldFecha;
	    private JTextField textFieldDuracion;
	    private JTextField textFieldDestino;
	    private JButton btnCrear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoViaje frame = new NuevoViaje();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NuevoViaje() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		   setTitle("Nuevo Viaje");
	        setBounds(100, 100, 400, 300);
	        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        getContentPane().setLayout(null);

	        JLabel lblNombre = new JLabel("Nombre:");
	        lblNombre.setBounds(10, 10, 80, 25);
	        getContentPane().add(lblNombre);

	        textFieldNombre = new JTextField();
	        textFieldNombre.setBounds(100, 10, 250, 25);
	        getContentPane().add(textFieldNombre);

	        JLabel lblDescripcion = new JLabel("Descripción:");
	        lblDescripcion.setBounds(10, 50, 80, 25);
	        getContentPane().add(lblDescripcion);

	        textAreaDescripcion = new JTextArea();
	        textAreaDescripcion.setBounds(100, 50, 250, 60);
	        getContentPane().add(textAreaDescripcion);

	        JLabel lblFecha = new JLabel("Fecha:");
	        lblFecha.setBounds(10, 120, 80, 25);
	        getContentPane().add(lblFecha);

	        textFieldFecha = new JTextField();
	        textFieldFecha.setBounds(100, 120, 250, 25);
	        getContentPane().add(textFieldFecha);

	        JLabel lblDuracion = new JLabel("Duración:");
	        lblDuracion.setBounds(10, 150, 80, 25);
	        getContentPane().add(lblDuracion);

	        textFieldDuracion = new JTextField();
	        textFieldDuracion.setBounds(100, 150, 250, 25);
	        getContentPane().add(textFieldDuracion);

	        JLabel lblDestino = new JLabel("Destino:");
	        lblDestino.setBounds(10, 180, 80, 25);
	        getContentPane().add(lblDestino);

	        textFieldDestino = new JTextField();
	        textFieldDestino.setBounds(100, 180, 250, 25);
	        getContentPane().add(textFieldDestino);

	        btnCrear = new JButton("Crear Viaje");
	        btnCrear.setBounds(100, 220, 150, 30);
	        getContentPane().add(btnCrear);
	        
	        
			btnCrear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     crearViaje();
		         }

			private void crearViaje() {
				// TODO Auto-generated method stub
				 String nombre = textFieldNombre.getText().trim();
			        String descripcion = textAreaDescripcion.getText().trim();
			        String fecha = textFieldFecha.getText().trim();
			        String duracion = textFieldDuracion.getText().trim();
			        String destino = textFieldDestino.getText().trim();

			        // Validar los campos
			        if (nombre.isEmpty() || descripcion.isEmpty() || fecha.isEmpty() || duracion.isEmpty() || destino.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			        
			        Connection conexion = null;
			        PreparedStatement sentencia = null;
			        
			        try {
			            // Conexión a la base de datos
			            Class.forName(BDUtils.DRIVER);
			            conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);

			            // Consulta SQL para insertar el nuevo viaje
			            String sql = "INSERT INTO viajes (nombre, descripcion, fecha, duracion, destino) VALUES (?, ?, ?, ?, ?)";
			            sentencia = conexion.prepareStatement(sql);

			            // Asignar los parámetros a la consulta SQL
			            sentencia.setString(1, nombre);
			            sentencia.setString(2, descripcion);
			            sentencia.setString(3, fecha);  // Asegúrate de que la fecha esté en formato 'YYYY-MM-DD'
			            sentencia.setString(4, duracion);
			            sentencia.setString(5, destino);

			            // Ejecutar la consulta
			            int filasAfectadas = sentencia.executeUpdate();

			            if (filasAfectadas > 0) {
			                // Viaje creado exitosamente
			                JOptionPane.showMessageDialog(null, "Viaje creado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			            } else {
			                // Error al crear el viaje
			                JOptionPane.showMessageDialog(null, "Error al crear el viaje.", "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        } catch (SQLException ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Error de base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        } catch (Exception ex) {
			            ex.printStackTrace(); 
			            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        } finally {
			            // Cerrar los recursos
			            try {
			                if (sentencia != null) sentencia.close();
			                if (conexion != null) conexion.close();
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			            }
			        }
			}
	     });
 }
}
