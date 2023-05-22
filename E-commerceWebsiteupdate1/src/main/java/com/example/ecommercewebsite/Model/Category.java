package com.example.ecommercewebsite.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Category {
    @NotEmpty(message = "must not be empty")
    @Size(max = 3,message = "have to be 3 character long")
    private int id;

    @NotEmpty(message = "must not be empty")
    @Size(max = 3,message = "have to be 3 length long")
    private String name;
}
