package kr.ac.jbnu.dao;

import java.util.List;

import kr.ac.jbnu.model.Product;

public interface ProductDao {
	public List<Product> queryProduct();
	public List<Product> findProduct(String productCode);
}
