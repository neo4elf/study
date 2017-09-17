package kr.kyle.study01.tobi.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
	
	@Bean("userDao")
	public UserDao userDao() {
		UserDao userDao = new UserDao(connectionMaker());
		return userDao;
	}
	
	@Bean("connectionMaker")
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}
}
