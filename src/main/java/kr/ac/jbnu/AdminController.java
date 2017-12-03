package kr.ac.jbnu;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AdminController  {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/adminhome"}, method = RequestMethod.GET)
	public String adminHome(Locale locale, Model model) {
		logger.info("adminHomeView", locale);
		
		return "admin/homeView";
	}
	
	@RequestMapping(value = {"/", "/user_manage"}, method = RequestMethod.GET)	
	public String userManage(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("userManageView", locale);
		
		return "admin/userManageView";
	}
	
	@RequestMapping(value = {"/", "/user_manage"}, method = RequestMethod.GET)		
	public String blockUser(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("userManageView", locale);
	
		return "admin/userManageView";
	}
	
	
}
