/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cerdik.desktop.JDBC;

import cerdik.desktop.Pasien;
import guiApoteker.Jadwal;
import guiApoteker.Obat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author KnightlyTech
 */
public class JDBC {
    static final String DB_URL = "jdbc:mysql://localhost/CERDIK";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static Connection connection;
    static Statement statement;
    static ResultSet results;
    
    
     public void TestConnection() throws SQLException{
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connection Berhasil");
            connection.close();
        }catch (SQLException e){
            System.out.println("Connection gagal!");
        }
    }
     
    public String getUsernameFromDB(int IDAkunPasien) throws SQLException{
        try{
            Connection cariAccountID = DriverManager.getConnection("jdbc:mysql://localhost/cerdik","root","");
            Statement statement = cariAccountID.createStatement();
            String getUsernameQuery = "SELECT Username FROM AKUN WHERE AccountID = " + "'" + IDAkunPasien + "'";
            ResultSet result = statement.executeQuery(getUsernameQuery);
            result.first();
            String hasil = result.getString("Username");
            
            if(!hasil.isEmpty()){
                return hasil;
            }
            
        }catch(Exception e){
            System.out.println("Error! " + e);
        }
        return "Tidak ada";
    } 
    
    public int getIDFromDB(String Username) throws SQLException{
        try{
            Connection cariAccountID = DriverManager.getConnection("jdbc:mysql://localhost/cerdik","root","");
            Statement statement = cariAccountID.createStatement();
            String getIDQuery = "SELECT AccountID FROM AKUN WHERE USERNAME = " + "'" + Username + "'";
            ResultSet result = statement.executeQuery(getIDQuery);
            result.first();
            int hasilGetID = result.getInt("AccountID");
            
            
            if(hasilGetID == 0){
                cariAccountID.close();
                statement.close();
                return -1;
            }else{
                cariAccountID.close();
                statement.close();
                return hasilGetID;
            }
        }catch(Exception e){
            System.out.println("Error! " + e);
        }
        return 0;
    }
    
    
    public int getObatIDFromDB(int IDPasien){
        try{
            Connection cariAccountID = DriverManager.getConnection("jdbc:mysql://localhost/cerdik","root","");
            Statement statement = cariAccountID.createStatement();
            String getIDQuery = "SELECT IDObat FROM JADWAL WHERE IDPasien = " + "'" + IDPasien + "'";
            ResultSet result = statement.executeQuery(getIDQuery);
            result.first();
            int hasilGetID = result.getInt("IDObat");
            
            
            if(hasilGetID == 0){
                cariAccountID.close();
                statement.close();
                return -1;
            }else{
                cariAccountID.close();
                statement.close();
                return hasilGetID;
            }
        }catch(Exception e){
            System.out.println("Error! " + e);
        }
        return 0;
    }
    
     public List<Obat> getObatList() {
    List<Obat> obatList = new ArrayList<>();
    Connection connection = null;
    Statement statement = null;
    ResultSet results = null;

    try {
        // Establish the connection
        connection = DriverManager.getConnection("jdbc:mysql://localhost/cerdik", "root", "");
        // Create the statement
        statement = connection.createStatement();
        System.out.println("Executing query");
        // Execute the query
        String query = "SELECT NAMA, JENIS, STOK FROM obat";
        results = statement.executeQuery(query);
        System.out.println("Query Executed");
        
        // Process the results
        while (results.next()) {
            Obat obat = new Obat(results.getString("NAMA"), results.getString("JENIS"), results.getInt("STOK"));
            obatList.add(obat);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
    } finally {
        // Close resources in the finally block to ensure they are closed even if an exception occurs
        try {
            if (results != null) results.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }

    return obatList;
}
     public List<Jadwal> getJadwalList() {
    List<Jadwal> jadwalList = new ArrayList<>();
    System.out.println("In get jadwal List...");

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cerdik", "root", "");
         Statement statement = connection.createStatement()) {
        
        System.out.println("Memulai query!");
        String query = "SELECT DISTINCT j.IDJadwal, j.IDPasien, j.IDObat, j.Gejala, j.Dosis, j.Start_Date, j.End_Date, j.IsConfirmedNakes, j.IsConfirmedApoteker, a.username, o.nama " +
               "FROM jadwal j " +
               "JOIN akun a ON j.IDPasien = a.AccountID " +
               "JOIN obat o ON j.IDObat = o.IDObat";
        ResultSet results = statement.executeQuery(query);
        System.out.println("Mendapat hasil query!");
        
        while (results.next()) {
            Jadwal jadwal = new Jadwal(
                results.getInt("IDPasien"),
                results.getInt("IDObat"),
                results.getString("Start_Date"),
                results.getString("End_Date"),
                results.getInt("IsConfirmedNakes"),
                results.getInt("IsConfirmedApoteker")
            );
            jadwal.setNamaPasien(results.getString("username"));
            jadwal.setNamaObat(results.getString("nama"));
            jadwalList.add(jadwal);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
    }
    return jadwalList;
}
   public Obat getObatDetails(String nama, String jenis) {
    Obat obat = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet results = null;

    try {
        // Establish the connection
        connection = DriverManager.getConnection("jdbc:mysql://localhost/cerdik", "root", "");

        String query = "SELECT NAMA, JENIS, STOK, `SARAN PENYAJIAN` FROM obat WHERE NAMA = ? AND JENIS = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nama);
        preparedStatement.setString(2, jenis);
        results = preparedStatement.executeQuery();
        
        if (results.next()) {
            String resultNama = results.getString("NAMA");
            String resultJenis = results.getString("JENIS");
            int stok = results.getInt("STOK");
            String saranPenyajian = results.getString("SARAN PENYAJIAN");

            // Debugging output to check values
            System.out.println("NAMA: " + resultNama + ", JENIS: " + resultJenis + ", STOK: " + stok + ", SARAN PENYAJIAN: " + saranPenyajian);

            obat = new Obat(resultNama, resultJenis, stok);
            obat.setSaranPenyajian(saranPenyajian);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
    } finally {
        try {
            if (results != null) results.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
    return obat;
}
   public Jadwal getJadwalDetails(String startDate, String obatName) {
    Jadwal jadwal = null;
    Connection connection = null;
    PreparedStatement getIdObatStmt = null;
    PreparedStatement getJadwalStmt = null;
    ResultSet obatResults = null;
    ResultSet jadwalResults = null;

    try {
        // Establish the connection
        connection = DriverManager.getConnection("jdbc:mysql://localhost/cerdik", "root", "");

        // Step 1: Retrieve IDObat from obatName
        String getIdObatQuery = "SELECT IDObat FROM obat WHERE nama = ?";
        getIdObatStmt = connection.prepareStatement(getIdObatQuery);
        getIdObatStmt.setString(1, obatName);
        obatResults = getIdObatStmt.executeQuery();

        if (obatResults.next()) {
            int idObat = obatResults.getInt("IDObat");
            System.out.println("IDObat found: " + idObat);

            // Step 2: Retrieve Jadwal details using startDate and idObat
            String getJadwalQuery = "SELECT IDJadwal, IDPasien, IDObat, Gejala, Dosis, Start_date, End_Date, IsConfirmedNakes, IsConfirmedApoteker FROM jadwal WHERE Start_Date = ? AND IDObat = ?";
            getJadwalStmt = connection.prepareStatement(getJadwalQuery);
            getJadwalStmt.setString(1, startDate);
            getJadwalStmt.setInt(2, idObat);
            jadwalResults = getJadwalStmt.executeQuery();

            if (jadwalResults.next()) {
                int idJadwal = jadwalResults.getInt("IDJadwal");
                int idPasien = jadwalResults.getInt("IDPasien");
                String gejala = jadwalResults.getString("Gejala");
                String dosis = jadwalResults.getString("Dosis");
                String start_date = jadwalResults.getString("Start_date");
                String end_date = jadwalResults.getString("End_Date");
                int isConfirmedNakes = jadwalResults.getInt("IsConfirmedNakes");
                int isConfirmedApoteker = jadwalResults.getInt("IsConfirmedApoteker");

                jadwal = new Jadwal(idPasien, idObat, start_date, end_date, isConfirmedNakes, isConfirmedApoteker);
                jadwal.setGejala(gejala);
                jadwal.setDosis(dosis);
                jadwal.setIDJadwal(idJadwal);

                System.out.println("Jadwal found: " + jadwal);
            } else {
                System.out.println("No records found for Start Date: " + startDate + " and ObatName: " + obatName);
            }
        } else {
            System.out.println("No Obat found with the name: " + obatName);
        }
    } catch (SQLException e) { 
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
    } finally {
        try {
            if (obatResults != null) obatResults.close();
            if (jadwalResults != null) jadwalResults.close();
            if (getIdObatStmt != null) getIdObatStmt.close();
            if (getJadwalStmt != null) getJadwalStmt.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
    return jadwal;
}

    
    
    public void approveJadwalNakes(Jadwal selectedJadwal) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        // Establish the connection
        connection = DriverManager.getConnection("jdbc:mysql://localhost/cerdik", "root", "");

        String tanggal = selectedJadwal.getStart_Date();
        int obat = selectedJadwal.getIDObat();

        String query = "UPDATE jadwal SET IsConfirmedApoteker = 1, IsConfirmedNakes = 0 WHERE Start_Date = ? and IDObat = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, tanggal);
        preparedStatement.setInt(2, obat);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Jadwal successfully approved.");
        } else {
            System.out.println("No jadwal found with the given start date.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
    } finally {
        try {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    public static Pasien getPasienDetails(Jadwal jadwal) {
    Pasien newPasien = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet results = null;

    try {
        // Establish the connection
        connection = DriverManager.getConnection("jdbc:mysql://localhost/cerdik", "root", "");

        // Step 1: Retrieve IDObat from obatName
        String getPasienQuery = "SELECT * from akun WHERE AccountID = ?";
        preparedStatement = connection.prepareStatement(getPasienQuery);
        preparedStatement.setInt(1, jadwal.getIDPasien());
        results = preparedStatement.executeQuery();

        if (results.next()) {
            newPasien = new Pasien();
            newPasien.setNama(results.getString("Username"));
        } else {
            System.out.println("No user found with the ID: " + jadwal.getIDPasien());
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
    } finally {
        try {
            if (results != null) results.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
    return newPasien;
}
    
    public List<Obat> getObatUntukJadwal(Jadwal jadwal) {
        List<Obat> listObat = new ArrayList<>();
        try{
            String getObat = "Select NAMA,JENIS,STOK from obat where IDOBAT = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(getObat);
            preparedStatement.setInt(1, jadwal.getIDObat());
            results = preparedStatement.executeQuery();
            if (results.next()) {
                Obat obat = new Obat(results.getString("NAMA"), results.getString("Jenis"), results.getInt("STOK"));
                listObat.add(obat);
            
        }else {
            System.out.println("No user found with the ID: " + jadwal.getIDPasien() );
        }
        preparedStatement.close();
        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
    }
        return listObat;
    }
    
    
     
    
}
