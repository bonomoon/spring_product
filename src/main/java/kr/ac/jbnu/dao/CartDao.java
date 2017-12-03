package kr.ac.jbnu.dao;

import java.util.List;

import kr.ac.jbnu.model.Product;

public interface CartDao {
	public List<Product> queryCart(String userid);
	public void insertCart(String userid, String code);
	public void deleteCart(String userName, String code);
}
