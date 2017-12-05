package kr.ac.jbnu;

import java.io.IOException;

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

import kr.ac.jbnu.dao.CommentDao;
import kr.ac.jbnu.model.Comment;
import kr.ac.jbnu.model.UserAccount;
import kr.ac.jbnu.util.MyUtils;

@Controller
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentDao commentDao;

	@RequestMapping(value = "/create_comment", method = RequestMethod.POST)
	public void addComment(Model model, @RequestParam("date") String date, @RequestParam("contents") String contents,
			@RequestParam("board_id") String board_id, HttpServletRequest request, HttpServletResponse response) {
		logger.info("CommentCreate!!!");

		HttpSession session = request.getSession();
		UserAccount loginedUser = MyUtils.getLoginedUser(session);

		String errorString = null;

		if (loginedUser == null) {
			errorString = "댓글을 남기려면 로그인하세요.";
			System.out.println(errorString);
			try {
				response.getWriter().write("not logged in");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		String author = loginedUser.getUserName();

		System.out.println(
				"date : " + date + " contents : " + contents + " board_id : " + board_id + " author : " + author);

		Comment comment = new Comment();
		comment.setAuthor(author);
		comment.setDate(date);
		comment.setBoard_id(board_id);
		comment.setContents(contents);

		commentDao.insertComment(comment);

		model.addAttribute("board_id", board_id);

		try {
			response.getWriter().write("logged in");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
