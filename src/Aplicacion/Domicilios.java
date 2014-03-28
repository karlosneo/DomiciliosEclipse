package Aplicacion;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;



public class Domicilios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame graficos;
	private JTextArea taDomicilio; 
	private String IdCliente=Aplicacion.TablaDomicilio.IdCliente;
	private String NombreCliente=Aplicacion.TablaDomicilio.NombreCliente;
	private String driver;
	private String user;
	private String pass;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Domicilios frame = new Domicilios();
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
	public Domicilios() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Domicilios.class.getResource("/Adjuntos/icono.png")));
		setTitle("Ingresar Domicilio");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 405, 259);
		contentPane = new JPanel() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		 };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMascota = new JLabel("Cliente");
		
		lblMascota.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		lblMascota.setBounds(12, 12, 59, 21);
		contentPane.add(lblMascota);
		
		JLabel label = new JLabel(Aplicacion.TablaDomicilio.NombreCliente);
		
		label.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		label.setBounds(89, 12, 231, 21);
		contentPane.add(label);
		
		JLabel lblHistorial = new JLabel("Domicilio");
		lblHistorial.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		
		lblHistorial.setBounds(12, 40, 65, 21);
		contentPane.add(lblHistorial);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 68, 375, 90);
		contentPane.add(scrollPane);
		
		taDomicilio = new JTextArea();
		taDomicilio.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
		scrollPane.setViewportView(taDomicilio);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IngresarHistorial();
			}
		});
		btnGuardar.setBounds(77, 191, 81, 29);
		
		contentPane.add(btnGuardar);
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				TablaDomicilio.main(null);
			}
		});
		btnCancelar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		
		btnCancelar.setBounds(239, 191, 86, 29);
		
		contentPane.add(btnCancelar);
		
		
		
		graficos = new JFrame("Grafico estadistico de ventas");
		graficos.setSize(500, 270);
		graficos.setLocationRelativeTo(getRootPane());
		this.setLocationRelativeTo(getRootPane());
		
		
		
		
		
	}
	
	public void IngresarHistorial() {

		try {
			 obtenerip.main(null);
			 driver=obtenerip.driver;
			 user=obtenerip.user;
			 pass=obtenerip.pass;
			
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(driver,user,pass);

			String cadenaSql = ("INSERT INTO Domicilios (Cliente, Nombre, Domicilio) VALUES ('"
					
					+ IdCliente.trim() +"','"
					+ NombreCliente.trim() +"','"
					+ taDomicilio.getText().trim() +"');");
		
			System.out.println(IdCliente.trim());
			System.out.println(taDomicilio.getText().trim());
			
			
			System.out.println(cadenaSql);
			
			Statement preparacion = conexion.createStatement();

			int resultados = preparacion.executeUpdate(cadenaSql);

			if (resultados > 0) {
				
				Statement preparacion1 = conexion.createStatement();
				PreparedStatement ps = conexion
						.prepareStatement("select last_insert_id() from Domicilios");
				
				ResultSet rs = ps.executeQuery();
				String ultimoid ="";
				while (rs.next()) {
					ultimoid = rs.getString(1);
					
					
				
				
				}
				preparacion1.close();
				
				
				JOptionPane.showMessageDialog(null,
						"El Domicilio fue guardado en la base de datos, el codigo es "+ultimoid);
				
				dispose();
				TablaDomicilio.main(null);
			} else
				JOptionPane.showMessageDialog(null,
						"Error al modificar los datos.");
			
			preparacion.close();
			conexion.close();

		} catch (ClassNotFoundException error) {
			JOptionPane.showMessageDialog(null, error.getMessage());
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, error.getMessage());
		}

	}
	
	
	
}
