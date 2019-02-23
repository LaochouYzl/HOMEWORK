package cn.laochou.service;

import cn.laochou.dao.UserDao;
import cn.laochou.pojo.User;

public class UserService {
	
	private UserDao userDao = new UserDao();
	
	/**
	 * 	校验用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public User checkUser(String userName, String password) {
		User user = userDao.selectUserByUserNameAndPassword(userName, password);
		return user == null ? null : user;
	}
	
	
	public boolean insertUser(String userName, String password, String email) {
		return userDao.insertUser(userName, password, email) == 1? true : false;
	}
}
