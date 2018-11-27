package cn.hw.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.hw.dao.UserDao;
import cn.hw.domain.User;
import cn.hw.exception.DaoException;
import cn.hw.util.DBCPUtil;

public class UserDaoImpl implements UserDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	public User login(String username, String password) {
		try {
			return qr.query("select * from user where username=? and password=?",new BeanHandler<User>(User.class),username,password);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void regist(User user) {
		try {
			qr.update("insert into user(id,username,password,cellphone,mobilephone,address,email) values(?,?,?,?,?,?,?)",
					user.getId(),user.getUsername(),user.getPassword(),user.getCellphone(),user.getMobilephone(),user.getAddress(),user.getEmail());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
}
