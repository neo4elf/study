package kr.kyle.study01.tobi;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.kyle.study01.tobi.dao.CountingConnectionMaker;
import kr.kyle.study01.tobi.dao.CountingDaoFactory;
import kr.kyle.study01.tobi.dao.UserDao;
import kr.kyle.study01.tobi.domain.User;

public class UserDaoConnectionCountingTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
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
		
		CountingConnectionMaker countingConnectionMaker = context.getBean("connectionMaker", CountingConnectionMaker.class);
		
		System.out.println("count is " + countingConnectionMaker.getCounter());

	}

}
