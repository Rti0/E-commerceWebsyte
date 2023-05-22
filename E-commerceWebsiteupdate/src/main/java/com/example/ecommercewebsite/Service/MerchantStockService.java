package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.ApiResponse.ApiResponse;
import com.example.ecommercewebsite.Model.MerchantStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class MerchantStockService {
    @Autowired UserService userService;
    @Autowired ProductService productService;

    ArrayList<MerchantStock> merchantStocks = new ArrayList();

    public ArrayList<MerchantStock> getMerchantStock() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(int id, MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (id == merchantStocks.get(i).getId()) {

                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(int id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.remove(id);
                return true;
            }
        }

        return false;

    }


    public MerchantStock findById(int id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                return merchantStocks.get(i);
            }
        }
        return null;
    }

    public ResponseEntity buyProduct( String userId,String productId, String merchantId) {
        if (userId==null || userId.length() > 3) {
            String message = "user id error";
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (productId==null || productId.length() > 3) {
            String message = "productId error";
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (merchantId==null || merchantId.length() > 3) {
            String message = "merchantId error";
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean productInMerchantStocks =false;
        for (int i = 0; i < merchantStocks.size(); i++) {

            if (merchantStocks.get(i).getProductId().equals(productId)) {
                for (int j = 0; j < userService.users.size(); j++) {
                    if (userId.equals(String.valueOf(userService.users.get(j).getId()))) {
                        for (int y = 0; y < productService.products.size(); y++) {
                            if (String.valueOf(productService.products.get(y).getId()).equals(productId)) {
                                if (userService.users.get(j).getBalance() - productService.products.get(y).getPrice() < 0) {
                                    String message = "Balance error";
                                    return ResponseEntity.status(400).body(new ApiResponse(message));
                                } else {
                               userService.users.get(j).setBalance(userService.users.get(j).getBalance() - productService.products.get(y).getPrice());
                                }

                            }
                        }
                    }
                }            merchantStocks.remove(i);
            }
        }    if(!productInMerchantStocks){
            String message = "productInMerchantStocks error";
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        return ResponseEntity.status(200).body("Buy Product");
    }
}
