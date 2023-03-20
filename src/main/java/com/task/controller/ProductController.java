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

import com.task.entity.Category;
import com.task.entity.Product;
import com.task.exception.CategoryException;
import com.task.exception.ProductException;
import com.task.service.CategoryService;
import com.task.service.ProductService;

@Controller
//@RequestMapping(value = "/api")
public class ProductController {

	@Autowired
	public ProductService productservice;
	
	@Autowired
	public CategoryService categoryservice;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct() throws ProductException {
		List<Product> product = productservice.getAllProduct();
		return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
	}

	@PostMapping("/product/add")
	public ResponseEntity<Product> addNewProduct(@RequestBody Product product, Model m) throws ProductException {
		Product product1 = productservice.addNewProduct(product);
		System.out.println(product);
		return new ResponseEntity<Product>(product1, HttpStatus.OK);
	}

	@GetMapping("/product/getById/{Id}")
	public ResponseEntity<Product> getProudctById(@PathVariable("Id") Integer id) throws ProductException {
		Product product1 = productservice.getProudctById(id);
		return new ResponseEntity<Product>(product1, HttpStatus.OK);
	}

	@PutMapping("/product/update/{id}")
	public ResponseEntity<Product> updateProductById(@RequestBody Product product) throws ProductException {
		Product product1 = productservice.updateProductById(product);

		return new ResponseEntity<Product>(product1, HttpStatus.OK);
	}

	@DeleteMapping("/product/delete/{Id}")
	public ResponseEntity<Product> deleteProductById(@PathVariable("Id") Integer id) throws ProductException {
		Product product1 = productservice.deleteProductById(id);
		return new ResponseEntity<Product>(product1, HttpStatus.OK);
	}

	// FRONTEND
	
	@GetMapping("/products/list")
	public String getProducts(Model model) throws ProductException, CategoryException {
		
		List<Product> products = productservice.getAllProduct();
		
		Category category = new Category();
		
		model.addAttribute("products", products);
		
		model.addAttribute("category", category);
		
		return "product/list-products";
	}
	
	@GetMapping("/products/add")
	public String addproductForm(Model model) throws CategoryException {
		
		// Create model attribute to bind from data
		Product product = new Product();
		
		List<Category> categories = categoryservice.getAllCategories();
		
		model.addAttribute("product", product);
		
		model.addAttribute("categories", categories);
				
		return "product/addProduct";
	}
	
	@PostMapping("/products/save")
	public String register(@ModelAttribute Product product) throws ProductException {

		productservice.addNewProduct(product);
		
		return "redirect:/products/list";
	}

}
