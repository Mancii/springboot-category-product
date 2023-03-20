package com.task.service;

import java.util.List;

import com.task.entity.Product;
import com.task.exception.ProductException;

public interface ProductService {

	public List<Product> getAllProduct() throws ProductException;

	public Product addNewProduct(Product product) throws ProductException;

	public Product getProudctById(Integer PId) throws ProductException;

	public Product updateProductById(Product product) throws ProductException;

	public Product deleteProductById(Integer Id) throws ProductException;

}
