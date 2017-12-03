package kr.ac.jbnu.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.ac.jbnu.model.Cart;
import kr.ac.jbnu.model.Product;

public class CartDaoImpl implements CartDao {
	private SessionFactory sessionFactory;
	
	@Autowired 
	private ProductDao productDao;
	
	@Override
	public List<Product> queryCart(String userid) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("select cart.cart_item from Cart as cart "
						+ "where cart_user=:cart_user");
		query.setParameter("cart_user", userid);
		
		@SuppressWarnings("unchecked")
		List<Cart> list = query.list();
		Iterator<Cart> iterator = list.iterator();
		List<Product> productList = new ArrayList<Product>();
		
		while(iterator.hasNext()) {
			Product product;
			product = productDao.findProduct(String.valueOf(iterator.next().getCart_id()));
			productList.add(product);
		}
		
		return productList;
	}

	@Override
	public void insertCart(String userid, String code) {
		// TODO Auto-generated method stub
		Cart cart = new Cart(userid, Integer.parseInt(code));
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(cart);
		session.getTransaction().commit();
	}

	@Override
	public void deleteCart(String userName, String code) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("delete Cart as cart "
						+ "where cart.cart_user=:cart_user and "
						+ "cart.cart_item=:cart_item");
		query.setParameter("cart_user", userName);
		query.setParameter("cart_item", code);
		
		@SuppressWarnings("unchecked")
		int result = query.executeUpdate();
	}

}
