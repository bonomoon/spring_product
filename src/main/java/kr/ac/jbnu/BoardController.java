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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("boardView!!!", locale);

		return "boardView";
	}

	@RequestMapping(value = "/board_edit", method = RequestMethod.GET)
	public String boardEditGet(Locale locale, Model model) {
		logger.info("boardEdit!!!", locale);

		// HttpSession session = request.getSession();
		// UserAccount loginedUser = MyUtils.getLoginedUser(session);
		//
		// if(loginedUser == null) {
		// response.sendRedirect(request.getContextPath() + "/user_register");
		// return;
		// }
		//
		// RequestDispatcher dispatcher =
		// this.getServletContext().getRequestDispatcher("/WEB-INF/views/boardEditView.jsp");
		// dispatcher.forward(request, response);
		//

		return "boardEditView";
	}

	@RequestMapping(value = "/board_edit", method = RequestMethod.POST)
	@ResponseBody
	public String boardEditPost(Locale locale, Model model, @RequestBody String title, @RequestBody String data,
			@RequestBody String contents, HttpServletRequest request, HttpServletResponse response) {
		logger.info("boardEdit!!!", locale);
		// Connection conn = MyUtils.getStoredConnection(request);
		//
		// String errorString = null;
		//
		// //세션에서 내 이름을 가져와 글쓴이로 활용.
		// HttpSession session = request.getSession();
		// UserAccount myInfo = MyUtils.getLoginedUser(session);
		//
		// if(myInfo == null) {
		// errorString = "세션에서 사용자 정보를 불러오지 못했습니다.";
		// response.setStatus(response.SC_NOT_FOUND);
		// System.out.println(errorString);
		// return;
		// }
		//
		// String title = request.getParameter("title");
		// String date = request.getParameter("date");
		// String contents = request.getParameter("contents");
		// String author = myInfo.getUserName();
		//
		// if(title == null || date == null || contents == null || author == null) {
		// errorString = "null인 정보가 있습니다.";
		// response.setStatus(response.SC_BAD_REQUEST);
		// System.out.println(errorString);
		// return;
		// }
		//
		// Board board = new Board();
		// board.setTitle(title);
		// board.setDate(date);
		// board.setContents(contents);
		// board.setAuthor(author);
		//
		// try {
		// DBUtils.addBoard(conn, board);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// errorString = "DB 저장 실패!!";
		// response.setStatus(response.SC_NOT_FOUND);
		// System.out.println(errorString);
		// return;
		// }
		//
		return "boardEditView";
	}

	@RequestMapping(value = "/board_detail", method = RequestMethod.GET)
	@ResponseBody
	public String boardDetailGet(@RequestParam("board_id") String board_id) {
		logger.info("boardDetailView!!!");

		// Connection conn = MyUtils.getStoredConnection(request);
		//
		// String board_id = request.getParameter("board_id");
		// String errorString = null;
		// Board board = null;
		// List<Comment> commentList = null;
		//
		// try {
		// board = DBUtils.findBoardById(conn, board_id);
		//
		// if(board == null) {
		// errorString = "해당 게시물을 찾을 수 없습니다.";
		// response.setStatus(response.SC_NOT_FOUND);
		// return;
		// }
		//
		// //조회수 1증가
		// DBUtils.updateBoardHits(conn, board);
		//
		// //해당 게시글의 댓글들 가져오기
		// commentList = DBUtils.findCommentById(conn, board_id);
		//
		//
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// request.setAttribute("board", board);
		// if(commentList.size() != 0) {
		// request.setAttribute("commentList", commentList);
		// }
		//
		// RequestDispatcher dispatcher = this.getServletContext()
		// .getRequestDispatcher("/WEB-INF/views/boardDetailView.jsp");
		// dispatcher.forward(request, response);
		//
		
//		return "method9 with board_id= "+board_id;
		return "boardDetailView";
	}

}
