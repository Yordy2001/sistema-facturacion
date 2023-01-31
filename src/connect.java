
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class connect {
        Connection connect = null;

    public Connection conexion() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:23306/sistema-facturacion", "user", "root");
            JOptionPane.showMessageDialog(null, "Database Connected");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al connectarse a la base de datos");
            System.out.println(e);
        }
        return connect;
    }
    
    public connect(){
        empleadoM empleado = new empleadoM();
            empleado.empleado_table();
    }
    
}
