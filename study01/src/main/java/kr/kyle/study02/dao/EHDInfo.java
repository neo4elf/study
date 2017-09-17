package kr.kyle.study02.dao;

public class EHDInfo implements DatabaseInfo {

	public String getJDBCDriver() {
		return "com.mysql.jdbc.Driver";
	}

	public String getJDBCURL() {
		return "jdbc:mysql://121.134.153.246:3306/gameroom?useSSl=false";
	}

	public String getAccount() {
		return "gameroom";
	}

	public String getPassword() {
		return "gameroom";
	}

	public String getDatabaseName() {
		return "gameroom";
	}

}
