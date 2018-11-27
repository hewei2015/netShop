package cn.hw.dao;

import cn.hw.domain.User;

public interface UserDao {

	User login(String username, String password);

	void regist(User user);

}
