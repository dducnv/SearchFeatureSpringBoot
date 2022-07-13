package com.example.spring_product_test.seeder;

import com.example.spring_product_test.entity.Product;
import com.example.spring_product_test.repository.ProductRepository;
import com.github.javafaker.Faker;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ProductSeeder implements CommandLineRunner {
    boolean isSeeded = false;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
        Faker faker = new Faker();
        String[] color = {"red", "blue", "green", "yellow", "black", "white", "orange", "pink", "purple", "brown", "grey"};
        String[] size = {"S", "M", "L", "XL", "XXL"};
        String[] gender = {"Male", "Female", "Other"};
        int maxPrice = 20000;
        int minPrice = 1000;
        int numberOfProducts = 10000;

        int colorIndex = color.length;
        int sizeIndex = size.length;
        int genderIndex = gender.length;

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < numberOfProducts; i++) {
            int pick = random.nextInt(colorIndex);
            int pickSize = random.nextInt(sizeIndex);
            int pickGender = random.nextInt(genderIndex);

            String colorPicked = color[pick];
            String sizePicked = size[pickSize];
            String genderPicked = gender[pickGender];

            products.add(Product.builder()
                    .name(faker.name().title())
                    .description(faker.name().title())
                    .image(faker.avatar().image())
                    .gender(genderPicked)
                    .color(colorPicked)
                    .size(sizePicked)
                    .price((double) faker.number().numberBetween(minPrice, maxPrice))
                    .status(faker.number().numberBetween(1, 2))
                    .quantity(faker.number().numberBetween(20, 220)).build()
            );
            if (isSeeded) {
                productRepository.saveAll(products);
            }
        }
    }
}
