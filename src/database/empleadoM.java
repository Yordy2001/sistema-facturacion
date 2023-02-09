package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class empleadoM {

    Connection cursor;

    public empleadoM(Connection cn) {
        cursor = cn;
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
                    + "    cargo varchar(255),\n"
                    + "    password varchar(255),\n"
                    + "    PRIMARY KEY (ID)"
                    + ");";
            PreparedStatement quer = cn.prepareStatement(query);

            int n = quer.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(empleadoM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertEmpleado(Connection cn, String last_name, String first_name,
            int age, String Address, String code_empleado, String user_password, String cargo) {
        try {
            String query = "INSERT INTO empleados(last_name,"
                    + " first_name, age, address, code_empleado, password, cargo ) VALUES"
                    + "('" + last_name + "', '" + first_name + "', '" + age + "','"
                    + Address + "', '" + code_empleado + "', '" + user_password + "', '" + cargo + "');";
            PreparedStatement sqlquery = cn.prepareStatement(query);
            int n = sqlquery.executeUpdate();
            JOptionPane.showMessageDialog(null, "Empleado agregado!");
        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Error de base de datos");
        }
    }

    public boolean login_db(Connection cn, String codeEmpleado, String userPassword) {
        try {
            String query = "SELECT code_empleado, password from empleados"
                    + " where code_empleado='" + codeEmpleado + "'and password='" + userPassword + "'";
            PreparedStatement sqlquery = cn.prepareStatement(query);
            ResultSet n = sqlquery.executeQuery(query);
            if (!n.next()) {
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

    public boolean delete_empleado(String codeEmpleado) {
        try {
            String query = "DELETE FROM empleados where code_empleado='" + codeEmpleado + "'";
            PreparedStatement sqlquery;
            sqlquery = cursor.prepareStatement(query);
            int n = sqlquery.executeUpdate(query);
            if (n != 1) {
                JOptionPane.showMessageDialog(null, "CREDENCIALES INVALIDAS!");
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
            return false;
        }
        return true;
    }

    public void update_empleado(String code, String last_name, String first_name,
            int age, String address, String cargo) {
        try {
            String query = "UPDATE empleados SET last_name='" + last_name + "', first_name='" + first_name +"', age='" + age + "', address='" + address + "' ,cargo='" + cargo + "' where code_empleado='" + code + "'";
            PreparedStatement sqlquery;
            sqlquery = cursor.prepareStatement(query);
            int n = sqlquery.executeUpdate(query);
            if (n != 1) {
                JOptionPane.showMessageDialog(null, "CREDENCIALES INVALIDAS!");
            }
            JOptionPane.showMessageDialog(null, "Empleado actualizado con exito");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
        }
    }

    public ResultSet getEmpleados() {
        ResultSet res = null;
        try {
            String query = "SELECT * from empleados";
            PreparedStatement sqlquery = cursor.prepareStatement(query);
            res = sqlquery.executeQuery(query);

            return res;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
        }
        return res;
    }

    public ResultSet getEmpleado(String code) {
        ResultSet res = null;
        try {
            String query = "SELECT * from empleados where code_empleado='" + code + "'";
            PreparedStatement sqlquery = cursor.prepareStatement(query);
            res = sqlquery.executeQuery(query);

            return res;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
        }
        return res;
    }

}
