package cn.hw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hw.dao.CategoryDao;
import cn.hw.domain.Category;
import cn.hw.exception.DaoException;
import cn.hw.util.DBCPUtil;

public class CategoryDaoImpl implements CategoryDao{
	private QueryRunner qr= new QueryRunner(DBCPUtil.getDataSource());
	public void addCategory(Category ca) {
		try {
			qr.update("insert into category(id,name,description) values(?,?,?)",
					ca.getId(),ca.getName(),ca.getDescription());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public List<Category> findAllCategory() {
		try {
			 return qr.query("select * from category",new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public Category findCategoryById(String categoryId) {
		try {
			 return qr.query("select * from category where id=?",new BeanHandler<Category>(Category.class),categoryId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
}
