/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

/**
 *
 * @author Ahmed mounir
 */
public class Employees {
    String name;
    String username;
    String password;
    private int id;
    String role;

    public Employees() {
    }
    
    public Employees(String name,String username,String password,int id,String role) {
        this.id=id;
        this.name=name;
        this.username=username;
        this.password=password;
        this.role=role;
    }
}
