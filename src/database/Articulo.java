package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Articulo {

    Connection cursor;

    public Articulo(Connection cn) {
        cursor = cn;
    }

    public void articuloTable() {
        try {
            String query = "CREATE TABLE if not exists articulos(\n"
                    + "    id int NOT NULL AUTO_INCREMENT,\n"
                    + "    name varchar(255) NOT NULL,\n"
                    + "    description varchar(255),\n"
                    + "    code varchar(255),\n"
                    + "    cantidad int,\n"
                    + "    precio_compra float NOT NULL ,\n"
                    + "    precio_venta float,\n"
                    + "    itbis int,\n"
                    + "    id_category int,\n"
                    + "    PRIMARY KEY (ID),\n"
                    + "    FOREIGN KEY(id_category) REFERENCES articulo_category(id)"
                    + ");";
            PreparedStatement quer = cursor.prepareStatement(query);
            int n = quer.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(empleadoM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getArticulos() {
        ResultSet res = null;
        try {
            String query = "SELECT name, description, code, cantidad, precio_venta,"
                    + " precio_compra, itbis, articulo_category.category as category FROM articulos"
                    + " JOIN articulo_category ON articulos.id_category = articulo_category.id";
            PreparedStatement sqlquery = cursor.prepareStatement(query);
            res = sqlquery.executeQuery(query);

            return res;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
        }
        return res;
    }

    public ResultSet getArticulo(String id) {
        ResultSet res = null;
        try {
            String query = "SELECT name, description, code, cantidad, precio_venta,"
                    + " precio_compra, itbis, articulos_category.category FROM articulos"
                    + " JOIN articulo_category ON articulos.id_category = articulo_category.id"
                    + " WHERE articulos.id= '" + id + "'";
            PreparedStatement sqlquery = cursor.prepareStatement(query);
            res = sqlquery.executeQuery(query);

            return res;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
        }
        return res;
    }
//    public void insertArticulo(String last_name, String first_name,
//            int age, String Address, String code_empleado, String user_password, String cargo) {
//        try {
//            String query = "INSERT INTO articulos(last_name,"
//                    + " first_name, age, address, code_empleado, password, cargo ) VALUES"
//                    + "('" + last_name + "', '" + first_name + "', '" + age + "','"
//                    + Address + "', '" + code_empleado + "', '" + user_password + "', '" + cargo + "');";
//            
//            PreparedStatement sqlquery = cursor.prepareStatement(query);
//            int n = sqlquery.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Empleado agregado!");
//        } catch (SQLException e) {
//            System.out.print(e);
//            JOptionPane.showMessageDialog(null, "Error de base de datos");
//        }
//    }

//
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
//            String query = "UPDATE empleados SET last_name='" + last_name + "', first_name='" + first_name + "', age='" + age + "', address='" + address + "' ,cargo='" + cargo + "' where code_empleado='" + code + "'";
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
}
