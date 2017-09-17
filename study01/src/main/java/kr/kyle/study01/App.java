package kr.kyle.study01;

import java.sql.SQLException;

import kr.kyle.study01.tobi.dao.DaoFactory;
import kr.kyle.study01.tobi.dao.UserDao;
import kr.kyle.study01.tobi.domain.User;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = (new DaoFactory()).userDao();
		
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
