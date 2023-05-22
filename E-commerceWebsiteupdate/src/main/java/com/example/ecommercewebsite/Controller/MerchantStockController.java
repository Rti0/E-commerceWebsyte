package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponse;
import com.example.ecommercewebsite.Model.MerchantStock;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Service.MerchantStockService;
import com.example.ecommercewebsite.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("merchants")
public class MerchantStockController {
    public final MerchantStockService merchantStockService;

    private final ProductService productService;

    @GetMapping("/get3")
    public ResponseEntity getMerchantStock() {
        ArrayList<MerchantStock> merchantStocks = merchantStockService.getMerchantStock();
        return ResponseEntity.status(200).body(merchantStocks);
    }
    //add

    @PostMapping("/addMerchantStock")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body("MerchantStock Added");
    }

    //update
    @PutMapping("updateMerchantStock/{id}")
    public ResponseEntity updateMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors, @PathVariable int id) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdate = merchantStockService.updateMerchantStock(id, merchantStock);

        if (isUpdate) {
            return ResponseEntity.status(400).body("MerchantStock updated");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }

    //Delete
    @DeleteMapping("/deleteMerchantStock/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id) {
        boolean isDelete = merchantStockService.deleteMerchantStock(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiResponse("Done deleted"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Wrong id"));
    }



    @PostMapping("{id}/add-product")
    public ResponseEntity addProductToMerchantStock(@PathVariable int id, @RequestBody int productId) {
        MerchantStock merchantStock = merchantStockService.findById(id);
        if (merchantStock == null) {
            return ResponseEntity.badRequest().body("Merchant not found");
        }
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.badRequest().body("Product not found");
        }
        merchantStock.getProducts().add(product);
        return ResponseEntity.ok("Product added to merchantStock");
    }
    @PostMapping("/buyProduct")
    public ResponseEntity buyProduct(@RequestParam String userId, @RequestParam String productId,@RequestParam String merchantId)
    {
        return merchantStockService.buyProduct(userId,productId, merchantId);
    }

}


