package kr.co.koreait.Interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.koreait.beans.ContentBean;
import kr.co.koreait.service.TopMenuService;


public class TopMenuInterceptor implements HandlerInterceptor {
	
	@SuppressWarnings("unused")
	@Autowired
	private TopMenuService  TopMenuService;
	public TopMenuInterceptor (TopMenuService TopMenuService) {
		
		this.TopMenuService = TopMenuService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		public List<ContentBean>getTopMenuList() {
			
			List<ContentBean> topmenuList = TopMenuService.getTopMenuList();
			request.setAttribute("topmenuList", topmenuList);
			
		return true;
		
		}
	}
	
}
