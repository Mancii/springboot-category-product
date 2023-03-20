package com.task.service;

import java.util.List;

import com.task.entity.Category;
import com.task.exception.CategoryException;

public interface CategoryService {

	public List<Category> getAllCategories() throws CategoryException;

	public Category addNewCategory(Category category) throws CategoryException;

	public Category getCategoryById(Integer CId) throws CategoryException;

	public Category updateCategoryById(Category category) throws CategoryException;

	public Category deleteCategoryById(Integer CId) throws CategoryException;

}
