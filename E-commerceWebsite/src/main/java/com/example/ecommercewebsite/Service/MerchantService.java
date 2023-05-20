package com.example.ecommercewebsite.Service;


import com.example.ecommercewebsite.Model.Merchant;
import com.example.ecommercewebsite.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {
    ArrayList<Merchant> merchants=new ArrayList();
    public ArrayList<Merchant> getMerchant(){
        return merchants;
    }
    public void addMerchant(Merchant merchant){
        merchants.add(merchant);
    }
    public  boolean updateMerchant(int id, Merchant merchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if ( String.valueOf(id).equals(merchants.get(i).getId()))  {

                merchants.set(i, merchant);
                return true;
            }
        }
        return false;
    }
    public boolean deleteMerchant(int id) {
        for (int i = 0; i < merchants.size(); i++) {
            if ( String.valueOf(id).equals(merchants.get(i).getId()))  {
                merchants.remove(id);
                return true;
            }
        }

        return false;

    }


}
