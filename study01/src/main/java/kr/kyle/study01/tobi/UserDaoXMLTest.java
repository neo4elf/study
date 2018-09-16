package kr.kyle.study01.tobi;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.kyle.study01.tobi.dao.UserDao;
import kr.kyle.study01.tobi.domain.User;

public class UserDaoXMLTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("user_context.xml", UserDaoXMLTest.class);
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("neo4elf");
		user.setName("Kyle");
		user.setPassword("1111");
		
		//dao.add(user);
		
		System.out.println(user.getId() + " 등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + " 조회 성공");
	}

}
