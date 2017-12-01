package kr.ac.jbnu;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BasketController {
	
	private static final Logger logger = LoggerFactory.getLogger(BasketController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public String basket(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("BasketView!", locale);
		
//		Connection conn = MyUtils.getStoredConnection(request);
//		
//		HttpSession session = request.getSession();
//		UserAccount loginedUser = MyUtils.getLoginedUser(session);
//		
//		if(loginedUser == null) {
//			return "/user_register";
//		} else {
//			String errorString = null;
//			List<Product> list = null;
//			try {
//				list = DBUtils.queryCart(conn, loginedUser.getId());
//			} catch (SQLException e) {
//				e.printStackTrace();
//				errorString = e.getMessage();
//			}
//			
//			request.setAttribute("errorString", errorString);
//			request.setAttribute("cartList", list);
//			
//			return "basketView";
//		}
		
		return "basketView";
	}
	
	@RequestMapping(value = "/deleteCartProduct", method = RequestMethod.GET)
	public String deleteCart(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("deleteCart", locale);
//		Connection conn = MyUtils.getStoredConnection(request);
//		String productCode = request.getParameter("product");
//		
//		HttpSession session = request.getSession();
//		UserAccount loginedUser = MyUtils.getLoginedUser(session);
//		
//		String errorString = null;
//		try {
//			DBUtils.deleteCart(conn, loginedUser.getId(), productCode);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			errorString = e.getMessage();
//		}
		
		return "redirect:/basket";
	}
	
	@RequestMapping(value = "/insertCartProduct", method = RequestMethod.POST)
	public void insertCart(@RequestParam("product") String productCode, Locale locale, Model modle) {
		logger.info("insertCart!! " + productCode, locale);
//		Connection conn = MyUtils.getStoredConnection(request);
//		String productCode = request.getParameter("product");
//		
//		HttpSession session = request.getSession();
//		UserAccount loginedUser = MyUtils.getLoginedUser(session);
//		
//		String errorString = null;
//		List<Product> list = null;
//		try {
//			DBUtils.insertCart(conn, loginedUser.getId(), productCode);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			errorString = e.getMessage();
//		}
	}
	
}
