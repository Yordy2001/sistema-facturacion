package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Factura {

    Connection cursor;

    public Factura(Connection cn) {
        cursor = cn;
    }

    public void tableFactura() {
        try {
            String query = "CREATE TABLE if not exists bill(\n"
                    + "    id int NOT NULL AUTO_INCREMENT,\n"
                    + "    id_store int,\n"
                    + "    id_customer int,\n"
                    + "    id_empleado int,\n"
                    + "    paid_method int,\n"
                    + "    type int NOT NULL ,\n"
                    + "    fecha TIMESTAMP, \n"
                    + "    PRIMARY KEY (ID),\n"
                    + "    FOREIGN KEY(paid_method) REFERENCES paid_method(id),"
                    + "    FOREIGN KEY(id_customer) REFERENCES customer(id),"
                    + "    FOREIGN KEY(id_empleado) REFERENCES empleados(id),"
                    + "    FOREIGN KEY(type) REFERENCES bill_type(id),"
                    + "    FOREIGN KEY(id_store) REFERENCES store_info(id)"
                    + ");";
            PreparedStatement quer = cursor.prepareStatement(query);
            int n = quer.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(empleadoM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getFacturaDetail(Integer id) {
        ResultSet res = null;
        try {
            String query = "SELECT bill_detail.sell_price,\n"
                    + "     ticulos.`name`,\n"
                    + "     articulos.itbis,\n"
                    + "     bill_type.`type`,\n"
                    + "     paid_method.`method`,\n"
                    + "     empleados.first_name,\n"
                    + "    customer.cedula,\n"
                    + "    customer.first_name,\n"
                    + "    store_info.`name`,\n"
                    + "    store_info.rnc,\n"
                    + "    store_info.ncf\n"
                    + "    fecha"
                    + " FROM\n"
                    + "    bill\n"
                    + "    JOIN bill_detail on bill_detail.id_bill=bill.id\n"
                    + "    JOIN articulos ON `bill_detail`.id_product=articulos.id\n"
                    + "    JOIN bill_type ON `bill`.`type`=bill_type.id\n"
                    + "    JOIN paid_method ON `bill`.paid_method=paid_method.id\n"
                    + "    JOIN empleados ON `bill`.id_empleado=empleados.id\n"
                    + "    JOIN customer ON `bill`.id_customer=customer.id\n"
                    + "    JOIN store_info ON bill.id_store=store_info.id"
                    + " WHERE\n"
                    + "    bill.id='" + id + "'";
            PreparedStatement sqlquery = cursor.prepareStatement(query);
            res = sqlquery.executeQuery(query);

            return res;

        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: COMUNIQUESE CON UN TECNICO");
        }
        return res;
    }

    public void insertBill(String id_store, String id_customer, String id_empleado, String paid_method, String type) {

        try {

            String query = "INSERT INTO bill(id_store, id_customer, id_empleado, paid_method, `type`)"
                    + " VALUES (1, 2, 1, 2, 1);";

            PreparedStatement sqlquery = cursor.prepareStatement(query);
            int n = sqlquery.executeUpdate();
            JOptionPane.showMessageDialog(null, "Articulo agregado!");
        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Error de base de datos");
        }
    }
}
