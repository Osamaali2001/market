package Database;

import java.sql.*;
import java.util.*;
import market.Employees;
import market.product;

public class database {

    public static Connection c = null;

    public void Connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        if (c == null) {
            c = DriverManager.getConnection("jdbc:sqlite:market");
        } else {
            c.close();
            c = DriverManager.getConnection("jdbc:sqlite:market");
        }
    }

    public void create() throws ClassNotFoundException {

        try {
            database d = new database();
            d.Connect();
            java.sql.PreparedStatement ps = c.prepareStatement("CREATE TABLE Employees (ID INTEGER UNIQUE,PRIMARY KEY(ID AUTOINCREMENT),name TEXT NOT NULL,username TEXT NOT NULL UNIQUE, password TEXT NOT NULL,role TEXT NOT NULL)");
            java.sql.PreparedStatement ps1 = c.prepareStatement("CREATE TABLE product (name TEXT NOT NULL UNIQUE, quantity INTEGER NOT NULL, expiry_date TEXT, price INTEGER NOT NULL,PRIMARY KEY(name))");
            System.out.println("Table is created");

            ps.execute();
            ps1.execute();
            ps.close();
            ps1.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertEmployee(String name, String username, String password, String role) {

        try {
            java.sql.PreparedStatement ps = c.prepareStatement("INSERT into Employees (name,username,password,role)"
                    + " values (?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.execute();
            System.out.println("employee is added");
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateEmployee(int id, String name, String username, String role, String password) {

        try {
            java.sql.PreparedStatement ps = c.prepareStatement("UPDATE Employees set name = ? ,username = ? ,role = ? ,password = ? WHERE id = ?");
            ps.setInt(5, id);
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, role);
            ps.setString(4, password);
            System.out.println(" table is updated");
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void DeleteEmployee(int id) {

        try {
            java.sql.PreparedStatement ps = c.prepareStatement("Delete FROM Employees WHERE id =?");
            ps.setInt(1, id);
            System.out.println("row is deleted");
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Employees> getEmployees() {
        ArrayList<Employees> arrayList = new ArrayList<>();

        try {
            java.sql.PreparedStatement ps = c.prepareStatement("SELECT * FROM Employees");

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                arrayList.add(new Employees(resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"),
                        resultSet.getInt("id"), resultSet.getString("role")));
            }
            resultSet.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arrayList;
    }

    public Employees searchEmployee(int id) {
//        Employees employee=new Employees();
        Employees employee = new Employees();
        try {
            java.sql.PreparedStatement ps = c.prepareStatement("SELECT * FROM Employees WHERE id = ? ");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            employee = new Employees(resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"),
                    resultSet.getInt("id"), resultSet.getString("role"));
            resultSet.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return employee;
    }

    public void insertproduct(String name, int quantity, String expiryDate, int price) {

        try {
            java.sql.PreparedStatement ps = c.prepareStatement("INSERT into product (name,quantity,expiry_date,price)"
                    + " values (?,?,?,?)");
            ps.setString(1, name);
            ps.setInt(2, quantity);
            ps.setString(3, expiryDate);
            ps.setInt(4, price);
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateproduct(String name, int quantity, int price, String expiryDate, String newName) {

        try {
            java.sql.PreparedStatement ps = c.prepareStatement("UPDATE product set quantity = ? ,expiry_date = ? ,price = ? ,name = ? WHERE name = ?");
            ps.setString(5, name);
            ps.setInt(1, quantity);
            ps.setString(2, expiryDate);
            ps.setInt(3, price);
            ps.setString(4, newName);
            System.out.println(" table is updated");
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Deleteproduct(String name) {

        try {
            java.sql.PreparedStatement ps = c.prepareStatement("Delete FROM product WHERE name =?");
            ps.setString(1, name);
            System.out.println("row is deleted");
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<product> getproduct() {
        ArrayList<product> arrayList = new ArrayList<>();

        try {
            java.sql.PreparedStatement ps = c.prepareStatement("SELECT * FROM product");

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                arrayList.add(new product(resultSet.getString("name"), resultSet.getInt("quantity"), resultSet.getString("expiry_date"), resultSet.getInt("price")));
            }
            resultSet.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arrayList;
    }

    public product searchproduct(String name) {
        product prod = new product();

        try {
            java.sql.PreparedStatement ps = c.prepareStatement("SELECT * FROM product WHERE name = ? ");
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            prod = new product(resultSet.getString("name"), resultSet.getInt("quantity"), resultSet.getString("expiry_date"), resultSet.getInt("price"));
            resultSet.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prod;
    }

    public void makeOrder(String name, int quantity) {

        try {
            java.sql.PreparedStatement ps = c.prepareStatement("SELECT * FROM product WHERE name = ? ");
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            int stack_quantity = resultSet.getInt("quantity");
            quantity = stack_quantity - quantity;
            ps = c.prepareStatement("UPDATE product set quantity = ?  WHERE name = ? ");
            ps.setString(2, name);
            ps.setInt(1, quantity);
            ps.execute();
            if (quantity < 5) {
                System.out.println("The stored product is less than 5");
            }
            System.out.println("order is made!");
            resultSet.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void cancelOrder(String name, int quantity) {

        try {
            java.sql.PreparedStatement ps = c.prepareStatement("SELECT * FROM product WHERE name = ? ");
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            int stack_quantity = resultSet.getInt("quantity");
            quantity = stack_quantity + quantity;
            ps = c.prepareStatement("UPDATE product set quantity = ?  where name = ? ");
            ps.setString(2, name);
            ps.setInt(1, quantity);

            ps.execute();
            System.out.println("order is made!");
            resultSet.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
