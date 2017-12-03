package kr.ac.jbnu.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import kr.ac.jbnu.model.Board;

public class BoardDaoImpl implements BoardDao {
	private SessionFactory sessionFactory;

	public BoardDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Board> queryBoard() {
		@SuppressWarnings("unchecked")
		List<Board> list = (List<Board>) sessionFactory.getCurrentSession().createCriteria(Board.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

	@Override
	@Transactional
	public Board findBoardById(String board_id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from board where board_id=:id");
		query.setParameter("id", board_id);

		Board board = (Board) query.uniqueResult();
		return board;
	}

	@Override
	@Transactional
	public void updateBoardHits(Board board) {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("update board set board_hits = :board_hits" + " where board_id = :board_id");

		int hit = Integer.parseInt(board.getHits()) + 1;

		query.setParameter("board_hits", String.valueOf(hit));
		query.setParameter("board_id", board.getId());
		
		@SuppressWarnings("unused")
		int result = query.executeUpdate();
	}

	@Override
	@Transactional
	public void addBoard(Board board) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(board);
		session.getTransaction().commit();
	}

}
