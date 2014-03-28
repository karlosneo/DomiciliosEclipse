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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.Font;
import java.awt.Toolkit;

public class OpcionesCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable Tabla;
	private JTextField tfSeleccion;
	private static String Seleccion;
	static String IdCliente;
	static String NombreCliente;
	static String ClienteSel;
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
					OpcionesCliente frame = new OpcionesCliente();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					Seleccion="";
					Llenar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OpcionesCliente() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(OpcionesCliente.class.getResource("/Adjuntos/icono.png")));
		setTitle("Operaciones con Cliente");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 726, 407);
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
		tfSeleccion.setBounds(324, 14, 175, 23);
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
		lblEscribaLaPrimer.setBounds(57, 13, 228, 21);
		contentPane.add(lblEscribaLaPrimer);
		
		 JButton btnModificar = new JButton("Modificar");
	        btnModificar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
	        btnModificar.addActionListener(new ActionListener() {
	        	
	       
	        	public void actionPerformed(ActionEvent e) {
	        	ClienteSel = Tabla.getValueAt(Tabla.getSelectedRow(), 0).toString();
	        	System.out.println(ClienteSel);
	        	ModificarCliente ventana = new ModificarCliente();
	        	ventana.setVisible(true);
	        	//DatosTabla.setRowCount(0);
	        	ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        	dispose();	
	        		
	        		
	        	}
	        });
	        btnModificar.setBounds(132, 334, 90, 28);
	        contentPane.add(btnModificar);

		JButton btnObtenerDatos = new JButton("Obtener Cliente");
		btnObtenerDatos.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		
		btnObtenerDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Seleccion = tfSeleccion.getText();
				Llenar();
			}
		});
		btnObtenerDatos.setBounds(530, 13, 127, 29);
		contentPane.add(btnObtenerDatos);

		JButton btnEliminar = new JButton("Borrar");
		btnEliminar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					IdCliente = Tabla.getValueAt(Tabla.getSelectedRow(), 0).toString();
					NombreCliente = Tabla.getValueAt(Tabla.getSelectedRow(), 1).toString();
					int n = JOptionPane.showConfirmDialog(
				            null,
				            "Esta seguro que desea borrar el Cliente "+NombreCliente+" ???",
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
		btnEliminar.setBounds(330, 334, 70, 29);
		contentPane.add(btnEliminar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Principal.main(null);

			}
		});
		btnCancelar.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		
		btnCancelar.setBounds(498, 334, 86, 29);
		contentPane.add(btnCancelar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 698, 267);
		contentPane.add(scrollPane);

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
			PreparedStatement ps = conexion
					.prepareStatement("select id, Nombre, Telefono, Celular, Barrio from Clientes " +
									  "WHERE Nombre LIKE '%"+Seleccion+"%' " +
									  "OR Telefono = '"+Seleccion+"'" +
									  "OR Celular = '"+Seleccion+"';");
			ResultSet rs = ps.executeQuery();
			Tabla = new JTable(modelo);
			scrollPane.setViewportView(Tabla);
			modelo.addColumn("ID");
			modelo.addColumn("Nombre");
			modelo.addColumn("Telefono");
			modelo.addColumn("Celular");
			modelo.addColumn("Barrio");

			
			// Ocultar Columna 0 ID
			Tabla.getColumnModel().getColumn(0).setMaxWidth(0);
			Tabla.getColumnModel().getColumn(0).setMinWidth(0);
			Tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
			
			while (rs.next()) {
				// Se crea un array que será una de las filas de la tabla.
				String[] fila = new String[5]; // Hay Cuatro columnas en la tabla

				// Se rellena cada posición del array con una de las columnas de
				// la tabla en base de datos.
				for (int i = 0; i < 1; i++) {
					System.out.println(rs.getString(i + 1));
					
					fila[0] = rs.getString(i + 1);
					fila[1] = rs.getString(i + 2);
					fila[2] = rs.getString(i + 3);
					fila[3] = rs.getString(i + 4);
					fila[4] = rs.getString(i + 5);
											
					modelo.addRow(fila);
					
					
					
					Tabla.repaint();
					
					
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
			/*Tabla.getColumnModel().getColumn(0).setMaxWidth(0);
			Tabla.getColumnModel().getColumn(0).setMinWidth(0);
			Tabla.getColumnModel().getColumn(0).setPreferredWidth(0);*/

			
			// Organizar ancho columnas
			int[] anchos = {0,30 ,0,0,0 };

			//hacemos un bucle FOR desde cero hasta la cantidad de columnas

			//de nuestra tabla

			for(int i = 0; i < Tabla.getColumnCount(); i++) {

			//Sacamos el modelo de columnas de nuestra tabla

			//luego obtenemos la columna en la posicion "i"

			//invocamos el metodo setPreferrefWidth para ajustar el ancho

			//y le damos el valor del entero que esta en el arreglo en la posicion "i"

			Tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
			
			Tabla.repaint();

		}
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
					.prepareStatement("Delete from Clientes where id='"
							+ IdCliente + "';");
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "El Cliente " + NombreCliente
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
