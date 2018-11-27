package cn.hw.service.impl;

import java.util.List;

import cn.hw.dao.BookDao;
import cn.hw.dao.CategoryDao;
import cn.hw.dao.OrderDao;
import cn.hw.dao.UserDao;
import cn.hw.dao.impl.BookDaoImpl;
import cn.hw.dao.impl.CategoryDaoImpl;
import cn.hw.dao.impl.OrderDaoImpl;
import cn.hw.dao.impl.UserDaoImpl;
import cn.hw.domain.Book;
import cn.hw.domain.Category;
import cn.hw.domain.Orders;
import cn.hw.domain.OrdersItem;
import cn.hw.domain.User;
import cn.hw.service.BusinessService;
import cn.hw.util.IDGeneratorUtil;
import cn.hw.util.Page;

public class BusinessServiceImpl implements BusinessService {
	private CategoryDao cd= new CategoryDaoImpl();
	private BookDao bd = new BookDaoImpl();
	private UserDao ud = new UserDaoImpl();
	private OrderDao od = new OrderDaoImpl();
	public void addCategory(Category ca) {
		ca.setId(IDGeneratorUtil.genPrimaryKey());
		cd.addCategory(ca);
	} 

	public List<Category> findAllCategory() {
		return cd.findAllCategory();
	}

	public void addBook(Book book) {
		book.setId(IDGeneratorUtil.genPrimaryKey());
		bd.addBook(book);
	}

	public Page findPageRecords(String pagenum) {
		int num=1;//默认页码
		if(pagenum!=null&&!"".equals(pagenum.trim())){
			num=Integer.parseInt(pagenum);
		}
		int totalrecords = bd.getTotalRecord();
		Page page = new Page(num, totalrecords);
		List records = bd.findPageBooks(page.getStartindex(), page.getPagesize());
		page.setRecords(records);
		return page;
	}

	public Category findCategoryById(String categoryId) {
		return cd.findCategoryById(categoryId);
	}

	public Page findPageRecords(String pagenum, String categoryId) {
		int num=1;//默认页码
		if(pagenum!=null&&!"".equals(pagenum.trim())){
			num=Integer.parseInt(pagenum);
		}
		int totalrecords = bd.getTotalRecord(categoryId);
		Page page = new Page(num, totalrecords);
		List records = bd.findPageBooks(page.getStartindex(), page.getPagesize(),categoryId);
		page.setRecords(records);
		return page;
	}

	public Book findBookById(String bookId) {
		return bd.findBookById(bookId);
	}

	public User login(String username, String password) {
		return ud.login(username,password);
	}

	public void regist(User user) {
		user.setId(IDGeneratorUtil.genPrimaryKey());
		ud.regist(user);
	}

	public void addOrders(Orders orders, User user) {
		orders.setOrdernum(IDGeneratorUtil.genPrimaryKey());
		orders.setId(IDGeneratorUtil.genPrimaryKey());
		List<OrdersItem> items = orders.getItems();
		for(OrdersItem item:items)
			item.setId(IDGeneratorUtil.genPrimaryKey());
		od.addOrders(orders,user);
	}

	public List<Orders> findOrdersByUserId(String id) {
		return od.findOrdersByUserId(id);
	}

	public Orders findOrdersById(String ordersId) {
		return od.findOrdersById(ordersId);
	}

	public List<Orders> findOrdersByState(int i) {
		return od.findOrdersByState(i);
	}

	public void sureOrders(String ordersId) {
		od.sureOrders(ordersId);
	}
}
