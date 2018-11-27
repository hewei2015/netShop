package cn.hw.web.control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.hw.domain.Book;
import cn.hw.domain.Category;
import cn.hw.domain.Orders;
import cn.hw.service.BusinessService;
import cn.hw.service.impl.BusinessServiceImpl;
import cn.hw.util.Page;
import cn.hw.util.WebUtil;

public class ManageServlet extends HttpServlet {
	private BusinessService  bs = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addCategory".equals(operation))
			addCategory(request,response);
		if("listCategory".equals(operation))
			listCategory(request,response);
		if("showCategoryUI".equals(operation))
			showCategoryUI(request,response);
		if("addBook".equals(operation))
			addBook(request,response);
		if("showAllBook".equals(operation))
			showAllBook(request,response);
		if("showAllOrders0".equals(operation)){
			showAllOrders0(request,response);
		}
		if("showAllOrders1".equals(operation)){
			showAllOrders1(request,response);
		}
		if("showOrdersDetail".equals(operation)){
			showOrdersDetail(request,response);
		}
		if("sureSend".equals(operation)){
			sureSend(request,response);
		}
	}
	private void sureSend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ordersId = request.getParameter("ordersId");
		bs.sureOrders(ordersId);
		request.setAttribute("message","成功发货");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	private void showOrdersDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//哪个用户的也要查询出来
		String ordersId = request.getParameter("ordersId");
		Orders o = bs.findOrdersById(ordersId);
		request.setAttribute("o", o);
		request.getRequestDispatcher("/manage/ordersMsg.jsp").forward(request, response);
	}
	private void showAllOrders1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Orders> os = bs.findOrdersByState(1);
		request.setAttribute("os", os);
		request.getRequestDispatcher("/manage/showOrders.jsp").forward(request, response);
	}
	//显示所有为发货的订单
	private void showAllOrders0(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Orders> os = bs.findOrdersByState(0);
		request.setAttribute("os", os);
		request.getRequestDispatcher("/manage/showOrders.jsp").forward(request, response);
	}
	//后台查询所有图书分页
	private void showAllBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = bs.findPageRecords(pagenum);
		page.setUrl("manageServlet?operation=showAllBook");//第二种分页方式传递到jsp页面的数据
		request.setAttribute("page",page);
		request.getRequestDispatcher("/manage/listBook.jsp").forward(request, response);
	}
	//添加书籍到数据库：jsp页面传过来的是mutipart类型
	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String resultPath = "";
		String storePath = getServletContext().getRealPath("/files");
		try {
			Book book = new Book();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);//获得待解析数据
			//解析数据
			for(FileItem item:items){
				if(item.isFormField()){
					//封装数据到javabean中
					String filedName = item.getFieldName();//字段名，即javabean的属性名，除了图片
					String filedValue = item.getString(request.getCharacterEncoding());//保证得到的字符串是UTF-8格式
					BeanUtils.setProperty(book,filedName,filedValue);//到这一步，除了图片路径，其它数据都有了
				}else{
					//处理文件上传
					InputStream in = item.getInputStream();
					String fileName = item.getName();//用户上传文件路径名
					//从用户上传的文件路径名中截取文件名。然后在加随机码，以防止用户上传的文件名相同发生冲突
					fileName = UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("\\")+1);
					//设置存储的图片文件名
					book.setImage(fileName);
					OutputStream out = new FileOutputStream(storePath+"\\"+fileName);
					byte b[] = new byte[1024];
					int len = -1;
					while((len=in.read(b))!=-1){
						out.write(b,0,len);
					}
					out.close();
					in.close();
					item.delete();//★删除临时文件
				}
			}
			//System.out.println(book);
			bs.addBook(book);//★★先测试，然后看方法对不对，在写service和dao层中写该方法  
			List<Category> cs = bs.findAllCategory();
			request.setAttribute("cs", cs);//如果没有这两句，添加完成后添加页面将不会显示图书分类
			resultPath = "/manage/addBook.jsp";
			request.setAttribute("message","<script type='text/javascript'>alert('添加成功')</script>");
		} catch (Exception e) {
			e.printStackTrace();
			resultPath="/message.jsp";
			//TODO
			request.setAttribute("message", "上传出问题了");
		}
		request.getRequestDispatcher(resultPath).forward(request, response);
	}

	private void showCategoryUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> cs = bs.findAllCategory();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/addBook.jsp").forward(request, response);
	}

	private void listCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> cs = bs.findAllCategory();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/listCategory.jsp").forward(request, response);	
	}

	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Category ca = WebUtil.fillBean(request, Category.class);
		bs.addCategory(ca);
		request.setAttribute("message", "<script type='text/javascript'>alert('添加成功')</script>");
		request.getRequestDispatcher("/manage/addCategory.jsp").forward(request, response);		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		this.doGet(request, response);

	}

}
