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
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam("product") String productCode, Locale locale, Model model, 
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("detailView!! productCode : " + productCode, locale);
		
//		Connection conn = MyUtils.getStoredConnection(request);
//		String productCode = request.getParameter("product");
//		
//		HttpSession session = request.getSession();
//		UserAccount loginedUser = MyUtils.getLoginedUser(session);
//		
//		if(loginedUser == null) {
//			request.setAttribute("sessionflag", false);
//		} else {
//			request.setAttribute("sessionflag", true);
//		}
//		
//		String errorString = null;
//		Product product = null;
//		try {
//			product = DBUtils.findProduct(conn, productCode);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			errorString = e.getMessage();
//		}
//		
//		request.setAttribute("product", product);
//		
//		RequestDispatcher dispatcher = request.getServletContext()
//                .getRequestDispatcher("/WEB-INF/views/detailView.jsp");
//        dispatcher.forward(request, response);
		
		return "detailView";
	}
	
}
