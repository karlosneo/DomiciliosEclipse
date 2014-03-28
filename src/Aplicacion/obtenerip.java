package Aplicacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class obtenerip {

/**
* @param args
 * @return 
*/
	
static String driver;
static String user;
static String pass;
static String texto[];

public static void main(String[] args) {
File f = new File("bd_connection.dll" );
BufferedReader entrada;

try {
	texto = new String[3];

entrada = new BufferedReader( new FileReader( f ) );

/*while(entrada.ready()){
String texto = entrada.readLine();
linea=(String)texto;
}*/

for (int i=0;i<3;i++){

	texto[i]= entrada.readLine();

}
driver=texto[0];
user=texto[1];
pass=texto[2];

System.out.println(driver);
System.out.println(user);
System.out.println(pass);
}catch (IOException e) {
e.printStackTrace();
}


}
}