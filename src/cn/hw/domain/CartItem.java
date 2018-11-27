package cn.hw.domain;

import java.io.Serializable;

public class CartItem implements Serializable {
	private Book book;
	private int num;//单项购买数目
	private float price;//单本书小计
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public float getPrice() {
		return book.getPrice()*num ;
	}
	//setPrice()方法不写
}
