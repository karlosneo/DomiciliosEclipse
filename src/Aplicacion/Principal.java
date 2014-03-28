package Aplicacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setLocationRelativeTo(null);
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
	public Principal() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Adjuntos/icono2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegistrar = new JButton("Registrar Cliente");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				RegistrarCliente.main(null);
			}
		});
		btnRegistrar.setBounds(161, 306, 129, 26);
		contentPane.add(btnRegistrar);
		
		JButton btnIngresarDomicilio = new JButton("Ingresar Domicilio");
		btnIngresarDomicilio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				TablaDomicilio.main(null);
			}
		});
		btnIngresarDomicilio.setBounds(12, 306, 137, 26);
		contentPane.add(btnIngresarDomicilio);
		
		JButton btnOpcionesDomicilio = new JButton("Opciones Domicilio");
		btnOpcionesDomicilio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TablaConsultaDomicilio.main(null);
			}
		});
		btnOpcionesDomicilio.setBounds(444, 306, 143, 26);
		contentPane.add(btnOpcionesDomicilio);
		
		JButton btnOpcionesCliente = new JButton("Opciones Cliente");
		btnOpcionesCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				OpcionesCliente.main(null);
			}
		});
		btnOpcionesCliente.setBounds(302, 306, 130, 26);
		contentPane.add(btnOpcionesCliente);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Principal.class.getResource("/Adjuntos/LogoDrog.png")));
		label.setBounds(74, 12, 464, 240);
		contentPane.add(label);
		
		JLabel lblSistemaDeGestion = new JLabel("SISTEMA DE GESTION DE DOMICILIOS");
		lblSistemaDeGestion.setForeground(Color.BLUE);
		lblSistemaDeGestion.setFont(new Font("Arial Rounded MT Bold", Font.BOLD | Font.ITALIC, 18));
		lblSistemaDeGestion.setBounds(112, 264, 385, 21);
		contentPane.add(lblSistemaDeGestion);
		
		JLabel lblDiseoEImplementacion = new JLabel("Dise\u00F1o e Implementacion Ing. Carlos Mario Morales.");
		lblDiseoEImplementacion.setBounds(174, 357, 306, 16);
		contentPane.add(lblDiseoEImplementacion);
	}
}
