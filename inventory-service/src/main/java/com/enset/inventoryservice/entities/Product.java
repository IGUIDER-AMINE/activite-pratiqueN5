package com.enset.inventoryservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;
}
