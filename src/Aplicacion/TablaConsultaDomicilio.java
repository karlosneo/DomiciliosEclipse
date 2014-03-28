package Aplicacion;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
//import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class TablaConsultaDomicilio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable Tabla;
	private JTextField tfSeleccion;
	private static String Seleccion;
	static String mensaje;
	static String IdCliente;
	static String IdDomicilio;
	static String NombreCliente;
	static String ClienteSel;
	static String DomicilioSel;
	static String Idsel;
	private static DefaultTableModel modelo;
	private static JScrollPane scrollPane;
	private static String driver;
	private static String user;
	private static String pass;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablaConsultaDomicilio frame = new TablaConsultaDomicilio();
					frame.setLocationRelativeTo(null);
					Seleccion="";
					Llenar();
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
	public TablaConsultaDomicilio() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TablaConsultaDomicilio.class.getResource("/Adjuntos/icono.png")));
		setTitle("Opciones Domicilio");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 624, 332);
		contentPane = new JPanel(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		 };
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfSeleccion = new JTextField();
		tfSeleccion.setText(null);
		tfSeleccion.setBounds(244, 14, 191, 23);
		tfSeleccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
							
					int key = e.getKeyCode();
					if (key == KeyEvent.VK_ENTER) {
							
						Seleccion = tfSeleccion.getText();
						Llenar();
										
					}
					}
			
		});
		contentPane.add(tfSeleccion);
		tfSeleccion.setColumns(10);

		JLabel lblEscribaLaPrimer = new JLabel(
				"Escriba el dato para la busqueda");
		
		lblEscribaLaPrimer.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		lblEscribaLaPrimer.setBounds(10, 13, 228, 21);
		contentPane.add(lblEscribaLaPrimer);

		JButton btnObtenerDatos = new JButton("Obtener datos");
		btnObtenerDatos.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		btnObtenerDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Seleccion = tfSeleccion.getText();
				Llenar();
			}
		});
		btnObtenerDatos.setBounds(468, 12, 123, 23);
		contentPane.add(btnObtenerDatos);

		JButton btnGenerarHistorial = new JButton("Datos Domicilio");
		btnGenerarHistorial.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		btnGenerarHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ClienteSel = Tabla.getValueAt(Tabla.getSelectedRow(), 3).toString();
				DomicilioSel =Tabla.getValueAt(Tabla.getSelectedRow(), 2).toString();
				Idsel= Tabla.getValueAt(Tabla.getSelectedRow(), 0).toString();
	        	System.out.println(ClienteSel);
	        	VerDomicilio ventana = new VerDomicilio();
	        	ventana.setVisible(true);
	        	//DatosTabla.setRowCount(0);
	        	
	        	dispose();
	        	
			}
		});
		btnGenerarHistorial.setBounds(40, 247, 127, 29);
		contentPane.add(btnGenerarHistorial);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Principal.main(null);

			}
		});
		btnCancelar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		btnCancelar.setBounds(493, 247, 86, 29);
		contentPane.add(btnCancelar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 598, 165);
		contentPane.add(scrollPane);
		
		modelo = new DefaultTableModel();
		Tabla = new JTable(modelo);
		scrollPane.setViewportView(Tabla);
		
		JButton btnModificarDomicilio = new JButton("Modificar Domicilio");
		btnModificarDomicilio.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		btnModificarDomicilio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Idsel= Tabla.getValueAt(Tabla.getSelectedRow(), 0).toString();
				ClienteSel = Tabla.getValueAt(Tabla.getSelectedRow(), 3).toString();
				DomicilioSel =Tabla.getValueAt(Tabla.getSelectedRow(), 2).toString();
	        	System.out.println("el cliente es"+ClienteSel);
	        	ModificarDomicilio ventana = new ModificarDomicilio();
	        	ventana.setVisible(true);
	        	//DatosTabla.setRowCount(0);
	        	
	        	dispose();
			}
		});
		btnModificarDomicilio.setBounds(205, 247, 147, 29);
		contentPane.add(btnModificarDomicilio);
		
		JButton btnEliminar = new JButton("Borrar");
		btnEliminar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					IdDomicilio = Tabla.getValueAt(Tabla.getSelectedRow(), 0).toString();
					ClienteSel = Tabla.getValueAt(Tabla.getSelectedRow(), 1).toString();
					int n = JOptionPane.showConfirmDialog(
				            null,
				            "Esta seguro que desea borrar el Domicilio de "+ClienteSel+" ???",
				            "Advertencia",
				            JOptionPane.YES_NO_OPTION);

				        if(n==0){
				        	Borrar();
				        }
					
				
				
				}
				catch(Exception e){ 
					JOptionPane.showMessageDialog(null, "Seleccione un producto");
									}
									}
				
			
		});
		btnEliminar.setBounds(383, 247, 70, 29);
		
		
		contentPane.add(btnEliminar);
		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Telefono");
		modelo.addColumn("Celular");
		modelo.addColumn("Barrio");
	}

	public static void Llenar() {
		modelo = new DefaultTableModel();
		
		
		try {
			 obtenerip.main(null);
			 driver=obtenerip.driver;
			 user=obtenerip.user;
			 pass=obtenerip.pass;
			
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(driver,user,pass);
			
			Statement preparacion = conexion.createStatement();
			/*System.out.println("select id, Nombre, Telefono, Celular from Domicilios " +
					  "WHERE Nombre LIKE '%"+Seleccion+"%' " +
					  "OR Identificacion = '"+Seleccion+"'" +
					  "OR Raza LIKE '%"+Seleccion+"%';");*/
			PreparedStatement ps = conexion
					.prepareStatement("select ID, Nombre, Domicilio, Cliente from Domicilios " +
									  "WHERE Nombre LIKE '%"+Seleccion+"%' " +
									  "OR ID LIKE '"+Seleccion+"'  " +
									  "OR Domicilio LIKE '"+Seleccion+"%';");
			ResultSet rs = ps.executeQuery();
			
			Tabla = new JTable(modelo);
			scrollPane.setViewportView(Tabla);
			
			modelo.addColumn("Codigo");
			modelo.addColumn("Nombre");
			modelo.addColumn("Productos");
			modelo.addColumn("ID");
			
			while (rs.next()) {
				// Se crea un array que será una de las filas de la tabla.
				String[] fila = new String[4]; // Hay Cuatro columnas en la tabla

				// Se rellena cada posición del array con una de las columnas de
				// la tabla en base de datos.
				for (int i = 0; i < 1; i++) {
					System.out.println(rs.getString(i + 1));
					
					fila[0] = rs.getString(i + 1);
					fila[1] = rs.getString(i + 2);
					fila[2] = rs.getString(i + 3);
					fila[3] = rs.getString(i + 4);
					
											
					modelo.addRow(fila);
					
					
				}
			}

			List<Object> lista = ((DefaultTableModel) Tabla.getModel())
					.getDataVector();
			
			conexion.close();
			preparacion.close();
			
			
			Collections.sort(lista, new Comparator<Object>() {

				//Arreglar en orden alfabetico
				
				
				public int compare(Object o1, Object o2) {

					
					List<Object> fila1 = (List<Object>) o1;
					List<Object> fila2 = (List<Object>) o2;
					String nombre1 = String.valueOf(fila1.get(1));
					String nombre2 = String.valueOf(fila2.get(1));
					return nombre1.compareToIgnoreCase(nombre2);
				}
			});
			
			
			// Ocultar Columna 0 ID
			Tabla.getColumnModel().getColumn(0).setMaxWidth(100);
			Tabla.getColumnModel().getColumn(0).setMinWidth(50);
			Tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
			
			// Ocultar Columna 4 ID
			Tabla.getColumnModel().getColumn(3).setMaxWidth(0);
			Tabla.getColumnModel().getColumn(3).setMinWidth(0);
			Tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
			
			// Organizar ancho columnas
						int[] anchos = {0,100 ,10,0};

						
						for(int i = 0; i < Tabla.getColumnCount(); i++) {

						Tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
						}
						
						Tabla.repaint();

		}

		catch (SQLException e) {
			System.out.println("error:" + e.toString());
		} catch (ClassNotFoundException e) {
			System.out.println("error:" + e.toString());
		}

	}
	
	public void Borrar() {
		try {

			 obtenerip.main(null);
			 driver=obtenerip.driver;
			 user=obtenerip.user;
			 pass=obtenerip.pass;
			
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(driver,user,pass);

			Statement preparacion = conexion.createStatement();
			PreparedStatement ps = conexion
					.prepareStatement("Delete from Domicilios where ID='"
							+ IdDomicilio + "';");
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "El Domicilio de " + ClienteSel
					+ " Ha sido borrado", "Informacion",
					JOptionPane.INFORMATION_MESSAGE);

			preparacion.close();
			conexion.close();
			dispose();

			main(null);

		} catch (SQLException e) {
			System.out.println("error:" + e.toString());
		} catch (ClassNotFoundException e) {
			System.out.println("error:" + e.toString());
		}

	}
}
