package com.service.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.product.dto.ProductRequest;
import com.service.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ProductRequest> addProduct(@RequestBody ProductRequest productRequest){
		productRequest = productService.addProduct(productRequest);
		
		return new ResponseEntity<ProductRequest>(productRequest, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<ProductRequest> editProduct(@RequestParam("id") Long id, 
			@RequestBody ProductRequest productRequest){
		productRequest = productService.editProduct(id, productRequest);
		
		return new ResponseEntity<ProductRequest>(productRequest, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ProductRequest>> getProductList() {
		List<ProductRequest> productsList = productService.getProductList();
		
		return new ResponseEntity<List<ProductRequest>>(productsList, HttpStatus.OK);
	}
	
}
