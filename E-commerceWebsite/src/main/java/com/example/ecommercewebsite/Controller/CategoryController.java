package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponse;
import com.example.ecommercewebsite.Model.Category;
import com.example.ecommercewebsite.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    public final CategoryService categoryService;
    @GetMapping("/get1")
    public ResponseEntity getCategory(){
        ArrayList<Category>categories=categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }
    //add
    @PostMapping("addCategory")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Category Added");
    }
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity updateCategory(@Valid @RequestBody Category category, Errors errors, @PathVariable int id){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdate= categoryService.updateCategory(id,category);

        if (isUpdate){
            return ResponseEntity.status(400).body("eCategory updated");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id){
        boolean isDelete= categoryService.deleteCategory(id);
        if (isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("Done deleted"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Wrong id"));
    }
}
