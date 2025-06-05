/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cerdik.desktop;

import static cerdik.desktop.Account.createID;
import cerdik.desktop.JDBC.JDBC;
import java.sql.SQLException;

/**
 *
 * @author KnightlyTech
 */
public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Login_UI newForm = new Login_UI();
        newForm.show();
        
    }
    
}
