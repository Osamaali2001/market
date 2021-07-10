/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;
import Database.database;
import java.sql.SQLException;
import java.util.*;


/**
 *
 * @author Ahmed mounir
 */
public class Market {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO code application logic here
        database d=new database();
        d.Connect();
        Scanner input = new Scanner(System.in);
        System.out.println("username:");
        String username = input.nextLine();
        username = username.substring(0,username.length());
        System.out.println("password");
        String password = input.nextLine();
        password = password.substring(0,password.length());
        login x;
        x = new login(username , password);
    }
}
