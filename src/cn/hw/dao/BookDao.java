package cn.hw.dao;

import java.util.List;

import cn.hw.domain.Book;

public interface BookDao {

	void addBook(Book book);
	
	List<Book> findPageBooks(int startIndex,int pageSize);
	
	int getTotalRecord();

	int getTotalRecord(String categoryId);

	List findPageBooks(int startindex, int pagesize, String categoryId);

	Book findBookById(String bookId);

}
