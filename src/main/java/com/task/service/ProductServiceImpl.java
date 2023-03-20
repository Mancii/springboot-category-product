package com.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.entity.Product;
import com.task.exception.ProductException;
import com.task.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService{

	private ProductDao productDao;
	
	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> getAllProduct() throws ProductException {
		return productDao.findAll();
	}
	
	@Override
	public Product addNewProduct(Product product) throws ProductException 
	{
		Product prd = productDao.save(product);
		
		if(prd != null) {
			return prd;
		}
		else {
			throw new ProductException("Product deatails is Empty...");
		}
	}

	@Override
	public Product getProudctById(Integer PId) throws ProductException {
		
		Optional<Product> result = productDao.findById(PId);
		
		Product  product = null;
		
		if(result.isPresent()) {
			product = result.get();
		}
		else {
			
			throw new ProductException("Product does not exist with Id :"+PId);
		}
		
		return product;
	}

	@Override
	public Product updateProductById(Product product) throws ProductException {
		
		Optional<Product> result = productDao.findById(product.getProductId());
		
		Product productUpdated = null;
		
		if (result.isPresent())
		{
			productUpdated = productDao.save(product);
		
		} else {
			
	        throw new ProductException("Product with given id is not presesnt...");
		}
		
		return productUpdated;
	}

	@Override
	public Product deleteProductById(Integer Id) throws ProductException {
	
		Optional<Product> result = productDao.findById(Id);
		
		Product product = null;
		
		if(result.isPresent()) {
			
			product = result.get();
			productDao.delete(product);
			
		}
		else {
			
			throw new ProductException("Product does not exist with Id :"+Id);
		}
			
		return product;
	}

}