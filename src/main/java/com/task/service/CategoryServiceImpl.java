package com.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.entity.Category;
import com.task.entity.Product;
import com.task.exception.CategoryException;
import com.task.repository.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;

	@Autowired
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> getAllCategories() throws CategoryException {
		return categoryDao.findAll();
	}

	@Override
	public Category addNewCategory(Category category) throws CategoryException {

		List<Product> product = category.getProducts();

		if (!product.isEmpty()) {
			for (Product p : product) {
				p.setCategory(category);
			}
		}
		Category addCategory = categoryDao.save(category);

		if (addCategory != null) {
			return addCategory;
		} else {
			throw new CategoryException("Product details is Empty...");
		}

	}

	@Override
	public Category updateCategoryById(Category category) throws CategoryException {

		Optional<Category> result = categoryDao.findById(category.getCategoryId());

		Category categoryUpdated = null;
		
		if (result.isPresent()) {
			categoryUpdated = categoryDao.save(category);
		} else {

			throw new CategoryException("Category with given id is not presesnt........ ");
		}
		return categoryUpdated;
	}

	@Override
	public Category getCategoryById(Integer CId) throws CategoryException {
		Optional<Category> result = categoryDao.findById(CId);

		Category category = null;

		if (result.isPresent()) {

			category = result.get();
		} else {

			throw new CategoryException("Category does not exist with Id :" + CId);
		}

		return category;
	}

	@Override
	public Category deleteCategoryById(Integer CId) throws CategoryException {
		Optional<Category> reuslt = categoryDao.findById(CId);

		Category category = null;

		if (reuslt.isPresent()) {

			category = reuslt.get();

			categoryDao.delete(category);
		} else {

			throw new CategoryException("Category does not exist with Id :" + CId);
		}

		return category;
	}

}