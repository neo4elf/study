package kr.kyle.study02.domain;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.kyle.study02.dao.UserDAO;
import kr.kyle.study02.service.UserService;
import kr.kyle.study02.service.impl.UserServiceImpl;

public class UserManager {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("UserApplicationContext.xml", UserManager.class);
		
//		UserDAO userDao = context.getBean("userDao", UserDAO.class);
		
		UserService userService = context.getBean("userService", UserService.class);
		
		User user = userService.getUser("neo4elf");
		
		System.out.println("user : " + user);
		

		
	}
}
