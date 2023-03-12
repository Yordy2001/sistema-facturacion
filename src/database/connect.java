package database;

//import empleadoM;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class connect {
    Connection connect = null;
    
    public Connection conexion() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:23306/sistema-facturacion", "user", "root");
            System.out.println("Database Connected");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al connectarse a la base de datos");
            System.out.println(e);
        }
        return connect;
    }

    public connect() {
        Connection cn = conexion();
        empleadoM empleado = new empleadoM(cn);
        Articulo articulo = new Articulo(cn);
        Factura factura = new Factura(cn);
        articulo.articuloTable();
        empleado.empleado_table(cn);
        factura.tableFactura();
        
    }

    PreparedStatement prepareStatement(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
