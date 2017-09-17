package kr.kyle.study02.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kyle.study02.dao.UserDAO;
import kr.kyle.study02.domain.User;
import kr.kyle.study02.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userDao")
	private UserDAO userDao;

	public int addUser(User user) {
		return userDao.addUser(user); 
	}

	public User getUser(String userId) {
		return userDao.getUser(userId);
	}

	public int modUser(User usere) {
		return 0;
	}

}
