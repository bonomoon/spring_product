package kr.ac.jbnu;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.jbnu.dao.BoardDao;
import kr.ac.jbnu.dao.CommentDao;
import kr.ac.jbnu.model.Board;
import kr.ac.jbnu.model.Comment;
import kr.ac.jbnu.model.UserAccount;
import kr.ac.jbnu.util.MyUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private CommentDao commentDao;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("boardView!!!");

		String errorString = null;
		List<Board> list = null;

		list = boardDao.queryBoard();

		if (list.size() == 0) {
			errorString = "게시글이 없습니다.";
		}

		model.addAttribute("errorString", errorString);
		model.addAttribute("boardList", list);

		return "boardView";
	}

	@RequestMapping(value = "/board_edit", method = RequestMethod.GET)
	public String boardEditGet(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("boardEdit!!!");

		HttpSession session = request.getSession();
		UserAccount loginedUser = MyUtils.getLoginedUser(session);

		if (loginedUser == null) {
			return "redirect:/user_register";
		}

		return "boardEditView";
	}

	@RequestMapping(value = "/board_edit", method = RequestMethod.POST)
	@ResponseBody
	public void boardEditPost(Model model, @RequestParam String title, @RequestParam String date,
			@RequestParam String contents, HttpServletRequest request, HttpServletResponse response) {

		logger.info("boardEdit!!!");

		String errorString = null;

		// 세션에서 내 이름을 가져와 글쓴이로 활용.
		HttpSession session = request.getSession();
		UserAccount myInfo = MyUtils.getLoginedUser(session);

		if (myInfo == null) {
			errorString = "세션에서 사용자 정보를 불러오지 못했습니다.";
			response.setStatus(response.SC_NOT_FOUND);
			System.out.println(errorString);
			return;
		}

		String author = myInfo.getUserName();

		if (title == null || date == null || contents == null || author == null) {
			errorString = "null인 정보가 있습니다.";
			response.setStatus(response.SC_BAD_REQUEST);
			System.out.println(errorString);
			return;
		}

		Board board = new Board();
		board.setTitle(title);
		board.setDate(date);
		board.setContents(contents);
		board.setAuthor(author);
		board.setHits("0");
		// todo : hits를 숫자로 바꾸기

		boardDao.insertBoard(board);
	}

	@RequestMapping(value = "/board_detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardDetailGet(Model model, @RequestParam("board_id") int board_id, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("boardDetailView!!!");

		String errorString = null;
		Board board = null;
		List<Comment> commentList = null;

		board = boardDao.findBoardById(board_id);

		if (board == null) {
			errorString = "해당 게시물을 찾을 수 없습니다.";
			response.setStatus(response.SC_NOT_FOUND);
			// return;
		}

		// 조회수 1증가
		boardDao.updateBoardHits(board);

		// 해당 게시글의 댓글들 가져오기
		commentList = commentDao.findCommentsById(board_id);

		model.addAttribute("errorString", errorString);
		model.addAttribute("board", board);

		if (commentList.size() != 0) {
			model.addAttribute("commentList", commentList);
		}

		return "boardDetailView";
	}
}
