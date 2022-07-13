package com.example.spring_product_test;

import com.example.spring_product_test.entity.Product;
import com.example.spring_product_test.repository.ProductRepository;
import com.example.spring_product_test.search.ProductSpecification;
import com.example.spring_product_test.search.SearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringProductTestApplication.class)
class SpringProductTestApplicationTests {

    @Autowired
    ProductRepository productRepository;
    @Test
    void testSearch() {
        ProductSpecification findBySize = new ProductSpecification(new SearchCriteria("size", "=", "XL"));
        List<Product> products = productRepository.findAll(findBySize);
        System.out.println("Find by size: " + products.size());
    }

}
