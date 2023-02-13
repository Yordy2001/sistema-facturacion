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
                    + " precio_compra, articulo_category.category as category, art_itbis.itbis as itbis FROM articulos"
                    + " JOIN articulo_category ON articulos.id_category = articulo_category.id"
                    + " JOIN art_itbis ON articulos.itbis = art_itbis.id;";
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

    public void insertArticulo(String name, String description, String code,
            int cantidad, float precio_venta, float precio_compra, String itbis, String category) {

        String category_id = this.getCategory(category);
        String itbisId = this.getItbis(itbis);
        //Get category id to relation article  
        try {

            String query = "INSERT INTO articulos(name,"
                    + " description, code, cantidad, precio_compra, precio_venta, itbis, id_category ) VALUES"
                    + "('" + name + "', '" + description + "', '" + code + "','"
                    + cantidad + "', '" + precio_compra + "', '" + precio_venta + "', '" + itbisId + "', '" + category_id + "');";

            PreparedStatement sqlquery = cursor.prepareStatement(query);
            int n = sqlquery.executeUpdate();
            JOptionPane.showMessageDialog(null, "Articulo agregado!");
        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Error de base de datos");
        }
    }
//
    public boolean deleteArticulo(String ArticleCode) {
        try {
            String query = "DELETE FROM articulos where code='" + ArticleCode + "'";
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

    private String getItbis(String itbi) {
        String itbisId = null;
        //Get category id to relation article  
        try {
            String query = "SELECT id FROM art_itbis WHERE itbis='" + itbi + "'";
            PreparedStatement sqlquery = cursor.prepareStatement(query);
            ResultSet rs = sqlquery.executeQuery(query);
            if (rs.next()) {
                itbisId = rs.getString("id");
            }

        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Error de base de datos");
        }
        return itbisId;
    }

    private String getCategory(String category) {
        String categoryId = null;
        //Get category id
        try {
            String query = "SELECT id FROM articulo_category WHERE category='" + category + "'";
            PreparedStatement sqlqueryItbis = cursor.prepareStatement(query);
            ResultSet Itbis = sqlqueryItbis.executeQuery(query);
            if (Itbis.next()) {
                categoryId = Itbis.getString("id");
            }

        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Error de base de datos");
        }
        return categoryId;
    }

}
