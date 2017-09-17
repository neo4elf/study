package kr.kyle.study02.service;

import kr.kyle.study02.domain.User;

public interface UserService {

	public int addUser(User user);
	
	public User getUser(String userId);
	
	public int modUser(User user);
}
