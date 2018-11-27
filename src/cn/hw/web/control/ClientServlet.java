package cn.hw.web.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hw.domain.Book;
import cn.hw.domain.Cart;
import cn.hw.domain.CartItem;
import cn.hw.domain.Category;
import cn.hw.domain.Orders;
import cn.hw.domain.OrdersItem;
import cn.hw.domain.User;
import cn.hw.service.BusinessService;
import cn.hw.service.impl.BusinessServiceImpl;
import cn.hw.util.Page;
import cn.hw.util.WebUtil;

public class ClientServlet extends HttpServlet {
	private BusinessService bs = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("showIndexCategory".equals(operation)){
			showIndexCategory(request,response);
		}
		if("showCategoryBook".equals(operation)){
			showCategoryBook(request,response);
		}
		if("buyBook".equals(operation)){
			buyBook(request,response);
		}
		if("genOrders".equals(operation)){
			genOrders(request,response);
		}
		if("regist".equals(operation)){
			regist(request,response);
		}
		if("login".equals(operation)){
			login(request,response);
		}
		if("logout".equals(operation)){
			logout(request,response);
		}
		if("showUsersOrders".equals(operation)){
			showUsersOrders(request,response);
		}
		if("showOrdersDetail".equals(operation)){
			showOrdersDetail(request,response);
		}
	}
	private void showOrdersDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.判断用户是否登录。1.1没有登录，转向登录页面
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			request.setAttribute("message", "请先登录！2秒后将自动转向登录页面。<meta http-equiv='Refresh' content='2;" +
					"URL="+request.getContextPath()+"/client/login.jsp'/>");
			request.getRequestDispatcher("/client/message.jsp").forward(request, response);
			return ;
		}
		String ordersId=request.getParameter("ordersId");
		Orders o = bs.findOrdersById(ordersId);//★★查询的是订单明细，与通过用户id查订单是不同的，明细中还要查出书的信息
		request.setAttribute("o", o);
		request.getRequestDispatcher("/client/ordersMsg.jsp").forward(request, response);
	}
	//依据用户id查询用户订单
	private void showUsersOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.判断用户是否登录。1.1没有登录，转向登录页面
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			request.setAttribute("message", "请先登录！2秒后将自动转向登录页面。<meta http-equiv='Refresh' content='2;" +
					"URL="+request.getContextPath()+"/client/login.jsp'/>");
			request.getRequestDispatcher("/client/message.jsp").forward(request, response);
			return ;
		}
		List<Orders> os = bs.findOrdersByUserId(user.getId());
		request.getSession().setAttribute("os", os);
		request.getRequestDispatcher("client/listOrders.jsp").forward(request,response);//转向主页
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("/client/welcome.jsp").forward(request, response);
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = bs.login(username,password);
		if(user==null){
			request.setAttribute("message", "用户名或密码错误！");
			request.getRequestDispatcher("/client/message.jsp").forward(request, response);
		}else{
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath());//转向主页
		}
	}
	private void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = WebUtil.fillBean(request, User.class);
		bs.regist(user);
		request.setAttribute("message", "注册成功");
		request.getRequestDispatcher("/client/message.jsp").forward(request, response);
	}
	//生成订单，把订单和订单项的信息存到数据库中
	private void genOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.判断用户是否登录。1.1没有登录，转向登录页面
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			request.setAttribute("message", "请先登录！2秒后将自动转向登录页面。<meta http-equiv='Refresh' content='2;" +
					"URL="+request.getContextPath()+"/client/login.jsp'/>");
			request.getRequestDispatcher("/client/message.jsp").forward(request, response);
			return ;
		}
		//2.如果已经登录，则将购物车里面的东西按规则生成订单信息
		//取出购物车：Cart Map<String,CartItem> 
		Cart cart  = (Cart)session.getAttribute("cart");//购物车<->订单
		Orders orders = new Orders();
		orders.setNum(cart.getNum());
		orders.setPrice(cart.getPrice());
		//★▲弄购物项
		List<OrdersItem> ordersItems = new ArrayList<OrdersItem>();//订单项<->订单项
		for(Map.Entry<String, CartItem> item:cart.getItems().entrySet()){//★▲▲填充模型
			CartItem i = item.getValue();//得到购物项
			OrdersItem orderItem = new OrdersItem();
			orderItem.setNum(i.getNum());
			orderItem.setPrice(i.getPrice());
			orderItem.setBook(i.getBook());
			ordersItems.add(orderItem);
		}
		orders.setItems(ordersItems);
		bs.addOrders(orders,user);
		request.setAttribute("message","订单生成成功！");
		request.getRequestDispatcher("/client/message.jsp").forward(request, response);
	}

	private void buyBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//购买书的id
		String bookId = request.getParameter("bookId");
		//获取要购买的书
		Book book = bs.findBookById(bookId);
		HttpSession session = request.getSession();
		//★▲从HttpSession中取出购物车
		Cart cart = (Cart)session.getAttribute("cart");
		//>创建购物车（判断是否有购物车）并放到httpsession中（购物车）
		if(cart==null){
			cart = new Cart();
			session.setAttribute("cart",cart);
		}
		//把书放到购物车中
		cart.addBook(book);
		//提示购买成功
		request.setAttribute("message", "购买成功");
		//页面转发
		request.getRequestDispatcher("/client/message.jsp").forward(request, response);
	}
	//按照分类进行分页书籍查询
	private void showCategoryBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		String categoryId = request.getParameter("categoryId");//★1.相对于下面那个方法再添加的
		Page page = bs.findPageRecords(pagenum,categoryId);//★2.相对于下面那个方法再添加的
		page.setUrl("/clientServlet?operation=showCategoryBook&categoryId="+categoryId);//★3.相对于下面那个方法再添加的
		request.setAttribute("page", page);
		request.getRequestDispatcher("/client/welcome.jsp").forward(request,response);
	}
	//查询所有分类，封装后，便于前端主页的显示
	private void showIndexCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> cs = bs.findAllCategory();
		request.getSession().setAttribute("cs", cs);//★★用session放大范围以保持header.jsp部分的数据维护
		//查询所有书籍,还要分页
		String pagenum = request.getParameter("pagenum");
		Page page = bs.findPageRecords(pagenum);
		page.setUrl("/clientServlet?operation=showIndexCategory");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/client/welcome.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		this.doGet(request, response);
	}

}
