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
		
		if(loginedUser == null) {
			model.addAttribute("sessionflag", false);
		} else {
			model.addAttribute("sessionflag", true);
		}
		

		// Connection conn = MyUtils.getStoredConnection(request);
		// String productCode = request.getParameter("product");
		//
		// HttpSession session = request.getSession();
		// UserAccount loginedUser = MyUtils.getLoginedUser(session);
		//
		// if(loginedUser == null) {
		// request.setAttribute("sessionflag", false);
		// } else {
		// request.setAttribute("sessionflag", true);
		// }
		//
		String errorString = null;

		Product product = productDao.findProduct(productCode);
		model.addAttribute("product", product);
		// try {
		// product = DBUtils.findProduct(conn, productCode);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// errorString = e.getMessage();
		// }
		//
		// request.setAttribute("product", product);
		//
		// RequestDispatcher dispatcher = request.getServletContext()
		// .getRequestDispatcher("/WEB-INF/views/detailView.jsp");
		// dispatcher.forward(request, response);

		return "detailView";
	}

	@RequestMapping(value = "/createProduct", method = RequestMethod.GET)
	public String createProductGet(Model model) {
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

//				// Creating the directory to store file
//				String rootPath = System.getProperty("catalina.home");
//				File dir = new File(rootPath + File.separator + "tmpFiles");
//				if (!dir.exists())
//					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(UPLOAD_DIRECTORY + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				
				String name = file.getOriginalFilename();
				image = "resources/img/" + name;
				 
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());
//				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
//				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			System.out.println("빈 파일");
//			return "You failed to upload " + name + " because the file was empty.";
		}
		// }

		// if (isMultipart) {
		// System.out.println("A");
		//
		// // Create a factory for disk-based file items
		// FileItemFactory factory = new DiskFileItemFactory();
		// // Create a new file upload handler
		// ServletFileUpload upload = new ServletFileUpload(factory);
		// try {
		// // Parse the request
		// List<FileItem> multiparts = upload.parseRequest(request);
		// for (FileItem item : multiparts) {
		// if (!item.isFormField()) {
		// String name = new File(item.getName()).getName();
		// System.out.println("name" + name);
		// item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
		// // image = UPLOAD_DIRECTORY + File.separator + name;
		// image = "resources/img/" + name;
		// // System.out.println(image);
		// } else {
		// // String fieldName = item.getFieldName();
		// // String fieldValue = item.getString();
		// //
		// // if (fieldName.equals("name")) {
		// // p_name = new String(fieldValue.getBytes("iso-8859-1"), "UTF-8");
		// // } else if (fieldName.equals("price")) {
		// // price = new String(fieldValue.getBytes("iso-8859-1"), "UTF-8");
		// // } else if (fieldName.equals("brand")) {
		// // brand = new String(fieldValue.getBytes("iso-8859-1"), "UTF-8");
		// // } else if (fieldName.equals("cpu")) {
		// // cpu = new String(fieldValue.getBytes("iso-8859-1"), "UTF-8");
		// // } else if (fieldName.equals("graphiccard")) {
		// // graphicCard = new String(fieldValue.getBytes("iso-8859-1"), "UTF-8");
		// // } else if (fieldName.equals("description")) {
		// // description = new String(fieldValue.getBytes("iso-8859-1"), "UTF-8");
		// // }
		// }
		// }
		// // File uploaded successfully
		// model.addAttribute("message", "Your file has been uploaded!");
		// } catch (Exception e) {
		// model.addAttribute("message", "File Upload Failed due to " + e);
		// }
		// } else {
		// model.addAttribute("message", "This Servlet only handles file upload
		// request");
		// }

		Product product = new Product(p_name, price, brand, cpu, graphicCard, description, image);

		productDao.insertProduct(product);

		return "createProductView";
	}
}
