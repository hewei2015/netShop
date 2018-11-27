package cn.hw.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//订单信息，对应的就是购物车的基本信息
public class Orders implements Serializable {
	private String id;
	private String ordernum;//订单号
	private int num;//数量
	private float price;//付款金额
	private int state = 0;//0表示未发货，1表示已发货
	private User user;//订单与用户的关系
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	private List<OrdersItem> items = new ArrayList<OrdersItem>();//订单中的订单项

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrdersItem> getItems() {
		return items;
	}

	public void setItems(List<OrdersItem> items) {
		this.items = items;
	}
	
	
}
