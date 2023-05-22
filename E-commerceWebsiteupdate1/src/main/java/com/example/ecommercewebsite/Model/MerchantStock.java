package com.example.ecommercewebsite.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class MerchantStock {
    @NotEmpty(message = "must not be empty")
    @Size(max = 3, message = "have to be 3 character long")
    private int id;

    @NotEmpty(message = "must not be empty")
    @Size(max = 3, message = "have to be 3 character long")
    public String productId;

    @NotEmpty(message = "must not be empty")
    @Size(max = 3, message = "have to be 3 length long")
    private String merchantId;

    @NotEmpty(message = "must not be empty")
    @Min(value = 11, message = "have to be more than 10 at start")
    private String stock;

    private List<Product> products;


    public List<Product> getProducts() {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }

        return products;
    }
}
