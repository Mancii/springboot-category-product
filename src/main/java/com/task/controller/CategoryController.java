package com.task.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.task.entity.Category;
import com.task.exception.CategoryException;
import com.task.service.CategoryService;

@Controller
//@RequestMapping(value = "/api")
public class CategoryController {

	@Autowired
	public CategoryService categoryservice;

	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() throws CategoryException {
		List<Category> categories = categoryservice.getAllCategories();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@PostMapping("/category/add")
	public ResponseEntity<Category> addNewCategory(@RequestBody Category category) throws CategoryException {
		Category category1 = categoryservice.addNewCategory(category);
		System.out.println(category);
		return new ResponseEntity<Category>(category1, HttpStatus.OK);
	}

	@GetMapping("/category/getById/{Id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("Id") Integer id) throws CategoryException {
		Category category1 = categoryservice.getCategoryById(id);
		return new ResponseEntity<Category>(category1, HttpStatus.OK);
	}

	@PutMapping("/category/update/{id}")
	public ResponseEntity<Category> updateCategoryById(@RequestBody Category category) throws CategoryException {
		Category category1 = categoryservice.updateCategoryById(category);

		return new ResponseEntity<Category>(category1, HttpStatus.OK);
	}

	@DeleteMapping("/category/delete/{Id}")
	public ResponseEntity<Category> deleteCategoryById(@PathVariable("Id") Integer id) throws CategoryException {
		Category category1 = categoryservice.deleteCategoryById(id);
		return new ResponseEntity<Category>(category1, HttpStatus.OK);
	}

	// FRONTEND

	@GetMapping("/")
	public String defaultPage() {
		return "index";
	}
	
	@GetMapping("/categories/list")
	public String getCategories(Model model) throws CategoryException {
		
		List<Category> categories = categoryservice.getAllCategories();
		
		model.addAttribute("categories", categories);
		
		return "category/list-categories";
	}
	
	@GetMapping("/categories/add")
	public String addCategoryForm(Model model) {
		
		// Create model attribute to bind from data
		Category category = new Category();
		
		model.addAttribute("category", category);
				
		return "category/addCategory";
	}

	@PostMapping("/save")
	public String register(@ModelAttribute Category category) throws CategoryException {

		categoryservice.addNewCategory(category);
		
		return "redirect:/categories/list";
	}
	
	@GetMapping("/categories/update")
	public String updateCategoryForm(@RequestParam("categoryId") int cId, Model model) throws CategoryException {
		
		Category category = categoryservice.getCategoryById(cId);
		
		model.addAttribute("category", category);
		
		return "category/category-form";
	}
	
	@GetMapping("/categories/delete")
	public String deleteCategoryForm(@RequestParam("categoryId") int cId) throws CategoryException {
		
		categoryservice.deleteCategoryById(cId);
		
		return "redirect:/categories/list";
	}
	
}
