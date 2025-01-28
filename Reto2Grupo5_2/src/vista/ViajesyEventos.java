package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ViajesyEventos extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public ViajesyEventos(Login Login) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 982, 625);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
                
                JPanel panel = new JPanel();
                panel.setBounds(0, 0, 966, 586);
                contentPane.add(panel);
                panel.setLayout(null);
                
                JButton btn_nuevoviaje = new JButton("Nuevo Viaje");
                btn_nuevoviaje.setBounds(729, 118, 134, 23);
                panel.add(btn_nuevoviaje);
                
                JButton btn_nuevoevento = new JButton("Nuevo Evento");
                btn_nuevoevento.setBounds(729, 280, 134, 23);
                panel.add(btn_nuevoevento);
                
                JButton btn_gestionarClientes = new JButton("Gestionar oferta clientes");
                btn_gestionarClientes.setBounds(295, 510, 197, 23);
                panel.add(btn_gestionarClientes);
                
                JButton btn_desconectar = new JButton("Desconectar");
                btn_desconectar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // volver a la pagina de bienvenida
                        llamarBienvenida();
                    }
                });
                btn_desconectar.setBounds(729, 510, 134, 23);
                panel.add(btn_desconectar);
                
                JLabel lbl_viajes = new JLabel("Viajes");
                lbl_viajes.setBounds(316, 122, 46, 14);
                panel.add(lbl_viajes);
                
                JLabel lbl_eventos = new JLabel("Eventos");
                lbl_eventos.setBounds(316, 284, 46, 14);
                panel.add(lbl_eventos);
                
                JPanel panel_fondo = new JPanel();
                panel_fondo.setBounds(0, 0, 262, 103);
                panel.add(panel_fondo);
                panel_fondo.setLayout(null);
                
                JLabel lbl_fotoLogo = new JLabel("");
                lbl_fotoLogo.setIcon(new ImageIcon("imagenes/logo1.jpg"));
                lbl_fotoLogo.setBounds(10, 0, 252, 103);
                panel_fondo.add(lbl_fotoLogo);
                        
        
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
    
    public void llamarBienvenida() {
        Bienvenida Bienvenida = new Bienvenida();
        setVisible(false);
        Bienvenida.setVisible(true);
    }
   
}