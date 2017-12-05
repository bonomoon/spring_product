package kr.ac.jbnu.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
	public Board findBoardById(int board_id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Board where id=:id");
		query.setInteger("id", board_id);

		Board board = (Board) query.uniqueResult();
		return board;
	}

	@Override
	@Transactional
	public void updateBoardHits(Board board) {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("update Board set hits = :board_hits" + " where id = :board_id");

		int hit = Integer.parseInt(board.getHits()) + 1;

		query.setParameter("board_hits", String.valueOf(hit));
		query.setParameter("board_id", board.getId());

		@SuppressWarnings("unused")
		int result = query.executeUpdate();
	}

	@Override
	@Transactional
	public void insertBoard(Board board) {
		Session session;

		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

		session.save(board);

	}

}
