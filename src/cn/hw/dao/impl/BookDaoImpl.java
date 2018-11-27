package cn.hw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hw.dao.BookDao;
import cn.hw.domain.Book;
import cn.hw.exception.DaoException;
import cn.hw.util.DBCPUtil;

public class BookDaoImpl implements BookDao {
	private QueryRunner qr= new QueryRunner(DBCPUtil.getDataSource());
	public void addBook(Book book) {
		try {
			qr.update("insert into book(id,name,author,price,image,description,category_id) values(?,?,?,?,?,?,?)",
					book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getImage(),book.getDescription(),
					book.getCategory_id());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	public List<Book> findPageBooks(int startIndex, int pageSize) {
		try {
			return qr.query("select * from book limit ?,?", new BeanListHandler<Book>(Book.class),startIndex,pageSize);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	public int getTotalRecord() {
		try {
			Long num = (Long)qr.query("select count(*) from book",new ScalarHandler(1));
			return num.intValue();
		}catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	public int getTotalRecord(String categoryId) {
		try {
			Long num = (Long)qr.query("select count(*) from book where category_id=?",new ScalarHandler(1),categoryId);
			return num.intValue();
		}catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	public List findPageBooks (int startindex, int pagesize, String categoryId) {
		try {
			return qr.query("select * from book where category_id=? limit ?,?", new BeanListHandler<Book>(Book.class), categoryId,startindex,pagesize);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	public Book findBookById(String bookId) {
		try {
			return qr.query("select * from book where id=?", new BeanHandler<Book>(Book.class), bookId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
}
