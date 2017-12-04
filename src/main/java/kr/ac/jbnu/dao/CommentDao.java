package kr.ac.jbnu.dao;

import java.util.List;

import kr.ac.jbnu.model.Comment;

public interface CommentDao {
	public List<Comment> findCommentsById(int board_id);
	public void insertComment(Comment comment);
}
