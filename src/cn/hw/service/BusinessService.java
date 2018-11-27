package cn.hw.service;

import java.util.List;

import cn.hw.domain.Book;
import cn.hw.domain.Category;
import cn.hw.domain.Orders;
import cn.hw.domain.User;
import cn.hw.util.Page;

public interface BusinessService {
	
	void addCategory(Category ca);
	
	List<Category> findAllCategory();

	void addBook(Book book);
	
	Page findPageRecords(String pagenum);

	Category findCategoryById(String categoryId);

	/**
	 * 按照分类查询分页数据
	 * @param pagenum
	 * @param categoryId
	 * @return
	 */
	Page findPageRecords(String pagenum, String categoryId);

	Book findBookById(String bookId);

	User login(String username, String password);

	void regist(User user);

	void addOrders(Orders orders, User user);

	List<Orders> findOrdersByUserId(String id);

	Orders findOrdersById(String ordersId);

	List<Orders> findOrdersByState(int i);

	void sureOrders(String ordersId);
}
