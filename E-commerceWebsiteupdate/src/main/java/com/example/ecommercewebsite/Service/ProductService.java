package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    ArrayList<Product> products=new ArrayList();


    public ArrayList<Product> getProducts(){
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }
    public  boolean updateProduct(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (id == products.get(i).getId()) {

                products.set(i, product);
                return true;
            }
        }
        return false;
    }
    public boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(id);
                return true;
            }
        }

        return false;

    }

    public Product findById(int productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {

                return products.get(i);
            }
        }
        return null;
    }
}