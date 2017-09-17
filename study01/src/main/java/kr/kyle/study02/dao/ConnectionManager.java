package kr.kyle.study02.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private DatabaseInfo _dbInfo;
	
	public ConnectionManager(DatabaseInfo dbInfo) {
		this._dbInfo = dbInfo;
	}
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		
		Class.forName(this._dbInfo.getJDBCDriver());
		conn = DriverManager.getConnection(this._dbInfo.getJDBCURL(), this._dbInfo.getAccount(), this._dbInfo.getPassword());
		
		return conn;
	}
	
}
