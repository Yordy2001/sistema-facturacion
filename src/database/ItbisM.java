package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ItbisM {

    Connection cursor;

    public ItbisM(Connection cn) {
        this.cursor = cn;
    }

    public ResultSet getArticulos() {
        ResultSet res = null;
        try {
            String query = "SELECT * FROM art_itbis";
            PreparedStatement sqlquery = this.cursor.prepareStatement(query);
            res = sqlquery.executeQuery(query);

            return res;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
        }
        return res;
    }

    public ResultSet getCategories() {
        ResultSet res = null;
        try {
            String query = "SELECT * FROM articulo_category";
            PreparedStatement sqlquery = this.cursor.prepareStatement(query);
            res = sqlquery.executeQuery(query);

            return res;
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
        }
        return res;
    }

}
