package kr.ac.jbnu;

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
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.jbnu.dao.ProductDao;
import kr.ac.jbnu.dao.UserAccountDao;
import kr.ac.jbnu.model.Product;
import kr.ac.jbnu.model.UserAccount;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserAccountDao userAccountDao;

	@Autowired
	private ProductDao productDao;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/admin_home", method = RequestMethod.GET)
	public String adminHome(Locale locale, Model model) {
		logger.info("adminHomeView", locale);

		List<Product> list = productDao.queryProduct();
		model.addAttribute("productList", list);

		return "admin/homeView";
	}

	@RequestMapping(value = "/user_manage", method = RequestMethod.GET)
	public String userManage(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("userManageView", locale);

		List<UserAccount> list = userAccountDao.queryUserAccount();
		model.addAttribute("userlist", list);

		return "admin/userManageView";
	}

	@RequestMapping(value = "/blockUser", method = RequestMethod.GET)
	public String blockUser(Locale locale, Model model, @RequestParam("id") String userId) {
		logger.info("userManageView", locale);

		userAccountDao.blockUserAccount(userId);

		List<UserAccount> list = userAccountDao.queryUserAccount();
		model.addAttribute("userlist", list);

		return "admin/userManageView";
	}

	@RequestMapping(value = "/unblockUser", method = RequestMethod.GET)
	public String unblockUser(Locale locale, Model model, @RequestParam("id") String userId) {
		logger.info("userManageView", locale);

		userAccountDao.unblockUserAccount(userId);

		List<UserAccount> list = userAccountDao.queryUserAccount();
		model.addAttribute("userlist", list);

		return "admin/userManageView";
	}
}
