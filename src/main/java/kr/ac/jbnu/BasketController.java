package kr.ac.jbnu;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String basket(Locale locale, Model model) {
		logger.info("BasketView!", locale);
		
		return "basketView";
	}
	
}
