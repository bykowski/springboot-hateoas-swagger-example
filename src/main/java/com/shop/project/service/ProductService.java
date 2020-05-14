package com.shop.project.service;

import com.shop.project.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> productList;

    public ProductService() {
        this.productList = new ArrayList<>();
        productList.add(new Product(1L, "HEAD & SHOULDERS", new BigDecimal(16), 5));
        productList.add(new Product(2L, "BAMBINO", new BigDecimal(9), 24));
        productList.add(new Product(3L, "MYDEŁKO FA", new BigDecimal(13), 10));
    }

    public Optional<Product> addProduct(Product product) {
        boolean isProductExists = productList.stream().anyMatch(element -> element.getName().equals(product.getName()));
        return isProductExists ? Optional.empty() : Optional.of(saveProduct(product));
    }

    public List<Product> findAllProducts() {
        return productList;
    }

    public Optional<Product> updateProduct(Product product) {
        return productList.stream()
                .filter(element -> element.getId() == product.getId())
                .findFirst()
                .map(element -> {
                    element.setPrice(product.getPrice());
                    element.setName(product.getName());
                    element.setAmount(product.getAmount());
                    return element;
                });
    }

    public Optional<Product> findById(Long productId) {
        return productList.stream().filter(element -> element.getId() == productId).findFirst();
    }

    public Optional<Product> buyProduct(Product orderProduct) {
        Optional<Product> productById = productList.stream().filter(element -> element.getId() == orderProduct.getId()).findFirst();
        return productById
                .filter(product -> orderProduct.getAmount() <= product.getAmount())
                .map(product -> {
                    product.setAmount(product.getAmount() - orderProduct.getAmount());
                    updateProduct(product);
                    return orderProduct;
                });
    }

    private Product saveProduct(Product product) {
        productList.add(product);
        return product;
    }
}
