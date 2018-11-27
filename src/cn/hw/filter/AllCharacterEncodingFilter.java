package cn.hw.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class AllCharacterEncodingFilter implements Filter {
	private FilterConfig filterConfig;
	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		//获取配置的过滤器参数encoding的值，如果没有，给定一个默认值
		String encoding = filterConfig.getInitParameter("encoding");
		if(encoding==null)
			encoding = "UTF-8";
		//设置post请求方式的中文请求参数的编码
		request.setCharacterEncoding(encoding);//字节流输出时通知客户端的解码码表
		//设置响应输出时的编码，字符流和字节流
		response.setContentType("text/html;charset="+encoding);
		MyHttpservletRequest mrequest = new MyHttpservletRequest(request);
		//放行
		chain.doFilter(mrequest,response);
	}

	public void destroy() {
	}
}

class MyHttpservletRequest extends HttpServletRequestWrapper{
	public MyHttpservletRequest(HttpServletRequest request){
		super(request);//构造函数建立MyHttpservletRequest对象
	}

	//只对get请求方式进行改写
	@Override
	public String getParameter(String name){
		String value  = super.getParameter(name);
		if(value==null)
			return value;
		String method = super.getMethod();//得到请求方式（是get还是post）
		if("get".equalsIgnoreCase(method))//忽略大小写
			try{
				value = new String(value.getBytes("ISO-8859-1"),super.getCharacterEncoding());//参数转变编码
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
		return value;//重新返回
	}
}
