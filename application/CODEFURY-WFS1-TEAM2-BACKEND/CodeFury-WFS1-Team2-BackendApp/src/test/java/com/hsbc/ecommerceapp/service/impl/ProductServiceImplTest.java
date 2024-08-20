package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.dao.ProductStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    private ProductStorage productStorage;
    private ProductServiceImpl productService;

    // setup before each test
    @BeforeEach
    public void setup() {
        productStorage = mock(ProductStorage.class);
        productService = new ProductServiceImpl(productStorage);
    }

    // test add product
    @Test
    public void testAddProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);

        productService.addProduct(product);

        verify(productStorage).addProduct(product);
    }

    // test update product
    @Test
    public void testUpdateProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);

        when(productStorage.getProductById("Product1")).thenReturn(product);

        product.setPrice(5.5);
        productService.updateProduct(product);

        verify(productStorage).updateProduct(product);
    }

    // test delete product
    @Test
    public void testDeleteProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);

        when(productStorage.getProductById("Product1")).thenReturn(product);

        productService.deleteProduct("Product1");

        verify(productStorage).deleteProduct("Product1");
    }

    // testing view all products
    @Test
    public void testViewAllProducts() {
        Product product1 = new Product("Product1", "Apple", "Fresh Apple", 3.0, true);
        Product product2 = new Product("Product2", "Banana", "Fresh Banana", 2.0, true);

        when(productStorage.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productStorage).getAllProducts();
    }
}
