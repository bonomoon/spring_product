package kr.ac.jbnu.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import kr.ac.jbnu.model.Product;

public class ProductDaoImpl implements ProductDao{
	private SessionFactory sessionFactory;
	
	public ProductDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public List<Product> queryProduct() {
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>)sessionFactory.getCurrentSession()
	                  	.createCriteria(Product.class)
	                  	.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}
	
	@Override
	@Transactional
	public Product findProduct(String productCode) {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Product as product where product.code=:code");
		query.setParameter("code", productCode);
		
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>)query.list();
		return list.get(0);
	}

	@Override
	@Transactional
	public List<Product> querySearchedProduct(String keyword) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Product as product where product.name like :name");
		query.setParameter("name", "%" + keyword + "%");
		
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>)query.list();
		return list;
	}

	@Override
	@Transactional
	public List<Product> queryPriceSearchedProduct(String minprice, String maxprice) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Product as product where product.price>=:minprice and "
						+ "product.price<=:maxprice");
		query.setInteger("minprice", Integer.parseInt(minprice));
		query.setInteger("maxprice", Integer.parseInt(maxprice));
		
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>)query.list();
		return list;
	}

	@Override
	@Transactional
	public List<Product> queryBrandSearchedProduct(String _brand) {
		// TODO Auto-generated method stub
		String hql = null;
		if(_brand.equals("others")) {
			hql = "from Product a where a.brand<>'samsung' and a.brand<>'dell' and a.brand<>'lg' and a.brand<>'asus'";
		} else {
			hql = "from Product a where a.brand='" + _brand + "'";
		}
		
		Query query = sessionFactory.getCurrentSession()
				.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>)query.list();
		return list;
	}

	@Override
	@Transactional
	public List<Product> queryCpuSearchedProduct(String _cpu) {
		// TODO Auto-generated method stub
		String hql = null;
		if(_cpu.equals("others")) {
			hql = "from Product a where a.cpu not like '%i3%' and a.cpu not like '%i5%' and a.cpu not like '%i7%'";
		} else {
			hql = "from Product a where a.cpu like '%" + _cpu + "%'";
		}
		
		Query query = sessionFactory.getCurrentSession()
				.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>)query.list();
		return list;
	}

	@Override
	@Transactional
	public List<Product> queryGraphicSearchedProduct(String graphic) {
		// TODO Auto-generated method stub
		String hql = null;
		if(graphic.equals("amd")) {
			hql = "from Product a where a.graphicCard like '%라데온%'";
		}
		if(graphic.equals("엔비디아")) {
			hql = "from Product a where a.graphicCard like '%지포스%'";
		}
		if(graphic.equals("인텔")) {
			hql = "from Product a where a.graphicCard like '%HD%'";
		}
		
		Query query = sessionFactory.getCurrentSession()
				.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>)query.list();
		return list;
	}

	@Override
	@Transactional
	public void insertProduct(Product product) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();
	}
}
