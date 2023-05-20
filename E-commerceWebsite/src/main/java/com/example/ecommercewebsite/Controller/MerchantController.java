package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponse;
import com.example.ecommercewebsite.Model.Merchant;
import com.example.ecommercewebsite.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("api/v1/Merchant")
@RequiredArgsConstructor
public class MerchantController {
    public final MerchantService merchantService;
    @GetMapping("/get2")
    public ResponseEntity getMerchant() {
        ArrayList<Merchant> merchants = merchantService.getMerchant();
        return ResponseEntity.status(200).body(merchants);
    }
    //add

    @PostMapping("/addMerchant")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("Merchant Added");
    }
    //update
    @PutMapping("updateMerchant/{id}")
    public ResponseEntity updateMerchant(@Valid @RequestBody Merchant merchant, Errors errors, @PathVariable int id){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdate= merchantService.updateMerchant(id,merchant);

        if (isUpdate){
            return ResponseEntity.status(400).body("Merchant updated");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }
    //Delete
    @DeleteMapping("/deleteMerchant/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id){
        boolean isDelete= merchantService.deleteMerchant(id);
        if (isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("Done deleted"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Wrong id"));
    }
}

