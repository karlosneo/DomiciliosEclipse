package Aplicacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class RegistrarCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCelular;
	private JTextField tfTelefono;
	private JTextField tfBarrio;
	private JTextField tfNombre;
	private JTextField tfDireccion;
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
					RegistrarCliente frame = new RegistrarCliente();
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
	public RegistrarCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarCliente.class.getResource("/Adjuntos/icono.png")));
		setTitle("Registrar Cliente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 344);
		contentPane = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			 };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProducto = new JLabel("Nombre:");
		lblProducto.setForeground(Color.BLACK);
		lblProducto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProducto.setBounds(24, 28, 70, 20);
		contentPane.add(lblProducto);
		
		JLabel lblPrecio = new JLabel("Celular:");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrecio.setForeground(Color.BLACK);
		lblPrecio.setBounds(24, 104, 64, 20);
		contentPane.add(lblPrecio);
		
		tfCelular = new JTextField();
		tfCelular.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfCelular.setBounds(174, 104, 307, 26);
		contentPane.add(tfCelular);
		tfCelular.setColumns(10);
		
		JLabel lbCantidad = new JLabel("Telefono:");
		lbCantidad.setForeground(Color.BLACK);
		lbCantidad.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbCantidad.setBounds(24, 66, 78, 20);
		contentPane.add(lbCantidad);
		
		tfTelefono = new JTextField();
		tfTelefono.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfTelefono.setBounds(174, 66, 307, 26);
		contentPane.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		JLabel lblReferencia = new JLabel("Barrio:");
		lblReferencia.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReferencia.setForeground(Color.BLACK);
		lblReferencia.setBounds(23, 142, 56, 20);
		contentPane.add(lblReferencia);
		
		tfBarrio = new JTextField();
		tfBarrio.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfBarrio.setBounds(174, 142, 307, 26);
		contentPane.add(tfBarrio);
		tfBarrio.setColumns(10);
		
		JButton btnGuardar = new JButton("Aceptar");
		btnGuardar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			Validar();
			}
		});
		btnGuardar.setBounds(103, 264, 90, 28);
		
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				Principal.main(null);
			}
		});
		
		btnCancelar.setBounds(300, 264, 90, 28);
		
		contentPane.add(btnCancelar);
		
		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfNombre.setBounds(174, 30, 307, 26);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setForeground(Color.BLACK);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDireccion.setBounds(24, 181, 81, 20);
		contentPane.add(lblDireccion);
		
		tfDireccion = new JTextField();
		tfDireccion.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(174, 180, 307, 26);
		contentPane.add(tfDireccion);
		
				
	}
	
	public void IngresarEnBase() {

		try {
			 obtenerip.main(null);
			 driver=obtenerip.driver;
			 user=obtenerip.user;
			 pass=obtenerip.pass;
			
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(driver,user,pass);

			String cadenaSql = ("INSERT INTO Clientes (Nombre, Telefono, Celular, Barrio, Direccion) VALUES ('"
					+ tfNombre.getText().trim().toUpperCase()+ "','"
					+ tfTelefono.getText().trim().toUpperCase() +"','"
					+ tfCelular.getText().trim().toUpperCase() +"','"
					+ tfBarrio.getText().trim().toUpperCase() +"','"
					+ tfDireccion.getText().trim().toUpperCase() +"');");
		
			System.out.println(tfNombre.getText().trim().toUpperCase());
			System.out.println(tfTelefono.getText().trim().toUpperCase());
			System.out.println(tfCelular.getText().trim().toUpperCase());
			System.out.println(tfBarrio.getText().trim().toUpperCase());
			System.out.println(tfDireccion.getText().trim().toUpperCase());
			
			System.out.println(cadenaSql);
			
			Statement preparacion = conexion.createStatement();

			int resultados = preparacion.executeUpdate(cadenaSql);

			if (resultados > 0) {
				JOptionPane.showMessageDialog(null,
						"El cliente fue ingresado correctamente");
				
				dispose();
				Principal.main(null);
			} else
				JOptionPane.showMessageDialog(null,
						"Error al registrar el cliente.");
			
			preparacion.close();
			conexion.close();

		} catch (ClassNotFoundException error) {
			JOptionPane.showMessageDialog(null, error.getMessage());
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, error.getMessage());
		}

	}
	
	public void Validar() {
		if (!(tfNombre.getText()).equals("")
				&& !(tfBarrio.getText()).equals("")
				)
			IngresarEnBase();
		else
			JOptionPane
					.showMessageDialog(null,
							"No pueden haber campos vacios.");

	}
}
