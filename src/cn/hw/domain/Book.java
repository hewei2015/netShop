package cn.hw.domain;

import java.io.Serializable;

public class Book implements Serializable {
	private String id;
	private String name;
	private String author;
	private float price;
	private String image;//图片只用于显示，所以只要记住图片的UUID文件名
	private String description;
	private String category_id;//★只能得到书籍的categoryg_id,所以在页面显示时要自定义EL表达式以显示数据的类别名字
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author
				+ ", price=" + price + ", image=" + image + ", description="
				+ description + ", category_id=" + category_id + "]";
	}
	
	
}
