package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponse;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor

public class ProductController {

    public final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProduct() {
        ArrayList<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }
    //add

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@Valid @RequestBody  Product product, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product Added");
    }
    //update
    @PutMapping("updateproduct/{id}")
    public ResponseEntity updateProduct(@Valid @RequestBody Product product, Errors errors, @PathVariable int id){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdate= productService.updateProduct(id,product);

        if (isUpdate){
            return ResponseEntity.status(400).body("Product updated");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }
    //Delete
    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id){
        boolean isDelete= productService.deleteProduct(id);
        if (isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("Done deleted"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Wrong id"));
    }
    public ProductService getProducts(){
        return productService;
    }

}
