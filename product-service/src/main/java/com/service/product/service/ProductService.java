package com.service.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.product.dto.ProductRequest;
import com.service.product.model.Product;
import com.service.product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductRequest addProduct(ProductRequest productRequest) {
		Product product = mapProductRequestToProduct(productRequest);
		productRepository.save(product);
		
		productRequest = mapProductToProductRequest(product);
		return productRequest;
	}
	
	public ProductRequest editProduct(Long id, ProductRequest productRequest) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		
		product.setName(productRequest.getName());
		product.setPrice(productRequest.getPrice());
		product.setSkuCode(productRequest.getSkuCode());
		product.setDescription(productRequest.getDescription());
		
		productRepository.save(product);
		
		productRequest = mapProductToProductRequest(product);
		return productRequest;
	}
	
	public List<ProductRequest> getProductList() {
		List<Product> productsList = productRepository.findAll();
		List<ProductRequest> productRequestssList = mapProductsListToProductRequestList(productsList);
		
		return productRequestssList;
	}
	
	// Map Product to ProductRequest
	private ProductRequest mapProductToProductRequest(Product product) {
		ProductRequest productRequest = new ProductRequest();
		
		productRequest.setId(product.getId());
		productRequest.setName(product.getName());
		productRequest.setPrice(product.getPrice());
		productRequest.setSkuCode(product.getSkuCode());
		productRequest.setDescription(product.getDescription());
		
		return productRequest;
	}
	
	// Map Products List to Product Requests List
	private List<ProductRequest> mapProductsListToProductRequestList(List<Product> products) {
		
		return products.stream()
			.map(this::mapProductToProductRequest)
			.collect(Collectors.toList());
		
	}
	
	// Map ProductRequest to Product
	private Product mapProductRequestToProduct(ProductRequest productRequest) {
		Product product = new Product();
		
		product.setId(productRequest.getId());
		product.setName(productRequest.getName());
		product.setPrice(productRequest.getPrice());
		product.setSkuCode(productRequest.getSkuCode());
		product.setDescription(productRequest.getDescription());
		
		return product;
	}
}
