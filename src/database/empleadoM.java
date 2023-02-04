/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import form.login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class empleadoM {

    public empleadoM(Connection cn) {
        Connection cursor = cn;
    }

    public void empleado_table(Connection cn) {

        try {
            String query = "CREATE TABLE if not exists empleados(\n"
                    + "    id int NOT NULL AUTO_INCREMENT,\n"
                    + "    last_name varchar(255) NOT NULL,\n"
                    + "    first_name varchar(255),\n"
                    + "    age int,\n"
                    + "    address varchar(255) NOT NULL ,\n"
                    + "    code_empleado varchar(255),\n"
                    + "    password varchar(255),\n"
                    + "    PRIMARY KEY (ID)"
                    + ");";
            PreparedStatement quer = cn.prepareStatement(query);

            int n = quer.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(empleadoM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertEmpleado(Connection cn, String last_name, String first_name, int age, String Address, String code_empleado, String user_password) {
        try {
            String query = "INSERT INTO empleados(last_name,"
                    + " first_name, age, address, code_empleado, password) VALUES"
                    + "('" + last_name + "', '" + first_name + "', '" + age + "','"
                    + Address + "', '" + code_empleado + "', '" + user_password + "');";
            PreparedStatement sqlquery = cn.prepareStatement(query);
            int n = sqlquery.executeUpdate();

        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Error de base de datos");
        }
    }

    public boolean login_db(Connection cn, String codeEmpleado, String userPassword) {
        try {
            String query = "SELECT code_empleado, password from empleados where code_empleado='" + codeEmpleado + "'and password='" + userPassword + "'";
            PreparedStatement sqlquery = cn.prepareStatement(query);
            ResultSet n = sqlquery.executeQuery(query);
            if(!n.next()){
                JOptionPane.showMessageDialog(null, "CREDENCIALES INVALIDAS!");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
        }
        return true;
    }
}
