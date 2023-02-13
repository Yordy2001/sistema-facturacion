package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Customer {

    Connection cursor;

    public Customer(Connection cn) {
        this.cursor = cn;
    }

    public void customer_table() {

        try {
            String query = "CREATE TABLE if not exists customer(\n"
                    + "    id int NOT NULL AUTO_INCREMENT,\n"
                    + "    last_name varchar(255) NOT NULL,\n"
                    + "    first_name varchar(255),\n"
                    + "    age int,\n"
                    + "    address varchar(255) NOT NULL ,\n"
                    + "    cedula varchar(255),\n"
                    + "    credito float,\n"
                    + "    PRIMARY KEY (ID)"
                    + ");";
            PreparedStatement quer = cursor.prepareStatement(query);

            int n = quer.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertCustomer(String last_name, String first_name,
            int age, String Address, String cedula, String credito) {
        try {
            String query = "INSERT INTO customer(last_name,"
                    + " first_name, age, address, cedula, password, cargo ) VALUES"
                    + "('" + last_name + "', '" + first_name + "', '" + age + "','"
                    + Address + "', '" + cedula + "');";
            PreparedStatement sqlquery = cursor.prepareStatement(query);
            int n = sqlquery.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente agregado!");
        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Error de base de datos");
        }
    }

    public ResultSet getCustomers() {
        ResultSet res = null;
        try {
            String query = "SELECT * from customer";
            PreparedStatement sqlquery = this.cursor.prepareStatement(query);
            res = sqlquery.executeQuery(query);
            return res;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
        }
        return res;
    }

        public ResultSet getCustomer(String code) {
        ResultSet res = null;
        try {
            String query = "SELECT * from customer where cedula='" + code + "'";
            PreparedStatement sqlquery = cursor.prepareStatement(query);
            res = sqlquery.executeQuery(query);

            return res;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
        }
        return res;
    }
    
//    public boolean delete_empleado(String codeEmpleado) {
//        try {
//            String query = "DELETE FROM empleados where code_empleado='" + codeEmpleado + "'";
//            PreparedStatement sqlquery;
//            sqlquery = cursor.prepareStatement(query);
//            int n = sqlquery.executeUpdate(query);
//            if (n != 1) {
//                JOptionPane.showMessageDialog(null, "CREDENCIALES INVALIDAS!");
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
//            return false;
//        }
//        return true;
//    }
//
//    public void update_empleado(String code, String last_name, String first_name,
//            int age, String address, String cargo) {
//        try {
//            String query = "UPDATE empleados SET last_name='" + last_name + "', first_name='" + first_name +"', age='" + age + "', address='" + address + "' ,cargo='" + cargo + "' where code_empleado='" + code + "'";
//            PreparedStatement sqlquery;
//            sqlquery = cursor.prepareStatement(query);
//            int n = sqlquery.executeUpdate(query);
//            if (n != 1) {
//                JOptionPane.showMessageDialog(null, "CREDENCIALES INVALIDAS!");
//            }
//            JOptionPane.showMessageDialog(null, "Empleado actualizado con exito");
//        } catch (SQLException e) {
//            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
//        }
//    }
//
//    public ResultSet getEmpleados() {
//        ResultSet res = null;
//        try {
//            String query = "SELECT * from empleados";
//            PreparedStatement sqlquery = cursor.prepareStatement(query);
//            res = sqlquery.executeQuery(query);
//
//            return res;
//        } catch (SQLException e) {
//            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
//        }
//        return res;
//    }
//


}
