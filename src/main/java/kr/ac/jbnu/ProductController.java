package kr.ac.jbnu;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.ac.jbnu.dao.ProductDao;
import kr.ac.jbnu.model.Product;
import kr.ac.jbnu.model.UserAccount;
import kr.ac.jbnu.util.MyUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("userAccount")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired 
	private ProductDao productDao;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam("product") String productCode,
			Locale locale, Model model, 
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("detailView!! productCode : " + productCode, locale);
				
		HttpSession session = request.getSession();
		UserAccount loginedUser = MyUtils.getLoginedUser(session);
		
		if(loginedUser == null) {
			model.addAttribute("sessionflag", false);
		} else {
			model.addAttribute("sessionflag", true);
		}
		
		Product product = productDao.findProduct(productCode);
		model.addAttribute("product", product);
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
	
//	@ModelAttribute("userAccount")
//	public UserAccount getUserAccount() {
//		
//	}
	
}
