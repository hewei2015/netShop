package cn.hw.dao;

import java.util.List;

import cn.hw.domain.Orders;
import cn.hw.domain.User;

public interface OrderDao {

	void addOrders(Orders orders, User user);

	List<Orders> findOrdersByUserId(String id);

	Orders findOrdersById(String ordersId);

	List<Orders> findOrdersByState(int i);

	void sureOrders(String ordersId);

}
