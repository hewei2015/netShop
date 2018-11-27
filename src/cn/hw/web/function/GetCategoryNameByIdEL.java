package cn.hw.web.function;

import cn.hw.domain.Category;
import cn.hw.service.BusinessService;
import cn.hw.service.impl.BusinessServiceImpl;

public class GetCategoryNameByIdEL{
	public static String getCategoryNameByIdEL(String categoryId){
		BusinessService bs = new BusinessServiceImpl();
		Category c = bs.findCategoryById(categoryId);
		if(c!=null)
			return c.getName();
		return "";
	}
}
