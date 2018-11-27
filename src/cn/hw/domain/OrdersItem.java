package cn.hw.domain;

import java.io.Serializable;

//对应购物车中的购物项
public class OrdersItem implements Serializable {
	private String id;
	private int num;
	private float price;
	private Book book;//一本书可以对应多个订单
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
 
}
