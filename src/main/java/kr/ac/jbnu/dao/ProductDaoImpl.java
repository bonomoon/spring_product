package kr.ac.jbnu.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import kr.ac.jbnu.model.Product;

public class ProductDaoImpl implements ProductDao{
	private SessionFactory sessionFactory;
	
	public ProductDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public List<Product> list() {
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>)sessionFactory.getCurrentSession()
	                  	.createCriteria(Product.class)
	                  	.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}
}
