/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

//import java.util.*;
/**
 *
 * @author Ahmed mounir
 */
public class product {
    String name;
    int quantity;
    String expiryDate;
    int price;
    
    public product(){}
    
    public product(String name,int quantity,String expiryDate,int price){
        this.name=name;
        this.quantity=quantity;
        this.expiryDate=expiryDate;
        this.price=price;
    }
    
//    public boolean expiryDate(Date expiryDate){
//        Date date= new Date();
//        if (date == expiryDate) {
//            return true;
//        }
//        return false;
//    }
    
}
