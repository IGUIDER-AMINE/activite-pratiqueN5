package com.enset.orderservice;

import com.enset.orderservice.entities.Order;
import com.enset.orderservice.entities.ProductItem;
import com.enset.orderservice.enums.OrderState;
import com.enset.orderservice.repository.OrderRepository;
import com.enset.orderservice.repository.ProductItemRepository;
import com.enset.orderservice.restclients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(OrderRepository orderRepository,
                                        ProductItemRepository productItemRepository,
                                        InventoryRestClient inventoryRestClient) {
        return args -> {
            //List<Product> allproducts = inventoryRestClient.getAllProducts();
           List<String> producrtIds = List.of("P01","P02","P03");
            for(int i=0;i<5;i++){
                Order order = Order.builder()
                        .id(UUID.randomUUID().toString())
                        .date(LocalDate.now())
                        .state(OrderState.values()[new Random().nextInt(OrderState.values().length)])
                        .build();
                Order savedOrder =orderRepository.save(order);

                producrtIds.forEach(productId -> {
                    ProductItem productItem = ProductItem.builder()
                            .productId(productId)
                            .price(1000 + new Random().nextInt(10000))
                            .quantity(new Random().nextInt(100))
                            .order(savedOrder)
                            .build();

                    productItemRepository.save(productItem);
                });

            }
        };
    }
}
