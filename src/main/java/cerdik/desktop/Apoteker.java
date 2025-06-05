/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cerdik.desktop;

import static cerdik.desktop.Account.createID;
import cerdik.desktop.JDBC.JDBC;
import guiApoteker.Obat;
import static guiApoteker.Obat.createID;
import static guiApoteker.Obat.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author KnightlyTech
 */
public class Apoteker extends Account implements SignUp,Displays{

    @Override
    public String Login(String Username, String Password) {
       try{
            Connection cariUsername = DriverManager.getConnection("jdbc:mysql://localhost/cerdik","root","");
            Statement statement = cariUsername.createStatement();
            String getUsernameQuery = "SELECT * FROM AKUN WHERE USERNAME = " + "'" + Username + "'";
            ResultSet result = statement.executeQuery(getUsernameQuery);
            result.first();
            
            if(result.getString("Password").equals(Password) && result.getString("Role").equals("APOTEKER")){
                return "APOTEKER";
            }
            
            if(!result.getString("Password").equals(Password)){
                return "PASSWORD SALAH!";
            }
            
            cariUsername.close();
            statement.close();
            
        }catch(Exception e){
            System.out.println("Error! " + e);
        }
        
        return "PASSWORD ATAU USERNAME SALAH!";
    }

    @Override
    public void signUp(String Username, String Password, String Email) {
        int hasilCreate = createID();
        try{
            Connection signUp = DriverManager.getConnection("jdbc:mysql://localhost/cerdik","root","");
            Statement statement = signUp.createStatement();
            String DaftarQuery = "INSERT INTO AKUN(AccountID,Username,Password,Role,Email)  VALUES('"+hasilCreate+"','"+Username+"','"+Password+"','"+"APOTEKER"+"','"+Email+"')";
            statement.executeUpdate(DaftarQuery);
            statement.close();
            signUp.close();
            System.out.println("Pasien Berhasil Daftar!");
        }catch(Exception e){
            System.out.println("Error! " + e);
        }
    }

    @Override
    public ResultSet DisplayInfo(String Username) {
        try{
            Connection cariUsername = DriverManager.getConnection("jdbc:mysql://localhost/cerdik","root","");
            Statement statement = cariUsername.createStatement();
            String getUserInfo = "SELECT * FROM AKUN WHERE USERNAME = " + "'" + Username + "'";
            ResultSet result = statement.executeQuery(getUserInfo);
            result.first();
            return result;
        }catch(Exception e){
            System.out.println("Error!" + e);
        }
        
        return null;
    }
    public static void addObat(String nama, String jenis, String saranPenyajian,int stok) {
          int idObatRandom = createID();
    try {
        Connection addObat = DriverManager.getConnection("jdbc:mysql://localhost/cerdik", "root", "");
        Statement statement = addObat.createStatement();
        String AddQuery = "INSERT INTO OBAT VALUES('" + idObatRandom + "','" + nama + "','" + jenis + "','" + saranPenyajian + "','" + stok + "')";
        statement.executeUpdate(AddQuery);
        statement.close();
        addObat.close();
        System.out.println("Obat Berhasil Ditambah!");
    } catch (Exception e) {
        System.out.println("Error! " + e);
    }
    }
    public static void deleteObat(Obat selectedObat){
         if (selectedObat != null){
            String nama = selectedObat.getNamaObat();
            String jenis = selectedObat.getJenisObat();
            try{
                Connection addObat = DriverManager.getConnection("jdbc:mysql://localhost/cerdik", "root", "");
                Statement statement = addObat.createStatement();
                String AddQuery = "DELETE FROM OBAT WHERE NAMA = '" + nama + "' AND JENIS = '" + jenis + "'";
                statement.executeUpdate(AddQuery);
                statement.close();
                System.out.println("Obat Berhasil dihapus");
            } catch (SQLException ex){
               System.out.println("Error! " + ex);
            }
        }     
    }
    
   public static void editObat(String nama, String jenis, String saranPenyajian, int stok) {
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cerdik", "root", "");
        String updateQuery = "UPDATE OBAT SET `JENIS` = ?, `SARAN PENYAJIAN` = ?, `STOK` = ? WHERE `NAMA` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString(1, jenis);
        preparedStatement.setString(2, saranPenyajian);
        preparedStatement.setInt(3, stok);
        preparedStatement.setString(4, nama);

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Obat Berhasil Diubah!");
        } else {
            System.out.println("Obat tidak ditemukan atau tidak ada perubahan.");
        }
        
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        System.out.println("Error! " + e);
    }
}

    
}