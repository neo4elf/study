package kr.kyle.study02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.kyle.study02.domain.Level;
import kr.kyle.study02.domain.User;

public class UserDAO {
	private ConnectionManager _cm;
	
	public UserDAO(ConnectionManager cm) {
		this._cm = cm;
	}
	
	public User getUser(String userId) {
		User user = null;
		String query = null;
		ResultSet rs = null;
		PreparedStatement prst = null;
		Connection conn = null;
		
		query = "select * from gameroom.users where id = ? ";
		
		try {
			conn = this._cm.makeConnection();
			prst = conn.prepareStatement(query);
			
			prst.setString(1, userId);
			
			rs = prst.executeQuery();
			
			if (rs.next()) {
				user = new User();
				
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setLevel(Level.valueOf(rs.getInt("level")));
				user.setLogin(rs.getInt("login"));
				user.setRecommend(rs.getInt("recommend"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs   != null) try {rs.close();}catch(Exception e) {};
			if (prst != null) try {prst.close();}catch(Exception e) {};
			if (conn != null) try {conn.close();}catch(Exception e) {};
		}
		
		return user;
	}
	
	public int addUser(User user) {
		int count = 0;
		String query = null;
		PreparedStatement prst = null;
		Connection conn = null;
		
		query = "INSERT INTO gameroom.users (id, name, password, level, login, recommend)"
				+ "VALUES(?,?,?,?,?,?)";

		try {
			int idx = 1;
			conn = this._cm.makeConnection();
			prst = conn.prepareStatement(query);
			
			prst.setString(idx++, user.getId());
			prst.setString(idx++, user.getName());
			prst.setString(idx++, user.getPassword());
			prst.setInt	  (idx++, user.getLevel().intValue());
			prst.setInt	  (idx++, user.getLogin());
			prst.setInt   (idx++, user.getRecommend());
			
			count = prst.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prst != null) try {prst.close();}catch(Exception e) {};
			if (conn != null) try {conn.close();}catch(Exception e) {};
		}
		
		return count;
	}

}
