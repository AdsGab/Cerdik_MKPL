/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cerdik.desktop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author KnightlyTech
 */
public class Pasien extends Account implements SignUp,Displays{
    private int Usia;
    private String Nama;    
    private String Gender;
    private String Gejala;
    private String Obat;

    public static ArrayList<Pasien> listPasien = new ArrayList<>();
    
    public Pasien(){}
    
    public Pasien(String username, String ID, String gejala, String Obat){
        super(username,ID);
        this.Gejala = gejala;
        this.Obat = Obat;
    }
    
    public Pasien(String Fullname, String Gender, int Usia){
        this.Usia = Usia;
        this.Nama = Fullname;
        this.Gender = Gender;
    }
    
    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public int getUsia() {
        return Usia;
    }

    public void setUsia(int Usia) {
        this.Usia = Usia;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getGejala() {
        return Gejala;
    }

    public void setGejala(String Gejala) {
        this.Gejala = Gejala;
    }

    public String getObat() {
        return Obat;
    }

    public void setObat(String Obat) {
        this.Obat = Obat;
    }
    
    
    
    
    @Override
    public String Login(String Username, String Pass) {
        try{
            Connection cariUsername = DriverManager.getConnection("jdbc:mysql://localhost/cerdik","root","");
            Statement statement = cariUsername.createStatement();
            System.out.println("SELECT * FROM AKUN WHERE USERNAME = " + "'" + Username + "'");
            String getUsernameQuery = "SELECT * FROM AKUN WHERE USERNAME = " + "'" + Username + "'";
            ResultSet result = statement.executeQuery(getUsernameQuery);
            result.first();
            System.out.println("'"+result.getString("Role")+"' = '"+"PASIEN");
                    
            
            System.out.println(result.getString("Password").equals(Pass));
            if(result.getString("Password").equals(Pass) && result.getString("Role").equals("PASIEN")){
                System.out.println("Hi Pasien!");
                return "PASIEN";
            }
            
            if(!result.getString("Password").equals(Pass)){
                System.out.println("PASSWORD SALAH");
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
            String DaftarQuery = "INSERT INTO AKUN(AccountID,Username,Password,Role,Email)  VALUES('"+hasilCreate+"','"+Username+"','"+Password+"','"+"PASIEN"+"','"+Email+"')";
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


    
}