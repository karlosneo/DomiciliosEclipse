package Aplicacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Toolkit;

public class ModificarCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfCelular;
	private JTextField tfTelefono;
	private JTextField tfBarrio;
	private String id;
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
					ModificarCliente frame = new ModificarCliente();
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
	public ModificarCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarCliente.class.getResource("/Adjuntos/icono.png")));
		setTitle("Modificar Cliente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(24, 28, 70, 20);
		contentPane.add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setForeground(Color.BLACK);
		tfNombre.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfNombre.setBounds(174, 28, 307, 26);
		contentPane.add(tfNombre);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCelular.setForeground(Color.BLACK);
		lblCelular.setBounds(24, 104, 64, 20);
		contentPane.add(lblCelular);
		
		tfCelular = new JTextField();
		tfCelular.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfCelular.setBounds(174, 104, 307, 26);
		contentPane.add(tfCelular);
		tfCelular.setColumns(10);
		
		JLabel lbTelefono = new JLabel("Telefono:");
		lbTelefono.setForeground(Color.BLACK);
		lbTelefono.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbTelefono.setBounds(24, 66, 78, 20);
		contentPane.add(lbTelefono);
		
		tfTelefono = new JTextField();
		tfTelefono.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfTelefono.setBounds(174, 66, 307, 26);
		contentPane.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		JLabel lbBarrio = new JLabel("Barrio:");
		lbBarrio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbBarrio.setForeground(Color.BLACK);
		lbBarrio.setBounds(23, 142, 56, 20);
		contentPane.add(lbBarrio);
		
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
		btnGuardar.setBounds(96, 264, 90, 28);
		
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				OpcionesCliente.main(null);
				
				dispose();
				
			}
		});
		
		btnCancelar.setBounds(283, 264, 90, 28);
		
		contentPane.add(btnCancelar);
		
		JLabel label = new JLabel("Direccion:");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(24, 181, 81, 20);
		contentPane.add(label);
		
		tfDireccion = new JTextField();
		tfDireccion.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(174, 179, 307, 26);
		contentPane.add(tfDireccion);
		
		try {
			 obtenerip.main(null);
			 driver=obtenerip.driver;
			 user=obtenerip.user;
			 pass=obtenerip.pass;
			
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(driver,user,pass);

			Statement preparacion = conexion.createStatement();
			PreparedStatement ps = conexion
					.prepareStatement("select * from Clientes where ID=?");
			ps.setString(1, Aplicacion.OpcionesCliente.ClienteSel);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				id=rs.getString(1);
				tfNombre.setText(rs.getString(2));
				tfTelefono.setText(rs.getString(3));
				tfCelular.setText(rs.getString(4));
				tfBarrio.setText(rs.getString(5));
				tfDireccion.setText(rs.getString(6));
				
			
			
			}
			preparacion.close();
			conexion.close();
			
			} catch (SQLException e) {
				System.out.println("error:" + e.toString());
			} catch (ClassNotFoundException e) {
				System.out.println("error:" + e.toString());
			}
		
		
		
	}
	
	public void ModificarDatos() {

		try {

			Class.forName("com.mysql.jdbc.Driver");
System.out.println("UPDATE Clientes SET  Celular='"
		+ tfCelular.getText().trim() + "', Telefono= '"
		+ tfTelefono.getText().trim() + "', Direccion= '"
		+ tfDireccion.getText().trim() + "', Barrio= '"
		+ tfBarrio.getText().trim() +"',Nombre= '"
		+ tfNombre.getText().trim()+"' WHERE ID ='"+id+"';");
			
			String cadenaSql = ("UPDATE Clientes SET  Celular='"
					+ tfCelular.getText().trim().toUpperCase() + "', Telefono= '"
					+ tfTelefono.getText().trim().toUpperCase() + "', Barrio= '"
					+ tfDireccion.getText().trim().toUpperCase() + "', Direccion= '"
					+ tfBarrio.getText().trim().toUpperCase() +"',Nombre= '"
					+ tfNombre.getText().trim().toUpperCase()+"' WHERE ID ='"+id+"';");
			
			Connection conexion = DriverManager.getConnection(driver,user,pass);
			Statement preparacion = conexion.createStatement();

			int resultados = preparacion.executeUpdate(cadenaSql);

			if (resultados > 0) {
				JOptionPane.showMessageDialog(null,
						"Los datos del Cliente fueron modificados.");
				OpcionesCliente.main(null);
				this.dispose();
			} else
				JOptionPane.showMessageDialog(null,
						"Error al modificar los datos.");

		} catch (ClassNotFoundException error) {
			JOptionPane.showMessageDialog(null, "modificar "+error.getMessage());
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "modificar "+error.getMessage());
		}

	}
	
	public void Validar() {
		if (!(tfNombre.getText()).equals("")
				&& !(tfBarrio.getText()).equals("")
				)
			ModificarDatos();
		else
			JOptionPane
					.showMessageDialog(null,
							"No pueden haber campos vacios.");

	}
}
