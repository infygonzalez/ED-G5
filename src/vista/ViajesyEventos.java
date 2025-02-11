package vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo_BDUtils.BDUtils;
import modelo_BDUtils.SQLQueries;

public class ViajesyEventos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableViajes;
    private JTable tableEventos;
    private  JPanel panelColor;
    private JPanel panelLogo;
    private int idAgencia;
    

    public ViajesyEventos(int idAgencia) {
        this.idAgencia = idAgencia;
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 966, 586);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton btn_nuevoviaje = new JButton("Nuevo Viaje");
        btn_nuevoviaje.setBounds(43, 510, 134, 23);
        btn_nuevoviaje.addActionListener(e -> abrirVentanaNuevoViaje());
        panel.add(btn_nuevoviaje);

        JButton btn_nuevoevento = new JButton("Nuevo Evento");
        btn_nuevoevento.addActionListener(e -> abrirVentanaNuevoEvento());
        btn_nuevoevento.setBounds(258, 510, 134, 23);
        panel.add(btn_nuevoevento);

        JButton btn_gestionarClientes = new JButton("Gestionar oferta clientes");
        btn_gestionarClientes.setBounds(466, 510, 197, 23);
        panel.add(btn_gestionarClientes);

        JButton btn_desconectar = new JButton("Desconectar");
        btn_desconectar.setBounds(731, 510, 134, 23);
        btn_desconectar.addActionListener(e -> llamarBienvenida());
        panel.add(btn_desconectar);

        JLabel lbl_viajes = new JLabel("Viajes");
        lbl_viajes.setBounds(316, 122, 46, 14);
        panel.add(lbl_viajes);

        JLabel lbl_eventos = new JLabel("Eventos");
        lbl_eventos.setVisible(false); // ocultar antes de seleccionar
        lbl_eventos.setBounds(316, 303, 46, 14);
        panel.add(lbl_eventos);
        
        panelLogo = new JPanel();
        panelLogo.setBounds(0, 0, 114, 96);
        panel.add(panelLogo);
        
        panelColor = new JPanel();
        panelColor.setBounds(115, 0, 851, 96);
        panel.add(panelColor);

        ImportarColoryLogo();

        /********************************TABLA VIAJES***************************************/
        JScrollPane scrollPaneViajes = new JScrollPane();
        
        scrollPaneViajes.setBounds(43, 147, 772, 120);
        panel.add(scrollPaneViajes);

        
        tableViajes = new JTable();
        tableViajes.setModel(new DefaultTableModel(
        	    new Object[][] {},
        	    new String[] {
        	        "IdViajes", "Nombre", "Tipo", "PaisDestino", "Fecha_Inicio", "Fecha_Fin", "Duracion", "ServNoIncluidos"
        	    }
        	) {
        	    @Override
        	    public boolean isCellEditable(int row, int column) {
        	        return false; // Hace que todas las celdas sean no editables
        	    }
        	});

    	// Permitir selección de filas completas
    	tableViajes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	tableViajes.setRowSelectionAllowed(true);
    	tableViajes.setColumnSelectionAllowed(false);
        	
        
    	

        
        
        /********************************BORRAR VIAJES***************************************/
        JButton btnborrarViaje = new JButton();
        ImageIcon icono = new ImageIcon("imagenes/basura.png");
        Image imagen = icono.getImage(); // Obtener la imagen de la imagen del icono
        Image imagenEscalada = imagen.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icono = new ImageIcon(imagenEscalada);
        btnborrarViaje.setIcon(icono); // Asignar el icono al botón

        btnborrarViaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableViajes.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un viaje para eliminar.", "Atención", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este viaje?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

                // Obtener el ID del viaje
                int idViaje = Integer.parseInt((String) tableViajes.getModel().getValueAt(selectedRow, 0));


                try (Connection conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD)) {
                    // 1. Eliminar los registros relacionados en la tabla Pais
                    try (PreparedStatement sentenciaPais = conexion.prepareStatement(SQLQueries.DELETE_PAIS_PARA_VIAJE)) {
                        sentenciaPais.setInt(1, idViaje); 
                        sentenciaPais.executeUpdate();
                    }
                    
                    // 2. Eliminar los eventos asociados al viaje
                    try (PreparedStatement sentenciaEvento = conexion.prepareStatement(SQLQueries.DELETE_EVENTOS_PARA_VIAJE)) {
                        sentenciaEvento.setInt(1, idViaje);
                        sentenciaEvento.executeUpdate();
                    }
                    
                    // 3. Eliminar los vuelos de ida relacionados con el viaje
                    try (PreparedStatement sentenciaVueloIda = conexion.prepareStatement(SQLQueries.DELETE_VUELO_IDA_PARA_VIAJE)) {
                        sentenciaVueloIda.setInt(1, idViaje);
                        sentenciaVueloIda.executeUpdate();
                    }

                    // 4. Eliminar los vuelos de vuelta relacionados con el viaje
                    try (PreparedStatement sentenciaVueloVuelta = conexion.prepareStatement(SQLQueries.DELETE_VUELO_VUELTA_PARA_VIAJE)) {
                        sentenciaVueloVuelta.setInt(1, idViaje);
                        sentenciaVueloVuelta.executeUpdate();
                    }

                    // 5. Ahora ya eliminamos el viaje
                    try (PreparedStatement sentenciaViaje = conexion.prepareStatement(SQLQueries.DELETE_VIAJE)) {
                        sentenciaViaje.setInt(1, idViaje);
                        int filasAfectadas = sentenciaViaje.executeUpdate();

                        if (filasAfectadas > 0) {
                            JOptionPane.showMessageDialog(null, "Viaje eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            cargarDatosViajes(); // Recargar la tabla de viajes
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo eliminar el viaje.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar el viaje.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnborrarViaje.setBounds(825, 173, 40, 40); // Ajusta la posición del botón
        panel.add(btnborrarViaje);
        
        
        /********************************TABLA EVENTOS***************************************/
        JScrollPane scrollPaneEventos = new JScrollPane();
        scrollPaneEventos.setVisible(false); // ocultar antes de seleccionar
        scrollPaneEventos.setBounds(43, 328, 772, 120);
        panel.add(scrollPaneEventos);

        tableEventos = new JTable();
        tableEventos.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Nombre", "Tipo", "Precio", "Fecha"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas no sean editables
            }
        });

        // Permitir selección de filas
        tableEventos.setRowSelectionAllowed(true);
        tableEventos.setColumnSelectionAllowed(false);
        tableEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPaneEventos.setViewportView(tableEventos);
        
       
        /********************************BORRAR EVENTOS***************************************/
        
        JButton btnborrareventos = new JButton();
        btnborrareventos.setVisible(false); // ocultar antes de seleccionar
        ImageIcon icono2 = new ImageIcon("imagenes/basura.png");
        Image imagen2 = icono2.getImage(); // Obtener la imagen de la imagen del icono
        Image imagenEscalada2 = imagen2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icono2 = new ImageIcon(imagenEscalada2);
        btnborrareventos.setIcon(icono2); // Asignar el icono al botón
        
        btnborrareventos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableEventos.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un evento para eliminar.", "Atención", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este evento?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
                
                String nombreEvento = (String) tableEventos.getModel().getValueAt(selectedRow, 0);
                
                try (Connection conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD)) {
                    // Primero eliminar los alojamientos asociados al evento
                    try (PreparedStatement sentenciaAlojamiento = conexion.prepareStatement(SQLQueries.DELETE_ALOJA_PARA_EVENTO)) {
                        sentenciaAlojamiento.setString(1, nombreEvento);
                        sentenciaAlojamiento.executeUpdate();
                    }
                    
                    // Luego eliminar el evento
                    try (PreparedStatement sentenciaEvento = conexion.prepareStatement(SQLQueries.DELETE_EVENTO)) {
                        sentenciaEvento.setString(1, nombreEvento);
                        int filasAfectadas = sentenciaEvento.executeUpdate();
                        
                        if (filasAfectadas > 0) {
                            JOptionPane.showMessageDialog(null, "Evento eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            cargarDatosEventos(); // Recargar la tabla de eventos
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo eliminar el evento.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar el evento.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnborrareventos.setBounds(825, 353, 40, 40);
        panel.add(btnborrareventos);

        
        JLabel basura1 = new JLabel("");
        basura1.setIcon(new ImageIcon(""));
        basura1.setBounds(803, 272, 46, 45);
        panel.add(basura1);

 
        cargarDatosViajes();
        cargarDatosEventos();
        
        scrollPaneViajes.setViewportView(tableViajes);
    	tableViajes.getSelectionModel().addListSelectionListener(e -> {
    	    if (!e.getValueIsAdjusting()) { // Ensures the event fires only once per selection
    	        int selectedRow = tableViajes.getSelectedRow();
    	        if (selectedRow != -1) { // Check if a valid row is selected
    	        	lbl_eventos.setVisible(true);
    	        	scrollPaneEventos.setVisible(true);
    	        	btnborrareventos.setVisible(true);
    	        }
    	    }
    	});
        
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
	
	/********************************CARGAR DATOS TABLA VIAJES***************************************/
	private void cargarDatosViajes() {
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
    private void cargarDatosEventos() {
        DefaultTableModel modelo = (DefaultTableModel) tableEventos.getModel();
        modelo.setRowCount(0);

        try (Connection conexion = DriverManager.getConnection(BDUtils.URL, BDUtils.USER, BDUtils.PASSWORD);
             PreparedStatement sentencia = conexion.prepareStatement(SQLQueries.SELECT_EVENTOS_IDVIAJE)) {
           
            sentencia.setInt(1, idAgencia);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String tipo = resultado.getString("tipo");
                double precio = resultado.getDouble("precio");
                String fecha = resultado.getString("fecha");
                modelo.addRow(new Object[]{nombre, tipo, precio, fecha});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaNuevoViaje() {
        NuevoViaje ventanaNuevoViaje = new NuevoViaje(idAgencia);
        ventanaNuevoViaje.setVisible(true);
    }
    
    private void abrirVentanaNuevoEvento() {
        int selectedRow = tableViajes.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un viaje para asignar el evento.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Asegurar que el ID se obtiene correctamente y se convierte a int
        String idViajeStr = (String) tableViajes.getModel().getValueAt(selectedRow, 0); // Usa la columna correcta
        int idViaje = Integer.parseInt(idViajeStr);

        NuevoEvento ventanaNuevoEvento = new NuevoEvento(idAgencia, idViaje);
        ventanaNuevoEvento.setVisible(true);
    }


    private void llamarBienvenida() {
        Bienvenida bienvenida = new Bienvenida();
        setVisible(false);
        bienvenida.setVisible(true);
    }
}
