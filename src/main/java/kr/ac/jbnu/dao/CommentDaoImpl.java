package kr.ac.jbnu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import kr.ac.jbnu.model.Comment;

public class CommentDaoImpl implements CommentDao{
	private SessionFactory sessionFactory;
	
	public CommentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Comment> findCommentById(String board_id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from comment where board_id=:id");
		query.setParameter("id", board_id);

		@SuppressWarnings("unchecked")
		List<Comment> list = (List<Comment>) query.list();
		return list;
	}

	@Override
	public void addComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(comment);
		session.getTransaction().commit();		
	}

}
