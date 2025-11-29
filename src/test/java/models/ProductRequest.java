package models;

import lombok.Data;

@Data
public class ProductRequest {

    private String title;
    private String description;
    private int price;
    private String category; // POST veya PUT için
}
