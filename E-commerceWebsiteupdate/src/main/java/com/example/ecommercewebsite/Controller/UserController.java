package com.example.ecommercewebsite.Controller;

import com.example.ecommercewebsite.ApiResponse.ApiResponse;
import com.example.ecommercewebsite.Model.Product;
import com.example.ecommercewebsite.Model.User;
import com.example.ecommercewebsite.Service.ProductService;
import com.example.ecommercewebsite.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser() {
        ArrayList<User> users = userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }
    //add

    @PostMapping("/addUser")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User Added");
    }
    //update
    @PutMapping("updateUser/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user, Errors errors, @PathVariable int id){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdate= userService.updateUser(id,user);

        if (isUpdate){
            return ResponseEntity.status(400).body("User updated");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }
    //Delete
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        boolean isDelete= userService.deleteUser(id);
        if (isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("Done deleted"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Wrong id"));
    }

}
