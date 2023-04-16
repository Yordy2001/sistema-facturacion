package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Factura {

    Connection cursor;
    Articulo articulo;

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

    public long insertBill(String id_store, String id_customer,
            String id_empleado, String paid_method, String type) {

        long bill_id = 0;
        String idStore = getIdStore(id_store);
        String idCustomer = getIdCustomer(id_customer);
        String idEmpleado = getIdEmpleado(id_empleado);
        String idPaidMethod = getPaidMethod(paid_method);
        String idType = getIdBillType(type);
        try {

            String query = "INSERT INTO bill(id_store, id_customer,"
                    + " id_empleado, paid_method, `type`)"
                    + " VALUES ('" + idStore
                    + "', '" + idCustomer + "',"
                    + " '" + idEmpleado + "', '" + idPaidMethod + "', '" + idType + "');";

            PreparedStatement sqlquery = cursor.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sqlquery.executeUpdate();
            ResultSet rs = sqlquery.getGeneratedKeys();
            rs.next();
            //Get id from factura that generate before 
            bill_id = rs.getLong(1);
            return bill_id;

        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Error de base de datos");
        }
        return bill_id;
    }

    public void insertBillDetail(long id_bill, String idProduct, String amount) {
        try {

            String query = "INSERT INTO bill_detail(id_bill, id_product,"
                    + " amount)"
                    + " VALUES ('" + id_bill + "',"
                    + " '" + idProduct + "', '" + amount + "');";

            PreparedStatement sqlquery = cursor.prepareStatement(query);
            sqlquery.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Error de base de datos");
        }
    }

    private String getIdStore(String name) {
        String idStore = "";
        try {
            String query = "SELECT id FROM store_info WHERE name='" + name + "';";

            PreparedStatement sqlquery = cursor.prepareStatement(query);
            ResultSet res = sqlquery.executeQuery(query);
            if (res.next()) {
                idStore = res.getString("id");
            }
        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Cominicarse con un tecnico");
        }
        return idStore;
    }

    private String getIdCustomer(String cedula) {
        String idCustomer = "";
        try {
            String query = "SELECT id FROM customer WHERE cedula='" + cedula + "';";

            PreparedStatement sqlquery = cursor.prepareStatement(query);
            ResultSet res = sqlquery.executeQuery(query);
            if (res.next()) {
                idCustomer = res.getString("id");
            }
        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Cominicarse con un tecnico");
        }
        return idCustomer;
    }

    private String getIdEmpleado(String code_empleado) {
        String idEmpleado = "";
        try {
            String query = "SELECT id FROM empleados WHERE code_empleado='" + code_empleado + "';";

            PreparedStatement sqlquery = cursor.prepareStatement(query);
            ResultSet res = sqlquery.executeQuery(query);
            if (res.next()) {
                idEmpleado = res.getString("id");
            }
        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Cominicarse con un tecnico");
        }
        return idEmpleado;
    }

    private String getPaidMethod(String paid_method) {
        String idPaidMethod = "";
        try {
            String query = "SELECT id FROM paid_method WHERE method='" + paid_method + "';";

            PreparedStatement sqlquery = cursor.prepareStatement(query);
            ResultSet res = sqlquery.executeQuery(query);
            if (res.next()) {
                idPaidMethod = res.getString("id");
            }
        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Cominicarse con un tecnico");
        }
        return idPaidMethod;
    }

    private String getIdBillType(String type) {
        String idtype = "";
        try {
            String query = "SELECT id FROM bill_type WHERE type='" + type + "';";

            PreparedStatement sqlquery = cursor.prepareStatement(query);
            ResultSet res = sqlquery.executeQuery(query);
            if (res.next()) {
                idtype = res.getString("id");
            }
        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Cominicarse con un tecnico");
        }
        return idtype;
    }
}
