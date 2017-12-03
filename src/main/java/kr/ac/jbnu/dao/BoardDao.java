package kr.ac.jbnu.dao;

import java.util.List;

import kr.ac.jbnu.model.Board;

public interface BoardDao {
	public List<Board> queryBoard();
	public Board findBoardById(String board_id);
	public void updateBoardHits(Board board);
	public void addBoard(Board board);
}
