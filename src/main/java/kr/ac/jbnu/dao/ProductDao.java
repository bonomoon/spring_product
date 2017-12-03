package kr.ac.jbnu.dao;

import java.util.List;

import kr.ac.jbnu.model.Product;

public interface ProductDao {
	public List<Product> queryProduct();
	public List<Product> querySearchedProduct(String keyword);
	public List<Product> queryPriceSearchedProduct(String minprice, String maxprice);
	public List<Product> queryBrandSearchedProduct(String _brand);
	public List<Product> queryCpuSearchedProduct(String _cpu);
	public List<Product> queryGraphicSearchedProduct(String graphic);
	public Product findProduct(String productCode);
	public void insertProduct(Product product);
}
