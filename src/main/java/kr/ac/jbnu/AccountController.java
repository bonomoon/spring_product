package kr.ac.jbnu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value = "/user_register", method = RequestMethod.GET)
	public String registerGet(Locale locale, Model model) {
		logger.info("userRegisterView", locale);
		
		return "userRegisterView";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void isLoggedin(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("LoginView", locale);
		
		HttpSession session = request.getSession();
//		UserAccount loginedUser = MyUtils.getLoginedUser(session);
		
		try {
			// Not logged in
			if (true) {//loginedUser == null) {
				response.getWriter().write("not logged in");
				return;
			}
			response.getWriter().write("logged in");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		return;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public void loginPost(Locale locale, Model model,
			@RequestBody String email,
			@RequestBody String password,
			@RequestBody String rememberMeStr,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("loginPost!!", locale);
		
		boolean remember = "Y".equals(rememberMeStr);
		
//		UserAccount user = null;
		boolean hasError = false;
		String errorString = null;

		if (email == null || password == null || email.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Required username and password!";
		} else {
//			Connection conn = MyUtils.getStoredConnection(request);
//			
//			try {
//				//블락당한 계정인지 확인
//				if(DBUtils.isBlockedUser(conn, email)) {
//					errorString = "정지당한 계정입니다.";
//					response.setContentType("text/plain");
//					response.getWriter().write("isBlocked");
//					System.out.println("??");
//					return;
//				}
//				
//				// Find the user in the DB.
//				user = DBUtils.findUser(conn, email, password);
//
//				if (user == null) {
//					hasError = true;
//					errorString = "User Name or password invalid";
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//				hasError = true;
//				errorString = e.getMessage();
//			}
		}
		// If error, forward to /WEB-INF/views/login.jsp
		if (hasError) {
			System.out.println("??????");
			System.out.println(errorString);
			try {
				notFoundHandler(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// If no error
		// Store user information in Session
		// And redirect to userInfo page.
		else {
//			System.out.println("로그인 성공");
//			HttpSession session = request.getSession();
//			MyUtils.storeLoginedUser(session, user);
//			
//			// If user checked "Remember me".
//			if (remember) {
//				MyUtils.storeUserCookie(response, user);
//			}
//			// Else delete cookie.
//			else {
//				MyUtils.deleteUserCookie(response);
//			}
//			
//			response.setContentType("text/plain");
//
//			if(user.isAdmin()) {
//				response.getWriter().write("isAdmin");
////				response.sendRedirect(request.getContextPath() + "/admin_home");
//				//가고싶은 페이지로 Redirect 하면 됨
//				return;
//			} else {
////				response.sendRedirect(request.getContextPath() + "/home");
//				response.getWriter().write("isNotAdmin");
//				return;
//			}
//
//			// Redirect to userInfo page.
////			RequestDispatcher dispatcher 
////			= this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
//////
		}
	}
	
	private void notFoundHandler(HttpServletResponse response) throws IOException {
		response.setStatus(response.SC_NOT_FOUND);
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("못찾겠당");
	}
}
