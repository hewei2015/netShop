package cn.hw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hw.dao.OrderDao;
import cn.hw.domain.Book;
import cn.hw.domain.Orders;
import cn.hw.domain.OrdersItem;
import cn.hw.domain.User;
import cn.hw.exception.DaoException;
import cn.hw.util.DBCPUtil;

public class OrderDaoImpl implements OrderDao{
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	public void addOrders(Orders orders, User user) {
		try{
			qr.update("insert into orders(id,ordernum,num,price,user_id) values(?,?,?,?,?)",
					orders.getId(),orders.getOrdernum(),orders.getNum(),orders.getPrice(),user.getId());
			//订单中的订单项
			List<OrdersItem> items = orders.getItems();
			if(items!=null&&items.size()>0){
				String sql="insert into ordersitem(id,num,price,orders_id,book_id) values(?,?,?,?,?)";
				Object pps[][] = new Object[items.size()][];
				for(int i=0;i<items.size();i++){
					OrdersItem item = items.get(i);
					pps[i] = new Object[]{item.getId(),item.getNum(),item.getPrice(),orders.getId(),item.getBook().getId()};
				}
				qr.batch(sql, pps);
			}
		}catch(SQLException e){
			throw new DaoException(e);
		}
	}
	
	public List<Orders> findOrdersByUserId(String id) {
		try {
			return qr.query("select * from orders where user_id=? order by ordernum desc", 
					new BeanListHandler<Orders>(Orders.class),id);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	public Orders findOrdersById(String ordersId) {
		try {
			//思路：订单信息->用户信息->书籍信息
			Orders o =qr.query("select * from orders where id=?", new BeanHandler<Orders>(Orders.class),ordersId);//先依据user_id查出订单
			if(o!=null){
				//查询用户信息:少的一方，不管有没有需求，默认都是查出来的
				User user = qr.query("select * from  user where id=(select user_id from orders where id=?)",
						new BeanHandler<User>(User.class),ordersId);
				o.setUser(user);
				List<OrdersItem> items = qr.query("select * from ordersitem where orders_id=?",
						new BeanListHandler<OrdersItem>(OrdersItem.class),ordersId);
				//查询书的信息
				if(items!=null&&items.size()>0){
					for(OrdersItem item:items){
						Book b = qr.query("select * from book where id=(select book_id from ordersitem where id=?)",
								new BeanHandler<Book>(Book.class),item.getId());
						item.setBook(b);
					}
				}
				o.setItems(items);
			}
			return o;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public List<Orders> findOrdersByState(int i) {
		try {
			List<Orders> os =qr.query("select * from orders where state=? order by ordernum desc", new BeanListHandler<Orders>(Orders.class),i);//先依据user_id查出订单
			//还需要查询用户，知道这个订单是属于谁的
			if(os!=null&&os.size()>0){
				for(Orders o:os){
					User user = qr.query("select * from user where id=(select user_id from orders where id=?)",
						new BeanHandler<User>(User.class),o.getId());
					o.setUser(user);
				}
			}
			return os;
		}catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public void sureOrders(String ordersId) {
		try {
			qr.update("update orders set state=? where id=?", 1,ordersId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
	}	
}
