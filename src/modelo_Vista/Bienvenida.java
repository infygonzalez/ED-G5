package modelo_Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bienvenida extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienvenida frame = new Bienvenida();
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
	public Bienvenida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 982, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_nombreEmpresa = new JLabel("TRAVEL FIVE");
		lbl_nombreEmpresa.setFont(new Font("Stencil", Font.BOLD, 33));
		lbl_nombreEmpresa.setForeground(new Color(255, 255, 255));
		lbl_nombreEmpresa.setBounds(418, 72, 287, 153);
		contentPane.add(lbl_nombreEmpresa);
		
		// FONDO
		JLabel lbl_imagen = new JLabel("");
		lbl_imagen.setIcon(new ImageIcon("imagenes/fotoFondo_1.png"));
		lbl_imagen.setBounds(0, 0, 956, 612);
		contentPane.add(lbl_imagen);
		
		// Evento para pinchar la foto
		lbl_imagen.addMouseListener(new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
				llamarLogin();
			}
		});
		
	}
	
	public void llamarLogin() {
		Login Login = new Login() ;
		setVisible(false);
		Login.setVisible(true);
	}
	
}
