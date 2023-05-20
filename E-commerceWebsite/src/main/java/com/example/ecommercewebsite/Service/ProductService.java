package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.MerchantStock;
import com.example.ecommercewebsite.Model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    ArrayList<Product> products=new ArrayList();


    public ArrayList<Product> getProducts(){
        return products;
    }

    public void addProduct(@Valid Product product){

        products.add(product);
    }
    public  boolean updateProduct(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if ( String.valueOf(id).equals(products.get(i).getId())) {

                products.set(i, product);
                return true;
            }
        }
        return false;
    }
    public boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if ( String.valueOf(id).equals(products.get(i).getId())) {
                products.remove(id);
                return true;
            }
        }

        return false;

    }
}