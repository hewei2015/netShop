package cn.hw.dao;

import java.util.List;

import cn.hw.domain.Category;

public interface CategoryDao {
	
	void addCategory(Category ca);
	
	List<Category> findAllCategory();

	Category findCategoryById(String categoryId);
	
}
