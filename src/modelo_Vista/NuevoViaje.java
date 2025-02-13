package modelo_Vista;

import com.toedter.calendar.JDateChooser;
import modelo_Gestor.GestorViajesyEventos;
import javax.swing.*;
import java.beans.*;
import java.util.Date;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevoViaje extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_nombre;
	private JTextField textField_dias;
	private JTextField textField_servicios;
	GestorViajesyEventos gestor= new GestorViajesyEventos();


	/**
	 * Create the frame.
	 * @param idAgencia 
	 */
	public NuevoViaje(int idAgencia) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1054, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(688, 0, 350, 665);
		contentPane.add(panel);
		
		JLabel lbl_nombre = new JLabel("Nombre Viaje:");
		lbl_nombre.setBounds(33, 68, 96, 14);
		contentPane.add(lbl_nombre);
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(159, 65, 181, 20);
		contentPane.add(textField_nombre);
		textField_nombre.setColumns(10);
		
		JLabel lbl_tipo = new JLabel("Tipo de Viaje:");
		lbl_tipo.setBounds(33, 131, 96, 14);
		contentPane.add(lbl_tipo);
		
		JComboBox comboBox_tipo = new JComboBox();
		comboBox_tipo.setModel(new DefaultComboBoxModel(new String[] {"", "Combinado", "Escapadas", "Familias con niños menores", "Grandes viajes", "Grupos", "Novios", "Senior"}));
		comboBox_tipo.setBounds(159, 127, 181, 20);
		contentPane.add(comboBox_tipo);
		
		JLabel lbl_inicio = new JLabel("Inicio de Viaje:");
		lbl_inicio.setBounds(33, 197, 96, 14);
		contentPane.add(lbl_inicio);
		
		JDateChooser dateChooser_inicio = new JDateChooser();
		dateChooser_inicio.setBounds(159, 197, 181, 20);
		contentPane.add(dateChooser_inicio);
		
		JLabel lbl_fin = new JLabel("Fin de Viaje:");
		lbl_fin.setBounds(33, 260, 96, 14);
		contentPane.add(lbl_fin);
		
		JDateChooser dateChooser_fin = new JDateChooser();
		dateChooser_fin.setBounds(159, 260, 181, 20);
		contentPane.add(dateChooser_fin);
		
		JLabel lbl_dias = new JLabel("Dias:");
		lbl_dias.setBounds(33, 325, 96, 14);
		contentPane.add(lbl_dias);
		
		textField_dias = new JTextField();
		textField_dias.setEditable(false);
		textField_dias.setColumns(10);
		textField_dias.setBounds(159, 322, 181, 20);
		contentPane.add(textField_dias);
		
		// Agregar un PropertyChangeListener para escuchar cambios en las fechas
        dateChooser_inicio.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Si ambas fechas están disponibles, calcular la diferencia en días
                if (dateChooser_inicio.getDate() != null && dateChooser_fin.getDate() != null) {
                    calcularDias(dateChooser_inicio.getDate(), dateChooser_fin.getDate());
                }
            }
        });
        
        dateChooser_fin.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Si ambas fechas están disponibles, calcular la diferencia en días
                if (dateChooser_inicio.getDate() != null && dateChooser_fin.getDate() != null) {
                    calcularDias(dateChooser_inicio.getDate(), dateChooser_fin.getDate());
                }
            }
        });
		
		JLabel lbl_pais = new JLabel("Pais:");
		lbl_pais.setBounds(33, 388, 96, 14);
		contentPane.add(lbl_pais);
		
		JComboBox comboBox_paises = new JComboBox();
		comboBox_paises.setModel(new DefaultComboBoxModel(new String[] {"", "ALEMANIA", "ARGENTINA", "AUSTRIA", " BÉLGICA", "BRASIL", "CANADA", "CROACIA", "REPUBLICA CHECA", "CUBA", "CHINA", "CHIPRE", "DINAMARCA", "EGIPTO", "ESPAÑA", "ESTADOS UNIDOS", "ESTONIA", "FINLANDIA", "FRANCIA", "GRECIA", "GUATEMALA", "HONG-KONG", "HUNGRIA", "INDIA", "INDONESIA", "IRLANDA", "ISLANDIA", "ITALIA", "jAMAICA", "JAPÓN", "KENIA", "LUXEMBURGO", "MALDIVAS", "MALTA", "MARRUECOS", "MEXICO", "MÓNACO", "NORUEGA", "PAISES BAJOS", "PALESTINA", "PANAMÁ", "PERÚ", "POLONIA", "PORTUGAL", "PUERTO RICO", "QATAR", "REINO UNIDO", "RUMANIA", "RUSIA", " SEYCHELLES", "SINGAPUR", "SUDÁFRICA", "SUECIA", "SUIZA", "TAILANDIA", "TANZANIA (INCLUYE ZANZIBAR)", "TÚNEZ", "TURQUIA", "VENEZUELA", "VIETNAM"}));
		comboBox_paises.setBounds(159, 384, 181, 20);
		contentPane.add(comboBox_paises);
		
		JLabel lbl_descripcion = new JLabel("Descripción:");
		lbl_descripcion.setBounds(33, 446, 96, 14);
		contentPane.add(lbl_descripcion);
		
		JTextPane textPane_descripcion = new JTextPane();
		textPane_descripcion.setBounds(159, 446, 181, 51);
		contentPane.add(textPane_descripcion);
		
		JLabel lbl_servicios = new JLabel("Serv. no incluidos:");
		lbl_servicios.setBounds(33, 525, 96, 33);
		contentPane.add(lbl_servicios);
		
		textField_servicios = new JTextField();
		textField_servicios.setColumns(10);
		textField_servicios.setBounds(159, 522, 181, 54);
		contentPane.add(textField_servicios);
		
		JButton btn_guardar = new JButton("Guardar");
		btn_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String nombreViaje = textField_nombre.getText().trim();
			        String tipo = comboBox_tipo.getSelectedItem().toString();
			        Date fechaInicio = dateChooser_inicio.getDate();
			        Date fechaFin = dateChooser_fin.getDate();
			        String dias = textField_dias.getText();
			        String pais = comboBox_paises.getSelectedItem().toString();
			        String descripcion = textPane_descripcion.getText().trim();
			        String servicios = textField_servicios.getText().trim();

			        // Validaciones
			        if (nombreViaje.isEmpty() || tipo.isEmpty() || fechaInicio == null || fechaFin == null ||
			            dias.isEmpty() || pais.isEmpty() || descripcion.isEmpty() || servicios.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
			            return;
			        }

			        // Validar que fechaInicio no sea mayor a fechaFin
			        if (fechaInicio.after(fechaFin)) {
			            JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser posterior a la de fin.");
			            return;
			        }

			        // Llamar al método del gestor
			        gestor.añadirNuevoViaje(nombreViaje, descripcion, tipo, pais, fechaInicio, fechaFin, Integer.parseInt(dias), servicios, idAgencia);
			    }	
		});
		btn_guardar.setBounds(265, 604, 89, 23);
		contentPane.add(btn_guardar);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamar a viajes y eventos
				setVisible(false);
				ViajesyEventos viajesyEventos = new ViajesyEventos(idAgencia);
				viajesyEventos.setVisible(true);
			}
		});
		btn_cancelar.setBounds(420, 604, 89, 23);
		contentPane.add(btn_cancelar);
		
		
	}
	
	 // Método para calcular la diferencia en días
    private void calcularDias(Date inicio, Date fin) {
        long diferencia = fin.getTime() - inicio.getTime();
        long dias = diferencia / (1000 * 60 * 60 * 24); 
        textField_dias.setText(String.valueOf(dias)); // Mostrar los días en el textField
    }
}
