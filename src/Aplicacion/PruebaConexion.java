package Aplicacion;

import java.sql.*;

public class PruebaConexion 
{
    public static void main( String args[] ) throws SQLException
    {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/drogueria","root","");
	       
	     
	        
	        Statement preparacion = conexion.createStatement();
	        
	        ResultSet resultados = preparacion.executeQuery( "SELECT * FROM clientes " );
	        System.out.println( "¡Datos obtenidos desde MySQL! Mostrando registros...\n" );
	        while( resultados.next() )
	            System.out.println( resultados.getInt( "id" ) + "\t" + resultados.getString( "Nombre" ) );
	        System.out.println( "Cerrando conexiones..." );
	        resultados.close();
	        preparacion.close();
	        conexion.close();
	        System.out.println( "¡Se ha cerrado la conexión!" );
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}