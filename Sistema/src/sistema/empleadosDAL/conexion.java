

package sistema.empleadosDAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class conexion {
	String strConexionDB = "jdbc:sqlserver://localhost:1433;databaseName=Sistema;user=sa;password=roquebernedo123;encrypt=true;trustServerCertificate=true";
	Connection conn = null;
	
	public conexion(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(strConexionDB);
			
			System.out.println("Conexion establecida");
		} catch (Exception e) {
			System.out.println("Error de Conexion"+e);
		}
	}
	
	public int ejecutarSentenciaSQL(String strSentenciaSQL) {
		try{
			PreparedStatement pstm = conn.prepareStatement(strSentenciaSQL);
			pstm.execute();
			return 1;
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		}
	}
	
	public ResultSet consultarRegistros(String strSentenciaSQL) {
		try{
			PreparedStatement pstm = conn.prepareStatement(strSentenciaSQL);
			ResultSet respuesta = pstm.executeQuery();
			return respuesta;
			
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
