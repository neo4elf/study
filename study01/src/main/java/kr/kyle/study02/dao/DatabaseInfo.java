package kr.kyle.study02.dao;

public interface DatabaseInfo {
	public String getJDBCDriver();
	public String getJDBCURL();
	public String getAccount();
	public String getPassword();
	public String getDatabaseName();
}
