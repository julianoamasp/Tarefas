package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	//Conex„o - thread safe
	private static final ThreadLocal<Connection> conn = new ThreadLocal<>();
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Obt√©m conex√£o com o banco de dados
	public static Connection obterConexao() throws SQLException {
		if (conn.get() == null){
			conn.set(DriverManager
					.getConnection("jdbc:mysql://localhost/Ex06?user=root&password=root"));
		}
        return conn.get();				
	}
    //fechar a conex„o - usudo no servlet destroy
	public static void fecharConexao() throws SQLException{
		if(conn.get() != null){	
		   conn.get().close();
		   conn.set(null);
			
		}
	}
}
