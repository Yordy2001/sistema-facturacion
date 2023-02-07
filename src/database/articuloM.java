
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class articuloM {
    Connection cursor;
     public articuloM(Connection cn) {
        cursor = cn;
    }
     
     public void articulo_table(){
         try {
            String query = "CREATE TABLE if not exists articulos(\n"
                    + "    id int NOT NULL AUTO_INCREMENT,\n"
                    + "    name varchar(255) NOT NULL,\n"
                    + "    description varchar(255),\n"
                    + "    code varchar(255),\n"
                    + "    cantidad int,\n"
                    + "    precio_compra float NOT NULL ,\n"
                    + "    precio_venta float,\n"
                    + "    itbis float,\n"
                    + "    PRIMARY KEY (ID)"
                    + ");";
            PreparedStatement quer = cursor.prepareStatement(query);

            int n = quer.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(empleadoM.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
