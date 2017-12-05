package kr.ac.jbnu;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.MultipartFile;

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
	public String detail(@RequestParam("product") String productCode, Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("detailView!! productCode : " + productCode, locale);

		HttpSession session = request.getSession();
		UserAccount loginedUser = MyUtils.getLoginedUser(session);

		if (loginedUser == null) {
			model.addAttribute("sessionflag", false);
		} else {
			model.addAttribute("sessionflag", true);
		}

		String errorString = null;

		Product product = productDao.findProduct(productCode);
		model.addAttribute("product", product);

		return "detailView";
	}

	@RequestMapping(value = "/createProduct", method = RequestMethod.GET)
	public String createProductGet(Model model, HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		UserAccount loginedUser = MyUtils.getLoginedUser(session);

		if (loginedUser == null) {
			return "redirect:/user_register";
		}
		return "createProductView";
	}

	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	public String createProductPost(Model model, @RequestParam("name") String p_name,
			@RequestParam("price") String price, @RequestParam("brand") String brand, @RequestParam("cpu") String cpu,
			@RequestParam("graphiccard") String graphicCard, @RequestParam("file") MultipartFile file,
			@RequestParam("description") String description, HttpServletRequest request, HttpServletResponse response) {

		String UPLOAD_DIRECTORY = request.getSession().getServletContext().getRealPath("/resources/img");

		String image = null;

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Create the file on server
				File serverFile = new File(UPLOAD_DIRECTORY + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

				String name = file.getOriginalFilename();
				image = "resources/img/" + name;

				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());
			} catch (Exception e) {
			}
		} else {
			System.out.println("빈 파일");
		}

		Product product = new Product(p_name, price, brand, cpu, graphicCard, description, image);

		productDao.insertProduct(product);

		return "redirect:/home";
	}
}
