/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yordy
 */
public class empleadoM {

    public void empleado_table() {
        String query = "";
        query = "CREATE TABLE Persons (\n"
                + "    ID int NOT NULL,\n"
                + "    LastName varchar(255) NOT NULL,\n"
                + "    FirstName varchar(255),\n"
                + "    Age int,\n"
                + "    code_empleado\n"
                + "    password varchar(255)\n"
                + "    PRIMARY KEY (ID)"
                + ");";
    }
}


