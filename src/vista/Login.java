package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelo_BDUtils.BDUtils;
import modelo_BDUtils.SQLQueries;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;

import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_usuario;
    private JTextField textField_contraseña;

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 969, 665);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl_contraseña = new JLabel("CONTRASEÑA:");
        lbl_contraseña.setForeground(new Color(255, 255, 255));
        lbl_contraseña.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_contraseña.setBounds(191, 268, 169, 14);
        contentPane.add(lbl_contraseña);

        JLabel lblNewLabel = new JLabel("USUARIO:");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(191, 201, 104, 14);
        contentPane.add(lblNewLabel);

        textField_usuario = new JTextField();
        textField_usuario.setBounds(191, 226, 188, 20);
        contentPane.add(textField_usuario);
        textField_usuario.setColumns(10);

        textField_contraseña = new JTextField();
        textField_contraseña.setColumns(10);
        textField_contraseña.setBounds(191, 305, 188, 20);
        contentPane.add(textField_contraseña);

        JLabel lbl_error = new JLabel("* LOS DOS CAMPOS SON OBLIGATORIOS");
        lbl_error.setVisible(false);
        lbl_error.setFont(new Font("Tahoma", Font.BOLD, 13));
        lbl_error.setForeground(new Color(255, 0, 0));
        lbl_error.setBounds(300, 363, 300, 14);
        contentPane.add(lbl_error);

        // BOTÓN PARA UNA NUEVA AGENCIA
        JButton btn_agencia = new JButton("Nueva Agencia");
        btn_agencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                llamarNuevoPerfil();
            }
        });
        btn_agencia.setBounds(467, 388, 133, 23);
        contentPane.add(btn_agencia);

        // BOTÓN PARA INICIAR SESIÓN
        JButton btn_inicio = new JButton("Iniciar sesión");
        btn_inicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar si los campos están vacíos
                String usu = textField_usuario.getText().trim();
                String contr = textField_contraseña.getText().trim();

                if (usu.isEmpty() || contr.isEmpty()) {
                    lbl_error.setText("Por favor, complete todos los campos.");
                    lbl_error.setVisible(true);
                } else {
                    int idAgencia = validarLogin(usu, contr);
                    if (idAgencia != -1) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                        lbl_error.setVisible(false);
                        llamarViajesyEventos(idAgencia);
                    } else {
                        lbl_error.setText("Usuario o contraseña incorrectos");
                        lbl_error.setVisible(true);
                    }
                }
            }
        });
        btn_inicio.setBounds(300, 388, 116, 23);
        contentPane.add(btn_inicio);

        JLabel lbl_nombreEmpresa = new JLabel("TRAVEL FIVE");
        lbl_nombreEmpresa.setFont(new Font("Stencil", Font.BOLD, 33));
        lbl_nombreEmpresa.setForeground(new Color(255, 255, 255));
        lbl_nombreEmpresa.setBounds(418, 72, 287, 153);
        contentPane.add(lbl_nombreEmpresa);

        // FONDO
        JLabel lbl_usuario = new JLabel("");
        lbl_usuario.setIcon(new ImageIcon("imagenes/fotoFondo_1.png"));
        lbl_usuario.setBounds(0, 0, 956, 612);
        contentPane.add(lbl_usuario);
    }

    public void llamarNuevoPerfil() {
        NuevoPerfil nuevoPerfil = new NuevoPerfil();
        setVisible(false);
        nuevoPerfil.setVisible(true);
    }

    public void llamarViajesyEventos(int idAgencia) {
        ViajesyEventos viajesyEventos = new ViajesyEventos(idAgencia);
        setVisible(false);
        viajesyEventos.setVisible(true);
    }

    // MÉTODO PARA VALIDAR LOGIN Y DEVOLVER EL idAgencia
    private int validarLogin(String usu, String contr) {
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
}
