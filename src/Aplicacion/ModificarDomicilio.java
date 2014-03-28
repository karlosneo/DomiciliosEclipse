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
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ModificarDomicilio extends JFrame {

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
	private String Iddomicilio;
	private JTextField tfDireccion;
	private JTextArea tpDomicilio;
	private String driver;
	private String user;
	private String pass;
	private JTextField tfcodigo;
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarDomicilio frame = new ModificarDomicilio();
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
	public ModificarDomicilio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarDomicilio.class.getResource("/Adjuntos/icono.png")));
		setTitle("Modificar Domicilio");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 499, 457);
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
		lblNombre.setBounds(25, 65, 70, 20);
		contentPane.add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setForeground(Color.BLACK);
		tfNombre.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfNombre.setBounds(175, 65, 307, 26);
		contentPane.add(tfNombre);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCelular.setForeground(Color.BLACK);
		lblCelular.setBounds(25, 141, 64, 20);
		contentPane.add(lblCelular);
		
		tfCelular = new JTextField();
		tfCelular.setEditable(false);
		tfCelular.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfCelular.setBounds(175, 141, 307, 26);
		contentPane.add(tfCelular);
		tfCelular.setColumns(10);
		
		JLabel lbTelefono = new JLabel("Telefono:");
		lbTelefono.setForeground(Color.BLACK);
		lbTelefono.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbTelefono.setBounds(25, 103, 78, 20);
		contentPane.add(lbTelefono);
		
		tfTelefono = new JTextField();
		tfTelefono.setEditable(false);
		tfTelefono.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfTelefono.setBounds(175, 103, 307, 26);
		contentPane.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		JLabel lbBarrio = new JLabel("Barrio:");
		lbBarrio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbBarrio.setForeground(Color.BLACK);
		lbBarrio.setBounds(24, 179, 56, 20);
		contentPane.add(lbBarrio);
		
		tfBarrio = new JTextField();
		tfBarrio.setEditable(false);
		tfBarrio.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfBarrio.setBounds(175, 179, 307, 26);
		contentPane.add(tfBarrio);
		tfBarrio.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TablaConsultaDomicilio.main(null);
				
				dispose();
				
			}
		});
		
		btnCancelar.setBounds(288, 389, 90, 28);
		
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Aceptar");
		btnGuardar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(tpDomicilio.getText());
			ModificarDatos();
			}
		});
		btnGuardar.setBounds(106, 389, 90, 28);
		
		contentPane.add(btnGuardar);
		
		JLabel label = new JLabel("Direccion:");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(25, 218, 81, 20);
		contentPane.add(label);
		
		tfDireccion = new JTextField();
		tfDireccion.setEditable(false);
		tfDireccion.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(175, 216, 307, 26);
		contentPane.add(tfDireccion);
		
		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setForeground(Color.BLACK);
		lblDomicilio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDomicilio.setBounds(24, 253, 81, 20);
		contentPane.add(lblDomicilio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(174, 252, 307, 115);
		contentPane.add(scrollPane);
		
		tpDomicilio = new JTextArea();
		scrollPane.setViewportView(tpDomicilio);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setForeground(Color.BLACK);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCodigo.setBounds(25, 30, 70, 20);
		contentPane.add(lblCodigo);
		
		tfcodigo = new JTextField();
		tfcodigo.setForeground(Color.BLACK);
		tfcodigo.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		tfcodigo.setEditable(false);
		tfcodigo.setBounds(175, 28, 307, 26);
		contentPane.add(tfcodigo);
		
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
			ps.setString(1, Aplicacion.TablaConsultaDomicilio.ClienteSel);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				id=rs.getString(1);
				Iddomicilio = Aplicacion.TablaConsultaDomicilio.Idsel;
				tfcodigo.setText(Aplicacion.TablaConsultaDomicilio.Idsel);
				tfNombre.setText(rs.getString(2));
				tfTelefono.setText(rs.getString(3));
				tfCelular.setText(rs.getString(4));
				tfBarrio.setText(rs.getString(5));
				tfDireccion.setText(rs.getString(6));
				tpDomicilio.setText(Aplicacion.TablaConsultaDomicilio.DomicilioSel);
				
			
			
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
		System.out.println(tpDomicilio.getText());

		try {

			Class.forName("com.mysql.jdbc.Driver");

			
			String cadenaSql = ("UPDATE Domicilios SET  Domicilio='"
					+ tpDomicilio.getText() +"' WHERE ID ='"+Iddomicilio+"';");
			
			Connection conexion = DriverManager.getConnection(driver,user,pass);
			Statement preparacion = conexion.createStatement();

			int resultados = preparacion.executeUpdate(cadenaSql);

			if (resultados > 0) {
				JOptionPane.showMessageDialog(null,
						"Los datos del Domicilio fueron modificados.");
				TablaConsultaDomicilio.main(null);
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
}
