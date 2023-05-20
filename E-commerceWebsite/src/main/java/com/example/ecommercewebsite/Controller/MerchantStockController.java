package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponse;
import com.example.ecommercewebsite.Model.MerchantStock;
import com.example.ecommercewebsite.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {
    public final MerchantStockService merchantStockService;

    @GetMapping("/get3")
    public ResponseEntity getMerchantStock() {
        ArrayList<MerchantStock> merchantStocks = merchantStockService.getMerchantStock();
        return ResponseEntity.status(200).body(merchantStocks);
    }
    //add

    @PostMapping("/addProduct1")
    public ResponseEntity addProductToMerchantStock(@Valid @RequestBody MerchantStock  merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        merchantStockService.addProductToMerchantStock(merchantStock);
        return ResponseEntity.status(200).body("Dene add");
    }
    //update
    @PutMapping("updateMerchantStock/{id}")
    public ResponseEntity updateProductToMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors, @PathVariable int id){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdate= merchantStockService.updateProductToMerchantStock(id,merchantStock);

        if (isUpdate){
            return ResponseEntity.status(400).body("MerchantStock updated");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }
    //Delete
    @DeleteMapping("/deleteMerchantStock/{id}")
    public ResponseEntity deleteProductToMerchantStock(@PathVariable int id){
        boolean isDelete= merchantStockService.deleteProductToMerchantStock(id);
        if (isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("Done deleted"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Wrong id"));
    }
    @PostMapping("/buyProduct")public ResponseEntity buyProduct(@RequestParam String userId, @RequestParam String productId,@RequestParam String merchantId)
    {    return merchantStockService.buyProduct(userId,productId, merchantId);
    }
}

