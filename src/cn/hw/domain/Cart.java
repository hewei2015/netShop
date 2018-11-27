package cn.hw.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
	private Map<String,CartItem> items = new HashMap<String,CartItem>();//key:购物项对应的书的id，value:购物项
	private int num;
	private float price;//总价，付款时的金额。set方法不要
	
	//★注意items不需要实现set方法
	
	public Map<String, CartItem> getItems() {
		return items;
	}
	//setNum()和setPrice()方法不要写
	public int getNum() {
		num=0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){//▲▲★★【记住】
			num+=me.getValue().getNum();
		}
		return num;
	}
	public float getPrice() {
		price=0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){//▲▲★★【记住】
			price+=me.getValue().getPrice();
		}
		return price;
	}
	public void addBook(Book book) {
		//如果有书
		if(items.containsKey(book.getId())){
			CartItem item=items.get(book.getId());
			item.setNum(item.getNum()+1);
		}else{
			CartItem item = new CartItem();
			item.setBook(book);
			item.setNum(1);
			items.put(book.getId(), item);
		}
	}
}
