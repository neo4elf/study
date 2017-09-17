package kr.kyle.study01.tobi.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {
	
	@Bean("userDao")
	public UserDao userDao() {
		UserDao userDao = new UserDao(connectionMaker());
		return userDao;
	}
	
	@Bean("connectionMaker")
	public ConnectionMaker connectionMaker() {
		return new CountingConnectionMaker(realConnectionMaker());
	}
	
	@Bean("realConnectionMaker")
	public ConnectionMaker realConnectionMaker() {
		return new DConnectionMaker();
	}

}
