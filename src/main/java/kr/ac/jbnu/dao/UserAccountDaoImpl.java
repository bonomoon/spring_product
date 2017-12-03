package kr.ac.jbnu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.jbnu.model.Product;
import kr.ac.jbnu.model.UserAccount;

public class UserAccountDaoImpl implements UserAccountDao {
	private SessionFactory sessionFactory;

	public UserAccountDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public UserAccount findUser(String userEmail, String password) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from UserAccount as userAccount "
						+ "where userAccount.user_email=:user_email "
						+ "and userAccount.user_password=:user_password");
		query.setParameter("user_email", userEmail);
		query.setParameter("user_password", password);
		
		@SuppressWarnings("unchecked")
		List<UserAccount> list = (List<UserAccount>)query.list();
		
		return list.get(0);
	}

	@Override
	@Transactional
	public UserAccount findUser(String userName) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from UserAccount as userAccount "
						+ "where userAccount.user_name=:user_name");
		query.setParameter("user_name", userName);
		
		@SuppressWarnings("unchecked")
		List<UserAccount> list = (List<UserAccount>)query.list();
		
		return list.get(0);
	}

	@Override
	@Transactional
	public boolean isBlockedUser(String userEmail) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("select userAccount.Is_Blocked from UserAccount as userAccount"
						+ "where userAccount.user_email=:user_email");
		query.setParameter("user_email", userEmail);
		
		@SuppressWarnings("unchecked")
		List list = query.list();
		
		return (boolean)list.get(0);
	}

	@Override
	@Transactional
	public void blockUserAccount(String userId) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("update UserAccount set Is_Blocked=:Is_Blocked where user_id=:user_id");
		query.setParameter("Is_Blocked", true);
		query.setParameter("user_id", userId);
		
		@SuppressWarnings("unchecked")
		int result = query.executeUpdate();
	}

	@Override
	@Transactional
	public void addUserAccount(UserAccount user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	@Override
	@Transactional
	public List<UserAccount> queryUserAccount() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<UserAccount> list = (List<UserAccount>)sessionFactory.getCurrentSession()
	                  	.createCriteria(UserAccount.class)
	                  	.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

	@Override
	public boolean checkUserAccount(String userId) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from UserAccount as userAccount "
						+ "where userAccount.user_id=:user_id");
		query.setParameter("user_id", userId);
		
		@SuppressWarnings("unchecked")
		List<UserAccount> list = (List<UserAccount>)query.list();
		
		if(list.isEmpty())
			return false;
		else
			return true;
	}

	@Override
	public void updateUserAccount(UserAccount userAccount) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("update UserAccount set user_major=:user_major, "
						+ "user_name=:user_name, "
						+ "user_password=:user_password "
						+ "where user_id=:user_id");
		query.setParameter("user_major", userAccount.getMajor());
		query.setParameter("user_name", userAccount.getUserName());
		query.setParameter("user_password", userAccount.getPassword());
		query.setParameter("user_id", userAccount.getId());
		
		@SuppressWarnings("unchecked")
		int result = query.executeUpdate();
	}

}
