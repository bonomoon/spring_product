package kr.ac.jbnu;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.jbnu.dao.ProductDao;
import kr.ac.jbnu.model.Product;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@Autowired 
	private ProductDao productDao;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, 
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		List<Product> list = productDao.list();
		model.addAttribute("productList", list);
//		Connection conn = MyUtils.getStoredConnection(request);
//		
//		String keyword = null;
//		String minprice = null;
//		String maxprice = null;
//		String brand = null;
//		String cpu = null;
//		String graphic = null;
//
//		if (request.getParameterMap().containsKey("keyword")) {
//            keyword = request.getParameter("keyword");
//        }
//		if(request.getParameterMap().containsKey("minprice")) {
//        	minprice = request.getParameter("minprice");
//        }
//		if(request.getParameterMap().containsKey("maxprice")) {
//        	maxprice = request.getParameter("maxprice");
//        }
//		if(request.getParameterMap().containsKey("brand")) {
//        	brand = request.getParameter("brand");
//        }
//		if(request.getParameterMap().containsKey("cpu")) {
//        	cpu = request.getParameter("cpu");
//        }
//		if(request.getParameterMap().containsKey("graphic")) {
//        	graphic = request.getParameter("graphic");
//        }
//
//		String errorString = null;
//		List<Product> list = null;
//		try {
//			if(keyword != null)
//				list = DBUtils.querySearchedProduct(conn, keyword);
//			else if(minprice != null)
//				list = DBUtils.queryPriceSearchedProduct(conn, minprice, maxprice);
//			else if(brand != null)
//				list = DBUtils.queryBrandSearchedProduct(conn, brand);
//			else if(cpu != null)
//				list = DBUtils.queryCpuSearchedProduct(conn, cpu);
//			else if(graphic != null)
//				list = DBUtils.queryGraphicSearchedProduct(conn, graphic);
//			else
//				list = DBUtils.queryProduct(conn);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			errorString = e.getMessage();
//		}
//		
//		request.setAttribute("errorString", errorString);
//		request.setAttribute("productList", list);
		
		return "homeView";
	}
	
}
