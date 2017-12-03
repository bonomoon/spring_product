package kr.ac.jbnu.dao;

import java.util.List;

import kr.ac.jbnu.model.Comment;

public interface CommentDao {
	public List<Comment> findCommentById(String board_id);
	public void addComment(Comment comment);
}
