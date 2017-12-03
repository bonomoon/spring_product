package kr.ac.jbnu;

import java.sql.Connection;
import java.util.List;
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

import kr.ac.jbnu.dao.CartDao;
import kr.ac.jbnu.model.Product;
import kr.ac.jbnu.model.UserAccount;
import kr.ac.jbnu.util.MyUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BasketController {
	
	private static final Logger logger = LoggerFactory.getLogger(BasketController.class);
	
	@Autowired 
	private CartDao cartDao;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public String basket(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("BasketView!", locale);
				
		HttpSession session = request.getSession();
		UserAccount loginedUser = MyUtils.getLoginedUser(session);
		
		if(loginedUser == null) {
			return "redirect:/user_register";
		} else {
			String errorString = null;
			List<Product> productList = null;
			List<Integer> cartidList = null;
			productList = cartDao.queryCart(loginedUser.getId());
			cartidList = cartDao.queryCartId(loginedUser.getId());
			
			model.addAttribute("errorString", errorString);
			model.addAttribute("cartList", productList);
			model.addAttribute("cartidList", cartidList);
			
			return "basketView";
		}
	}
	
	@RequestMapping(value = "/deleteCartProduct", method = RequestMethod.GET)
	public String deleteCart(@RequestParam("cartid") String cartid,
			Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("deleteCart", locale);		
		
		cartDao.deleteCart(cartid);
		
		return "redirect:/basket";
	}
	
	@RequestMapping(value = "/insertCartProduct", method = RequestMethod.POST)
	public void insertCart(@RequestParam("product") String productCode,
			Locale locale, Model modle, HttpServletRequest request) {
		logger.info("insertCart!! " + productCode, locale);
		
		HttpSession session = request.getSession();
		UserAccount loginedUser = MyUtils.getLoginedUser(session);
		
		cartDao.insertCart(loginedUser.getId(), productCode);
	}
	
}
