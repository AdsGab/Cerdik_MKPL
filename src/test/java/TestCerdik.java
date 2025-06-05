/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SeleniumTests/SeleneseIT.java to edit this template
 */

import cerdik.desktop.Pasien;
import org.junit.jupiter.api.Test; // ✅ JUnit 5
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author M.Iqbal Nurhaq
 */
public class TestCerdik {
    
   static Connection connection;
    static Statement statement;

    @BeforeAll
    static void setupDatabase() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/cerdik", "root", "");
        statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM akun WHERE username = 'testuser'");
    }

    @AfterAll
    static void closeDatabase() throws Exception {
        statement.close();
        connection.close();
    }

    @Test
    void testSignUpAndLogin() throws Exception {
        Pasien p = new Pasien();
        p.signUp("testuser", "testpass", "test@example.com");

        String loginResult = p.Login("testuser", "testpass");
        assertEquals("PASIEN", loginResult);
    }

    @Test
    void testDisplayInfo() throws Exception {
        Pasien p = new Pasien();
        ResultSet rs = p.DisplayInfo("testuser");
        assertNotNull(rs);
        rs.first();
        assertEquals("testuser", rs.getString("Username"));
    }
    
}
