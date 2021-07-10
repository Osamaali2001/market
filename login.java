/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

import Database.database;
import static java.lang.System.exit;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Ahmed mounir
 */
public class login {
        public  login(String username , String enteredPassword){
        String password;
        database db=new database();
        Scanner input = new Scanner(System.in);
        String role;
        int x ;
        try {
            java.sql.PreparedStatement ps =database.c.prepareStatement("SELECT * FROM Employees WHERE username=?");
            ps.setString(1, username);
            ResultSet resultSet =ps.executeQuery();
            password=resultSet.getString("password");
            role = resultSet.getString("role");
            ps.close();
            resultSet.close();
            if (password.equals(enteredPassword)){
                if("admin".equals(role.toLowerCase())){
                    System.out.println("choose from 1 to 6 :\n1-Add Employee\n"
                            + "2-delete Employee\n3-update Employee\n4-search Employee\n5-list all Employees\n6-to logout\n");
                    while (true) {
                        x = input.nextInt();
                        input.nextLine();
                        switch (x) {
                            case 1:
                                
                                System.out.println("name: ");
                                String nameNewUser = input.nextLine();
                                nameNewUser = nameNewUser.substring(0, nameNewUser.length());
                                System.out.println("username: ");
                                String usernameNewUser = input.nextLine();
                                usernameNewUser = usernameNewUser.substring(0, usernameNewUser.length());
                                System.out.println("password: ");
                                String passwordNewUser = input.nextLine();
                                passwordNewUser = passwordNewUser.substring(0, passwordNewUser.length());
                                System.out.println("role: ");
                                String roleNewUser = input.nextLine();
                                roleNewUser = roleNewUser.substring(0, roleNewUser.length());
                                db.insertEmployee(nameNewUser, usernameNewUser, passwordNewUser, roleNewUser);
                                break;
                            case 2:
                                System.out.println("ID: ");
                                int IdNewUser = input.nextInt();
                                input.nextLine();
                                db.DeleteEmployee(IdNewUser);
                                break;
                            case 3:
                                System.out.println("name: ");
                                String nameUpdateUser = input.nextLine();
                                nameUpdateUser = nameUpdateUser.substring(0, nameUpdateUser.length());
                                System.out.println("username: ");
                                String usernameUpdateUser = input.nextLine();
                                usernameUpdateUser = usernameUpdateUser.substring(0, usernameUpdateUser.length());
                                System.out.println("password: ");
                                String passwordUpdateUser = input.nextLine();
                                passwordUpdateUser = passwordUpdateUser.substring(0, passwordUpdateUser.length());
                                System.out.println("role: ");
                                String roleUpdateUser = input.nextLine();
                                roleUpdateUser = roleUpdateUser.substring(0, roleUpdateUser.length());
                                System.out.println("ID: ");
                                int IdUpdateUser = input.nextInt();
                                input.nextLine();
                                db.updateEmployee(IdUpdateUser, nameUpdateUser, usernameUpdateUser, roleUpdateUser , passwordUpdateUser);
                                break;
                            case 4:
                                System.out.println("ID: ");
                                int IdSearchUser = input.nextInt();
                                input.nextLine();
                                db.searchEmployee(IdSearchUser);
                                Employees employee= db.searchEmployee(IdSearchUser);
                                System.out.println(employee.name +"\t" + employee.role +"\t" + employee.username +"\t" + employee.password );
                                break;
                            case 5:
                                ArrayList<Employees> arrayList =db.getEmployees();
                                for(int i = 0 ; i < arrayList.size() ; i++){
                                    System.out.println(arrayList.get(i).name +"\t" + arrayList.get(i).role +"\t" + arrayList.get(i).username +"\t" + arrayList.get(i).password );
                                }
                                break;
                            case 6:
                                exit(1);
                                break;
                            default:
                                System.out.println("choose from 1 to 6");
                        }
                    }
                }
                if("inventory employee".equals(role.toLowerCase())){
                    System.out.println("choose from 1 to 6 :\n1-Add product\n"
                            + "2-delete product\n3-update product\n4-search product\n5-list all products\n6-to logout\n");
                    while (true) {
                        x = input.nextInt();
                        input.nextLine();
                        switch (x) {
                            case 1:
                                System.out.println("name: ");
                                String nameNewProduct = input.nextLine();
                                nameNewProduct = nameNewProduct.substring(0, nameNewProduct.length()); 
                                System.out.println("quantity: ");
                                int quantityNewProduct= input.nextInt();
                                input.nextLine();
                                System.out.println("expirydate");
                                String expirydateNewProduct = input.nextLine();
                                expirydateNewProduct = expirydateNewProduct.substring(0, expirydateNewProduct.length()); 
                                System.out.println("price: ");
                                int priceNewProduct= input.nextInt();
                                input.nextLine();
                                db.insertproduct(nameNewProduct,quantityNewProduct , expirydateNewProduct,priceNewProduct );
                                break;
                            case 2:
                                System.out.println("name: ");
                               String nameDeleteProduct = input.nextLine();
                               nameDeleteProduct = nameDeleteProduct.substring(0, nameDeleteProduct.length());
                                db.Deleteproduct(nameDeleteProduct);
                                break;
                            case 3:
                                System.out.println("name: ");
                                String nameUpdateProuct = input.nextLine();
                                nameUpdateProuct = nameUpdateProuct.substring(0 , nameUpdateProuct.length());
                                System.out.println("quantity: ");
                                int quantityUpdateProduct = input.nextInt();
                                input.nextLine();
                                System.out.println("price: ");
                                int priceUpdateProduct = input.nextInt();
                                input.nextLine();
                                System.out.println("expiry date: ");
                                String expirydateUpdateProduct = input.nextLine();
                                expirydateUpdateProduct=expirydateUpdateProduct.substring(0, expirydateUpdateProduct.length());
                                System.out.println("new name: ");
                                String newNamedateUpdateProduct = input.nextLine();
                                newNamedateUpdateProduct=newNamedateUpdateProduct.substring(0, newNamedateUpdateProduct.length());
                                db.updateproduct(nameUpdateProuct,quantityUpdateProduct ,priceUpdateProduct ,expirydateUpdateProduct, newNamedateUpdateProduct );
                                break;
                            case 4:
                                System.out.println("product name: ");
                                String nameSearchProduct = input.nextLine();
                                nameSearchProduct = nameSearchProduct.substring(0, nameSearchProduct.length());
                                product prod = db.searchproduct(nameSearchProduct);
                                System.out.println(prod.name+"\t"+prod.quantity+"\t"+prod.price+"\t"+prod.expiryDate);
                                break;
                            case 5:
                                ArrayList<product> arrayList =db.getproduct();
                                for(int i = 0 ; i < arrayList.size() ; i++){
                                    System.out.println(arrayList.get(i).name+"\t"+arrayList.get(i).quantity+"\t"+arrayList.get(i).price+"\t"+arrayList.get(i).expiryDate);
                                } 
                                break;
                            case 6:
                                exit(1);
                                break;
                            default:
                                System.out.println("choose from 1 to 6");
                        }
                    }
                }
                if("seller".equals(role.toLowerCase())){
                    System.out.println("choose from 1 to 5 :\n1-search product\n"
                            + "2-list all products\n3-make order\n4-cancel order\n5-to logout\n");
                    while (true) {
                        x = input.nextInt();
                        input.nextLine();
                        switch (x) {
                            case 1:
                                System.out.println("product name: ");
                                String nameSearchProduct = input.nextLine();
                                nameSearchProduct = nameSearchProduct.substring(0, nameSearchProduct.length());
                                product prod = db.searchproduct(nameSearchProduct);
                                System.out.println(prod);
                                break;
                            case 2:
                                ArrayList<product> arrayList = db.getproduct();
                                for (int i = 0; i < arrayList.size(); i++) {
                                    System.out.println(arrayList.get(i));
                                }
                                break;
                            case 3:
                                System.out.println("name: ");
                                String name = input.nextLine();
                                name = name.substring(0, name.length());
                                System.out.println("quantity: ");
                                int quntity = input.nextInt();
                                input.nextLine();
                                db.makeOrder(name, quntity);
                                break;
                            case 4:
                                System.out.println("name: ");
                                String canceledName = input.nextLine();
                                canceledName = canceledName.substring(0, canceledName.length());
                                System.out.println("quantity: ");
                                int canceledQuntity = input.nextInt();
                                input.nextLine();
                                db.cancelOrder(canceledName, canceledQuntity);
                            case 5:
                                exit(1);
                                break;
                            default:
                                System.out.println("choose from 1 to 5");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Enter a valid username");
        }
    }
    
}
